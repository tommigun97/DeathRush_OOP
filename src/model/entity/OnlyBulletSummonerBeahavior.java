package model.entity;

import model.Direction;
import model.Location;
import model.room.Room;

/**
 * entity that create bullet from the room corners.
 *
 */
public class OnlyBulletSummonerBeahavior implements Behavior {
    private static final long F_SHOOT_FROM_CORNER = 4000; // millisec == 1 sec
    private static final long F_CHANGE_DIRECTION = 500; // millisec == 4 sec
    private static final double LONGER = 0.8;
    private static final double SHORTER = 0.2;
    private Entity e;
    private final ImageCalculator imgCalc;
    private long tShoot;
    private long tShootFromCorner;
    private long tChangeDirection;
    private Direction currentDirection;
    private final CollisionSupervisor cs;
    private final Room currentRoom;
    private final EntityFactory eFactory;

    /**
     * @param imgCalc
     *            image calculator
     * @param cs
     *            collision supervisor for collisions
     * @param currentRoom
     *            room where is placed
     * @param eFactory
     *            factory for summon enemy
     */
    public OnlyBulletSummonerBeahavior(final ImageCalculator imgCalc, final CollisionSupervisor cs,
            final Room currentRoom, final EntityFactory eFactory) {
        super();
        this.imgCalc = imgCalc;
        this.tShoot = 0;
        this.tShootFromCorner = 0;
        this.tChangeDirection = 0;
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
    public final void setEntity(final Entity e) {
        this.e = e;
        checkProperties();
        this.e.setImage(this.imgCalc.getCurrentImage(Direction.NOTHING));
    }

    /*
     * (non-Javadoc) if you want overrid ethis method you need to know that with it
     * you can summon bullet from room wall
     */
    @Override
    public void update() {
        if (System.currentTimeMillis() - this.tChangeDirection >= F_CHANGE_DIRECTION) {
            this.currentDirection = Direction.randomDirection();
            tChangeDirection = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - this.tShootFromCorner >= F_SHOOT_FROM_CORNER) {
            this.currentRoom.addEntity(this.eFactory.createBullet(SHORTER, LONGER, currentRoom, Direction.SW,
                    EntityType.ENEMY_BULLET, e.getIntegerProperty("Shoot Damage"), e.getDoubleProperty("Bullet Speed"),
                    EntityType.ENEMY));
            this.currentRoom.addEntity(this.eFactory.createBullet(LONGER, SHORTER, currentRoom, Direction.SW,
                    EntityType.ENEMY_BULLET, e.getIntegerProperty("Shoot Damage"), e.getDoubleProperty("Bullet Speed"),
                    EntityType.ENEMY));
            this.currentRoom.addEntity(this.eFactory.createBullet(SHORTER, SHORTER, currentRoom, Direction.SE,
                    EntityType.ENEMY_BULLET, e.getIntegerProperty("Shoot Damage"), e.getDoubleProperty("Bullet Speed"),
                    EntityType.ENEMY));
            this.currentRoom.addEntity(this.eFactory.createBullet(LONGER, LONGER, currentRoom, Direction.NW,
                    EntityType.ENEMY_BULLET, e.getIntegerProperty("Shoot Damage"), e.getDoubleProperty("Bullet Speed"),
                    EntityType.ENEMY));
            this.tShootFromCorner = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - this.tShoot >= (Long) this.e.getObjectProperty("Shoot Frequency")) {
            Direction d = Direction.randomDirection();
            this.currentRoom.addEntity(this.eFactory.createBullet(e.getLocation().getX(), e.getLocation().getY(),
                    currentRoom, d, EntityType.ENEMY_BULLET, e.getIntegerProperty("Shoot Damage"),
                    e.getDoubleProperty("Bullet Speed"), EntityType.ENEMY));
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
