package model.entity;

import model.Direction;
import model.room.Room;

/**
 * Class that define the behavior of a bullet.
 *
 */
public class BulletBehavior implements Behavior {

    private final Direction d;
    private Entity b;
    private CollisionSupervisor cs;
    private final Room currentRoom;

    public BulletBehavior(final Direction d, final CollisionSupervisor cs, final Room currentRoom) {
        this.d = d;
        this.cs = cs;
        this.currentRoom = currentRoom;
    }

    @Override
    public void setEntity(Entity e) {
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
