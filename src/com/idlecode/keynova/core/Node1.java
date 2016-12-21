package com.idlecode.keynova.core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public abstract class Node1<In1, Out> extends Node<Out> {

  private Node<In1> source;

  public Node1(Node<In1> source) {
    this.source = source;
  }

  @Override
  final protected Optional<Out> applyImpl() {
    return process(source.getOutput());
  }

  @Override
  final public List<Node<?>> getSources() {
    return Collections.singletonList(source);
  }

  abstract protected Optional<Out> process(In1 in1);
}