package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import model.entity.CompleteImageSetCalculator;
import model.entity.EntitityImpl;
import model.entity.Entity;
import model.entity.EntityType;

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
    private static final String[] N_IMAGE = { "n_sx", "n_dx", "n_stand" };
    private static final String[] S_IMAGE = { "s_sx", "s_dx", "s_stand" };
    private static final String[] E_IMAGE = { "e_sx", "e_dx", "e_stand" };
    private static final String[] W_IMAGE = { "w_sx", "w_dx", "w_stand" };

/*    @Test
    void buildingTest() {
        final Entity w = new EntitityImpl.EntitiesBuilder().with("alive", true).with("speed", INT_PROP)
                .with("maxLife", DOUBLE_PROP).setType(EntityType.PLAYER).with("string", STRING_PROP).build();

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
    }*/

    @Test
    void completeImageSetCalculatorTest() {
        CompleteImageSetCalculator c = new CompleteImageSetCalculator(Arrays.asList(N_IMAGE), Arrays.asList(S_IMAGE),
                Arrays.asList(E_IMAGE), Arrays.asList(W_IMAGE));
        System.out.println(c.getCurrentImage(Optional.empty()));
        assertEquals(c.getCurrentImage(Optional.empty()), "s_stand");
    }

}
