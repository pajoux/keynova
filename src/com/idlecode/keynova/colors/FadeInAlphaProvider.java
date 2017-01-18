package com.idlecode.keynova.colors;

public class FadeInAlphaProvider implements AlphaProvider {
  private final float alpha;
  private final int dissolveTimeMs;
  private long startTime;

  public FadeInAlphaProvider(float alpha, int dissolveTimeMs) {
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
      return alpha;
    }
    return alpha * delta;
  }

  @Override
  public AlphaProvider getCopy() {
    return new FadeInAlphaProvider(alpha, dissolveTimeMs);
  }

  @Override
  public long getStartTime() {
    return startTime;
  }
}
