package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.*;

/**
 *
 */
public class NewKeyBurst extends Node2<Long, Map<KeyCode, Long>, ROColorBitmap> {

  private ColorProviderBitmap buffer = new ColorProviderBitmap();
  private final float switchDelayMs;
  private List<KeyCode> keyBurstKeys = new ArrayList<>();
  private Map<KeyCode, KeyColorTime> keyStartTimes = new HashMap<>();
  private ColorProvider colorProvider;

  private NewKeyBurst(
    Clock clock,
    Node<Map<KeyCode, Long>> source,
    int switchDelayMs
  ) {
    super(clock, source);
    this.switchDelayMs = switchDelayMs;
  }

  public NewKeyBurst(
    Clock clock,
    Node<Map<KeyCode, Long>> source,
    int switchDelayMs,
    ColorProvider colorProvider
  ) {
    this(clock, source, switchDelayMs);
    this.colorProvider = colorProvider;
    SingleColorProvider cp = new SingleColorProvider(colorProvider.getColor(new Long(0)), colorProvider.getAlphaProvider());
    buffer.initializeAll(cp);
  }

  public NewKeyBurst(
    Clock clock,
    Node<Map<KeyCode, Long>> source,
    int switchDelayMs,
    ColorProvider colorProvider,
    AlphaProvider alphaProvider
  ) {
    this(clock, source, switchDelayMs);
    this.colorProvider = colorProvider;
    SingleColorProvider cp = new SingleColorProvider(colorProvider.getColor(new Long(0)), alphaProvider);
    buffer.initializeAll(cp);
  }

  private void checkAndUpdateBurstMap(KeyCode currentEntry, KeyColorTime newKCT, Long t) {
    KeyColorTime currentKCT = keyStartTimes.get(currentEntry);
    if (currentKCT == null || (!currentKCT.equals(newKCT) && currentKCT.getTime() < newKCT.getTime())) {
      keyStartTimes.put(currentEntry, newKCT);
      keyBurstKeys.add(currentEntry);
      buffer.getColorProvider(currentEntry).setColorStartTime(newKCT.getColor(), t);
    }
  }

  @Override
  protected Optional<ROColorBitmap> process(Long t, Map<KeyCode, Long> keyMap) {
    for (Map.Entry<KeyCode, Long> entry : keyMap.entrySet()) {
      KeyCode currentEntry = entry.getKey();
      colorProvider.setStartTime(t);
      KeyColorTime kct = new KeyColorTime(currentEntry, colorProvider.getColor(t), entry.getValue());
      checkAndUpdateBurstMap(currentEntry, kct, t);
    }
    keyMap.clear();
    List<KeyCode> keyBurstKeysClone = new ArrayList<>(keyBurstKeys);
    for (KeyCode entry : keyBurstKeysClone) {
      ColorProvider cp = buffer.getColorProvider(entry);
      Long delta = t - cp.getStartTime();
      KeyColorTime kct = keyStartTimes.get(entry);
      if (kct != null) {
        if (delta > switchDelayMs) {
          keyBurstKeys.remove(entry);
          List<KeyCode> leftKeys = KeyLocations.getLeftKeys(entry);
          for (KeyCode leftKey : leftKeys) {
            checkAndUpdateBurstMap(leftKey, kct, t);
          }
          List<KeyCode> rightKeys = KeyLocations.getRightKeys(entry);
          for (KeyCode rightKey : rightKeys) {
            checkAndUpdateBurstMap(rightKey, kct, t);
          }
          List<KeyCode> topKeys = KeyLocations.getTopKeys(entry);
          for (KeyCode topKey : topKeys) {
            checkAndUpdateBurstMap(topKey, kct, t);
          }
          List<KeyCode> bottomKeys = KeyLocations.getBottomKeys(entry);
          for (KeyCode bottomKey : bottomKeys) {
            checkAndUpdateBurstMap(bottomKey, kct, t);
          }
        }
      }
    }
    return Optional.of(buffer.processPixels(t));
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return buffer;
  }
}
