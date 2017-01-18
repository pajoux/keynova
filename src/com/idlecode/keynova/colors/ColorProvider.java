package com.idlecode.keynova.colors;

/**
 *
 */
public interface ColorProvider {
  void setStartTime(long t);

  int getColor(long t);

  ColorProvider getCopy();

  AlphaProvider getAlphaProvider();

  void setColorStartTime(int color, long t);

  long getStartTime();
}
