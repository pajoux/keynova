package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.Map;
import java.util.Optional;

/**
 *
 */
public class KeyDissolve extends Node2<Long, Map<KeyCode, Long>, ROColorBitmap> {

  private ColorBitmap buffer = new ColorBitmap();
  private final int dissolveTimeMs;
  private final int color;

  public KeyDissolve(
    Clock clock,
    Node<Map<KeyCode, Long>> source,
    int dissolveTimeMs,
    int color
  ) {
    super(clock, source);
    this.dissolveTimeMs = dissolveTimeMs;
    this.color = color;
  }

  @Override
  protected Optional<ROColorBitmap> process(Long t, Map<KeyCode, Long> keyMap) {
    for (Map.Entry<KeyCode, Long> entry : keyMap.entrySet()) {
      float delta = (float)(t - entry.getValue()) / dissolveTimeMs;
      if (delta < 1.0f) {
        buffer.setKey(entry.getKey(), Color.setA(color, 1.0f - delta));
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
