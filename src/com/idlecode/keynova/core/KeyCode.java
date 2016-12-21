package com.idlecode.keynova.core;

import com.logitech.gaming.LogiLED;

/**
 *
 */
public enum KeyCode {
  ESC(0, 0, LogiLED.ESC),
  F1(0, 1, LogiLED.F1),
  F2(0, 2, LogiLED.F2),
  F3(0, 3, LogiLED.F3),
  F4(0, 4, LogiLED.F4),
  F5(0, 5, LogiLED.F5),
  F6(0, 6, LogiLED.F6),
  F7(0, 7, LogiLED.F7),
  F8(0, 8, LogiLED.F8),
  F9(0, 9, LogiLED.F9),
  F10(0, 10, LogiLED.F10),
  F11(0, 11, LogiLED.F11),
  F12(0, 12, LogiLED.F12),
  PRINT_SCREEN(0, 13, LogiLED.PRINT_SCREEN),
  SCROLL_LOCK(0, 14, LogiLED.SCROLL_LOCK),
  PAUSE_BREAK(0, 15, LogiLED.PAUSE_BREAK),
  TILDE(1, 0, LogiLED.TILDE),
  ONE(1, 1, LogiLED.ONE),
  TWO(1, 2, LogiLED.TWO),
  THREE(1, 3, LogiLED.THREE),
  FOUR(1, 4, LogiLED.FOUR),
  FIVE(1, 5, LogiLED.FIVE),
  SIX(1, 6, LogiLED.SIX),
  SEVEN(1, 7, LogiLED.SEVEN),
  EIGHT(1, 8, LogiLED.EIGHT),
  NINE(1, 9, LogiLED.NINE),
  ZERO(1, 10, LogiLED.ZERO),
  MINUS(1, 11, LogiLED.MINUS),
  EQUALS(1, 12, LogiLED.EQUALS),
  BACKSPACE(1, 13, LogiLED.BACKSPACE),
  INSERT(1, 14, LogiLED.INSERT),
  HOME(1, 15, LogiLED.HOME),
  PAGE_UP(1, 16, LogiLED.PAGE_UP),
  NUM_LOCK(1, 17, LogiLED.NUM_LOCK),
  NUM_SLASH(1, 18, LogiLED.NUM_SLASH),
  NUM_ASTERISK(1, 19, LogiLED.NUM_ASTERISK),
  NUM_MINUS(1, 20, LogiLED.NUM_MINUS),
  TAB(2, 0, LogiLED.TAB),
  Q(2, 1, LogiLED.Q),
  W(2, 2, LogiLED.W),
  E(2, 3, LogiLED.E),
  R(2, 4, LogiLED.R),
  T(2, 5, LogiLED.T),
  Y(2, 6, LogiLED.Y),
  U(2, 7, LogiLED.U),
  I(2, 8, LogiLED.I),
  O(2, 9, LogiLED.O),
  P(2, 10, LogiLED.P),
  OPEN_BRACKET(2, 11, LogiLED.OPEN_BRACKET),
  CLOSE_BRACKET(2, 12, LogiLED.CLOSE_BRACKET),
  BACKSLASH(2, 13, LogiLED.BACKSLASH),
  KEYBOARD_DELETE(2, 14, LogiLED.KEYBOARD_DELETE),
  END(2, 15, LogiLED.END),
  PAGE_DOWN(2, 16, LogiLED.PAGE_DOWN),
  NUM_SEVEN(2, 17, LogiLED.NUM_SEVEN),
  NUM_EIGHT(2, 18, LogiLED.NUM_EIGHT),
  NUM_NINE(2, 19, LogiLED.NUM_NINE),
  NUM_PLUS (2, 20, LogiLED.NUM_PLUS),
  CAPS_LOCK(3, 0, LogiLED.CAPS_LOCK),
  A(3, 1, LogiLED.A),
  S(3, 2, LogiLED.S),
  D(3, 3, LogiLED.D),
  F(3, 4, LogiLED.F),
  G(3, 5, LogiLED.G),
  H(3, 6, LogiLED.H),
  J(3, 7, LogiLED.J),
  K(3, 8, LogiLED.K),
  L(3, 9, LogiLED.L),
  SEMICOLON(3, 10, LogiLED.SEMICOLON),
  APOSTROPHE(3, 11, LogiLED.APOSTROPHE),
  ENTER(3, 13, LogiLED.ENTER),
  NUM_FOUR(3, 17, LogiLED.NUM_FOUR),
  NUM_FIVE(3, 18, LogiLED.NUM_FIVE),
  NUM_SIX(3, 19, LogiLED.NUM_SIX),
  LEFT_SHIFT(4, 0, LogiLED.LEFT_SHIFT),
  Z(4, 2, LogiLED.Z),
  X(4, 3, LogiLED.X),
  C(4, 4, LogiLED.C),
  V(4, 5, LogiLED.V),
  B(4, 6, LogiLED.B),
  N(4, 7, LogiLED.N),
  M(4, 8, LogiLED.M),
  COMMA(4, 9, LogiLED.COMMA),
  PERIOD(4, 10, LogiLED.PERIOD),
  FORWARD_SLASH(4, 11, LogiLED.FORWARD_SLASH),
  RIGHT_SHIFT(4, 13, LogiLED.RIGHT_SHIFT),
  ARROW_UP(4, 15, LogiLED.ARROW_UP),
  NUM_ONE(4, 17, LogiLED.NUM_ONE),
  NUM_TWO(4, 18, LogiLED.NUM_TWO),
  NUM_THREE(4, 19, LogiLED.NUM_THREE),
  NUM_ENTER(4, 20, LogiLED.NUM_ENTER),
  LEFT_CONTROL(5, 0, LogiLED.LEFT_CONTROL),
  LEFT_WINDOWS(5, 1, LogiLED.LEFT_WINDOWS),
  LEFT_ALT(5, 2, LogiLED.LEFT_ALT),
  SPACE(5, 5, LogiLED.SPACE),
  RIGHT_ALT(5, 11, LogiLED.RIGHT_ALT),
  RIGHT_WINDOWS(5, 12, LogiLED.RIGHT_WINDOWS),
  APPLICATION_SELECT(5, 13, LogiLED.APPLICATION_SELECT),
  RIGHT_CONTROL(5, 14, LogiLED.RIGHT_CONTROL),
  ARROW_LEFT(5, 15, LogiLED.ARROW_LEFT),
  ARROW_DOWN(5, 16, LogiLED.ARROW_DOWN),
  ARROW_RIGHT(5, 17, LogiLED.ARROW_RIGHT),
  NUM_ZERO(5, 18, LogiLED.NUM_ZERO),
  NUM_PERIOD(5, 19, LogiLED.NUM_PERIOD);

  private int row;
  private int col;
  private int keyCode;

  KeyCode(int row, int col, int keyCode) {
    this.row = row;
    this.col = col;
    this.keyCode = keyCode;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getInt() {
    return keyCode;
  }

  public static KeyCode fromInt(int keyCode) {
    switch (keyCode) {
      case LogiLED.ESC: return ESC;
      case LogiLED.F1: return   F1;
      case LogiLED.F2: return   F2;
      case LogiLED.F3: return   F3;
      case LogiLED.F4: return   F4;
      case LogiLED.F5: return   F5;
      case LogiLED.F6: return   F6;
      case LogiLED.F7: return   F7;
      case LogiLED.F8: return   F8;
      case LogiLED.F9: return   F9;
      case LogiLED.F10: return   F10;
      case LogiLED.F11: return   F11;
      case LogiLED.F12: return   F12;
      case LogiLED.PRINT_SCREEN: return   PRINT_SCREEN;
      case LogiLED.SCROLL_LOCK: return   SCROLL_LOCK;
      case LogiLED.PAUSE_BREAK: return   PAUSE_BREAK;
      case LogiLED.TILDE: return   TILDE;
      case LogiLED.ONE: return   ONE;
      case LogiLED.TWO: return   TWO;
      case LogiLED.THREE: return   THREE;
      case LogiLED.FOUR: return   FOUR;
      case LogiLED.FIVE: return   FIVE;
      case LogiLED.SIX: return   SIX;
      case LogiLED.SEVEN: return   SEVEN;
      case LogiLED.EIGHT: return   EIGHT;
      case LogiLED.NINE: return   NINE;
      case LogiLED.ZERO: return   ZERO;
      case LogiLED.MINUS: return   MINUS;
      case LogiLED.EQUALS: return   EQUALS;
      case LogiLED.BACKSPACE: return   BACKSPACE;
      case LogiLED.INSERT: return   INSERT;
      case LogiLED.HOME: return   HOME;
      case LogiLED.PAGE_UP: return   PAGE_UP;
      case LogiLED.NUM_LOCK: return   NUM_LOCK;
      case LogiLED.NUM_SLASH: return   NUM_SLASH;
      case LogiLED.NUM_ASTERISK: return   NUM_ASTERISK;
      case LogiLED.NUM_MINUS: return   NUM_MINUS;
      case LogiLED.TAB: return   TAB;
      case LogiLED.Q: return   Q;
      case LogiLED.W: return   W;
      case LogiLED.E: return   E;
      case LogiLED.R: return   R;
      case LogiLED.T: return   T;
      case LogiLED.Y: return   Y;
      case LogiLED.U: return   U;
      case LogiLED.I: return   I;
      case LogiLED.O: return   O;
      case LogiLED.P: return   P;
      case LogiLED.OPEN_BRACKET: return   OPEN_BRACKET;
      case LogiLED.CLOSE_BRACKET: return   CLOSE_BRACKET;
      case LogiLED.BACKSLASH: return   BACKSLASH;
      case LogiLED.KEYBOARD_DELETE: return   KEYBOARD_DELETE;
      case LogiLED.END: return   END;
      case LogiLED.PAGE_DOWN: return   PAGE_DOWN;
      case LogiLED.NUM_SEVEN: return   NUM_SEVEN;
      case LogiLED.NUM_EIGHT: return   NUM_EIGHT;
      case LogiLED.NUM_NINE: return   NUM_NINE;
      case LogiLED.NUM_PLUS: return   NUM_PLUS ;
      case LogiLED.CAPS_LOCK: return   CAPS_LOCK;
      case LogiLED.A: return   A;
      case LogiLED.S: return   S;
      case LogiLED.D: return   D;
      case LogiLED.F: return   F;
      case LogiLED.G: return   G;
      case LogiLED.H: return   H;
      case LogiLED.J: return   J;
      case LogiLED.K: return   K;
      case LogiLED.L: return   L;
      case LogiLED.SEMICOLON: return   SEMICOLON;
      case LogiLED.APOSTROPHE: return   APOSTROPHE;
      case LogiLED.ENTER: return   ENTER;
      case LogiLED.NUM_FOUR: return   NUM_FOUR;
      case LogiLED.NUM_FIVE: return   NUM_FIVE;
      case LogiLED.NUM_SIX: return   NUM_SIX;
      case LogiLED.LEFT_SHIFT: return   LEFT_SHIFT;
      case LogiLED.Z: return   Z;
      case LogiLED.X: return   X;
      case LogiLED.C: return   C;
      case LogiLED.V: return   V;
      case LogiLED.B: return   B;
      case LogiLED.N: return   N;
      case LogiLED.M: return   M;
      case LogiLED.COMMA: return   COMMA;
      case LogiLED.PERIOD: return   PERIOD;
      case LogiLED.FORWARD_SLASH: return   FORWARD_SLASH;
      case LogiLED.RIGHT_SHIFT: return   RIGHT_SHIFT;
      case LogiLED.ARROW_UP: return   ARROW_UP;
      case LogiLED.NUM_ONE: return   NUM_ONE;
      case LogiLED.NUM_TWO: return   NUM_TWO;
      case LogiLED.NUM_THREE: return   NUM_THREE;
      case LogiLED.NUM_ENTER: return   NUM_ENTER;
      case LogiLED.LEFT_CONTROL: return   LEFT_CONTROL;
      case LogiLED.LEFT_WINDOWS: return   LEFT_WINDOWS;
      case LogiLED.LEFT_ALT: return   LEFT_ALT;
      case LogiLED.SPACE: return   SPACE;
      case LogiLED.RIGHT_ALT: return   RIGHT_ALT;
      case LogiLED.RIGHT_WINDOWS: return   RIGHT_WINDOWS;
      case LogiLED.APPLICATION_SELECT: return   APPLICATION_SELECT;
      case LogiLED.RIGHT_CONTROL: return   RIGHT_CONTROL;
      case 57416: return ARROW_UP;
      case 57421: return ARROW_RIGHT;
      case 57424: return ARROW_DOWN;
      case 57419: return ARROW_LEFT;
      case LogiLED.ARROW_LEFT: return   ARROW_LEFT;
      case LogiLED.ARROW_DOWN: return   ARROW_DOWN;
      case LogiLED.ARROW_RIGHT: return   ARROW_RIGHT;
      case LogiLED.NUM_ZERO: return   NUM_ZERO;
      case LogiLED.NUM_PERIOD: return NUM_PERIOD;
    }
    return P;
  }
}
