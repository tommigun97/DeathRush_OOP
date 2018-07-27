package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

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
import model.entity.Player;
import model.entity.PlayerBehavior;
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
        Room r = new RoomImpl(" ", 1, false, RoomType.INTERMEDIATE, new CopyOnWriteArraySet<>(), new HashSet<>());
        Entity p = E_FACTORY.createPlayer(new Pair<>(0.30, 0.50), r, Player.ANIS);
        Entity se = E_FACTORY.isaacStalkerEnemy(DEFAULT_LOC.getX(), DEFAULT_LOC.getY(), p, r);
        r.addEntity(se);
        r.addEntity(p);
        ((PlayerBehavior) p.getBehaviour().get()).shoot(Direction.S);
        r.getEntities().forEach(e -> {
            if (e.getBehaviour().isPresent()) {
                e.getBehaviour().get().update();
            }
        });
    }
}
