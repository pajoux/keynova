package com.idlecode.keynova.colors;

import com.idlecode.keynova.core.*;

/**
 *
 */
public class SingleColorProvider implements ColorProvider {
  private int color;
  private final AlphaProvider alphaProvider;
  private long startTime;

  public SingleColorProvider(int color, AlphaProvider alphaProvider) {
    this.color = color;
    this.alphaProvider = alphaProvider;
  }

  @Override
  public void setStartTime(long t) {
    startTime = t;
    alphaProvider.setStartTime(t);
  }

  @Override
  public int getColor(long t) {
    return Color.setA(color, alphaProvider.getAlpha(t));
  }

  @Override
  public ColorProvider getCopy() {
    return new SingleColorProvider(color, alphaProvider.getCopy());
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
