package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.Node0;

import java.util.Optional;

/**
 *
 */
public class ConstNode<Out> extends Node0<Out> {

  private final Out value;

  public ConstNode(Out value) {
    this.value = value;
  }

  @Override
  protected Optional<Out> process() {
    return Optional.of(value);
  }

  @Override
  protected Out defaultValue() {
    return value;
  }
}
