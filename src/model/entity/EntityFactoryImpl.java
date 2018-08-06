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
import java.util.Optional;

import model.Area;
import model.Direction;
import model.Location;
import model.map.Coordinates;

import model.room.Room;
import utilities.Pair;

/**
 * Implementation of EntityFactory.
 */
public final class EntityFactoryImpl implements EntityFactory {
    private static final double DEFAULT_BULLET_WEIGHT = 0.01;
    private static final double DEFAULT_BULLET_HEIGHT = 0.01;
    private static final double DEFAULT_BULLET_SPEED = 0.01;
    private static final double DEFAULT_OBSTACLE_WEIGHT = 0.1;
    private static final double DEFAULT_OBSTACLE_HEIGHT = 0.1;
    private static final String DEFAULT_OBSTACLE_IMAGE = "OBSTACLE";
    private static final List<String> DEFAULT_STALKER_ENEMY_S = new ArrayList<>(Arrays.asList(" ", " ", " "));
    private static final List<String> DEFAULT_STALKER_ENEMY_N = new ArrayList<>(Arrays.asList(" ", " ", " "));
    private static final List<String> DEFAULT_STALKER_ENEMY_E = new ArrayList<>(Arrays.asList(" ", " ", " "));
    private static final List<String> DEFAULT_STALKER_ENEMY_W = new ArrayList<>(Arrays.asList(" ", " ", " "));
    private static final String DEFAULT_STALKER_ENEMY_STAND = " ";
    private static final double DEFAULT_STALKER_ENEMY_SPEED = 0.1;
    private static final int DEFAULT_STALKER_ENEMY_MAX_LIFE = 3;
    private static final Long DEFAULT_STALKER_ENEMY_SHOOT_FREQUENCY = Long.valueOf(500);
    private static final int DEFAULT_STALKER_ENEMY_COLLISION_DAMAGE = 1;
    private static final int DEFAULT_STALKER_ENEMY_SHOOT_DAMAGE = 1;
    private static final Area DEFAULT_STALKER_ENEMY_AREA = new Area(0.30, 0.30);
    private static final int DEFAULT_STALKER_ENEMY_REWARD = 50;
    private static final String DEFAULT_MOSQUITO_IMAGE_1 = " ";
    private static final String DEFAULT_MOSQUITO_IMAGE_2 = " ";
    private static final double DEFAULT_MOSCOW_ENEMY_SPEED = 0.1;
    private static final int DEFAULT_MOSCOW_ENEMY_MAX_LIFE = 3;
    private static final int DEFAULT_MOSCOW_ENEMY_COLLISION_DAMAGE = 1;
    private static final Area DEFAULT_MOSCOW_ENEMY_AREA = new Area(0.30, 0.30);
    private static final int DEFAULT_MOSCOW_ENEMY_REWARD = 25;

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
                .with("Bullet Speed", DEFAULT_BULLET_SPEED).with("Money", 0).build();
    }

    @Override
    public Entity isaacStalkerEnemy(final double x, final double y, final Entity eToStalk, final Room currentRoom,
            final boolean canShoot) {
        StalkerEnemyBehavior sb = new StalkerEnemyBehavior(eToStalk,
                new CompleteImageSetCalculator(DEFAULT_STALKER_ENEMY_N, DEFAULT_STALKER_ENEMY_S,
                        DEFAULT_STALKER_ENEMY_E, DEFAULT_STALKER_ENEMY_W, DEFAULT_STALKER_ENEMY_STAND),
                this.cs, currentRoom, this, canShoot);
        return new EntityImpl.EntitiesBuilder().setType(EntityType.ENEMY).setBehaviour(sb)
                .setLocation(new Location(x, y, DEFAULT_STALKER_ENEMY_AREA)).setImage(" ")
                .with("Speed", DEFAULT_STALKER_ENEMY_SPEED).with("Max Life", DEFAULT_STALKER_ENEMY_MAX_LIFE)
                .with("Current Life", DEFAULT_STALKER_ENEMY_MAX_LIFE)
                .with("Shoot Frequency", DEFAULT_STALKER_ENEMY_SHOOT_FREQUENCY)
                .with("Collision Damage", DEFAULT_STALKER_ENEMY_COLLISION_DAMAGE)
                .with("Bullet Speed", DEFAULT_BULLET_SPEED).with("Shoot Damage", DEFAULT_STALKER_ENEMY_SHOOT_DAMAGE)
                .with("Reward", DEFAULT_STALKER_ENEMY_REWARD).build();
    }

    @Override
    public Entity createBullet(final double x, final double y, final Room currentRoom, final Direction direction,
            final EntityType bulletType, final int damage, final double speed) {
        BulletBehavior bb = new BulletBehavior(direction, cs, currentRoom);
        return new EntityImpl.EntitiesBuilder().setType(bulletType).setBehaviour(bb).with("Shoot Damage", damage)
                .setImage("bullet/bullet.png")
                .with("Speed", speed)
                .setLocation(new Location(x, y, new Area(DEFAULT_BULLET_WEIGHT, DEFAULT_BULLET_HEIGHT))).build();
    }

    @Override
    public final Entity createDoor(final double x, final double y, final DoorStatus status, final Room nextRoom,
            final String image, final Coordinates coor, final Area area ) {
        return new EntityImpl.EntitiesBuilder().setLocation(new Location(x, y, new Area(coor.getArea().getWidth(), coor.getArea().getHeight())))
                .with("doorStatus", status).with("nextRoom", nextRoom).with("image", image).with("coordinate", coor)
                .build();
    }

    @Override
    public Entity createObstacle(final double x, final double y) {
        return new EntityImpl.EntitiesBuilder().setType(EntityType.OBSTACLE)
                .setLocation(new Location(x, y, new Area(DEFAULT_OBSTACLE_WEIGHT, DEFAULT_OBSTACLE_HEIGHT)))
                .setImage(DEFAULT_OBSTACLE_IMAGE).build();
    }

    @Override
    public Entity createMoscow(final double x, final double y, final Entity eToStalk, final Room currentRoom) {
        StalkerEnemyBehavior b = new StalkerEnemyBehavior(eToStalk,
                new TwoImageCalculator(DEFAULT_MOSQUITO_IMAGE_1, DEFAULT_MOSQUITO_IMAGE_2), cs, currentRoom, this,
                false);
        return new EntityImpl.EntitiesBuilder().setType(EntityType.ENEMY)
                .setLocation(new Location(x, y, DEFAULT_MOSCOW_ENEMY_AREA)).with("Speed", DEFAULT_MOSCOW_ENEMY_SPEED)
                .with("Max Life", DEFAULT_MOSCOW_ENEMY_MAX_LIFE).with("Current Life", DEFAULT_MOSCOW_ENEMY_MAX_LIFE)
                .with("Collision Damage", DEFAULT_MOSCOW_ENEMY_COLLISION_DAMAGE)
                .with("Reward", DEFAULT_MOSCOW_ENEMY_REWARD).setBehaviour(b).build();
    }

    @Override
    public Entity createBoss(final double x, final double y, final Room currentRoom, final Optional<Entity> eToStalk,
            final Boss who) {
        if (who == Boss.THOR) {
            CompleteSummonerBehavior b = new CompleteSummonerBehavior(
                    new CompleteImageSetCalculator(Boss.THOR.images(Direction.N), Boss.THOR.images(Direction.S), Boss.THOR.images(Direction.E), Boss.THOR.images(Direction.W), Boss.THOR.standImage()), 
                    cs, currentRoom, this, eToStalk.get());
            return new EntityImpl.EntitiesBuilder().setBehaviour(b)
                    .setLocation(new Location(x, y, Boss.THOR.getArea())).setImage(" ")
                    .with("Speed", Boss.THOR.getSpeed()).with("Max Life", Boss.THOR.getStartingMaxLife())
                    .with("Current Life", Boss.THOR.getStartingMaxLife())
                    .with("Shoot Frequency", Boss.THOR.startingBossShootFrequency())
                    .with("Collision Damage", Boss.THOR.collisionDamage()).with("Bullet Speed", DEFAULT_BULLET_SPEED)
                    .with("Shoot Damage", Boss.THOR.shootingDamage()).with("Reward", Boss.THOR.reward()).build();
        } else if (who == Boss.CIATTO) {
            OnlyBulletSummonerBeahavior b = new OnlyBulletSummonerBeahavior(
                    new CompleteImageSetCalculator(Boss.CIATTO.images(Direction.N), Boss.CIATTO.images(Direction.S),
                            Boss.CIATTO.images(Direction.E), Boss.CIATTO.images(Direction.W), Boss.CIATTO.standImage()),
                    cs, currentRoom, this);
            return new EntityImpl.EntitiesBuilder().setBehaviour(b)
                    .setLocation(new Location(x, y, Boss.CIATTO.getArea())).setImage(" ")
                    .with("Speed", Boss.CIATTO.getSpeed()).with("Max Life", Boss.CIATTO.getStartingMaxLife())
                    .with("Current Life", Boss.CIATTO.getStartingMaxLife())
                    .with("Shoot Frequency", Boss.CIATTO.startingBossShootFrequency())
                    .with("Collision Damage", Boss.CIATTO.collisionDamage()).with("Bullet Speed", DEFAULT_BULLET_SPEED)
                    .with("Shoot Damage", Boss.CIATTO.shootingDamage()).with("Reward", Boss.CIATTO.reward()).build();
        } else {
            StalkerEnemyBehavior b = new StalkerEnemyBehavior(eToStalk.get(),
                    new CompleteImageSetCalculator(Boss.CROATTI.images(Direction.N), Boss.CROATTI.images(Direction.S),
                            Boss.CROATTI.images(Direction.E), Boss.CROATTI.images(Direction.W),
                            Boss.CROATTI.standImage()),
                    cs, currentRoom, this, true);
            return new EntityImpl.EntitiesBuilder().setBehaviour(b)
                    .setLocation(new Location(x, y, Boss.CROATTI.getArea())).setImage(" ")
                    .with("Speed", Boss.CROATTI.getSpeed()).with("Max Life", Boss.CROATTI.getStartingMaxLife())
                    .with("Current Life", Boss.CROATTI.getStartingMaxLife())
                    .with("Shoot Frequency", Boss.CROATTI.startingBossShootFrequency())
                    .with("Collision Damage", Boss.CROATTI.collisionDamage()).with("Bullet Speed", DEFAULT_BULLET_SPEED)
                    .with("Shoot Damage", Boss.CROATTI.shootingDamage()).with("Reward", Boss.CROATTI.reward()).build();
        }
    }

}