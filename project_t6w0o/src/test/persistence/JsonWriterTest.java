package persistence;

import model.Dish;
import model.Menu;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for JsonWriter
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Menu menu = new Menu();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMenu() {
        try {
            Menu menu = new Menu();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMenu.json");
            writer.open();
            writer.write(menu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMenu.json");
            menu = reader.read();
            assertEquals(menu.toString(), "");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMenu() {
        try {
            Menu menu = new Menu();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMenu.json");
            menu.addDish(new Dish("Cake", "Dessert", 1, "My favourite flavor is chocolate"));
            writer.open();
            writer.write(menu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMenu.json");
            menu = reader.read();
            assertEquals(menu.toString(), "Dish{name='Cake', category='Dessert', " +
                    "rank=1, comment='My favourite flavor is chocolate'}\n" );
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

