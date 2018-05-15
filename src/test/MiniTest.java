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
    private static final int INT_PROP = 20;
    private static final double DOUBLE_PROP = 21.5;
    private static final String STRING_PROP = "I'M AN ENTITY";
    private static final int NEW_INT_PROP = 23;
    private static final double NEW_DOUBLE_PROP = 21.5;
    private static final String NEW_STRING_PROP = "MY PROPERTIES ARE CHANGED";
    private static final String UNCORRECT_PROPERTY = "Uncorrect";

    @Test
    void buildingTest() {
        final Entity w = new EntitityImpl.EntitiesBuilder().with("alive", true).with("speed", INT_PROP).with("maxLife", DOUBLE_PROP)
                .setType(PLAYER).with("string", STRING_PROP).build();

        assertEquals(w.getDoubleProperty("maxLife"), DOUBLE_PROP);
        assertEquals(w.getIntegerProperty("speed"), INT_PROP);
        assertEquals(w.getObjectProperty("string"), STRING_PROP);
        assertTrue(w.getBooleanProperty("alive"));
        w.changeBooleanProperty("alive", false);
        w.changeIntProperty("speed", NEW_INT_PROP);
        w.changeDoubleProperty("maxLife", NEW_DOUBLE_PROP);
        w.changeObjectProperty("string", NEW_STRING_PROP);
        assertFalse(w.getBooleanProperty("alive"));
        assertEquals(w.getIntegerProperty("speed"), NEW_INT_PROP);
        assertEquals(w.getDoubleProperty("maxLife"), NEW_DOUBLE_PROP);

        try {
            w.changeDoubleProperty(UNCORRECT_PROPERTY, NEW_DOUBLE_PROP);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            w.changeIntProperty(UNCORRECT_PROPERTY, NEW_INT_PROP);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            w.changeBooleanProperty(UNCORRECT_PROPERTY, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            w.changeObjectProperty(UNCORRECT_PROPERTY, NEW_STRING_PROP);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
