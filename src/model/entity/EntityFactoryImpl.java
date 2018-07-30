package model.entity;

import model.Area;
import model.Direction;
import model.Location;
import model.room.Room;
import utilities.Pair;
import model.entity.DoorStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.Area;
import model.Direction;
import model.Location;
import model.map.Coordinates;

import model.room.Room;
import utilities.Pair;

/**
 * Implementation of EntityFactory.
 */
public class EntityFactoryImpl implements EntityFactory {
    private static final Pair<Double, Double> STARTING_POSITION = new Pair<Double, Double>(0.50, 0.50);
    private static final double DEFAULT_BULLET_WEIGHT = 0.1;
    private static final double DEFAULT_BULLET_HEIGHT = 0.1;
    private static final double DEFAULT_BULLET_SPEED = 0.1;
    private static final double DEFAULT_OBSTACLE_WEIGHT = 0.1;
    private static final double DEFAULT_OBSTACLE_HEIGHT = 0.1;
    private static final String DEFAULT_OBSTACLE_IMAGE = "OBSTACLE";
    private static final List<String> DEFAULT_STALKER_ENEMY_S = new ArrayList<>(Arrays.asList(" ", " ", " "));
    private static final List<String> DEFAULT_STALKER_ENEMY_N = new ArrayList<>(Arrays.asList(" ", " ", " "));
    private static final List<String> DEFAULT_STALKER_ENEMY_E = new ArrayList<>(Arrays.asList(" ", " ", " "));
    private static final List<String> DEFAULT_STALKER_ENEMY_W = new ArrayList<>(Arrays.asList(" ", " ", " "));
    private static final String DEFAULT_STALKER_ENEMY_STAND = "OBSTACLE";
    private static final double DEFAULT_STALKER_ENEMY_SPEED = 0.1;
    private static final int DEFAULT_STALKER_ENEMY_MAX_LIFE = 3;
    private static final Long DEFAULT_STALKER_ENEMY_SHOOT_FREQUENCY = Long.valueOf(500);
    private static final int DEFAULT_STALKER_ENEMY_COLLISION_DAMAGE = 3;
    private static final int DEFAULT_STALKER_ENEMY_SHOOT_DAMAGE = 1;
    private static final Area DEFAULT_STALKER_ENEMY_AREA = new Area(0.30, 0.30);

    private final CollisionSupervisor cs;

    /**
     * @param cs
     *            collision supervisor used by some entities
     */
    public EntityFactoryImpl(final CollisionSupervisor cs) {
        this.cs = cs;
    }

    @Override
    public Entity createPlayer(final Pair<Double, Double> pos, final Room currentRoom, final Player who) {
        PlayerBehavior pB = null;
        Area pA = null;
        double playerSpeed = 0;
        long shootFequency = 0;
        int shootDamage = 0;
        int startMaxLife = 0;
        if (who.equals(Player.SIMO)) {
            pB = new PlayerBehavior(
                    new CompleteImageSetCalculator(Player.SIMO.images(Direction.N), Player.SIMO.images(Direction.S),
                            Player.SIMO.images(Direction.E), Player.SIMO.images(Direction.W), Player.SIMO.standImage()),
                    cs, currentRoom, this);
            pA = Player.SIMO.getArea();
            playerSpeed = Player.SIMO.getSpeed();
            shootFequency = Player.SIMO.startingPlayerShootFrequency();
            shootDamage = Player.SIMO.shootingDamage();
            startMaxLife = Player.SIMO.getStartingMaxLife();
        } else if (who.equals(Player.ANIS)) {
            pB = new PlayerBehavior(
                    new CompleteImageSetCalculator(Player.ANIS.images(Direction.N), Player.ANIS.images(Direction.S),
                            Player.ANIS.images(Direction.E), Player.ANIS.images(Direction.W), Player.ANIS.standImage()),
                    cs, currentRoom, this);
            pA = Player.ANIS.getArea();
            playerSpeed = Player.ANIS.getSpeed();
            shootFequency = Player.ANIS.startingPlayerShootFrequency();
            shootDamage = Player.ANIS.shootingDamage();
            startMaxLife = Player.ANIS.getStartingMaxLife();
        } else if (who.equals(Player.TOMMI)) {
            pB = new PlayerBehavior(new CompleteImageSetCalculator(Player.TOMMI.images(Direction.N),
                    Player.TOMMI.images(Direction.S), Player.TOMMI.images(Direction.E),
                    Player.TOMMI.images(Direction.W), Player.TOMMI.standImage()), cs, currentRoom, this);
            pA = Player.TOMMI.getArea();
            playerSpeed = Player.TOMMI.getSpeed();
            shootFequency = Player.TOMMI.startingPlayerShootFrequency();
            shootDamage = Player.TOMMI.shootingDamage();
            startMaxLife = Player.TOMMI.getStartingMaxLife();
        } else {
            pB = new PlayerBehavior(
                    new CompleteImageSetCalculator(Player.KASO.images(Direction.N), Player.KASO.images(Direction.S),
                            Player.KASO.images(Direction.E), Player.KASO.images(Direction.W), Player.KASO.standImage()),
                    cs, currentRoom, this);
            pA = Player.KASO.getArea();
            playerSpeed = Player.KASO.getSpeed();
            shootFequency = Player.KASO.startingPlayerShootFrequency();
            shootDamage = Player.KASO.shootingDamage();
            startMaxLife = Player.KASO.getStartingMaxLife();
        }

        return new EntityImpl.EntitiesBuilder().setLocation(new Location(pos.getFirst(), pos.getSecond(), pA))
                .setType(EntityType.PLAYER).setImage("error").setBehaviour(pB).with("Speed", playerSpeed)
                .with("Max Life", startMaxLife).with("Current Life", startMaxLife)
                .with("Shoot Frequency", Long.valueOf(shootFequency)).with("Shooting Damage", shootDamage)
                .with("Bullet Speed", DEFAULT_BULLET_SPEED).build();
    }

    @Override
    public Entity isaacStalkerEnemy(final double x, final double y, final Entity eToStalk, final Room currentRoom) {
        StalkerEnemyBehavior sb = new StalkerEnemyBehavior(eToStalk,
                new CompleteImageSetCalculator(DEFAULT_STALKER_ENEMY_N, DEFAULT_STALKER_ENEMY_S,
                        DEFAULT_STALKER_ENEMY_E, DEFAULT_STALKER_ENEMY_W, DEFAULT_STALKER_ENEMY_STAND),
                this.cs, currentRoom, this, true);
        return new EntityImpl.EntitiesBuilder().setType(EntityType.ENEMY).setBehaviour(sb)
                .setLocation(new Location(x, y, DEFAULT_STALKER_ENEMY_AREA)).setImage(" ")
                .with("Speed", DEFAULT_STALKER_ENEMY_SPEED).with("Max Life", DEFAULT_STALKER_ENEMY_MAX_LIFE)
                .with("Current Life", DEFAULT_STALKER_ENEMY_MAX_LIFE)
                .with("Shoot Frequency", DEFAULT_STALKER_ENEMY_SHOOT_FREQUENCY)
                .with("Collision Damage", DEFAULT_STALKER_ENEMY_COLLISION_DAMAGE)
                .with("Bullet Speed", DEFAULT_BULLET_SPEED)
                .with("Shoot Damage", DEFAULT_STALKER_ENEMY_SHOOT_DAMAGE).build();
    }

    @Override
    public Entity createBullet(final double x, final double y, final Room currentRoom, final Direction direction,
            final EntityType bulletType, final int damage, final double speed) {
        BulletBehavior bb = new BulletBehavior(direction, cs, currentRoom);
        return new EntityImpl.EntitiesBuilder().setType(bulletType).setBehaviour(bb).with("Shoot Damage", damage)
                .with("Speed", speed)
                .setLocation(new Location(x, y, new Area(DEFAULT_BULLET_WEIGHT, DEFAULT_BULLET_HEIGHT))).build();
    }

    @Override
    public final Entity createDoor(final double x, final double y, final DoorStatus status, final Room nextRoom, final String image
                                    , final Coordinates coor) {
        return new EntityImpl.EntitiesBuilder()
                                .setLocation(new Location(x, y, new Area(0.5, 0.5)))
                                .with("doorStatus", status)
                                .with("nextRoom", nextRoom)
                                .with("image", image)
                                .with("coordinate", coor).build();
    }

    @Override
    public Entity createObstacle(final double x, final double y) {
        return new EntityImpl.EntitiesBuilder().setType(EntityType.OBSTACLE)
                .setLocation(new Location(x, y, new Area(DEFAULT_OBSTACLE_WEIGHT, DEFAULT_OBSTACLE_HEIGHT)))
                .setImage(DEFAULT_OBSTACLE_IMAGE).build();
    }

}