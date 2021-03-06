package com.idlecode.keynova.nodes;

import java.util.*;

import com.idlecode.keynova.core.*;

/**
 *
 */
public class KeyBurst extends Node2<Long, Map<KeyCode, Long>, ROColorBitmap> {

  private ColorBitmap buffer = new ColorBitmap();
  private final float switchDelay;
  private final int dissolveTimeMs;
  private final int color;
  private Map<KeyCode, Long> keyBurstMap = new HashMap<>();
  private Map<KeyCode, KeyColorTime> keyStartTimes = new HashMap<>();

  public KeyBurst(
    Clock clock, Node<Map<KeyCode, Long>> source, int dissolveTimeMs, int switchDelay, int color
  ) {
    super(clock, source);
    this.dissolveTimeMs = dissolveTimeMs;
    this.color = color;
    if (switchDelay > 99) {
      this.switchDelay = 0.99f;
    } else if (switchDelay < 1) {
      this.switchDelay = 0.01f;
    } else {
      this.switchDelay = switchDelay / 100.0f;
    }
  }

  private void checkAndUpdateBurstMap(KeyCode currentEntry, KeyColorTime newKCT, Long t) {
    KeyColorTime currentKCT = keyStartTimes.get(currentEntry);
    if (currentKCT == null || (!currentKCT.equals(newKCT) && currentKCT.getTime() < newKCT.getTime())) {
      keyStartTimes.put(currentEntry, newKCT);
      keyBurstMap.put(currentEntry, t);
    }
  }

  @Override
  protected Optional<ROColorBitmap> process(Long t, Map<KeyCode, Long> keyMap) {
    for (Map.Entry<KeyCode, Long> entry : keyMap.entrySet()) {
      KeyCode currentEntry = entry.getKey();
      KeyColorTime kct = new KeyColorTime(currentEntry, this.color, entry.getValue());
      checkAndUpdateBurstMap(currentEntry, kct, t);
    }
    Map<KeyCode, Long> keyBurstMapClone = new HashMap<>(keyBurstMap);
    for (Map.Entry<KeyCode, Long> entry : keyBurstMapClone.entrySet()) {
      float delta = (float)(t - entry.getValue()) / dissolveTimeMs;
      KeyCode entryKey = entry.getKey();
      KeyColorTime kct = keyStartTimes.get(entryKey);
      if (kct != null && delta < 1.0f) {
        buffer.setKey(entryKey, Color.setA(color, 1.0f - delta));
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
