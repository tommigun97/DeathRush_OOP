package tests;

import model.Area;
import model.Location;
import model.entity.Player;
import model.entity.StalkerEnemy;

import static org.junit.Assert.*;
import static model.entity.LivenessEntityType.*;
import static model.Direction.*;

/**
 * Tests for the movements of the player and enemies.
 *
 */
public final class MovementsTests {
    private static final Player P = new Player(new Location(0.50, 0.50, new Area(0.10, 0.10)), null, DEFAULT_ENEMY, E);
    private static final StalkerEnemy EN = new StalkerEnemy(new Location(0.0, 0.0, new Area(0.10, 0.10)), null,
            DEFAULT_PLAYER, P, E);

    /**
     * Test for view if StalkerEnemy follow the palyer.
     */
    @org.junit.Test
    public void stalkerEnemyMovement() {
        P.move();
        EN.move();
        System.out.println(P.getLocation().getX());
        assertEquals(EN.getCurrentDirection(), SE);
        P.setCurrentDirection(N);
        P.move();
        P.move();
        P.move();
        EN.move();
        assertTrue(EN.getCurrentDirection().equals(NE));

    }

}
