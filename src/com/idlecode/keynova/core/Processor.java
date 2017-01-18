package com.idlecode.keynova.core;

import com.idlecode.keynova.concurrency.SequentialContext;
import com.logitech.gaming.LogiLED;
import jdk.nashorn.api.scripting.ClassFilter;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Runs a composition on a dedicated thread.
 *
 * Use execute() to schedule things to run on that thread.
 */
public class Processor extends SequentialContext {

  private static class ScriptLoaderFilter implements ClassFilter {
    @Override
    public boolean exposeToScripts(String s) {
      return
        s.startsWith("com.idlecode.keynova.core") ||
        s.startsWith("com.idlecode.keynova.nodes") ||
        s.startsWith("com.idlecode.keynova.colors") ||
        s.startsWith("java.util");
    }
  }

  private ScheduledFuture<?> loop;
  private Composition runningComposition;
  private final ScriptEngine engine;

  private Processor() {
    super();
    LogiLED.LogiLedInit();
    LogiLED.LogiLedSaveCurrentLighting();

    NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
    engine = factory.getScriptEngine(new ScriptLoaderFilter());
  }

  public static Sequent<Processor> create() {
    Processor processor = new Processor();
    return processor.bind(processor);
  }

  public void run(Path fileName, long frequencyMs) throws Exception {
    engine.eval(new FileReader(fileName.toFile()));
    Invocable invocable = (Invocable) engine;
    Object result = invocable.invokeFunction("composition");
    if (result instanceof Composition) {
      run((Composition) result, frequencyMs);
    } else {
      throw new Exception("NOT VALID RESULT FROM JS");
    }
  }

  public void run(Composition composition, long frequencyMs) {
    stopCurrentComposition();

    runningComposition = composition;
    composition.init();

    // Setup initial overall backlighting.
    int color = composition.getBackgroundColor();
    LogiLED.LogiLedSetLighting(
      (Color.r(color) / 255) * 100,
      (Color.g(color) / 255) * 100,
      (Color.b(color) / 255) * 100
    );

    final ByteBitmap bitmap = new ByteBitmap();
    loop = executor.scheduleAtFixedRate(
      () -> {
        try {
          Bitmapable buffer = composition.process();
          LogiLED.LogiLedSetLightingFromBitmap(buffer.getBitmap(bitmap).getBytes());
        } catch (Exception e) {
          e.printStackTrace();
        }
      },
      0,
      frequencyMs,
      TimeUnit.MILLISECONDS
    );
  }

  private void stopCurrentComposition() {
    if (loop != null) {
      loop.cancel(true);
    }
    if (runningComposition != null) {
      runningComposition.close();
    }
  }

  @Override
  public void shutdown() {
    stopCurrentComposition();
    super.shutdown();
  }
}
