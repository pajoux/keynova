// Core Classes
var KeyCode = Java.type('com.idlecode.keynova.core.KeyCode');
var KeyLocations = Java.type('com.idlecode.keynova.core.KeyLocations');
var Composition = Java.type('com.idlecode.keynova.core.Composition');
var Clock = Java.type('com.idlecode.keynova.core.Clock');
var Color = Java.type('com.idlecode.keynova.core.Color');
var StaticRainbowColorProvider = Java.type('com.idlecode.keynova.core.StaticRainbowColorProvider');
var SingleAlphaProvider = Java.type('com.idlecode.keynova.core.SingleAlphaProvider');
var FadeOutAlphaProvider = Java.type('com.idlecode.keynova.core.FadeOutAlphaProvider');

// Nodes
var KeyListener = Java.type('com.idlecode.keynova.nodes.KeyListener');
var Blend = Java.type('com.idlecode.keynova.nodes.Blend');
var RainbowWave = Java.type('com.idlecode.keynova.nodes.RainbowWave');
var SolidColor = Java.type('com.idlecode.keynova.nodes.SolidColor');
var NewKeyBurst = Java.type('com.idlecode.keynova.nodes.NewKeyBurst');
var ConstNode = Java.type('com.idlecode.keynova.nodes.ConstNode');
var RainbowWave = Java.type('com.idlecode.keynova.nodes.RainbowWave');

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
  var singleAlphaProvider = new FadeOutAlphaProvider(1.0, 1000);
  var staticRainbowColorProvider = new StaticRainbowColorProvider(50, 1.0, singleAlphaProvider);
  var wave = new NewKeyBurst(clock, listener, 200, staticRainbowColorProvider);
  // return null;
  // asdf,/.,/.,
  // return new Composition(wave);
  var solid = new RainbowWave(clock, 6000, 0.50);
  var combine1 = new Blend(solid, wave);
  return new Composition(combine1);
};

// Test


