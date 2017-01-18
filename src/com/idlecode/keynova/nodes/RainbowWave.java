package com.idlecode.keynova.nodes;

import com.idlecode.keynova.colors.*;
import com.idlecode.keynova.core.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class RainbowWave extends Node1<Long, ROColorBitmap> {

  private final ColorProviderBitmap buffer = new ColorProviderBitmap();
  private final List<ColorProvider> rowProviders;

  public RainbowWave(Clock clock, long cycleLengthMs, float alpha) {
    super(clock);
    rowProviders = new ArrayList<>();
    AlphaProvider alphaProvider = new SingleAlphaProvider(alpha);
    for (int row = 0; row < ColorBitmap.ROWS; ++row) {
      double phase = ((double)row / ColorBitmap.ROWS) * 4.0;
      rowProviders.add(new RainbowColorProvider(cycleLengthMs, phase, alphaProvider));
    }
    buffer.initializeAllByRows(rowProviders);
  }

  @Override
  public Optional<ROColorBitmap> process(Long t) {
    return Optional.of(buffer.processPixels(t));
  }

  @Override
  protected ROColorBitmap defaultValue() {
    return buffer;
  }
}
