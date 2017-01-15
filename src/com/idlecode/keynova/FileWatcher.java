package com.idlecode.keynova;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class FileWatcher {

  public interface Callback {
    void apply(Path filename);
  }

  private final WatchService watcher;
  private ConcurrentHashMap<String, Callback> callbacks = new ConcurrentHashMap<>();

  public FileWatcher() throws IOException {
    watcher = FileSystems.getDefault().newWatchService();
  }

  public void watch(Path filename, Callback callback) throws IOException {
    Path dir = filename.toAbsolutePath().getParent();
    String key = filename.toAbsolutePath().toString();
    if (callbacks.get(key) == null) {
      callbacks.put(key, callback);
      dir.register(
        watcher,
        StandardWatchEventKinds.ENTRY_MODIFY
      );
    }
  }

  public void unwatch(Path filename) {
    callbacks.remove(filename.toAbsolutePath().toString());
  }

  public Future<?> poll(ExecutorService executorService) {
    return executorService.submit(() -> {
      while (!Thread.interrupted()) {
        WatchKey key;
        try {
          key = watcher.poll(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ClosedWatchServiceException e) {
          break;
        }
        if (key == null) {
          continue;
        }
        for (WatchEvent<?> event: key.pollEvents()) {
          WatchEvent.Kind<?> kind = event.kind();
          if (kind == StandardWatchEventKinds.OVERFLOW) {
            continue;
          }
          WatchEvent<Path> ev = (WatchEvent<Path>)event;
          Path dir = (Path)key.watchable();
          Path filename = dir.resolve(ev.context()).toAbsolutePath();
          Callback callback = callbacks.get(filename.toString());
          if (callback != null) {
            callback.apply(filename);
          }
        }
        key.reset();
      }
    });

  }
}
