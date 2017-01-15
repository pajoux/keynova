package com.idlecode.keynova.core;

import java.util.List;
/**
 *
 */
public class ColorProviderBitmap implements ROColorBitmap, Bitmapable {

  public static int ROWS = 6;
  public static int COLS = 21;
  public static int SIZE = ROWS * COLS;

  private final ColorProvider[] pixelProviders = new  ColorProvider[SIZE];
  private final int[] pixels = new  int[SIZE];

  public ColorProviderBitmap initializeAll(ColorProvider cp) {
    for (int i = 0; i < SIZE; ++i) {
      pixelProviders[i] = cp.getCopy();
    }
    return this;
  }

  public ColorProviderBitmap initializeAllByRows(List<ColorProvider> cpRows) {
    for (int i = 0; i < ROWS; ++i) {
      ColorProvider cp = cpRows.get(i);
      for (int j = 0; j < COLS; ++j) {
        pixelProviders[i * COLS + j] = cp.getCopy();
      }
    }
    return this;
  }

  public ColorProviderBitmap setStartTime(int row, int col, Long t) {
    return setStartTime(row * COLS + col, t);
  }

  public ColorProviderBitmap setStartTime(int index, Long t) {
    pixelProviders[index].setStartTime(t);
    return this;
  }

  public ColorProviderBitmap setKeyStartTime(KeyCode key, Long t) {
    return setStartTime(key.getRow(), key.getCol(), t);
  }

  public ColorProvider getColorProvider(int row, int col) {
    return getColorProvider(row * COLS + col);
  }

  public ColorProvider getColorProvider(int index) {
    return pixelProviders[index];
  }

  public ColorProvider getColorProvider(KeyCode key) {
    return getColorProvider(key.getRow(), key.getCol());
  }

  public ColorProviderBitmap processPixels(Long t) {
    for (int i = 0; i < SIZE; ++i) {
      pixels[i] = pixelProviders[i].getColor(t);
    }
    return this;
  }

  @Override
  public int getKey(KeyCode key) {
    return getPixel(key.getRow(), key.getCol());
  }

  @Override
  public int getPixel(int row, int col) {
    return pixels[row * COLS + col];
  }

  @Override
  public int getPixel(int index) {
    return pixels[index];
  }

  @Override
  public ByteBitmap getBitmap(ByteBitmap out) {
    return out.setFrom(this);
  }
}
