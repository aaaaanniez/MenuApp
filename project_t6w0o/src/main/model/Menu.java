package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//create the menu.json
public class Menu implements Writable {

    private ArrayList<Dish> items;

    public Menu() {
        items = new ArrayList<Dish>();
    }

    //EFFECTS: add dish into the menu.json
    //MODIFIES: dish
    public void addDish(Dish dish) {
        items.add(dish);
        EventLog.getInstance().logEvent(new Event("Add dish: " + dish.toString()));
    }

    //EFFECTS: delete dish from the menu.json.
    //MODIFIES: dish
    public void removeDish(String name) {
        Dish dishToRemove = null;
        for (Dish dish : items) {
            if (dish.getName().equals(name)) {
                dishToRemove = dish;
                break;
            }
        }
        if (dishToRemove != null) {
            items.remove(dishToRemove);
            EventLog.getInstance().logEvent(new Event("Remove dish: " + dishToRemove.toString()));
        }
    }

    //EFFECTS: produces true if the dish with the same name already exists
    public boolean containDish(String name) {
        for (Dish dish : items) {
            if (dish.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: change comment
    public void changeComment(String name, String comment) {
        Dish dishToChangeComment = null;
        for (Dish dish : items) {
            if (dish.getName().equals(name)) {
                dishToChangeComment = dish;
                break;
            }
        }
        if (dishToChangeComment != null) {
            dishToChangeComment.setComment(comment);
            EventLog.getInstance().logEvent(new Event("Comment has changed for: "
                    + dishToChangeComment.toString()));
        }
    }

    //EFFECTS: returns the number of dishes currently in the menu.json.
    public int length() {
        return items.size();
    }

    //EFFECTS: returns true if the menu.json is empty, false otherwise
    public boolean isEmpty() {
        return items.isEmpty();
    }

    //EFFECTS: print event logs
    public void printEventLogs() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e);
        }
    }

    //EFFECTS: returns the menu.json with name, category, rank, and comment of each dish
    @Override
    public String toString() {
        EventLog.getInstance().logEvent(new Event("Print Menu "));
        String str = "";
        for (Dish dish : items) {
            str += dish.toString() + "\n";
        }
        return str;
    }

    //EFFECTS: returns null
    @Override
    public JSONObject toJson() {
        return null;
    }


    //EFFECTS: returns dish in this menu as a JSON array
    @Override
    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Dish t : items) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}