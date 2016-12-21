package com.idlecode.keynova.uix.model;

import java.nio.file.Path;

/**
 *
 */
public class Composition {

  private final Path scriptPath;

  public Composition(Path scriptPath) {
    this.scriptPath = scriptPath;
  }

  public Path getScriptPath() {
    return scriptPath;
  }
}
