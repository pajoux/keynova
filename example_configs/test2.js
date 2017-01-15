// Core Classes
var KeyCode = Java.type('com.idlecode.keynova.core.KeyCode');
var KeyLocations = Java.type('com.idlecode.keynova.core.KeyLocations');
var Composition = Java.type('com.idlecode.keynova.core.Composition');
var Clock = Java.type('com.idlecode.keynova.core.Clock');
var Color = Java.type('com.idlecode.keynova.core.Color');
var StaticRainbowColorProvider = Java.type('com.idlecode.keynova.core.StaticRainbowColorProvider');
var ListColorProvider = Java.type('com.idlecode.keynova.core.ListColorProvider');
var SingleAlphaProvider = Java.type('com.idlecode.keynova.core.SingleAlphaProvider');
var FadeOutAlphaProvider = Java.type('com.idlecode.keynova.core.FadeOutAlphaProvider');
var FadeInAlphaProvider = Java.type('com.idlecode.keynova.core.FadeInAlphaProvider');

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
  var blue = Color.rgb(0.0, 0.0, 1.0);
  var yellow = Color.rgb(1.0, 1.0, 0.0);
  var purple = Color.rgb(1.0, 0.0, 1.0);
  var gold = Color.rgb(1.0, 0.6, 0.0);
  var green = Color.rgb(0.0, 1.0, 0.0);

  var clock = new Clock();
  var listener = new KeyListener(clock);
  var solid = new SolidColor(blue);
  //var slide = new KeyBurst(clock, listener, 500, 10, gold);
  var fadeInAlphaProvider = new FadeInAlphaProvider(1.0, 1000);
  var singleAlphaProvider = new SingleAlphaProvider(1.0);
  var colorList = [ red, blue, yellow, purple, gold, green ];
  var colorList2 = [ green, red, blue, yellow, purple, gold ];
  var listColorProvider2 = new ListColorProvider(colorList2, singleAlphaProvider);
  var listColorProvider = new ListColorProvider(colorList, singleAlphaProvider);
  var wave2 = new NewKeyBurst(clock, listener, 200, listColorProvider2);
  var wave = new NewKeyBurst(clock, listener, 200, listColorProvider, fadeInAlphaProvider);
  // return null;
  // asdf,/.,/.,
  // return new Composition(wave);
  var solid = new SolidColor(blue);
  var combine1 = new Blend(wave2, wave);
  return new Composition(wave);
  return new Composition(wave);
};

// Test


