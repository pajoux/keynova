package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.*;

/**
 *
 */
public class KeySlide extends Node2<Long, Map<KeyCode, Long>, ROColorBitmap> {

    private ColorBitmap buffer = new ColorBitmap();
    private final Long switchDelay;
    private final int dissolveTimeMs;
    private final int color;
    private final Map<KeyCode, Long> keyStartTimes = new HashMap<>();

    public KeySlide(
            Clock clock,
            Node<Map<KeyCode, Long>> source,
            int dissolveTimeMs,
            Long switchDelay,
            int color
    ) {
        super(clock, source);
        this.dissolveTimeMs = dissolveTimeMs;
        this.color = color;
        this.switchDelay = switchDelay;
    }

    @Override
    protected Optional<ROColorBitmap> process(Long t, Map<KeyCode, Long> keyMap) {
        Map<KeyCode, Long> keyMapClone = new HashMap<KeyCode, Long>(keyMap);
        for (Map.Entry<KeyCode, Long> entry : keyMapClone.entrySet()) {
            float delta = (float)(t - entry.getValue()) / dissolveTimeMs;
            if (delta < 1.0f) {
                KeyCode currentKey = entry.getKey();
                Long currentKeyT = entry.getValue();
                Long pastKeyT = keyStartTimes.get(currentKey);
                buffer.setKey(currentKey, Color.setA(color, 1.0f - delta));
                if (delta > switchDelay && (pastKeyT == null || pastKeyT < currentKeyT)) {
                    keyStartTimes.put(currentKey, currentKeyT);
                    List<KeyCode> leftKeys = KeyLocations.getLeftKeys(entry.getKey());
                    for(KeyCode leftKey : leftKeys) {
                        if (leftKey.getInt() != KeyCode.NULL.getInt()) {
                            keyMap.put(leftKey, t);
                        }
                    }
                }
            } else {
                buffer.setKey(entry.getKey(), Color.TRANSPARENT);
            }
        }
        return Optional.of(buffer);
    }

    @Override
    protected ROColorBitmap defaultValue() {
        return buffer;
    }
}
