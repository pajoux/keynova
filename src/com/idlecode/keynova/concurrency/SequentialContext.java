package com.idlecode.keynova.concurrency;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 *
 */
public class SequentialContext {

  public interface Fun<V> {
    void apply(V value);
  }

  protected final ScheduledThreadPoolExecutor executor;

  public SequentialContext() {
    executor = new ScheduledThreadPoolExecutor(1);
  }

  public class Sequent<V> {
    private V value;
    Sequent(V value) {
      this.value = value;
    }
//
//    public <O> ScheduledFuture<O> bind(Function<V, O> fun) {
//      return executor.schedule(() -> fun.apply(value), 0, TimeUnit.SECONDS);
//    }

    public ScheduledFuture bind(Fun<V> fun) {
      return executor.schedule(() -> fun.apply(value), 0, TimeUnit.SECONDS);
    }

    public ExecutorService getExecutor() {
      return executor;
    }
  }

  public <V> Sequent<V> bind(V value) {
    return new Sequent(value);
  }

  public <O> ScheduledFuture<O> execute(Callable<O> fun) {
    return executor.schedule(fun, 0, TimeUnit.SECONDS);
  }

  public ScheduledThreadPoolExecutor getExecutor() {
    return executor;
  }

  public void shutdown() {
    executor.shutdownNow();
  }
}
