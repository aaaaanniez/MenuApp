package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import model.Event;
import model.EventLog;
import model.Menu;
import model.Dish;
import org.json.*;

// code based on JsonReader from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads menu.json from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads menu.json from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Menu read() throws IOException {
        EventLog.getInstance().logEvent(new Event("Load Menu"));
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        Menu menu = new Menu();
        parseMenu(menu, jsonArray);
        return menu;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: menu.json
    // EFFECTS: parses dish from JSON Array and adds them to menu.json
    private void parseMenu(Menu menu, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject jsonDishObject = (JSONObject) json;
            addDish(menu, jsonDishObject);
        }
    }

    // MODIFIES: menu.json
    // EFFECTS: parses dish from JSON object and adds it to menu.json
    private void addDish(Menu menu, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String category = jsonObject.getString("category");
        int rank = jsonObject.getInt("rank");
        String comment = jsonObject.getString("comment");
        menu.addDish(new Dish(name, category, rank, comment));
    }

}