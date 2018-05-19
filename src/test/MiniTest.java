package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Area;
import model.Direction;
import model.Location;
import model.entity.CompleteImageSetCalculator;
import model.entity.EntitityImpl;
import model.entity.Entity;
import model.entity.EntityType;
import model.entity.PlayerBehavior;

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
    private static final List<String> N_IMAGE = new ArrayList<>(Arrays.asList("n_sx", "n_dx", "n_stand"));
    private static final List<String> S_IMAGE = new ArrayList<>(Arrays.asList("s_sx", "s_dx", "s_stand"));
    private static final List<String> E_IMAGE = new ArrayList<>(Arrays.asList("e_sx", "e_dx", "e_stand"));
    private static final List<String> W_IMAGE = new ArrayList<>(Arrays.asList("w_sx", "w_dx", "w_stand"));
    private static final Location DEFAULT_LOC = new Location(0, 0, new Area(10, 10));

    @Test
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
            // System.out.println(e.getMessage());
        }
        try {
            w.changeIntProperty(UNCORRECT_PROPERTY, NEW_INT_PROP);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
        try {
            w.changeBooleanProperty(UNCORRECT_PROPERTY, true);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
        try {
            w.changeObjectProperty(UNCORRECT_PROPERTY, NEW_STRING_PROP);
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }

    @Test
    void completeImageSetCalculatorTest() {
        final CompleteImageSetCalculator c = new CompleteImageSetCalculator(N_IMAGE, S_IMAGE, E_IMAGE, W_IMAGE);
        assertEquals(c.getCurrentImage(Direction.S), "s_sx");
        assertEquals(c.getCurrentImage(Direction.S), "s_dx");
        c.getCurrentImage(Direction.S);
        assertEquals(c.getCurrentImage(Direction.N), "n_dx");
        assertEquals(c.getCurrentImage(Direction.NW), "n_sx");
        assertEquals(c.getCurrentImage(Direction.E), "e_dx");
        assertEquals(c.getCurrentImage(Direction.NOTHING), "e_stand");
        assertEquals(c.getCurrentImage(Direction.NOTHING), "e_stand");
        assertEquals(c.getCurrentImage(Direction.SW), "s_sx");
        assertEquals(c.getCurrentImage(Direction.NOTHING), "s_stand");
    }

    @Test
    void testPlayerBehavior() {
        final PlayerBehavior pB = new PlayerBehavior(
                new CompleteImageSetCalculator(N_IMAGE, S_IMAGE, E_IMAGE, W_IMAGE));
        final Entity p = new EntitityImpl.EntitiesBuilder().setLocation(DEFAULT_LOC).setImage("error").setBehaviour(pB)
                .with("Speed", 10.0).build();
        assertEquals(p.getImage(), "s_stand");

        // test if the entity is moving
        ((PlayerBehavior) p.getBehaviour().get()).setCurrentDirection(Direction.N);
        p.getBehaviour().get().update();
        assertEquals(p.getLocation().getY(), -10);

        // test if the entity return the correct image after the stop
        assertEquals(((PlayerBehavior) p.getBehaviour().get()).getCurrentDirection(), Direction.NOTHING);
        p.getBehaviour().get().update();
        assertEquals(p.getImage(), "n_stand");
    }

}
