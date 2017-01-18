package com.idlecode.keynova.colors;

import com.idlecode.keynova.core.*;

/**
 *
 */
public class RainbowColorProvider implements ColorProvider {
  private final long cycleLengthMs;
  private final double f;
  private final double phase;
  private final AlphaProvider alphaProvider;
  private long startTime;

  public RainbowColorProvider(Long cycleLengthMs, double phase, AlphaProvider alphaProvider) {
    this.cycleLengthMs = cycleLengthMs;
    this.f = Math.PI * 2 / cycleLengthMs;
    this.phase = phase;
    this.alphaProvider = alphaProvider;
    this.startTime = -1;
  }

  @Override
  public void setStartTime(long t) {
    startTime = t;
    alphaProvider.setStartTime(t);
  }

  @Override
  public int getColor(long t) {
    return Color.getRainbowColor(t, cycleLengthMs, f, phase, alphaProvider.getAlpha(t));
  }

  @Override
  public ColorProvider getCopy() {
    return new RainbowColorProvider(cycleLengthMs, phase, alphaProvider.getCopy());
  }

  @Override
  public void setColorStartTime(int color, long t) {
    this.startTime = t;
    alphaProvider.setStartTime(t);
  }

  @Override
  public long getStartTime() {
    return startTime;
  }

  @Override
  public AlphaProvider getAlphaProvider() {
    return alphaProvider;
  }
}
