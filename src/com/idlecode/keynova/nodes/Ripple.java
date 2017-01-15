package com.idlecode.keynova.nodes;

import java.util.*;

import com.idlecode.keynova.core.*;

public class Ripple extends Node1<Long, ROColorBitmap> {

  private final ColorBitmap buffer = new ColorBitmap();

  public Ripple(Clock clock) {
    super(clock);
  }

  @Override
  protected Optional<ROColorBitmap> process(Long t) {
    int crow = KeyCode.G.getRow();
    int ccol = KeyCode.G.getCol();
    for (int row = 0; row < ColorBitmap.ROWS; ++row) {
      for (int col = 0; col < ColorBitmap.COLS; ++col) {
        int r = (row - crow);
        int c = (col - ccol);
        double d = Math.sqrt(r*r + c*c) + Math.PI / 2.0;
        double z = Math.sin(d) * ((10.0 - d) / 10.0);
        int h = (int)(z * 128.0 + 127.0);
        buffer.setPixel(row, col, Color.rgba(255, 0, 0, h));
      }
    }

    return Optional.of(buffer);
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return buffer;
  }
}
