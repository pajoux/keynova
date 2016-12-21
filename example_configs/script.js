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

  var keys = new java.util.HashMap();
  // Q, W, E and A are colored red
  keys.put(KeyCode.Q, red);
  keys.put(KeyCode.W, red);
  keys.put(KeyCode.E, red);
  keys.put(KeyCode.A, red);
  // B, G, V are colored blue
  keys.put(KeyCode.B, blue);
  keys.put(KeyCode.G, blue);
  keys.put(KeyCode.V, blue);
  // R is special color
  keys.put(KeyCode.R, yellow);
  // keys.put(KeyCode.H, gold);

  var clock = new Clock();
  var listener = new KeyListener(clock);
  var key = new StaticColor(keys);
  var dissolve = new KeyDissolve(clock, listener, 2000, purple);

  var wave = new RainbowWave(clock, 6000, 0.7);
  var combine1 = new Blend(wave, key);
  var combine2 = new Blend(combine1, dissolve);
  return new Composition(combine2);
};

// Test


