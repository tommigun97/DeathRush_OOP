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
    private Room currentRoom;
    private final EntityFactory eFactory;
    private Direction currentDirection;
    private long t;
    private final ImageCalculator imgCalc;
    private final CollisionSupervisor cs;

    /**
     * @param imgCalc
     *            calculator player image
     */
    public PlayerBehavior(final ImageCalculator imgCalc, final CollisionSupervisor cs, final Room currentRoom,
            final EntityFactory eFactory) {
        this.currentRoom = currentRoom;
        this.eFactory = eFactory;
        this.currentDirection = Direction.NOTHING;
        this.imgCalc = imgCalc;
        this.t = System.currentTimeMillis();
        this.cs = cs;
    }


    /**
     * @return the current room where is set the player
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @param currentRoom
     *            the new current room to set
     */
    public void setCurrentRoom(final Room currentRoom) {
        this.currentRoom = currentRoom;
    }

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
        if (System.currentTimeMillis() - this.t <= (Long) this.e.getObjectProperty("Shoot Frequency")) {
            this.currentRoom.addEntity(this.eFactory.createBullet(e.getLocation().getX(), e.getLocation().getY(),
                    currentRoom, d, EntityType.PLAYER_BULLET, e.getIntegerProperty("Shooting Damage"), e.getDoubleProperty("Bullet Speed")));
            System.out.println("sparo");
        }
        t = System.currentTimeMillis();
    }

    @Override
    public void update() {
        Location prev = new Location(e.getLocation());
        this.currentDirection.changeLocation(e.getLocation(), e.getDoubleProperty("Speed"));
        cs.collisionWithBound(prev, e);
        cs.collisionWithObstacles(e, this.currentRoom.getEntities(), prev);
        this.e.setImage(this.imgCalc.getCurrentImage(this.getCurrentDirection()));
        this.currentDirection = Direction.NOTHING;

        // controllo sugli ostacoli
    }

    private void checkProperties() {
        // check per verificare che ci siano tutte le proprietà necessarie per
        // l'utilizzo del player
        e.getDoubleProperty("Speed");
        e.getIntegerProperty("Max Life");
        e.getIntegerProperty("Current Life");
        e.getObjectProperty("Shoot Frequency");
        e.getIntegerProperty("Shooting Damage");
        e.getIntegerProperty("Shooting Damage");
        e.getDoubleProperty("Bullet Speed");
    }

}
