package com.idlecode.keynova;

import com.idlecode.keynova.core.Processor;
import com.idlecode.keynova.uix.CompositionList;
import com.idlecode.keynova.uix.ErrorLog;
import com.idlecode.keynova.uix.model.Configuration;
import com.idlecode.keynova.uix.model.Store;
import com.logitech.gaming.LogiLED;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class KeyNova extends Application {

  private Path configFileName = Paths.get(System.getProperty("user.home"), "keynova");
  private ExecutorService threadPool = Executors.newCachedThreadPool();
  private Configuration config = new Configuration();
  private Processor.Sequent<Processor> processor = Processor.create();
  private FileWatcher fileWatcher;
  private ObservableList<Exception> exceptions = FXCollections.observableArrayList();
  private Store store = new Store();
  private Stage errorStage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Initialize the key-listeners. Have events triggered on the processor thread.
    GlobalScreen.registerNativeHook();
    GlobalScreen.setEventDispatcher(processor.getExecutor());

    // Initialize the fileWatcher.
    fileWatcher = new FileWatcher();
    fileWatcher.poll(threadPool);

    // Bind the configuration to changes to the store (save configuration on changes).
    store.getCompositions().addListener(
      (ListChangeListener.Change<? extends Property<com.idlecode.keynova.uix.model.Composition>> change) -> {
        config.getCompositions().clear();
        config.getCompositions().addAll(store.getCompositions().stream().map(
          (comp) -> comp.getValue().getScriptPath().toString()
        ).collect(Collectors.toList()));
        threadPool.submit(() -> {
          try {
            Configuration.save(config, configFileName);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
      }
    );

    CompositionList listView = new CompositionList(store);
    listView.getSelectionModel().selectedItemProperty().addListener((ob, oldValue, newValue) -> {
      // When we select a new composition file, do the following:
      //  1. Listen to changes for the file.
      //  2. Run the composition.
      Path fileName = newValue.getValue().getScriptPath();
      try {
        fileWatcher.watch(fileName, this::runComposition);
      } catch (IOException e) {
        e.printStackTrace();
      }
      runComposition(fileName);
    });

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Composition File");
    fileChooser.getExtensionFilters().add(
      new FileChooser.ExtensionFilter("Javascript files (*.js)", "*.js")
    );

    Button add = new Button("Add");
    add.setOnAction((event) -> {
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        store.addCompositions(Collections.singleton(file));
      }
    });
    Button remove = new Button("Remove");
    remove.setOnAction((event) -> {
      store.removeComposition(listView.getSelectionModel().getSelectedIndex());
    });
    Button errorsButton = new Button("Errors");
    errorsButton.setOnAction((event) -> {
      maybeShowErrorStage();
    });
    exceptions.addListener(
      (ListChangeListener.Change<? extends Exception> change) -> {
        if (exceptions.isEmpty()) {
          errorsButton.setId(null);
        } else {
          errorsButton.setId("errors-btn");
        }
      }
    );

    HBox menu = new HBox(add, remove, errorsButton);
    menu.setId("menu");

    BorderPane container = new BorderPane();
    container.setCenter(listView);
    container.setBottom(menu);

    // Setup the Scene.
    Scene scene = new Scene(container, 500, 400);
    scene.getStylesheets().add(KeyNova.class.getResource("style.css").toExternalForm());
    primaryStage.setTitle("KeyNova");
    primaryStage.setScene(scene);
    primaryStage.setOnCloseRequest((e) -> close());
    primaryStage.show();


    // Load the initial configuration.
    try {
      config = Configuration.load(configFileName);
      store.getCompositions().setAll(
        config.getCompositions().stream().map((file) -> {
          com.idlecode.keynova.uix.model.Composition comp = new com.idlecode.keynova.uix.model.Composition(
            Paths.get(file)
          );
          return new SimpleObjectProperty<>(comp);
        }).collect(Collectors.toList())
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void maybeShowErrorStage() {
    if (errorStage == null) {
      // Exception list with a clear button.
      Button clear = new Button("Clear");
      clear.setOnAction((event) -> {
        exceptions.clear();
      });
      HBox menu = new HBox(clear);
      menu.setId("menu");
      BorderPane pane = new BorderPane();
      pane.setCenter(new ErrorLog(exceptions));
      pane.setBottom(menu);

      // Error window.
      Scene errorScene = new Scene(pane, 500, 400);
      errorScene.getStylesheets().add(KeyNova.class.getResource("style.css").toExternalForm());
      errorStage = new Stage();
      errorStage.setTitle("Errors");
      errorStage.setScene(errorScene);
      errorStage.show();
    }
    errorStage.show();
    errorStage.requestFocus();
  }

  private void runComposition(Path fileName) {
    processor.bind(p -> {
      try {
        p.run(fileName, 30);
      } catch (Exception e) {
        Platform.runLater(() -> {
          exceptions.add(0, e);
        });
      }
    });
  }

  private void close() {
    try {
      GlobalScreen.unregisterNativeHook();
      LogiLED.LogiLedRestoreLighting();
      LogiLED.LogiLedShutdown();
      threadPool.shutdownNow();
      processor.bind(Processor::shutdown);
    } catch (NativeHookException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws NativeHookException, IOException {
    // Turn off logging of GlobalScreen library.
    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    logger.setLevel(Level.OFF);
    launch(args);
  }
}
