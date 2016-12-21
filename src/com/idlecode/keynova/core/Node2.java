package com.idlecode.keynova.core;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public abstract class Node2<In1, In2, Out> extends Node<Out> {

  private Node<In1> source1;
  private Node<In2> source2;

  public Node2(Node<In1> source1, Node<In2> source2) {
    this.source1 = source1;
    this.source2 = source2;
  }

  @Override
  final protected Optional<Out> applyImpl() {
    return process(source1.getOutput(), source2.getOutput());
  }

  @Override
  final public List<Node<?>> getSources() {
    return Arrays.asList(source1, source2);
  }

  abstract protected Optional<Out> process(In1 in1, In2 in2);
}