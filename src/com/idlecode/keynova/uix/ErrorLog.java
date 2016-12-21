package com.idlecode.keynova.uix;

import javafx.collections.ObservableList;
import javafx.scene.control.*;

/**
 *
 */
public class ErrorLog extends ListView<Exception> {

  public ErrorLog(ObservableList<Exception> exceptions) {
    super(exceptions);

    // Custom cell rendering.
    setCellFactory(param -> new ListCell<Exception>() {
      @Override
      public void updateItem(Exception item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
          setGraphic(null);
          return;
        }
        Label firstLine = new Label();
        firstLine.getStyleClass().add("error-label");
        firstLine.setText(item.getMessage());
        setGraphic(firstLine);
      }
    });
  }

}
