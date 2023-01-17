package ui;

import model.Dish;
import model.Menu;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//create the UI of the app
public class MenuGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/menu.json";
    private Menu menu;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextArea mainJta;
    private JTextArea nameJta;
    private JTextArea categoryJta;
    private JTextArea rankJta;
    private JTextArea commentJta;


    //constructs the MenuGUI
    public MenuGUI() {
        menu = new Menu();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initJTextArea();
        initButtons();
        this.pack();
        this.setVisible(true);
    }


    ///EFFECTS: create dish items to enter
    public void initJTextArea() {
        mainJta = new JTextArea();;
        nameJta = new JTextArea();
        categoryJta = new JTextArea();
        rankJta = new JTextArea();
        commentJta = new JTextArea();
        mainJta.setPreferredSize(new Dimension(400, 700));
        mainJta.setEnabled(false);
        getContentPane().add(mainJta);
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        Panel panel = new Panel();
        addJTextAreaToPanel(panel, nameJta, "Name:", 20);
        addJTextAreaToPanel(panel, categoryJta, "Category:", 20);
        addJTextAreaToPanel(panel, rankJta, "Rank:", 20);
        addJTextAreaToPanel(panel, commentJta, "Comment:", 20);
        this.getContentPane().add(panel);
    }

    //EFFECTS: create panel
    private void addJTextAreaToPanel(Panel panel, JTextArea text, String labelName, int width) {
        JLabel label = new JLabel(labelName);
        panel.add(label);
        text.setColumns(width);
        panel.add(text);
    }

    //EFFECTS: create buttons
    private void initButtons() {
        Panel panel = new Panel();
        addJButtonToPanel(panel,"Create");
        addJButtonToPanel(panel,"All");
        addJButtonToPanel(panel,"Save");
        addJButtonToPanel(panel,"Load");
        addJButtonToPanel(panel,"Delete");
        addJButtonToPanel(panel,"Change Comment");
        addJButtonToPanel(panel,"Quit");
        this.getContentPane().add(panel);
    }

    //EFFECTS: add panel buttons
    private void addJButtonToPanel(Panel panel, String btnName) {
        JButton button = new JButton(btnName);
        button.setName(btnName);
        button.addActionListener(this);
        panel.add(button);
    }


    //EFFECTS: create mouse event when different buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String btnName = button.getName();
        //System.out.println("btnName: " + btnName);
        if (btnName.equals("Create")) {
            addDish();
        } else if (btnName.equals("All")) {
            mainJta.setText(menu.toString());
        } else if (btnName.equals("Save")) {
            handleSave();
        } else if (btnName.equals("Load")) {
            handleRead();
        } else if (btnName.equals("Delete")) {
            removeDish();
        } else if (btnName.equals("Change Comment")) {
            changeComment();
        } else if (btnName.equals("Quit")) {
            menu.printEventLogs();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    //EFFECTS: add dish to the text area
    //Picture URL: https://in.pinterest.com/pin/679058450042237241/
    public void addDish() {
        String name = nameJta.getText();
        String category = categoryJta.getText();
        int rank = 0;
        try {
            rank = Integer.parseInt(rankJta.getText());
        } catch (NumberFormatException e) {
            mainJta.setText("rank should be Integer");
            return;
        }
        String comment = commentJta.getText();
        if (name.equals("")) {
            mainJta.setText("Name should not be empty");
            return;
        } else if (menu.containDish(name)) {
            mainJta.setText(name + " already exists");
            return;
        }
        menu.addDish(new Dish(name, category, rank, comment));
        clearJTextAreas();
        mainJta.setText(menu.toString());
        JLabel label = new JLabel(new ImageIcon("./data/restaurant.jpg"));
        JOptionPane.showMessageDialog(null, label, "Added Successfully to Restaurant",
                JOptionPane.PLAIN_MESSAGE, null);
    }

    //EFFECTS: remove existing dishes
    public void removeDish() {
        System.out.println("Please input a dish name to remove");
        String name = nameJta.getText();
        if (name.equals("")) {
            mainJta.setText("Name should not be empty");
            return;
        } else if (!menu.containDish(name)) {
            mainJta.setText(name + " does not exists");
            return;
        }
        menu.removeDish(name);
        clearJTextAreas();
        mainJta.setText(menu.toString());
        popSuccessRemoveImg();
    }

    //EFFECTS: change the comment of an existing dish
    public void changeComment() {
        System.out.println("Please input a dish name to change comment");
        String name = nameJta.getText();
        String comment = commentJta.getText();
        if (name.equals("")) {
            mainJta.setText("Name should not be empty");
            return;
        } else if (!menu.containDish(name)) {
            mainJta.setText(name + " does not exists");
            return;
        } else if (comment.equals("")) {
            mainJta.setText("New comment should not be empty");
            return;
        }
        menu.changeComment(name, comment);
        clearJTextAreas();
        mainJta.setText(menu.toString());
    }


    //EFFECTS: pop image when removing dish
    private void popSuccessRemoveImg() {
        JLabel label = new JLabel(new ImageIcon("./data/tobs.jpg"));
        JOptionPane.showMessageDialog(null, label, "Removed Successfully", JOptionPane.PLAIN_MESSAGE,
                null);
    }

    //EFFECTS: clear the text area
    private void clearJTextAreas() {
        mainJta.setText("");
        nameJta.setText("");
        categoryJta.setText("");
        rankJta.setText("");
        commentJta.setText("");
    }

    //EFFECTS:save menu
    public void handleSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(menu);
            jsonWriter.close();
            System.out.println("Write to file: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS:read menu
    private void handleRead() {
        try {
            menu = jsonReader.read();
            System.out.println("Loaded menu from " + JSON_STORE);
            clearJTextAreas();
            mainJta.setText(menu.toString());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
