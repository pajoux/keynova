package com.idlecode.keynova.core;

public class KeyColorTime {
  private KeyCode keyCode;
  private int color;
  private Long time;

  public KeyColorTime(KeyCode keyCode, int color, Long time) {
    this.keyCode = keyCode;
    this.color = color;
    this.time = time;
  }

  public KeyCode getKeyCode() {
    return this.keyCode;
  }

  public int getColor() {
    return this.color;
  }

  public Long getTime() {
    return this.time;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof KeyColorTime) {
      return false;
    }
    KeyColorTime kct = (KeyColorTime) other;
    return this.keyCode.getInt() == kct.keyCode.getInt() && this.color == kct.color && this.time == kct.time;
  }
}
