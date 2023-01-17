package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Unit tests for Dish class
class DishTest {
    private Dish dish;

    @BeforeEach
    public void setup() {
        dish = new Dish("Cake", "Dessert", 1, "My favourite flavor is chocolate");
    }

    @Test
    public void testConstructor() {
        assertEquals("Cake", dish.getName());
        assertEquals("Dessert", dish.getCategory());
        assertEquals(1, dish.getRank());
        assertEquals("My favourite flavor is chocolate", dish.getComment());
        dish.setComment("My favourite is strawberry");
        assertEquals("My favourite is strawberry", dish.getComment());
    }


    @Test
    public void testToString() {
        assertEquals("Dish{name='Cake', category='Dessert', " +
                "rank=1, comment='My favourite flavor is chocolate'}", dish.toString());
    }

    @Test
    public void testToJsonArray(){
        assertEquals(null, dish.toJsonArray());
    }

}