package model.entity;

import model.Direction;
import model.Location;
import model.room.Room;

/**
 * Behavior of an Enemies that follow the Player.
 *
 */
public class StalkerEnemyBehavior implements Behavior {

    private Entity e;
    private final Entity toStalk;
    private final ImageCalculator imgCalc;
    private long t;
    private Direction currentDirection;
    private final CollisionSupervisor cs;
    private final Room currentRoom;
    private final EntityFactory eFactory;
    private final boolean canShoot;

    /**
     * @param toStalk
     *            entity to stalk
     * @param imgCalc
     *            image calculator
     * @param cs
     *            collision supervisor for collision
     * @param currentRoom
     *            room where entity is placed
     * @param eFactory
     *            factory for create bullet
     * @param canShoot
     *            if entity can shoot or not
     */
    public StalkerEnemyBehavior(final Entity toStalk, final ImageCalculator imgCalc, final CollisionSupervisor cs,
            final Room currentRoom, final EntityFactory eFactory, final boolean canShoot) {
        this.toStalk = toStalk;
        this.imgCalc = imgCalc;
        this.t = 0;
        this.currentDirection = Direction.NOTHING;
        this.cs = cs;
        this.currentRoom = currentRoom;
        this.eFactory = eFactory;
        this.canShoot = canShoot;
    }

    /**
     * Select new direction for stalker enemies.
     * 
     * @return The current direction.
     */
    private Direction checkNewDirection() {
        Direction d = Direction.NOTHING;
        if (toStalk.getLocation().getY() < e.getLocation().getY()) {
            d = Direction.N;
            if (toStalk.getLocation().getX() > e.getLocation().getX()) {
                d = Direction.NE;
            } else if (toStalk.getLocation().getX() < e.getLocation().getX()) {
                d = Direction.NW;
            }
        } else if (toStalk.getLocation().getY() > e.getLocation().getY()) {
            d = Direction.S;
            if (toStalk.getLocation().getX() > e.getLocation().getX()) {
                d = Direction.SE;
            } else if (toStalk.getLocation().getX() < e.getLocation().getX()) {
                d = Direction.SW;
            }
        } else if (toStalk.getLocation().getX() > e.getLocation().getX()) {
            d = Direction.E;
        } else {
            d = Direction.W;
        }
        d = toStalk.getLocation().locationsCollide(e.getLocation()) ? Direction.NOTHING : d;
        return d;
    }

    /**
     * Check entity properties.
     */
    private void checkProperties() {
        e.getDoubleProperty("Speed");
        e.getIntegerProperty("Max Life");
        e.getIntegerProperty("Current Life");
        e.getIntegerProperty("Collision Damage");
        if (canShoot) {
            e.getObjectProperty("Shoot Frequency");
            e.getIntegerProperty("Shoot Damage");
        }
    }

    @Override
    public final void setEntity(final Entity e) {
        this.e = e;
        checkProperties();
        this.e.setImage(this.imgCalc.getCurrentImage(Direction.NOTHING));
    }

    @Override
    public final void update() {
        currentDirection = checkNewDirection();
        final Location prev = new Location(e.getLocation());

        if (canShoot && System.currentTimeMillis() - this.t >= (Long) this.e.getObjectProperty("Shoot Frequency")) {
            this.currentRoom.addEntity(this.eFactory.createBullet(e.getLocation().getX(), e.getLocation().getY(),
                    currentRoom, currentDirection, EntityType.ENEMY_BULLET, e.getIntegerProperty("Shoot Damage"),
                    e.getDoubleProperty("Bullet Speed")));
            t = System.currentTimeMillis();
        }
        this.currentDirection.changeLocation(e.getLocation(), e.getDoubleProperty("Speed"));
        cs.collisionWithBound(prev, e);
        this.e.setImage(this.imgCalc.getCurrentImage(this.currentDirection));
        cs.collisionWithObstacles(e, this.currentRoom.getEntities(), prev);
    }

    /**
     * @return current direction
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * @return the room where the entity is placed
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
}
