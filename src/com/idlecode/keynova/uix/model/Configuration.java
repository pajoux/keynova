package com.idlecode.keynova.uix.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Configuration {

  private List<String> compositions;

  public Configuration() {
    compositions = new ArrayList<>();
  }

  public Configuration(List<String> compositions) {
    this.compositions = new ArrayList<>(compositions);
  }

  public List<String> getCompositions() {
    return compositions;
  }

  public static Configuration load(Path fileName) throws IOException {
    byte[] bytes = Files.readAllBytes(fileName);
    String content = new String(bytes);
    JSONObject object = new JSONObject(content);
    List<String> compositions = new ArrayList<>();
    JSONArray arr = object.getJSONArray("compositions");
    for (int i = 0; i < arr.length(); ++i) {
      compositions.add(arr.getString(i));
    }
    return new Configuration(compositions);
  }

  public static void save(Configuration config, Path fileName) throws IOException {
    String content = config.toJSON().toString();
    Files.write(fileName, content.getBytes(), StandardOpenOption.CREATE,
      StandardOpenOption.TRUNCATE_EXISTING);
  }

  public JSONObject toJSON() {
    JSONObject object = new JSONObject();
    object.put("compositions", compositions);
    return object;
  }
}
