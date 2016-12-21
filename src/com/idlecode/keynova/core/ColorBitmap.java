package com.idlecode.keynova.core;

/**
 *
 */
public class ColorBitmap implements ROColorBitmap, Bitmapable {

  public static int ROWS = 6;
  public static int COLS = 21;
  public static int SIZE = ROWS * COLS;

  private final int[] pixels = new int[SIZE];

  public ColorBitmap clear(int color) {
    for (int i = 0; i < SIZE; ++i) {
      pixels[i] = color;
    }
    return this;
  }

  public ColorBitmap setPixel(int row, int col, int color) {
    return setPixel(row * COLS + col, color);
  }

  public ColorBitmap setPixel(int index, int color) {
    pixels[index] = color;
    return this;
  }

  public ColorBitmap setKey(KeyCode key, int color) {
    return setPixel(key.getRow(), key.getCol(), color);
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
