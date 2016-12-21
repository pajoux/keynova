package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.Optional;

/**
 *
 */
public class SolidColor extends Node0<ROColorBitmap> {

  private final ColorBitmap buffer = new ColorBitmap();

  public SolidColor(int color) {
    buffer.clear(color);
  }

  @Override
  protected Optional<ROColorBitmap> process() {
    return Optional.of(buffer);
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return buffer;
  }
}
