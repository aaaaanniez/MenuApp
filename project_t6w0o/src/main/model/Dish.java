package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//create dish that has a name, category, rank, and comment
public class Dish implements Writable {

    private String name;
    private String category;
    private int rank;
    private String comment;


    // Effects: name of the dish is set to name; category of the dish is set to category;
    // rank of the dish is set to rank; comment of the dish is set to comment;
    public Dish(String name, String category, int rank, String comment) {
        this.name = name;
        this.category = category;
        this.rank = rank;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getRank() {
        return rank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    //EFFECTS: returns the name, category, rank, and comment of the dish
    @Override
    public String toString() {
        return
                String.format("Dish{name='%s', category='%s', rank=%d, comment='%s'}", name, category, rank, comment);
    }


    //EFFECTS: returns Dish as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("category", category);
        json.put("rank", rank);
        json.put("comment", comment);
        return json;
    }

    //EFFECTS: returns null
    @Override
    public JSONArray toJsonArray() {
        return null;
    }

}