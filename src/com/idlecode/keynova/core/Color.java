package com.idlecode.keynova.core;

/**
 * Construct 8-bit pre-multiplied alpha packed integer colors.
 *
 * In a 32-bit integer we pack a red, green, blue, and alpha channel.
 *
 *   bits [24,31] => Alpha
 *   bits [16,23] => Red
 *   bits [8,14] => Green
 *   bits [0,7] => Blue
 *
 * Each byte value is written in Java big-endian order (high order bits first).
 *
 * A pre-multiplied color means that the alpha channel has been multiplied into each of the red,
 * green, and blue channels. In practice, per-multiplied values result in better results.
 */
public class Color {

  public static final int TRANSPARENT = Color.rgba(0, 0, 0, 0);

  /**
   * A basic alpha-blend of a {@code fg} (foreground) color onto a {@code bg} (background) color.
   * The resulting color is a pre-multiplied packed integer. This means the red, green, blue
   * values in the resulting color can be used as-is to display without the need to multiply in
   * the alpha channel. The alpha channel will contain the blended alpha channel.
   */
  public static int alphaBlend(int bg, int fg) {
    int inv = 256 - (a(fg) + 1);
    return pack(
      (((r(fg) << 8) + inv * r(bg)) >> 8),
      (((g(fg) << 8) + inv * g(bg)) >> 8),
      (((b(fg) << 8) + inv * b(bg)) >> 8),
      (((a(fg) << 8) + inv * a(bg)) >> 8)
    );
  }

  /**
   * Given a {@code color}, change the alpha channel. This will re-compute the red, green, and blue
   * channels so that the resulting color is properly pre-multiplied.
   */
  public static int setA(int color, int a) {
    return rgba(r(color), g(color), b(color), a);
  }

  public static int setA(int color, float a) {
    return setA(color, (int)(a * 255.0f));
  }

  public static int a(int color) {
    return (color >> 24) & 0xFF;
  }

  public static int r(int color) {
    return (color >> 16) & 0xFF;
  }

  public static int g(int color) {
    return (color >> 8) & 0xFF;
  }

  public static int b(int color) {
    return color & 0xFF;
  }

  public static int rgb(float r, float g, float b) {
    return rgba(r, g, b, 1.0f);
  }

  public static int rgba(float r, float g, float b, float a) {
    return rgba((int)(r * 255.0f), (int)(g * 255.0f), (int)(b * 255.0f), (int)(a * 255.0f));
  }

  public static int rgb(int r, int g, int b) {
    return rgba(r, g, b, 255);
  }

  public static int rgba(int r, int g, int b, int a) {
    int t = a + 1;
    return pack((r * t) >> 8, (g * t) >> 8, (b * t) >> 8, a);
  }

  private static int pack(int r, int g, int b, int a) {
    return ((a << 24) & 0xFF000000) | ((r << 16) & 0xFF0000) | ((g << 8) & 0xFF00) | (b & 0xFF);
  }
}
