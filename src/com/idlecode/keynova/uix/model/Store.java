package com.idlecode.keynova.uix.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 */
public class Store {

  private ObservableList<Property<Composition>> compositions =
    FXCollections.observableArrayList();

  public ObservableList<Property<Composition>> getCompositions() {
    return compositions;
  }


  public void addComposition(Composition details) {
    compositions.add(new SimpleObjectProperty<>(details));
  }

  public void removeComposition(int index) {
    compositions.remove(index);
  }

  public void addCompositions(Collection<File> files) {
    compositions.addAll(files.stream().map(
      (file) -> new SimpleObjectProperty<>(new Composition(file.toPath()))
    ).collect(Collectors.toList()));
  }
}
