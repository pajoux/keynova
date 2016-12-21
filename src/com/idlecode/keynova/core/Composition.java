package com.idlecode.keynova.core;

import java.util.*;

/**
 *
 */
public class Composition {

  private final List<Node<?>> nodes;
  private final Node<? extends Bitmapable> output;
  private long version = 0L;

  public Composition(Node<? extends Bitmapable> output) {
    this.output = output;
    this.nodes = sort(output);
  }

  public int getBackgroundColor() {
    return Color.rgb(120, 50, 255);
  }

  private static List<Node<?>> sort(Node<?> leaf) {
    // Get a set of all nodes in our composition.
    Set<Node<?>> N = new HashSet<>();
    Stack<Node<?>> toVisit = new Stack<>();
    toVisit.push(leaf);
    while (!toVisit.empty()) {
      Node<?> n = toVisit.pop();
      if (N.contains(n)) {
        continue;
      }
      N.add(n);
      n.getSources().forEach(toVisit::push);
    }

    // Build a map from node to # of outgoing edges.
    Map<Node<?>, Integer> edgeCount = new HashMap<>();
    for (Node<?> n : N) {
      edgeCount.put(n, 0);
    }
    for (Node<?> n : N) {
      for (Node<?> m : n.getSources()) {
        edgeCount.put(m, edgeCount.get(m) + 1);
      }
    }

    // Do a topological sort.
    ArrayList<Node<?>> sorted = new ArrayList<>();
    Stack<Node<?>> S = new Stack<>();
    S.push(leaf);
    while (!S.empty()) {
      Node<?> n = S.pop();
      sorted.add(n);
      for (Node<?> m : n.getSources()) {
        int count = edgeCount.get(m);
        if (count == 1) {
          S.push(m);
        }
        edgeCount.put(m, count - 1);
      }
    }
    Collections.reverse(sorted);
    return sorted;
  }

  public void init() {
    nodes.forEach(Node::onInit);
  }

  public void close() {
    nodes.forEach(Node::onStop);
  }

  public Bitmapable process() {
    // Process the DAG in a topologically sorted order.
    for (Node<?> node : nodes) {
      node.apply(version);
    }
    ++version;
    return output.getOutput();
  }
}
