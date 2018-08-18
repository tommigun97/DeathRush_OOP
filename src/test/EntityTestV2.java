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
import model.GameStatus;
import model.Location;
import model.Model;
import model.ModelImpl;
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
    private static final CollisionSupervisor CS = new CollisionSupervisorImpl();
    private static final EntityFactory E_FACTORY = new EntityFactoryImpl(CS);
    private static final Location DEFAULT_LOC = new Location(0.50, 0.50, new Area(0.50, 0.50));

    /*
     * @Test void testStalkerEnemyBehaviorV2() { final Room r = new RoomImpl(" ", 1,
     * false, RoomType.INTERMEDIATE, new CopyOnWriteArraySet<>(), new HashSet<>(),
     * true); final Entity p = E_FACTORY.createPlayer(new Pair<>(0.30, 0.50),
     * Player.ANIS); ((PlayerBehavior) p.getBehaviour().get()).setCurrentRoom(r);
     * final Entity se = E_FACTORY.stalkerSpiritEnemy(DEFAULT_LOC.getX(),
     * DEFAULT_LOC.getY(), p, r, true); r.addEntity(se); r.addEntity(p);
     * ((PlayerBehavior) p.getBehaviour().get()).shoot(Direction.S);
     * r.getEntities().forEach(e -> { if (e.getBehaviour().isPresent()) {
     * e.getBehaviour().get().update(); } }); }
     */

/*
    @Test
    void modelTest() {
        // player dead
        List<Direction> shoots = new ArrayList<>();
        ModelImpl m = new ModelImpl();
        m.start(Player.KASO);
        final Entity e = E_FACTORY.stalkerSpiritEnemy(0.70, 0.50, m.getPlayer(), m.getCurrentRoom(), true);
        m.getCurrentRoom().addEntity(e);
        shoots.add(Direction.E);
        m.update(Direction.NOTHING, shoots);
        assertTrue(m.getCurrentRoom().getEntities().stream().count() == 2);
        shoots.clear();
        IntStream.range(0, 40000000).forEach(i -> {
            m.update(Direction.NOTHING, shoots);
        });
        assertTrue(m.getPlayerLife() <= 0);
        assertTrue(m.getGameStatus() == GameStatus.Over);
        assertTrue(m.getPlayer().getLocation().getX() >= 0.05);
        assertTrue(m.getPlayer().getIntegerProperty("Current Life") < m.getPlayer().getIntegerProperty("Max Life"));
        // player win and change room
        final ModelImpl m2 = new ModelImpl();
        m2.start(Player.SIMO);
        final Entity e2 = E_FACTORY.stalkerSpiritEnemy(0.80, 0.50, m.getPlayer(), m.getCurrentRoom(), false);
        m2.getCurrentRoom().addEntity(e2);
        shoots.clear();
        IntStream.range(0, 200000).forEach(i -> {
            shoots.add(Direction.E);
            m2.update(Direction.W, shoots);
            System.out.println(i);
        });
        assertTrue(m2.getCurrentRoom().isComplited());
        final Room prec = m2.getCurrentRoom();
        shoots.clear();

        // si muove verso la porta e cambia stanza
        IntStream.range(0, 20000).forEach(i -> {
            m2.update(Direction.E, shoots);
            System.out.println(i + " player " + m2.getPlayer().getLocation());
        });
        assertTrue(prec != m2.getCurrentRoom());
        m2.getCurrentRoom().getDoor().forEach(d -> System.out.println("porta 1 " + d.getLocation()));
    }*/

}
