// Core Classes
var KeyCode = Java.type('com.idlecode.keynova.core.KeyCode');
var Composition = Java.type('com.idlecode.keynova.core.Composition');
var Clock = Java.type('com.idlecode.keynova.core.Clock');
var Color = Java.type('com.idlecode.keynova.core.Color');

// Nodes
var Blend = Java.type('com.idlecode.keynova.nodes.Blend');
var RainbowWave = Java.type('com.idlecode.keynova.nodes.RainbowWave');
var KeyDissolve = Java.type('com.idlecode.keynova.nodes.KeyDissolve');
var KeyListener = Java.type('com.idlecode.keynova.nodes.KeyListener');
var StaticColor = Java.type('com.idlecode.keynova.nodes.StaticColor');
var ConstNode = Java.type('com.idlecode.keynova.nodes.ConstNode');

// Composition Creation
var composition = function () {
  // Some colors we will be using.
  var red = Color.rgb(1.0, 0.0, 0.0);
  var blue = Color.rgb(0.0, 0.0, 1.0);
  var yellow = Color.rgb(1.0, 1.0, 0.0);
  var purple = Color.rgb(1.0, 0.0, 1.0);
  var gold = Color.rgb(1.0, 0.6, 0.0);
  var green = Color.rgb(0.0, 1.0, 0.0);
  var transparent = Color.rgba(0.0, 0.0, 1.0, 1.0);

  var keys = new java.util.HashMap();
  keys.put(KeyCode.LEFT_SHIFT, yellow);
  keys.put(KeyCode.Q, green);
  keys.put(KeyCode.T, blue);
  keys.put(KeyCode.S, blue);
  keys.put(KeyCode.I, blue);
  keys.put(KeyCode.ONE, red);
  keys.put(KeyCode.TWO, red);
  keys.put(KeyCode.THREE, red);
  keys.put(KeyCode.FOUR, red);

  var clock = new Clock();
  var listener = new KeyListener(clock);
  var key = new StaticColor(keys);
  var dissolve = new KeyDissolve(clock, listener, 2000, transparent);
  var combine2 = new Blend(key, dissolve);

  return new Composition(combine2);
};

// Test


