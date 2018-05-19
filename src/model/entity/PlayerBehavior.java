package model.entity;
import model.Direction;
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
    private long now;
    private final ImageCalculator imgCalc;

    /**
     * @param imgCalc calculator player image
     */
    public PlayerBehavior(final ImageCalculator imgCalc) {
        // this.currentRoom = currentRoom;
        // this.eFactory = eFactory;
        this.currentDirection = Direction.NOTHING;
        this.imgCalc = imgCalc;
        now = System.currentTimeMillis();
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
        e.setImage(this.imgCalc.getCurrentImage(Direction.NOTHING));

    }

    /**
     * @return current direction of the player
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * @param currentDirection new player direction
     */
    public void setCurrentDirection(final Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     * @param d shoot's direction
     */
    public void shoot(final Direction d) {
        if (System.currentTimeMillis() - this.now <= (long) this.e.getObjectProperty("Shoot Frequency")) {
            // da aggingere alla lista delle entità della
            // roomthis.eFactory.createBullet(this.e.getLocation().getX(),
            // this.e.getLocation().getY(), this.currentRoom, d);
        }
        now = System.currentTimeMillis();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        // il player si muove
        this.currentDirection.changeLocation(e.getLocation(), e.getDoubleProperty("Speed"));

        // controllo sulle dimensioni della stanza
        this.e.setImage(this.imgCalc.getCurrentImage(this.getCurrentDirection()));
        this.currentDirection = Direction.NOTHING;

    }

}
