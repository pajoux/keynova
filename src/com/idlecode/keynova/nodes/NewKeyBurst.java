package com.idlecode.keynova.nodes;

import com.idlecode.keynova.colors.*;
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
    Clock clock, Node<Map<KeyCode, Long>> source, int switchDelayMs
  ) {
    super(clock, source);
    this.switchDelayMs = switchDelayMs;
  }

  public NewKeyBurst(
    Clock clock, Node<Map<KeyCode, Long>> source, int switchDelayMs, ColorProvider colorProvider
  ) {
    this(clock, source, switchDelayMs);
    this.colorProvider = colorProvider;
    SingleColorProvider cp = new SingleColorProvider(
      colorProvider.getColor(0L),
      colorProvider.getAlphaProvider()
    );
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
    SingleColorProvider cp = new SingleColorProvider(
      colorProvider.getColor(0L),
      alphaProvider
    );
    buffer.initializeAll(cp);
  }

  public NewKeyBurst(
    Clock clock,
    Node<Map<KeyCode, Long>> source,
    int switchDelayMs,
    ColorProvider colorProvider,
    ColorProvider bufferColorProvider
  ) {
    this(clock, source, switchDelayMs);
    this.colorProvider = colorProvider;
    buffer.initializeAll(bufferColorProvider);
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
      KeyColorTime kct = new KeyColorTime(
        currentEntry,
        colorProvider.getColor(t),
        entry.getValue()
      );
      checkAndUpdateBurstMap(currentEntry, kct, t);
    }
    keyMap.clear();
    List<KeyCode> keyBurstKeysClone = new ArrayList<>(keyBurstKeys);
    for (KeyCode entry : keyBurstKeysClone) {
      ColorProvider cp = buffer.getColorProvider(entry);
      Long delta = t - cp.getStartTime();
      KeyColorTime kct = keyStartTimes.get(entry);
      if (kct != null && delta > switchDelayMs) {
        keyBurstKeys.remove(entry);
        KeyLocations.getLeftKeys(entry).forEach(k -> checkAndUpdateBurstMap(k, kct, t));
        KeyLocations.getRightKeys(entry).forEach(k -> checkAndUpdateBurstMap(k, kct, t));
        KeyLocations.getTopKeys(entry).forEach(k -> checkAndUpdateBurstMap(k, kct, t));
        KeyLocations.getBottomKeys(entry).forEach(k -> checkAndUpdateBurstMap(k, kct, t));
      }
    }
    return Optional.of(buffer.processPixels(t));
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return buffer;
  }
}
