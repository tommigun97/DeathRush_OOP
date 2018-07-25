package model.entity;

import model.Direction;
import model.Location;
import model.room.Room;

/**
 * Class define player behavior.
 *
 */
public final class PlayerBehavior implements Behavior {

    private Entity e;
    // private Room currentRoom;
    // private EntityFactory eFactory;
    private Direction currentDirection;
    private long t;
    private final ImageCalculator imgCalc;
    private final CollisionSupervisor cs;

    /**
     * @param imgCalc
     *            calculator player image
     */
    public PlayerBehavior(final ImageCalculator imgCalc, final CollisionSupervisor cs) {
        // this.currentRoom = currentRoom;
        // this.eFactory = eFactory;
        this.currentDirection = Direction.NOTHING;
        this.imgCalc = imgCalc;
        this.t = System.currentTimeMillis();
        this.cs = cs;
    }

    // public Room getCurrentRoom() {
    // return currentRoom;
    // }
    //
    // public void setCurrentRoom(Room currentRoom) {
    // this.currentRoom = currentRoom;
    // }

    // public EntityFactory geteFactory() {
    // return eFactory;
    // }
    //
    // public void seteFactory(EntityFactory eFactory) {
    // this.eFactory = eFactory;
    // }
    //
    @Override
    public void setEntity(final Entity e) {
        this.e = e;
        checkProperties();
        e.setImage(this.imgCalc.getCurrentImage(Direction.NOTHING));

    }

    /**
     * @return current direction of the player
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * @param currentDirection
     *            new player direction
     */
    public void setCurrentDirection(final Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     * @param d
     *            shoot's direction
     */
    public void shoot(final Direction d) {
        if (System.currentTimeMillis() - this.t <= (long) this.e.getObjectProperty("Shoot Frequency")) {
            // da aggingere alla lista delle entità della
            // roomthis.eFactory.createBullet(this.e.getLocation().getX(),
            // this.e.getLocation().getY(), this.currentRoom, d);
        }
        t = System.currentTimeMillis();
    }

    @Override
    public void update() {
        Location prev = new Location(e.getLocation());
        this.currentDirection.changeLocation(e.getLocation(), e.getDoubleProperty("Speed"));
        cs.collisionWithBound(prev, e);
        this.e.setImage(this.imgCalc.getCurrentImage(this.getCurrentDirection()));
        this.currentDirection = Direction.NOTHING;

        // controllo sugli ostacoli
    }

    private void checkProperties() {
        // check per verificare che ci siano tutte le proprietà necessarie per
        // l'utilizzo del player
        e.getDoubleProperty("Speed");
        e.getDoubleProperty("Max Life");
        e.getDoubleProperty("Current Life");
        e.getObjectProperty("Shoot Frequency");
        e.getIntegerProperty("Shooting Damage");
    }

}
