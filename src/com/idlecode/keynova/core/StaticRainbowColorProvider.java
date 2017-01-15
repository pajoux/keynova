package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class StaticRainbowColorProvider implements ColorProvider {
  private final long cycleLengthMs;
  private final double f;
  private final double phase;
  private int color;
  private final AlphaProvider alphaProvider;
  private Long startTime;

  public StaticRainbowColorProvider(Long cycleLengthMs, double phase, AlphaProvider alphaProvider) {
    this.cycleLengthMs = cycleLengthMs;
    this.f = Math.PI * 2 / cycleLengthMs;
    this.color = Color.getRainbowColor(new Long(0), cycleLengthMs, f, phase, 1.0f);
    this.phase = phase;
    this.alphaProvider = alphaProvider;
    this.startTime = new Long(-1);
  }

  @Override
  public void setStartTime(Long t) {
    color = Color.getRainbowColor(t, cycleLengthMs, f, phase, 1.0f);
    startTime = t;
    alphaProvider.setStartTime(t);
  }

  @Override
  public int getColor(Long t) {
    return  Color.setA(color, alphaProvider.getAlpha(t));
  }

  @Override
  public ColorProvider getCopy() {
    return new StaticRainbowColorProvider(cycleLengthMs, phase, alphaProvider.getCopy());
  }

  @Override
  public void setColorStartTime(int color, Long t) {
    this.color = color;
    this.startTime = t;
    alphaProvider.setStartTime(t);
  }

  @Override
  public Long getStartTime() {
    return startTime;
  }

  @Override
  public AlphaProvider getAlphaProvider() {
    return alphaProvider;
  }
}
