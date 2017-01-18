// Core Classes
var Composition = Java.type('com.idlecode.keynova.core.Composition');
var Clock = Java.type('com.idlecode.keynova.core.Clock');
var RainbowColorProvider = Java.type('com.idlecode.keynova.colors.RainbowColorProvider');
var FadeOutAlphaProvider = Java.type('com.idlecode.keynova.colors.FadeOutAlphaProvider');

// Nodes
var KeyListener = Java.type('com.idlecode.keynova.nodes.KeyListener');
var NewKeyBurst = Java.type('com.idlecode.keynova.nodes.NewKeyBurst');

// Composition Creation
var composition = function () {

  var clock = new Clock();
  var listener = new KeyListener(clock);
  var fadeOutAlphaProvider = new FadeOutAlphaProvider(1.0, 1000);
  var rainbowColorProvider = new RainbowColorProvider(2000, 0, fadeOutAlphaProvider);
  var wave = new NewKeyBurst(clock, listener, 50, rainbowColorProvider, rainbowColorProvider);
  return new Composition(wave);
};

/*
testing this out
*/
