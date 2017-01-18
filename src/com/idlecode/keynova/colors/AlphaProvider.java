package com.idlecode.keynova.colors;

/**
 *
 */
public interface AlphaProvider {
  void setStartTime(long t);

  float getAlpha(long t);

  AlphaProvider getCopy();

  long getStartTime();
}
