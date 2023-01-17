package persistence;

import model.Dish;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for Json
public class JsonTest {
    protected void checkDish(String name, String category, int rank, String comment, Dish dish) {
        assertEquals(name, dish.getName());
        assertEquals(category, dish.getCategory());
        assertEquals(rank, dish.getRank());
        assertEquals(comment, dish.getComment());


    }
}