package com.idlecode.keynova.core;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Created by Taylor on 1/13/2017.
 */
public class KeyLocations {
    private static List<KeyCode> rowZero = Arrays.asList(
            KeyCode.ESC,
            KeyCode.F1,
            KeyCode.F2,
            KeyCode.F3,
            KeyCode.F4,
            KeyCode.F5,
            KeyCode.F6,
            KeyCode.F7,
            KeyCode.F8,
            KeyCode.F9,
            KeyCode.F10,
            KeyCode.F11,
            KeyCode.F12,
            KeyCode.PRINT_SCREEN,
            KeyCode.SCROLL_LOCK,
            KeyCode.PAUSE_BREAK,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NULL
    );
    private static List<KeyCode> rowOne = Arrays.asList(
            KeyCode.TILDE,
            KeyCode.ONE,
            KeyCode.TWO,
            KeyCode.THREE,
            KeyCode.FOUR,
            KeyCode.FIVE,
            KeyCode.SIX,
            KeyCode.SEVEN,
            KeyCode.EIGHT,
            KeyCode.NINE,
            KeyCode.ZERO,
            KeyCode.MINUS,
            KeyCode.EQUALS,
            KeyCode.BACKSPACE,
            KeyCode.INSERT,
            KeyCode.HOME,
            KeyCode.PAGE_UP,
            KeyCode.NUM_LOCK,
            KeyCode.NUM_SLASH,
            KeyCode.NUM_ASTERISK,
            KeyCode.NUM_MINUS
    );
    private static List<KeyCode> rowTwo = Arrays.asList(
            KeyCode.TAB,
            KeyCode.Q,
            KeyCode.W,
            KeyCode.E,
            KeyCode.R,
            KeyCode.T,
            KeyCode.Y,
            KeyCode.U,
            KeyCode.I,
            KeyCode.O,
            KeyCode.P,
            KeyCode.OPEN_BRACKET,
            KeyCode.CLOSE_BRACKET,
            KeyCode.BACKSLASH,
            KeyCode.KEYBOARD_DELETE,
            KeyCode.END,
            KeyCode.PAGE_DOWN,
            KeyCode.NUM_SEVEN,
            KeyCode.NUM_EIGHT,
            KeyCode.NUM_NINE,
            KeyCode.NUM_PLUS
    );
    private static List<KeyCode> rowThree = Arrays.asList(
            KeyCode.CAPS_LOCK,
            KeyCode.A,
            KeyCode.S,
            KeyCode.D,
            KeyCode.F,
            KeyCode.G,
            KeyCode.H,
            KeyCode.J,
            KeyCode.K,
            KeyCode.L,
            KeyCode.SEMICOLON,
            KeyCode.APOSTROPHE,
            KeyCode.NULL,
            KeyCode.ENTER,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NUM_FOUR,
            KeyCode.NUM_FIVE,
            KeyCode.NUM_SIX,
            KeyCode.NULL
    );
    private static List<KeyCode> rowFour = Arrays.asList(
            KeyCode.LEFT_SHIFT,
            KeyCode.NULL,
            KeyCode.Z,
            KeyCode.X,
            KeyCode.C,
            KeyCode.V,
            KeyCode.B,
            KeyCode.N,
            KeyCode.M,
            KeyCode.COMMA,
            KeyCode.PERIOD,
            KeyCode.FORWARD_SLASH,
            KeyCode.NULL,
            KeyCode.RIGHT_SHIFT,
            KeyCode.NULL,
            KeyCode.ARROW_UP,
            KeyCode.NULL,
            KeyCode.NUM_ONE,
            KeyCode.NUM_TWO,
            KeyCode.NUM_THREE,
            KeyCode.NUM_ENTER
    );
    private static List<KeyCode> rowFive = Arrays.asList(
            KeyCode.LEFT_CONTROL,
            KeyCode.LEFT_WINDOWS,
            KeyCode.LEFT_ALT,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.SPACE,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.NULL,
            KeyCode.RIGHT_ALT,
            KeyCode.RIGHT_WINDOWS,
            KeyCode.APPLICATION_SELECT,
            KeyCode.RIGHT_CONTROL,
            KeyCode.ARROW_LEFT,
            KeyCode.ARROW_DOWN,
            KeyCode.ARROW_RIGHT,
            KeyCode.NUM_ZERO,
            KeyCode.NUM_PERIOD,
            KeyCode.NULL
    );
    public static List<List<KeyCode>> keyLocations = Arrays.asList(
            rowZero,
            rowOne,
            rowTwo,
            rowThree,
            rowFour,
            rowFive
    );
    public static List<KeyCode> getTopKeys(KeyCode keyCode) {
        List<KeyCode> listOfLeftKeys = new ArrayList();
        if (keyCode != KeyCode.NULL) {
            int currentCol = keyCode.getCol();
            int currentRow = keyCode.getRow() - 1;
            if (currentRow >= 0) {
                KeyCode currentKeyCode = keyLocations.get(currentRow).get(currentCol);
                while (currentKeyCode.getInt() == KeyCode.NULL.getInt() && currentRow > 0) {
                    currentRow--;
                    currentKeyCode = keyLocations.get(currentRow).get(currentCol);
                }
                if (currentKeyCode.getInt() != KeyCode.NULL.getInt()) {
                    listOfLeftKeys.add(currentKeyCode);
                }
            }
        }
        return listOfLeftKeys;
    }
    public static List<KeyCode> getBottomKeys(KeyCode keyCode) {
        List<KeyCode> listOfLeftKeys = new ArrayList();
        if (keyCode != KeyCode.NULL) {
            int currentCol = keyCode.getCol();
            int currentRow = keyCode.getRow() + 1;
            if (currentRow <= 5) {
                KeyCode currentKeyCode = keyLocations.get(currentRow).get(currentCol);
                while (currentKeyCode.getInt() == KeyCode.NULL.getInt() && currentRow < 5) {
                    currentRow++;
                    currentKeyCode = keyLocations.get(currentRow).get(currentCol);
                }
                if (currentKeyCode.getInt() != KeyCode.NULL.getInt()) {
                    listOfLeftKeys.add(currentKeyCode);
                }
            }
        }
        return listOfLeftKeys;
    }
    public static List<KeyCode> getLeftKeys(KeyCode keyCode) {
        List<KeyCode> listOfLeftKeys = new ArrayList();
        if (keyCode != KeyCode.NULL) {
            int currentCol = keyCode.getCol() - 1;
            int currentRow = keyCode.getRow();
            if (currentCol >= 0) {
                KeyCode currentKeyCode = keyLocations.get(currentRow).get(currentCol);
                while (currentKeyCode.getInt() == KeyCode.NULL.getInt() && currentCol > 0) {
                    currentCol--;
                    currentKeyCode = keyLocations.get(currentRow).get(currentCol);
                }
                if (currentKeyCode.getInt() != KeyCode.NULL.getInt()) {
                    listOfLeftKeys.add(currentKeyCode);
                }
            }
        }
        return listOfLeftKeys;
    }
    public static List<KeyCode> getRightKeys(KeyCode keyCode) {
        List<KeyCode> listOfLeftKeys = new ArrayList();
        if (keyCode != KeyCode.NULL) {
            int currentCol = keyCode.getCol() + 1;
            int currentRow = keyCode.getRow();
            if (currentCol <= 20) {
                KeyCode currentKeyCode = keyLocations.get(currentRow).get(currentCol);
                while (currentKeyCode.getInt() == KeyCode.NULL.getInt() && currentCol < 20) {
                    currentCol++;
                    currentKeyCode = keyLocations.get(currentRow).get(currentCol);
                }
                if (currentKeyCode.getInt() != KeyCode.NULL.getInt()) {
                    listOfLeftKeys.add(currentKeyCode);
                }
            }
        }
        return listOfLeftKeys;
    }
}
