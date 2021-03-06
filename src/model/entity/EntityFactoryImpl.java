package model.entity;

import model.Area;
import model.Direction;
import model.Location;
import model.room.Room;
import model.world.Coordinates;
import utilities.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of EntityFactory.
 */
public final class EntityFactoryImpl implements EntityFactory {
    private static final double DEFAULT_BULLET_WEIGHT = 0.03;
    private static final double DEFAULT_BULLET_HEIGHT = 0.03;
    private static final double DEFAULT_BULLET_SPEED = 0.01;
    private static final double DEFAULT_BULLET_SPEED_ENEMY = 0.0075;
    private static final double DEFAULT_OBSTACLE_WEIGHT = 0.03;
    private static final double DEFAULT_OBSTACLE_HEIGHT = 0.04;
    private static final String DEFAULT_OBSTACLE_IMAGE = "room/rock.png";
    private static final List<String> DEFAULT_STALKER_ENEMY_S = new ArrayList<>(
            Arrays.asList("images/enemies/stalker_S2.png", "images/enemies/stalker_S.png"));
    private static final List<String> DEFAULT_STALKER_ENEMY_N = new ArrayList<>(
            Arrays.asList("images/enemies/stalker_N.png", "images/enemies/stalker_N2.png"));
    private static final List<String> DEFAULT_STALKER_ENEMY_E = new ArrayList<>(
            Arrays.asList("images/enemies/stalker_S2.png", "images/enemies/stalker_S.png"));
    private static final List<String> DEFAULT_STALKER_ENEMY_W = new ArrayList<>(
            Arrays.asList("images/enemies/stalker_S2.png", "images/enemies/stalker_S.png"));
    private static final String DEFAULT_STALKER_ENEMY_STAND = "images/enemies/stalker_N.png";
    private static final double DEFAULT_STALKER_ENEMY_SPEED = 0.002;
    private static final int DEFAULT_STALKER_ENEMY_MAX_LIFE = 600;
    private static final Long DEFAULT_STALKER_ENEMY_SHOOT_FREQUENCY = Long.valueOf(2000);
    private static final int DEFAULT_STALKER_ENEMY_COLLISION_DAMAGE = 1;
    private static final int DEFAULT_STALKER_ENEMY_SHOOT_DAMAGE = 1;
    private static final Area DEFAULT_STALKER_ENEMY_AREA = new Area(0.04, 0.04);
    private static final int DEFAULT_STALKER_ENEMY_REWARD = 5;
    private static final String DEFAULT_MOSQUITO_IMAGE_1 = "images/enemies/moscow.png";
    private static final String DEFAULT_MOSQUITO_IMAGE_2 = "images/enemies/moscow2.png";
    private static final double DEFAULT_MOSCOW_ENEMY_SPEED = 0.003;
    private static final int DEFAULT_MOSCOW_ENEMY_MAX_LIFE = 400;
    private static final int DEFAULT_MOSCOW_ENEMY_COLLISION_DAMAGE = 1;
    private static final Area DEFAULT_MOSCOW_ENEMY_AREA = new Area(0.03, 0.03);
    private static final int DEFAULT_MOSCOW_ENEMY_REWARD = 5;
    private static final Long GUITAR_UPGRADE = Long.valueOf(150);
    private static final double SUGAR_UPGRADE = 0.003;
    private static final int GUN_UPGRADE = 25;
    private static final int CIGARETTE_UPGRADE = 5;
    private static final String PLAYER_BULLET = "bullet/bullet1.png";
    private static final String ENEMY_BULLET = "bullet/bullet2.png";
    private final CollisionSupervisor cs;

    /**
     * @param cs
     *            collision supervisor used by some entities
     */
    public EntityFactoryImpl(final CollisionSupervisor cs) {
        this.cs = cs;
    }

    @Override
    public Entity createPlayer(final Pair<Double, Double> pos, final Player who) {

        final PlayerBehavior pB = new PlayerBehavior(new CompleteImageSetCalculator(who.images(Direction.N),
                who.images(Direction.S), who.images(Direction.E), who.images(Direction.W), who.standImage()), cs, this);
        return new EntityImpl.EntitiesBuilder()
                .setLocation(new Location(pos.getFirst(), pos.getSecond(), who.getArea())).setType(EntityType.PLAYER)
                .setImage("error").setBehaviour(pB).with("Speed", who.getSpeed())
                .with("Max Life", who.getStartingMaxLife()).with("Current Life", who.getStartingMaxLife())
                .with("Shoot Frequency", Long.valueOf(who.startingPlayerShootFrequency()))
                .with("Shooting Damage", who.shootingDamage()).with("Bullet Speed", DEFAULT_BULLET_SPEED)
                .with("Money", 0).build();

    }

    @Override
    public Entity stalkerSpiritEnemy(final double x, final double y, final Entity eToStalk, final Room currentRoom,
            final boolean canShoot) {
        final StalkerEnemyBehavior sb = new StalkerEnemyBehavior(eToStalk,
                new CompleteImageSetCalculator(DEFAULT_STALKER_ENEMY_N, DEFAULT_STALKER_ENEMY_S,
                        DEFAULT_STALKER_ENEMY_E, DEFAULT_STALKER_ENEMY_W, DEFAULT_STALKER_ENEMY_STAND),
                this.cs, currentRoom, this, canShoot);
        return new EntityImpl.EntitiesBuilder().setType(EntityType.ENEMY).setBehaviour(sb)
                .setLocation(new Location(x, y, DEFAULT_STALKER_ENEMY_AREA)).setImage(" ")
                .with("Speed", DEFAULT_STALKER_ENEMY_SPEED).with("Max Life", DEFAULT_STALKER_ENEMY_MAX_LIFE)
                .with("Current Life", DEFAULT_STALKER_ENEMY_MAX_LIFE)
                .with("Shoot Frequency", DEFAULT_STALKER_ENEMY_SHOOT_FREQUENCY)
                .with("Collision Damage", DEFAULT_STALKER_ENEMY_COLLISION_DAMAGE)
                .with("Bullet Speed", DEFAULT_BULLET_SPEED_ENEMY)
                .with("Shoot Damage", DEFAULT_STALKER_ENEMY_SHOOT_DAMAGE).with("Reward", DEFAULT_STALKER_ENEMY_REWARD)
                .build();
    }

    @Override
    public Entity createBullet(final double x, final double y, final Room currentRoom, final Direction direction,
            final EntityType bulletType, final int damage, final double speed) {
        if (bulletType == EntityType.PLAYER_BULLET) {
            final BulletBehavior bb = new BulletBehavior(direction, cs, currentRoom);
            return new EntityImpl.EntitiesBuilder().setType(bulletType).setBehaviour(bb).with("Shoot Damage", damage)
                    .setImage(PLAYER_BULLET).with("Speed", speed)
                    .setLocation(new Location(x, y, new Area(DEFAULT_BULLET_WEIGHT, DEFAULT_BULLET_HEIGHT))).build();
        } else {
            final BulletBehavior bb = new BulletBehavior(direction, cs, currentRoom);
            return new EntityImpl.EntitiesBuilder().setType(bulletType).setBehaviour(bb).with("Shoot Damage", damage)
                    .setImage(ENEMY_BULLET).with("Speed", speed)
                    .setLocation(new Location(x, y, new Area(DEFAULT_BULLET_WEIGHT, DEFAULT_BULLET_HEIGHT))).build();
        }
    }

    @Override
    public Entity createDoor(final double x, final double y, final DoorStatus status, final Room nextRoom,
            final String image, final Coordinates coor, final Area area) {
        return new EntityImpl.EntitiesBuilder().setImage(image)
                .setLocation(new Location(x, y, new Area(coor.getArea().getWidth(), coor.getArea().getHeight())))
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
    public Entity createFly(final double x, final double y, final Entity eToStalk, final Room currentRoom) {
        final StalkerEnemyBehavior b = new StalkerEnemyBehavior(eToStalk,
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
        Behavior b = null;
        if (who == Boss.BOSS_1) {
            b = new CompleteSummonerBehavior(
                    new CompleteImageSetCalculator(Boss.BOSS_1.images(Direction.N), Boss.BOSS_1.images(Direction.S),
                            Boss.BOSS_1.images(Direction.E), Boss.BOSS_1.images(Direction.W), Boss.BOSS_1.standImage()),
                    cs, currentRoom, this, eToStalk.get());
        } else if (who == Boss.BOSS_2) {
            b = new OnlyBulletSummonerBeahavior(
                    new CompleteImageSetCalculator(Boss.BOSS_2.images(Direction.N), Boss.BOSS_2.images(Direction.S),
                            Boss.BOSS_2.images(Direction.E), Boss.BOSS_2.images(Direction.W), Boss.BOSS_2.standImage()),
                    cs, currentRoom, this);
        } else {
            b = new StalkerEnemyBehavior(eToStalk.get(),
                    new CompleteImageSetCalculator(Boss.BOSS_3.images(Direction.N), Boss.BOSS_3.images(Direction.S),
                            Boss.BOSS_3.images(Direction.E), Boss.BOSS_3.images(Direction.W), Boss.BOSS_3.standImage()),
                    cs, currentRoom, this, true);
        }

        return new EntityImpl.EntitiesBuilder().setBehaviour(b).setType(EntityType.ENEMY)
                .setLocation(new Location(x, y, who.getArea())).setImage(" ").with("Speed", who.getSpeed())
                .with("Max Life", who.getStartingMaxLife()).with("Current Life", who.getStartingMaxLife())
                .with("Shoot Frequency", Long.valueOf(who.startingBossShootFrequency()))
                .with("Collision Damage", who.collisionDamage()).with("Bullet Speed", DEFAULT_BULLET_SPEED)
                .with("Shoot Damage", who.shootingDamage()).with("Reward", who.reward()).build();
    }

    @Override
    public Entity createPowerUp(final double x, final double y, final Room currentRoom, final PowerUp who) {
        if (who == PowerUp.CHITARRA) {
            return new EntityImpl.EntitiesBuilder().setType(EntityType.POWER_UP).with("Type", PowerUp.CHITARRA)
                    .setLocation(new Location(x, y, PowerUp.CHITARRA.getArea())).with("Cost", who.getCost())
                    .with("Increse Attack Frequency", GUITAR_UPGRADE).setImage(who.getImage()).build();
        } else if (who == PowerUp.SIGARETTA) {
            return new EntityImpl.EntitiesBuilder().setType(EntityType.POWER_UP).with("Type", PowerUp.SIGARETTA)
                    .setLocation(new Location(x, y, PowerUp.SIGARETTA.getArea())).with("Increase Hp", CIGARETTE_UPGRADE)
                    .with("Cost", who.getCost()).setImage(who.getImage()).build();
        } else if (who == PowerUp.ZUCCHERO) {
            return new EntityImpl.EntitiesBuilder().setType(EntityType.POWER_UP).with("Type", PowerUp.ZUCCHERO)
                    .setLocation(new Location(x, y, PowerUp.ZUCCHERO.getArea()))
                    .with("Increase Movement Speed", SUGAR_UPGRADE).with("Cost", who.getCost()).setImage(who.getImage())
                    .build();
        } else {
            return new EntityImpl.EntitiesBuilder().setType(EntityType.POWER_UP).with("Type", PowerUp.PISTOLA)
                    .setLocation(new Location(x, y, PowerUp.PISTOLA.getArea())).with("Increase Damage", GUN_UPGRADE)
                    .with("Cost", who.getCost()).setImage(who.getImage()).build();
        }
    }
}
