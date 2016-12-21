package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.Map;
import java.util.Optional;

/**
 *
 */
public class ColorizeKeys extends Node1<Map<KeyCode, Integer>, ROColorBitmap> {

  private ColorBitmap bitmap = new ColorBitmap();

  public ColorizeKeys(Node<Map<KeyCode, Integer>> source) {
    super(source);
  }

  @Override
  protected Optional<ROColorBitmap> process(Map<KeyCode, Integer> colors) {
    for (Map.Entry<KeyCode, Integer> entry : colors.entrySet()) {
      bitmap.setKey(entry.getKey(), entry.getValue());
    }
    return Optional.of(bitmap);
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return bitmap;
  }
}
