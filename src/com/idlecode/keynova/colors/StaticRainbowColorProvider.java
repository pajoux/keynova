package com.idlecode.keynova.colors;

import com.idlecode.keynova.core.*;

/**
 *
 */
public class StaticRainbowColorProvider implements ColorProvider {
  private final long cycleLengthMs;
  private final double f;
  private final double phase;
  private int color;
  private final AlphaProvider alphaProvider;
  private long startTime;

  public StaticRainbowColorProvider(Long cycleLengthMs, double phase, AlphaProvider alphaProvider) {
    this.cycleLengthMs = cycleLengthMs;
    this.f = Math.PI * 2 / cycleLengthMs;
    this.color = Color.getRainbowColor(0L, cycleLengthMs, f, phase, 1.0f);
    this.phase = phase;
    this.alphaProvider = alphaProvider;
    this.startTime = -1;
  }

  @Override
  public void setStartTime(long t) {
    color = Color.getRainbowColor(t, cycleLengthMs, f, phase, 1.0f);
    startTime = t;
    alphaProvider.setStartTime(t);
  }

  @Override
  public int getColor(long t) {
    return  Color.setA(color, alphaProvider.getAlpha(t));
  }

  @Override
  public ColorProvider getCopy() {
    return new StaticRainbowColorProvider(cycleLengthMs, phase, alphaProvider.getCopy());
  }

  @Override
  public void setColorStartTime(int color, long t) {
    this.color = color;
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
