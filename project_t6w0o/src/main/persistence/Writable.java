package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

// code based on JsonReader from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Construct Writable interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

    // EFFECTS: returns this as JSON object
    JSONArray toJsonArray();
}