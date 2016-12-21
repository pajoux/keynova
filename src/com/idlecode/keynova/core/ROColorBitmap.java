package com.idlecode.keynova.core;

/**
 *
 */
public interface ROColorBitmap {

  int getKey(KeyCode key);

  int getPixel(int row, int col);

  int getPixel(int index);
}
