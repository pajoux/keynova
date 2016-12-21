package com.idlecode.keynova.uix;

import com.idlecode.keynova.uix.model.Composition;
import com.idlecode.keynova.uix.model.Store;
import javafx.beans.property.Property;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.nio.file.FileSystems;

/**
 *
 */
public class CompositionList extends ListView<Property<Composition>> {

  public CompositionList(Store store) {
    super(store.getCompositions());

    // Drag-and-drop files.
    setOnDragDropped((event) -> {
      Dragboard db = event.getDragboard();
      boolean success = false;
      if (db.hasFiles()) {
        success = true;
        store.addCompositions(db.getFiles());
      }
      event.setDropCompleted(success);
      event.consume();
    });
    setOnDragOver((event) -> {
      Dragboard db = event.getDragboard();
      if (db.hasFiles()) {
        event.acceptTransferModes(TransferMode.LINK);
      } else {
        event.consume();
      }
    });

    // Custom cell rendering.
    setCellFactory(param -> new ListCell<Property<Composition>>() {
      @Override
      public void updateItem(Property<Composition> item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
          setGraphic(null);
          return;
        }

        String file = item.getValue().getScriptPath().getFileName().toString();
        String parent = item.getValue().getScriptPath().getParent().toString() + FileSystems.getDefault().getSeparator();

        Hyperlink link = new Hyperlink("open");
        Label parentLabel = new Label(parent);
        parentLabel.setPrefWidth(200);
        parentLabel.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
        parentLabel.setId("parent");
        Label fileLabel = new Label(file);
        fileLabel.setId("file");

        link.setOnAction((event) -> {
          try {
            Runtime.getRuntime().exec("explorer.exe /select," + item.getValue().getScriptPath().toString());
          } catch (IOException e) {
            e.printStackTrace();
          }
        });


        HBox pane = new HBox(parentLabel, fileLabel, link);
        setGraphic(pane);
      }
    });
  }
}
