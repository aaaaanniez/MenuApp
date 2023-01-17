package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for SongList class
public class MenuTest {
    private Dish d1;
    private Dish d2;
    private Dish d3;
    private Dish d4;
    private Menu menu;

    @BeforeEach
    public void setup() {
        d1 = new Dish("Cake", "Dessert", 1, "My favourite flavor is chocolate");
        d2 = new Dish("Peking Duck", "Entree", 2, "Chinese dish");
        // d3 = new Dish("Onion Soup", "Soup", 3, "French Dish");
        // d4 = new Dish("Pasta", "Entree", 4, "Italian Soup");
        menu = new Menu();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, menu.length());
        assertEquals("Cake", d1.getName());
        assertEquals("Dessert", d1.getCategory());
        assertEquals(1, d1.getRank());
        assertEquals("My favourite flavor is chocolate", d1.getComment());
        d1.setComment("My favourite is strawberry");
        assertEquals("My favourite is strawberry", d1.getComment());
    }

    @Test
    public void testAddDish() {
        menu.addDish(d1);
        assertEquals(1, menu.length());
    }

    @Test
    public void testRemoveDish(){
        menu.addDish(d1);
        menu.addDish(d2);
        menu.removeDish("hello");
        assertEquals("Dish{name='Cake', category='Dessert', rank=1, comment='My favourite flavor is chocolate'}"
                        +"\n" + "Dish{name='Peking Duck', category='Entree', rank=2, comment='Chinese dish'}"  +"\n",
                menu.toString());
        menu.removeDish("Cake");
        assertEquals(1, menu.length());
        assertEquals("Dish{name='Peking Duck', category='Entree', rank=2, comment='Chinese dish'}"  +"\n",
                menu.toString());

    }

    @Test
    public void testIsEmpty() {
        menu.addDish(d1);
        assertEquals(false, menu.isEmpty());
        menu.removeDish("Cake");
        assertEquals(true, menu.isEmpty());
    }

    @Test
    public void testToString(){
        menu.addDish(d1);
        menu.addDish(d2);
        assertEquals("Dish{name='Cake', category='Dessert', rank=1, comment='My favourite flavor is chocolate'}"
                        +"\n" + "Dish{name='Peking Duck', category='Entree', rank=2, comment='Chinese dish'}"  +"\n",
                menu.toString());
    }


    @Test
    public void testContainDish(){
        menu.addDish(d1);
        assertEquals(true, menu.containDish("Cake"));
        assertEquals(false, menu.containDish("Pasta"));
    }

    @Test
    public void testPrintEventLogs() {
        String expected = "";
        menu.printEventLogs();
    }

    @Test
    public void testChangeComment(){
        menu.addDish(d1);
        assertEquals("My favourite flavor is chocolate", d1.getComment());
        menu.changeComment("Marshmallow", "I like it" );
        assertEquals("My favourite flavor is chocolate", d1.getComment());
        menu.changeComment("Cake", "I like it" );
        assertEquals("I like it", d1.getComment());
    }

    @Test
    public void testToJsonArray(){
        assertEquals(null, menu.toJson());
    }


}
