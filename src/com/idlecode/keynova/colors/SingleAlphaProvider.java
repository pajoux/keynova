package com.idlecode.keynova.colors;

public class SingleAlphaProvider implements AlphaProvider {
  private final float alpha;
  private long startTime;

  public SingleAlphaProvider(float alpha) {
    this.alpha = alpha;
    this.startTime = -1;
  }

  @Override
  public void setStartTime(long t) {
    startTime = t;
  }

  @Override
  public float getAlpha(long t) {
    return alpha;
  }

  @Override
  public AlphaProvider getCopy() {
    return new SingleAlphaProvider(alpha);
  }

  @Override
  public long getStartTime() {
    return startTime;
  }
}
