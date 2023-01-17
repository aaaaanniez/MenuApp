package ui;

import model.Dish;
import model.Menu;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Menu Application
public class MenuApp {

    private static final String JSON_STORE = "./data/menu.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Menu menu;
    private Scanner scanner;

    //EFFECTS: runs the Menu Application
    public MenuApp() {
        menu = new Menu();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void start() {
        while (true) {
            displayMainMenu();
            System.out.print("Please input an option: ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 1) {
                addDish();
            } else if (option == 2) {
                removeDish();
            } else if (option == 3) {
                displayMenu();
            } else if (option == 4) {
                changeComment();
            } else if (option == 5) {
                handleSave();
            } else if (option == 6) {
                handleRead();
            } else {
                break;
            }
        }
    }

    // EFFECTS: displays menu.json of options to user
    public void displayMainMenu() {
        System.out.println("1. add dish");
        System.out.println("2. remove dish");
        System.out.println("3. display all the dishes");
        System.out.println("4. change comment on existing dishes");
        System.out.println("5. save all the dishes");
        System.out.println("6. read previous saved dishes");
        System.out.println("7. exit");
    }

    // MODIFIES: this
    // EFFECTS: adds a dish to the menu.json
    public void addDish() {

        System.out.println("Please input the dish's name: ");
        String name = scanner.nextLine();

        System.out.println("Please input the dish's category: ");
        String category = scanner.nextLine();

        System.out.println("Please rank how much you like this dish: ");
        int rank = Integer.parseInt(scanner.nextLine());

        System.out.println("write a comment for this dish: ");
        String comment = scanner.nextLine();

        if (menu.containDish(name)) {
            System.out.println("The dish already exists. Please try another dish name! ");

        } else {

            menu.addDish(new Dish(name, category, rank, comment));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a dish from the menu.json
    public void removeDish() {
        System.out.println("Please input a dish name to remove ");
        String name = scanner.nextLine();

        menu.removeDish(name);
    }


    // EFFECTS: displays te existing menu.json
    public void displayMenu() {

        System.out.println(menu);
    }

    // MODIFIES: this
    // EFFECTS: changes the comment of existing dish
    public void changeComment() {
        System.out.println("Please input a dish name to change comment");
        String name = scanner.nextLine();

        System.out.println("Please input the new comment");
        String comment = scanner.nextLine();

        menu.changeComment(name, comment);
    }


    // EFFECTS: saves the menu to file
    public void handleSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(menu);
            jsonWriter.close();
            System.out.println("Saved menu to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads menu from file
    private void handleRead() {
        try {
            menu = jsonReader.read();
            System.out.println("Loaded menu from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}


