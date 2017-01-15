package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.Optional;

/**
 *
 */
public class RainbowWave extends Node1<Long, ROColorBitmap> {

  private final ColorBitmap buffer = new ColorBitmap();
  private final double f;
  private final long cycleLengthMs;
  private final float alpha;

  public RainbowWave(Clock clock, long cycleLengthMs, float alpha) {
    super(clock);
    this.alpha = alpha;
    this.cycleLengthMs = cycleLengthMs;
    f = Math.PI * 2 / cycleLengthMs;
  }

  @Override
  public Optional<ROColorBitmap> process(Long t) {
    long delta = t % cycleLengthMs;
    double r0 = f * delta + 2;
    double r1 = f * delta + 0;
    double r2 = f * delta + 4;
    int index = 0;
    for (int row = 0; row < ColorBitmap.ROWS; ++row) {
      double phase = ((double)row / ColorBitmap.ROWS) * 4.0;
      float r = (float)(Math.sin(r0 + phase) * 0.5 + 0.5);
      float g = (float)(Math.sin(r1 + phase) * 0.5 + 0.5);
      float b = (float)(Math.sin(r2 + phase) * 0.5 + 0.5);
      for (int col = 0; col < ColorBitmap.COLS; ++col) {
        buffer.setPixel(index, Color.rgba(r, g, b, alpha));
        ++index;
      }
    }
    return Optional.of(buffer);
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return buffer;
  }
}
