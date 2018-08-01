package model.entity;

import model.Direction;
import model.Location;
import model.room.Room;

/**
 * entity that create bullet from the room corners.
 *
 */
public class OnlyBulletSummonerBeahavior implements Behavior {
    private static final long F_SHOOT_FROM_CORNER = 1000; // millisec == 1 sec
    private static final long F_CHANGE_DIRECTION = 4000; // millisec == 4 sec
    private Entity e;
    private final ImageCalculator imgCalc;
    private long tShoot;
    private long tShootFromCorner;
    private long tChangeDirection;
    private Direction currentDirection;
    private final CollisionSupervisor cs;
    private final Room currentRoom;
    private final EntityFactory eFactory;

    public OnlyBulletSummonerBeahavior(ImageCalculator imgCalc, CollisionSupervisor cs, Room currentRoom,
            EntityFactory eFactory) {
        super();
        this.imgCalc = imgCalc;
        this.tShoot = System.currentTimeMillis();
        this.tShootFromCorner = System.currentTimeMillis();
        this.tChangeDirection = System.currentTimeMillis();
        this.currentDirection = Direction.NOTHING;
        this.cs = cs;
        this.currentRoom = currentRoom;
        this.eFactory = eFactory;
    }

    /**
     * @return the current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @return the entity factory
     */
    public EntityFactory geteFactory() {
        return eFactory;
    }

    /**
     * @return the entity
     */
    public Entity getEntity() {
        return e;
    }

    @Override
    public void setEntity(final Entity e) {
        this.e = e;
        checkProperties();
        this.e.setImage(this.imgCalc.getCurrentImage(Direction.NOTHING));
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - this.tChangeDirection >= F_CHANGE_DIRECTION) {
            this.currentDirection = Direction.randomDirection();
            tChangeDirection = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - this.tShootFromCorner >= F_SHOOT_FROM_CORNER) {
            this.currentRoom
                    .addEntity(this.eFactory.createBullet(0, 1, currentRoom, currentDirection, EntityType.ENEMY_BULLET,
                            e.getIntegerProperty("Shoot Damage"), e.getDoubleProperty("Bullet Speed")));
            this.currentRoom
                    .addEntity(this.eFactory.createBullet(1, 0, currentRoom, currentDirection, EntityType.ENEMY_BULLET,
                            e.getIntegerProperty("Shoot Damage"), e.getDoubleProperty("Bullet Speed")));
            this.currentRoom
                    .addEntity(this.eFactory.createBullet(0, 0, currentRoom, currentDirection, EntityType.ENEMY_BULLET,
                            e.getIntegerProperty("Shoot Damage"), e.getDoubleProperty("Bullet Speed")));
            this.currentRoom
                    .addEntity(this.eFactory.createBullet(1, 1, currentRoom, currentDirection, EntityType.ENEMY_BULLET,
                            e.getIntegerProperty("Shoot Damage"), e.getDoubleProperty("Bullet Speed")));
        }
        if (System.currentTimeMillis() - this.tShoot >= (Long) this.e.getObjectProperty("Shoot Frequency")) {
            this.currentRoom.addEntity(this.eFactory.createBullet(e.getLocation().getX(), e.getLocation().getY(),
                    currentRoom, currentDirection, EntityType.ENEMY_BULLET, e.getIntegerProperty("Shoot Damage"),
                    e.getDoubleProperty("Bullet Speed")));
            tShoot = System.currentTimeMillis();
        }
        final Location prev = new Location(e.getLocation());
        this.currentDirection.changeLocation(e.getLocation(), e.getDoubleProperty("Speed"));
        cs.collisionWithBound(prev, e);
        this.e.setImage(this.imgCalc.getCurrentImage(this.currentDirection));
        cs.collisionWithObstacles(e, this.currentRoom.getEntities(), prev);
    }

    private void checkProperties() {
        e.getDoubleProperty("Speed");
        e.getIntegerProperty("Max Life");
        e.getIntegerProperty("Current Life");
        e.getIntegerProperty("Collision Damage");
        e.getObjectProperty("Shoot Frequency");
        e.getIntegerProperty("Shoot Damage");
    }

}
