package model.entity;

import model.Direction;
import model.Location;

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
    // private final Room currentRoom;
    // private final EntityFactory eFactory;

    public StalkerEnemyBehavior(Entity toStalk, ImageCalculator imgCalc, CollisionSupervisor cs) {
        this.toStalk = toStalk;
        this.imgCalc = imgCalc;
        this.t = System.currentTimeMillis();
        this.currentDirection = Direction.NOTHING;
        this.cs = cs;
    }

    @Override
    public final void setEntity(final Entity e) {
        this.e = e;
        checkProperties();
        this.e.setImage(this.imgCalc.getCurrentImage(Direction.NOTHING));
    }

    @Override
    public void update() {
        currentDirection = checkNewDirection();
        Location prev = new Location(e.getLocation());
        this.currentDirection.changeLocation(e.getLocation(), e.getDoubleProperty("Speed"));
        cs.collisionWithBound(prev, e);
        this.e.setImage(this.imgCalc.getCurrentImage(this.getCurrentDirection()));
        
        //controllo sugli ostacoli
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    private void checkProperties() {
        // mancano qui le proprietà per la definizione del danno da collisione ecc ecc
        e.getDoubleProperty("Speed");
        e.getDoubleProperty("Max Life");
        e.getDoubleProperty("Current Life");
        e.getObjectProperty("Shoot Frequency");
        e.getIntegerProperty("Collision Damage");
        e.getIntegerProperty("Shoot Damage");
    }

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

}
