package com.idlecode.keynova.colors;

/**
 * Created by Taylor on 1/15/2017.
 */
public class FadeOutAlphaProvider implements AlphaProvider {
  private final float alpha;
  private final int dissolveTimeMs;
  private long startTime;

  public FadeOutAlphaProvider(float alpha, int dissolveTimeMs) {
    this.alpha = alpha;
    this.startTime = -1;
    this.dissolveTimeMs = dissolveTimeMs;
  }

  @Override
  public void setStartTime(long t) {
    startTime = t;
  }

  @Override
  public float getAlpha(long t) {
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
  public long getStartTime() {
    return startTime;
  }
}
