package com.idlecode.keynova.core;

/**
 * Created by Taylor on 1/15/2017.
 */
public class FadeOutAlphaProvider implements AlphaProvider {
  private final float alpha;
  private final int dissolveTimeMs;
  private Long startTime;

  public FadeOutAlphaProvider(float alpha, int dissolveTimeMs) {
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
      return 0.0f;
    }
    return alpha * (1.0f - delta);
  }

  @Override
  public AlphaProvider getCopy() {
    return new FadeOutAlphaProvider(alpha, dissolveTimeMs);
  }

  @Override
  public Long getStartTime() {
    return startTime;
  }
}
