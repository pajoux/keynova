package com.idlecode.keynova.core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public abstract class Node0<Out> extends Node<Out> {

  @Override
  final protected Optional<Out> applyImpl() {
    return process();
  }

  @Override
  final public List<Node<?>> getSources() {
    return Collections.emptyList();
  }

  abstract protected Optional<Out> process();
}
