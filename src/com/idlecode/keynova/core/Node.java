package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 * The base-node.
 */
public abstract class Node<Out> {

  private long version = 0;
  private Out output;

  public Out getOutput() {
    return output;
  }

  final public void apply(long nextVersion) {
    // Set a default value if we have no value yet.
    if (output == null) {
      output = defaultValue();
    }

    // Only recompute the node if any inputs are dirty.
    boolean recompute =
      getSources().stream().anyMatch(n -> n.isDirty(nextVersion)) ||
      this instanceof Clock;

    if (recompute) {
      Optional<Out> output = applyImpl();
      if (output.isPresent()) {
        this.output = output.get();
        version = nextVersion;
      }
    }
  }

  abstract public List<Node<?>> getSources();

  abstract protected Optional<Out> applyImpl();

  abstract protected Out defaultValue();

  protected void onInit() {
  }

  protected void onStop() {
  }

  private boolean isDirty(long nextVersion) {
    return version >= nextVersion;
  }
}
