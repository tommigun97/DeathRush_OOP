package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import model.ModelImpl;
import model.entity.BulletBehavior;
import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.CompleteImageSetCalculator;
import model.entity.EntityImpl;
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

//CHECKSTYLE: MagicNumber OFF
/**
 * class for testing entities.
 *
 */
public class EntityTestV1 {
    private static final int INT_PROP = 20;
    private static final double DOUBLE_PROP = 21.5;
    private static final String STRING_PROP = "I'M AN ENTITY";
    private static final int NEW_INT_PROP = 23;
    private static final double NEW_DOUBLE_PROP = 21.5;
    private static final String NEW_STRING_PROP = "MY PROPERTIES ARE CHANGED";
    private static final String UNCORRECT_PROPERTY = "Uncorrect";
    private static final String STAND = "stand";
    private static final List<String> N_IMAGE = new ArrayList<>(Arrays.asList("n_sx", "n_dx", "n_stand"));
    private static final List<String> S_IMAGE = new ArrayList<>(Arrays.asList("s_sx", "s_dx", "s_stand"));
    private static final List<String> E_IMAGE = new ArrayList<>(Arrays.asList("e_sx", "e_dx", "e_stand"));
    private static final List<String> W_IMAGE = new ArrayList<>(Arrays.asList("w_sx", "w_dx", "w_stand"));
    private static final Location DEFAULT_LOC = new Location(0.50, 0.50, new Area(0.50, 0.50));
    private static final CollisionSupervisor CS = new CollisionSupervisorImpl();
    private static final Room DEFAULT_ROOM = new RoomImpl(" ", 0, RoomType.INTERMEDIATE, new HashSet<Entity>(),
            new HashSet<Entity>(), true);
    private static final EntityFactory E_FACTORY = new EntityFactoryImpl(CS);

    /**
     * test for build entities.
     */
    @Test
    public void buildingTest() {
        final Entity w = new EntityImpl.EntitiesBuilder().with("alive", true).with("speed", INT_PROP)
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
    }

    /**
     * test for PlayerBehavior.
     */
    @Test
    public void testPlayerBehavior() {
        final PlayerBehavior pB = new PlayerBehavior(
                new CompleteImageSetCalculator(N_IMAGE, S_IMAGE, E_IMAGE, W_IMAGE, STAND), CS, E_FACTORY);
        pB.setCurrentRoom(DEFAULT_ROOM);
        // test for check if the behavior check the necessary properties of the player
        try {
            new EntityImpl.EntitiesBuilder().with("Max Life", 10).with("Current Life", 10).setBehaviour(pB).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            new EntityImpl.EntitiesBuilder().with("Speed", 10.0).setBehaviour(pB).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        final Entity p = new EntityImpl.EntitiesBuilder().setLocation(DEFAULT_LOC).setImage("error").setBehaviour(pB)
                .with("Speed", 0.2).with("Max Life", 10).with("Current Life", 10).with("Shoot Frequency", (long) 10)
                .with("Shooting Damage", 10).with("Bullet Speed", 10.0).build();
        assertEquals(p.getImage(), STAND);

        // test if the entity return the correct image after the stop
        assertEquals(((PlayerBehavior) p.getBehaviour().get()).getCurrentDirection(), Direction.NOTHING);
        p.getBehaviour().get().update();
        assertEquals(p.getImage(), STAND);

        // test for check that the entity don't come out from the bound
        p.changeDoubleProperty("Speed", 0.04);
        // north Bound
        p.setLocation(new Location(0.50, 0.50, new Area(0.50, 0.50)));
        for (int i = 0; i < 100; i++) {
            ((PlayerBehavior) p.getBehaviour().get()).setCurrentDirection(Direction.N);
            p.getBehaviour().get().update();
        }
        // south Bound
        p.setLocation(new Location(0.50, 0.50, new Area(0.50, 0.50)));
        for (int i = 0; i < 100; i++) {
            ((PlayerBehavior) p.getBehaviour().get()).setCurrentDirection(Direction.S);
            p.getBehaviour().get().update();
        }
        assertTrue(p.getLocation().getY() <= 1 - p.getLocation().getArea().getHeight() / 2);
        // east Bound
        p.setLocation(new Location(0.50, 0.50, new Area(0.50, 0.50)));
        for (int i = 0; i < 100; i++) {
            ((PlayerBehavior) p.getBehaviour().get()).setCurrentDirection(Direction.E);
            p.getBehaviour().get().update();
        }
        assertTrue(p.getLocation().getY() <= 1 - p.getLocation().getArea().getWidth() / 2);
        // weast bound
        p.setLocation(new Location(0.50, 0.50, new Area(0.50, 0.50)));
        for (int i = 0; i < 100; i++) {
            ((PlayerBehavior) p.getBehaviour().get()).setCurrentDirection(Direction.W);
            p.getBehaviour().get().update();
        }
        assertTrue(p.getLocation().getY() >= p.getLocation().getArea().getWidth() / 2);
    }

    /**
     * Stalker Enemy Test.
     */
    @Test
    public void testStalkerEnemyBehaviorV1() {
        final PlayerBehavior pB = new PlayerBehavior(
                new CompleteImageSetCalculator(N_IMAGE, S_IMAGE, E_IMAGE, W_IMAGE, STAND), CS, E_FACTORY);
        pB.setCurrentRoom(DEFAULT_ROOM);
        final Entity p = new EntityImpl.EntitiesBuilder().setLocation(DEFAULT_LOC).setImage("error").setBehaviour(pB)
                .with("Speed", 0.2).with("Max Life", 10).with("Current Life", 10).with("Shoot Frequency", (long) 10)
                .with("Shooting Damage", 10).with("Bullet Speed", 10.0).build();
        final StalkerEnemyBehavior sB = new StalkerEnemyBehavior(p,
                new CompleteImageSetCalculator(N_IMAGE, S_IMAGE, E_IMAGE, W_IMAGE, STAND), CS, DEFAULT_ROOM, E_FACTORY,
                false);
        final Entity stalker = new EntityImpl.EntitiesBuilder()
                .setLocation(new Location(0.50, 0.70, new Area(0.2, 0.2))).setImage("error").setBehaviour(sB)
                .with("Speed", 0.2).with("Max Life", 10).with("Current Life", 10).with("Shoot Frequency", (long) 10)
                .with("Collision Damage", 1).with("Shoot Damage", 1).build();
        assertEquals(stalker.getImage(), STAND);
        // check if the NewDIrection is correct
        // Direction N
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.N));
        // Direction S
        stalker.setLocation(new Location(0.50, 0.30, new Area(0.2, 0.2)));
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.S));
        // Direction E
        stalker.setLocation(new Location(0.30, 0.50, new Area(0.2, 0.2)));
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.E));
        // Direction W
        stalker.setLocation(new Location(0.70, 0.50, new Area(0.2, 0.2)));
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.W));
        // Direction NE
        stalker.setLocation(new Location(0.30, 0.70, new Area(0.2, 0.2)));
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.NE));
        // Direction NW
        stalker.setLocation(new Location(0.70, 0.70, new Area(0.2, 0.2)));
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.NW));
        // Direction SE
        stalker.setLocation(new Location(0.30, 0.30, new Area(0.2, 0.2)));
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.SE));
        // Direction NW
        stalker.setLocation(new Location(0.70, 0.30, new Area(0.2, 0.2)));
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.SW));
        // Same Location
        stalker.setLocation(new Location(0.50, 0.50, new Area(0.2, 0.2)));
        ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update();
        assertTrue(
                ((StalkerEnemyBehavior) stalker.getBehaviour().get()).getCurrentDirection().equals(Direction.NOTHING));

        // Follow the Player
        Location prev = new Location(0.30, 0.30, new Area(0.2, 0.2));
        stalker.setLocation(new Location(0.30, 0.30, new Area(0.2, 0.2)));
        IntStream.range(0, 20).forEach(i -> ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update());
        assertTrue(isMuchNear(prev, stalker.getLocation(), p.getLocation()));

        // Follow the Player 2nd try
        p.setLocation(new Location(0.70, 0.70, new Area(0.2, 0.2)));
        prev = new Location(stalker.getLocation());
        // System.out.println("start " + stalker.getLocation());
        IntStream.range(0, 20).forEach(i -> {
            ((StalkerEnemyBehavior) stalker.getBehaviour().get()).update(); });
        assertTrue(isMuchNear(prev, stalker.getLocation(), p.getLocation()));
    }

    private boolean isMuchNear(final Location prev, final Location current, final Location goal) {
        return distanceVector(prev, goal) >= distanceVector(current, goal);
    }

    private double distanceVector(final Location l, final Location w) {
        return Math.hypot(Math.abs(l.getX() - w.getX()), Math.abs(l.getY() - w.getY()));
    }

    /**
     * Bullet Behavior test.
     */
    @Test
    public void testBulletBehavior() {
        final Entity b = E_FACTORY.createBullet(0.50, 0.50, DEFAULT_ROOM, Direction.N, EntityType.PLAYER_BULLET, 10,
                0.1);
        DEFAULT_ROOM.addEntity(b);
        b.getBehaviour().get().update();
        assertTrue(b.getLocation().getX() == 0.50 && b.getLocation().getY() == 0.40);
        IntStream.range(0, 60).forEach(i -> ((BulletBehavior) b.getBehaviour().get()).update());
        assertFalse(DEFAULT_ROOM.getEntities().contains(b));

    }

    /**
     * Collision Test.
     */
    @Test
    public void obstacleCollisionTest() {
        System.out.println("START [ obstacleCollisionTest ] ");
        final Room r = new RoomImpl(" ", 1, RoomType.INTERMEDIATE, new CopyOnWriteArraySet<>(), new HashSet<>(),
                true);
        final Entity o = E_FACTORY.createObstacle(0.70, DEFAULT_LOC.getY());
        final Entity p = E_FACTORY.createPlayer(new Pair<Double, Double>(0.20, 0.50), Player.TOMMI);
        ((PlayerBehavior) p.getBehaviour().get()).setCurrentRoom(r);
        r.addEntity(p);
        r.addEntity(o);
        ((PlayerBehavior) p.getBehaviour().get()).shoot(Direction.E);
        IntStream.range(0, 20000).forEach(i -> {
            r.getEntities().forEach(e -> {
                if (e.getBehaviour().isPresent()) {
                    if (e.getType() == EntityType.PLAYER) {
                        ((PlayerBehavior) p.getBehaviour().get()).setCurrentDirection(Direction.E);
                    }
                    e.getBehaviour().get().update();
                }
            });

        });
        assertTrue(
                p.getLocation().getY() == o.getLocation().getY() && p.getLocation().getX() <= o.getLocation().getX());
        assertTrue(r.getEntities().size() <= 2);
    }

    /**
     * Model test.
     */
    @Test
    public void modelTest() {
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
    }

    /**
     * Another stalker enemy test.
     */
    @Test
    public void testStalkerEnemyBehaviorV2() {
        final Room r = new RoomImpl(" ", 1, RoomType.INTERMEDIATE, new CopyOnWriteArraySet<>(), new HashSet<>(),
                true);
        final Entity p = E_FACTORY.createPlayer(new Pair<>(0.30, 0.50), Player.ANIS);
        ((PlayerBehavior) p.getBehaviour().get()).setCurrentRoom(r);
        final Entity se = E_FACTORY.stalkerSpiritEnemy(DEFAULT_LOC.getX(), DEFAULT_LOC.getY(), p, r, true);
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
