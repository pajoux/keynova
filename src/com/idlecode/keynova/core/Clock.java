package com.idlecode.keynova.core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class Clock extends Node<Long> {

  public long getCurrentTimeMs() {
    return System.nanoTime() / 1000000;
  }

  @Override
  public List<Node<?>> getSources() {
    return Collections.emptyList();
  }

  @Override
  protected Optional<Long> applyImpl() {
    return Optional.of(getCurrentTimeMs());
  }

  @Override
  protected Long defaultValue() {
    return 0L;
  }
}
