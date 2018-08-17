package model.entity;

import model.Direction;
import model.room.Room;

/**
 * Class that define the behavior of a bullet.
 *
 */
public final class BulletBehavior implements Behavior {

    private final Direction d;
    private Entity b;
    private final CollisionSupervisor cs;
    private final Room currentRoom;

    /**
     * @param d
     *            direction where bullet is shoot
     * @param cs
     *            collision supervisor for check the movement
     * @param currentRoom
     *            room where bullet is placed
     */
    public BulletBehavior(final Direction d, final CollisionSupervisor cs, final Room currentRoom) {
        this.d = d;
        this.cs = cs;
        this.currentRoom = currentRoom;
    }

    @Override
    public void setEntity(final Entity e) {
        this.b = e;
        checkProperty();

    }

    @Override
    public void update() {
        d.changeLocation(b.getLocation(), b.getDoubleProperty("Speed"));
        cs.collisionWithBound(b, currentRoom);
        cs.collisionWithObstacles(b, currentRoom);
    }

    private void checkProperty() {
        b.getIntegerProperty("Shoot Damage");
        b.getDoubleProperty("Speed");
    }

}
