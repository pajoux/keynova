package com.idlecode.keynova.core;

/**
 * Created by Taylor on 1/15/2017.
 */
public class FadeInAlphaProvider extends AlphaProvider {
  private final float alpha;
  private final int dissolveTimeMs;
  private Long startTime;

  public FadeInAlphaProvider(float alpha, int dissolveTimeMs) {
    this.alpha = alpha;
    this.startTime = new Long(-1);
    this.dissolveTimeMs = dissolveTimeMs;
  }

  @Override
  public void setStartTime(Long t) {
    startTime = t;
  }

  @Override
  public float getAlpha(Long t) {
    if (startTime < 0) {
      return 0.0f;
    }
    float delta = (float)(t - startTime) / dissolveTimeMs;
    if (delta > 1.0f) {
      return alpha;
    }
    return alpha * delta;
  }

  @Override
  public AlphaProvider getCopy() {
    return new FadeInAlphaProvider(alpha, dissolveTimeMs);
  }

  @Override
  public Long getStartTime() {
    return startTime;
  }
}
