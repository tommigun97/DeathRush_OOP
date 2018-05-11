package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import model.entity.EntitityImpl;
import model.entity.Entity;
import static model.entity.EntityType.*;

/**
 * class for testing entities.
 *
 */
public class MiniTest {

    @Test
    void buildingTest() {
        Entity w = new EntitityImpl.EntitiesBuilder().with("alive", true).with("speed", 10).with("maxLife", 1.9)
                .setType(PLAYER).with("ciao", "mamma").build();

        assertEquals(w.getDoubleProperty("maxLife"), 1.9);
        assertEquals(w.getIntegerProperty("speed"), 10);
        assertEquals(w.getObjectProperty("ciao"), "mamma");
        assertTrue(w.getBooleanProperty("alive"));
        w.changeBooleanProperty("alive", false);
        w.changeIntProperty("speed", 100);
        w.changeDoubleProperty("maxLife", 2);
        System.out.println(w.getDoubleProperty("maxLife"));
        assertFalse(w.getBooleanProperty("alive"));
        assertEquals(w.getIntegerProperty("speed"), 100);
        assertEquals(w.getDoubleProperty("maxLife"), 2.0);
    }

}
