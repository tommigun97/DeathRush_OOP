package model.entity;
import java.util.Optional;

import model.Direction;
import model.room.Room;

/**
 * Class define player behavior.
 *
 */
public class PlayerBehavior implements Behavior {

    private Entity e;
    private Room currentRoom;
    private EntityFactory eFactory;
    private Optional<Direction> currentDirection;
    private long now;
    // bisogna mettergli un image calculator private ImageCalculator imgCalc;    

    public PlayerBehavior(Room currentRoom, EntityFactory eFactory) {
        this.currentRoom = currentRoom;
        this.eFactory = eFactory;
        this.currentDirection = Optional.empty();
        now = System.currentTimeMillis();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public EntityFactory geteFactory() {
        return eFactory;
    }

    public void seteFactory(EntityFactory eFactory) {
        this.eFactory = eFactory;
    }

    @Override
    public void setEntity(final Entity e) {
        this.e = e;

    }

    public Optional<Direction> getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(final Direction currentDirection) {
        this.currentDirection = Optional.of(currentDirection);
    }

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
        this.currentDirection.get().changeLocation(e.getLocation(), e.getDoubleProperty("Speed"));
        //controllo sulle dimensioni della stanza
        //mettere una nuova immagine
        this.currentDirection = Optional.empty();

    }

}
