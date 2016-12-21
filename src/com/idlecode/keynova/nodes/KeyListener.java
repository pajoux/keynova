package com.idlecode.keynova.nodes;

import com.idlecode.keynova.core.Clock;
import com.idlecode.keynova.core.KeyCode;
import com.idlecode.keynova.core.Node1;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.*;

/**
 *
 */
public class KeyListener extends Node1<Long, Map<KeyCode, Long>> implements NativeKeyListener {

  private final ArrayList<KeyCode> keys = new ArrayList<>();
  private final Map<KeyCode, Long> keyMap = new HashMap<>();

  public KeyListener(Clock clock) {
    super(clock);
  }

  @Override
  protected void onInit() {
    GlobalScreen.addNativeKeyListener(this);
  }

  @Override
  protected void onStop() {
    GlobalScreen.removeNativeKeyListener(this);
  }

  @Override
  protected Optional<Map<KeyCode, Long>> process(Long t) {
    // No new events, don't return or update anything.
    if (keys.isEmpty()) {
      return Optional.empty();
    }

    // Return the updated map of keys.
    for (KeyCode key : keys) {
      keyMap.put(key, t);
    }
    keys.clear();
    return Optional.of(keyMap);
  }

  @Override
  protected Map<KeyCode, Long> defaultValue() {
    return keyMap;
  }

  @Override
  public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
//    System.out.println(nativeKeyEvent.getKeyCode() + " " + nativeKeyEvent.getKeyLocation() + " " +
//      nativeKeyEvent.getRawCode() + " ");
    keys.add(KeyCode.fromInt(nativeKeyEvent.getKeyCode()));
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
  }
}
