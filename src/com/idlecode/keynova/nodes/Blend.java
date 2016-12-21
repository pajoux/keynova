package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.*;

import java.util.Optional;

/**
 *
 */
public class Blend<In1 extends ROColorBitmap, In2 extends ROColorBitmap>
  extends Node2<In1, In2, ROColorBitmap> {

  private ColorBitmap buffer = new ColorBitmap();

  public Blend(Node<In1> source1, Node<In2> source2) {
    super(source1, source2);
  }

  @Override
  public Optional<ROColorBitmap> process(In1 a, In2 b) {
    for (int i = 0; i < ColorBitmap.SIZE; ++i) {
      buffer.setPixel(i, Color.alphaBlend(a.getPixel(i), b.getPixel(i)));
    }
    return Optional.of(buffer);
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return buffer;
  }
}
