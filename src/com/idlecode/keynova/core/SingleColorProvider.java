package com.idlecode.keynova.core;

/**
 *
 */
public class SingleColorProvider implements ColorProvider {
  private final int color;

  public SingleColorProvider(int color) {
    this.color = color;
  }

  @Override
  public int getColor(Long t) {
    return color;
  }
}
