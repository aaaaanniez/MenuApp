package persistence;


import model.Menu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for JsonReader
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Menu menu = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderOneDish() {
        JsonReader reader = new JsonReader("./data/testReaderOneDish.json");
        try {
            Menu menu = reader.read();
            assertEquals("Dish{name='Cake', category='Dessert', " +
                    "rank=1, comment='My favourite flavor is chocolate'}\n", menu.toString());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}