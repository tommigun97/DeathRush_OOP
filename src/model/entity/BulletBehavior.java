package model.entity;

import model.Direction;

/**
 * Class that define the behavior of a bullet.
 *
 */
public class BulletBehavior implements Behavior {

    private final Direction d;
    private Entity b;
    private CollisionSupervisor cs;
    //Room

    public BulletBehavior(final Direction d, final CollisionSupervisor cs) {
        this.d = d;
        this.cs = cs;
    }

    @Override
    public void setEntity(Entity e) {
        this.b = e;

    }

    @Override
    public void update() {
        d.changeLocation(b.getLocation(), b.getDoubleProperty("Speed"));
        cs.collisionWithBound(b);
        //mancano le collisioni con gli ostacoli
    }

    private void checkProperty() {
        b.getIntegerProperty("Shoot Damage");
        b.getDoubleProperty("Speed");
        b.getBooleanProperty("isFromPlayer");
    }

}
