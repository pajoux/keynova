package com.idlecode.keynova.core;

/**
 * An object that can be turned into a ByteBitmap.
 */
public interface Bitmapable {
  /**
   * Populate {@code out} with the bitmap this object represents.
   *
   * @param out A ByteBitmap object to populate.
   * @return The {@code out} object for fluent interface.
   */
  ByteBitmap getBitmap(ByteBitmap out);
}
