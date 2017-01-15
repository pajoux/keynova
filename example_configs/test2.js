// Core Classes
var KeyCode = Java.type('com.idlecode.keynova.core.KeyCode');
var KeyLocations = Java.type('com.idlecode.keynova.core.KeyLocations');
var Composition = Java.type('com.idlecode.keynova.core.Composition');
var Clock = Java.type('com.idlecode.keynova.core.Clock');
var Color = Java.type('com.idlecode.keynova.core.Color');
var RainbowColorProvider = Java.type('com.idlecode.keynova.core.RainbowColorProvider');

// Nodes
var KeyListener = Java.type('com.idlecode.keynova.nodes.KeyListener');
var Blend = Java.type('com.idlecode.keynova.nodes.Blend');
var RainbowWave = Java.type('com.idlecode.keynova.nodes.RainbowWave');
var SolidColor = Java.type('com.idlecode.keynova.nodes.SolidColor');
var RainbowKeyBurst = Java.type('com.idlecode.keynova.nodes.RainbowKeyBurst');
var ConstNode = Java.type('com.idlecode.keynova.nodes.ConstNode');

// Composition Creation
var composition = function () {
  // Some colors we will be using.
  var red = Color.rgb(1.0, 0.0, 0.0);
  var blue = Color.rgb(0.0, 0.0, 0.25);
  var yellow = Color.rgb(1.0, 1.0, 0.0);
  var purple = Color.rgb(1.0, 0.0, 1.0);
  var gold = Color.rgb(1.0, 0.6, 0.0);

  var clock = new Clock();
  var listener = new KeyListener(clock);
  var solid = new SolidColor(blue);
  //var slide = new KeyBurst(clock, listener, 500, 10, gold);
  var rainbowColorProvider = new RainbowColorProvider(50, 1.0)
  var wave = new RainbowKeyBurst(clock, listener, 800, 80, rainbowColorProvider);
  // return null;
  // asdf,/.,/.,
  // return new Composition(wave);
  var combine1 = new Blend(solid, wave);
  return new Composition(combine1);
};

// Test05ghjd12563484it5kpllppllplplplplplplplplikujuj


