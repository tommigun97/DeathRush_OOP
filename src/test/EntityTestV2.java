package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import model.Area;
import model.Direction;
import model.Location;
import model.entity.BulletBehavior;
import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.EntityType;
import model.entity.Player;
import model.entity.PlayerBehavior;
import model.entity.StalkerEnemyBehavior;
import model.room.Room;
import model.room.RoomImpl;
import model.room.RoomType;
import utilities.Pair;

/**
 * test for entities.
 *
 */
public class EntityTestV2 {
    private static final List<String> N_IMAGE = new ArrayList<>(Arrays.asList("n_sx", "n_dx", "n_stand"));
    private static final List<String> S_IMAGE = new ArrayList<>(Arrays.asList("s_sx", "s_dx", "s_stand"));
    private static final List<String> E_IMAGE = new ArrayList<>(Arrays.asList("e_sx", "e_dx", "e_stand"));
    private static final List<String> W_IMAGE = new ArrayList<>(Arrays.asList("w_sx", "w_dx", "w_stand"));
    private static final CollisionSupervisor CS = new CollisionSupervisorImpl();
    private static final EntityFactory E_FACTORY = new EntityFactoryImpl(CS);
    private static final Location DEFAULT_LOC = new Location(0.50, 0.50, new Area(0.50, 0.50));

    @Test
    void testStalkerEnemyBehaviorV2() {
        final Room r = new RoomImpl(" ", 1, false, RoomType.INTERMEDIATE, new CopyOnWriteArraySet<>(), new HashSet<>());
        final Entity p = E_FACTORY.createPlayer(new Pair<>(0.30, 0.50), r, Player.ANIS);
        final Entity se = E_FACTORY.isaacStalkerEnemy(DEFAULT_LOC.getX(), DEFAULT_LOC.getY(), p, r, true);
        r.addEntity(se);
        r.addEntity(p);
        ((PlayerBehavior) p.getBehaviour().get()).shoot(Direction.S);
        r.getEntities().forEach(e -> {
            if (e.getBehaviour().isPresent()) {
                e.getBehaviour().get().update();
            }
        });
    }

    @Test
    void testCollisionBetweenEntities() {
        Room r = new RoomImpl(" ", 1, false, RoomType.INTERMEDIATE, new CopyOnWriteArraySet<>(), new HashSet<>());
        final Entity p = E_FACTORY.createPlayer(new Pair<Double, Double>(0.20, 0.50), r, Player.SIMO);
        final Entity e = E_FACTORY.isaacStalkerEnemy(0.70, 0.50, p, r, true);
        final Entity e2 = E_FACTORY.isaacStalkerEnemy(0.70, 0.50, p, r, false);
        // collision between an enemy and a bullet
        ((PlayerBehavior) p.getBehaviour().get()).shoot(Direction.E);
        IntStream.range(0, 70).forEach(i -> {
            r.getEntities().forEach(l -> {
                if (l.getType() != EntityType.ENEMY) {
                    l.getBehaviour().get().update();
                }
                CS.collisionBetweenEntities(e, r.getEntities());
            });
        });
        assertTrue(e.getIntegerProperty("Current Life") == e.getIntegerProperty("Max Life")
                - p.getIntegerProperty("Shooting Damage"));
        // collision between a player and a bullet
        ((StalkerEnemyBehavior) e.getBehaviour().get()).update();
        IntStream.range(0, 70).forEach(i -> {
            r.getEntities().forEach(l -> {
                if (l.getType() != EntityType.ENEMY) {
                    l.getBehaviour().get().update();
                }
                CS.collisionBetweenEntities(p, r.getEntities());
            });
        });
        // collision between a player and an enemy
        r.getEntities().remove(e);
        while (p.getIntegerProperty("Current Life") == p.getIntegerProperty("Max Life")) {
            e2.getBehaviour().get().update();
        }
        assertTrue(p.getIntegerProperty("Current Life") == p.getIntegerProperty("Max Life")
                - e2.getIntegerProperty("Collision Damage"));
    }
    
    @Test
    void modelTest() {
        
    }
}
