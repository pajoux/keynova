package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.*;

/**
 *
 */
public class RainbowKeyBurst extends Node2<Long, Map<KeyCode, Long>, ROColorBitmap> {

    private ColorBitmap buffer = new ColorBitmap();
    private final float switchDelay;
    private final int dissolveTimeMs;
    private final long cycleLengthMs;
    private final double f;
    private final float alpha;
    private Map<KeyCode, Long> keyBurstMap = new HashMap<>();
    private Map<KeyCode, KeyColorTime> keyStartTimes = new HashMap<>();

    public RainbowKeyBurst(
            Clock clock,
            Node<Map<KeyCode, Long>> source,
            int dissolveTimeMs,
            int switchDelay,
            long cycleLengthMs,
            float alpha
    ) {
        super(clock, source);
        this.dissolveTimeMs = dissolveTimeMs;
        this.cycleLengthMs = cycleLengthMs;
        this.alpha = alpha;
        f = Math.PI * 2 / cycleLengthMs;
        if (switchDelay > 99) {
            this.switchDelay = 0.99f;
        } else if (switchDelay < 1) {
            this.switchDelay = 0.01f;
        } else {
            this.switchDelay = switchDelay / 100.0f;
        }
    }

    private int getColorAtTime(Long t) {
        long delta = t % cycleLengthMs;
        double r0 = f * delta + 2;
        double r1 = f * delta + 0;
        double r2 = f * delta + 4;
        float r = (float)(Math.sin(r0) * 0.5 + 0.5);
        float g = (float)(Math.sin(r1) * 0.5 + 0.5);
        float b = (float)(Math.sin(r2) * 0.5 + 0.5);
        return Color.rgba(r, g, b, alpha);
    }

    private void checkAndUpdateBurstMap(KeyCode currentEntry, KeyColorTime newKCT, Long t) {
        KeyColorTime currentKCT = keyStartTimes.get(currentEntry);
        if (currentKCT == null || (!currentKCT.Equals(newKCT) && currentKCT.getTime() < newKCT.getTime())) {
            keyStartTimes.put(currentEntry, newKCT);
            keyBurstMap.put(currentEntry, t);
        }
    }

    @Override
    protected Optional<ROColorBitmap> process(Long t, Map<KeyCode, Long> keyMap) {
        for (Map.Entry<KeyCode, Long> entry : keyMap.entrySet()) {
            KeyCode currentEntry = entry.getKey();
            KeyColorTime kct = new KeyColorTime(currentEntry, getColorAtTime(t), entry.getValue());
            checkAndUpdateBurstMap(currentEntry, kct, t);
        }
        Map<KeyCode, Long> keyBurstMapClone = new HashMap<>(keyBurstMap);
        for (Map.Entry<KeyCode, Long> entry : keyBurstMapClone.entrySet()) {
            float delta = (float)(t - entry.getValue()) / dissolveTimeMs;
            KeyCode entryKey = entry.getKey();
            KeyColorTime kct = keyStartTimes.get(entryKey);
            if (kct != null) {
                buffer.setKey(entryKey, Color.setA(kct.getColor(), alpha));
                if (delta > switchDelay) {
                    List<KeyCode> leftKeys = KeyLocations.getLeftKeys(entryKey);
                    for (KeyCode leftKey : leftKeys) {
                        checkAndUpdateBurstMap(leftKey, kct, t);
                    }
                    List<KeyCode> rightKeys = KeyLocations.getRightKeys(entryKey);
                    for (KeyCode rightKey : rightKeys) {
                        checkAndUpdateBurstMap(rightKey, kct, t);
                    }
                    List<KeyCode> topKeys = KeyLocations.getTopKeys(entryKey);
                    for (KeyCode topKey : topKeys) {
                        checkAndUpdateBurstMap(topKey, kct, t);
                    }
                    List<KeyCode> bottomKeys = KeyLocations.getBottomKeys(entryKey);
                    for (KeyCode bottomKey : bottomKeys) {
                        checkAndUpdateBurstMap(bottomKey, kct, t);
                    }
                }
            } else {
                buffer.setKey(entryKey, Color.TRANSPARENT);
            }
        }
        return Optional.of(buffer);
    }

    @Override
    protected ROColorBitmap defaultValue() {
        return buffer;
    }
}
