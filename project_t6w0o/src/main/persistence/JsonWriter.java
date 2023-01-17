package persistence;


import model.Menu;
import org.json.JSONArray;
import model.Event;
import model.EventLog;


import java.io.*;

// code based on JsonReader from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of menu.json to file
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        EventLog.getInstance().logEvent(new Event("Save Menu"));
        writer = new PrintWriter(new File(destination));
    }


    // MODIFIES: this
    // EFFECTS: writes JSON representation of menu to file
    public void write(Menu menu) {
        JSONArray json = menu.toJsonArray();
        saveToFile(json.toString());
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}

