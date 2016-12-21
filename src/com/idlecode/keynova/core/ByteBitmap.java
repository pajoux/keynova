package com.idlecode.keynova.core;

/**
 * Represents a keyboard color bitmap with red, green, blue, and alpha channels as bytes.
 */
public class ByteBitmap {

  public static int ROWS = 6;
  public static int COLS = 21;
  private static int SIZE = ROWS * COLS * 4;

  private final byte[] bitmap = new byte[SIZE];

  /**
   * A helper function that creates a new ByteBitmap object. This makes fluent style easier.
   * For example,
   * <code>
   *   ByteBitmap bitmap = ByteBitmap.create().setFromBuffer(buffer);
   * </code>
   *
   * @return A new ByteBitmap object.
   */
  public static ByteBitmap create() {
    return new ByteBitmap();
  }

  public ByteBitmap setFrom(ROColorBitmap colorBitmap) {
    for (int i = 0, o = 0; o < SIZE; ++i, o += 4) {
      int color = colorBitmap.getPixel(i);
      bitmap[o] = (byte)Color.b(color);
      bitmap[o + 1] = (byte)Color.g(color);
      bitmap[o + 2] = (byte)Color.r(color);
      bitmap[o + 3] = (byte)255;//Color.a(color);
    }
    return this;
  }

  /**
   * @return The keyboard 2d bitmap of colors as a single byte array. Pixels are packed as 4 byte
   *         sequences in row-major order. The channel order is (blue, green, red, alpha). Thus,
   *         bitmap[0]=pixel_0_0_blue, bitmap[1]=pixel_0_0_green, bitmap[2]=pixel_0_0_red, and
   *         bitmap[3]=pixel_0_0_alpha.
   */
  public byte[] getBytes() {
    return bitmap;
  }
}
