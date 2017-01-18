package com.idlecode.keynova.colors;

import java.util.List;

import com.idlecode.keynova.core.*;

/**
 *
 */
public class ListColorProvider implements ColorProvider {
  private final List<Integer> colors;
  private final AlphaProvider alphaProvider;
  private int index;
  private long startTime;

  public ListColorProvider(List<Integer> colors, AlphaProvider alphaProvider) {
    this.colors = colors;
    this.alphaProvider = alphaProvider;
    this.index = 0;
    this.startTime = -1;
  }

  @Override
  public void setStartTime(long t) {
    index++;
    if (index > colors.size() - 1) {
      index = 0;
    }
    startTime = t;
    alphaProvider.setStartTime(t);
  }

  @Override
  public int getColor(long t) {
    return Color.setA(colors.get(index), alphaProvider.getAlpha(t));
  }

  @Override
  public ColorProvider getCopy() {
    return new ListColorProvider(colors, alphaProvider.getCopy());
  }

  @Override
  public void setColorStartTime(int color, long t) {
    index = colors.indexOf(color);
    if (index < 0) {
      index = 0;
    }
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
