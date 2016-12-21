package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.Map;
import java.util.Optional;

/**
 *
 */
public class StaticColor extends Node0<ROColorBitmap> {

  private final ColorBitmap buffer = new ColorBitmap();

  public StaticColor(Map<KeyCode, Integer> keys) {
    for (Map.Entry<KeyCode, Integer> entry : keys.entrySet()) {
      buffer.setKey(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public Optional<ROColorBitmap> process() {
    return Optional.of(buffer);
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return buffer;
  }
}
