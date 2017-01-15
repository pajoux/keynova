package com.idlecode.keynova.core;

/**
 * Created by Taylor on 1/15/2017.
 */
public class SingleAlphaProvider implements AlphaProvider {
  private final float alpha;
  private Long startTime;

  public SingleAlphaProvider(float alpha) {
    this.alpha = alpha;
    this.startTime = new Long(-1);
  }

  @Override
  public void setStartTime(Long t) {
    startTime = t;
  }

  @Override
  public float getAlpha(Long t) {
    return alpha;
  }

  @Override
  public AlphaProvider getCopy() {
    return new SingleAlphaProvider(alpha);
  }

  @Override
  public Long getStartTime() {
    return startTime;
  }
}
