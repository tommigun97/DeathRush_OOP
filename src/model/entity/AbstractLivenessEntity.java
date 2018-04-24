package model.entity;

import java.util.Optional;

import model.Direction;
import model.Location;
import model.RoomInterface;
import model.weapon.WeaponInterface;

/**
 * 
 *
 */
public abstract class AbstractLivenessEntity implements Entity, LivenessEntityInterface, CollisionedEntity {

    private int maxLife;
    private int life;
    private Location location;
    private Optional<WeaponInterface> weapon;
    private Direction currentDirection;
    private RoomInterface currentRoom;
    private double movementSpeed;
    private LivenessEntityType type;
    private final boolean consistence;

    /**
     * 
     * @param location
     *            Entity's starting location
     * @param currentRoom
     *            Room where Entity is placed
     * @param type
     *            Type of entity, one among the types described in the enum
     *            EntityType
     */
    public AbstractLivenessEntity(final Location location, final RoomInterface currentRoom,
            final LivenessEntityType type, final Direction currentDirection) {
        this.life = type.getMaxLife();
        this.location = location;
        this.weapon = type.getWeapon();
        this.currentDirection = null;
        this.currentRoom = currentRoom;
        this.movementSpeed = type.getMovementSpeed();
        this.maxLife = type.getMaxLife();
        this.consistence = type.isConsistence();
        this.currentDirection = currentDirection;
    }

    /**
     * @return get the current Direction
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * @return Room where Entity is placed
     */
    public RoomInterface getCurrentRoom() {
        return currentRoom;
    }

    /*
     * (non-Javadoc)
     * 
     * @see model.entity.EntityIterface#getImage()
     */
    @Override
    public String getImage() {
        return type.imageToDrow(this.currentDirection);
    }

    /**
     * @return how much life has the entity
     */
    public int getLife() {
        return life;
    }

    @Override
    public final Location getLocation() {
        return location;
    }

    /**
     * @return The max value for Entity's life
     */
    public int getMaxLife() {
        return maxLife;
    }

    /**
     * @return Entity's movements speed
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * @return Entity type
     */
    public LivenessEntityType getType() {
        return this.type;
    }

    /**
     * @return the Entity current Weapon
     */
    public Optional<WeaponInterface> getWeapon() {
        return weapon;
    }

    /**
     * @param increment
     *            how much the life is increased
     */
    public void increaseLife(final int increment) {
        if (increment > 0) {
            throw new IllegalArgumentException("The increment it must be a positive number");
        }
        life = life >= this.maxLife ? life : life + increment;
    }

    /**
     * @param damage
     *            how much the life are decreased
     */
    public void loseLife(final int damage) {
        if (damage <= 0) {
            throw new IllegalArgumentException("The damage it must be a positive number");
        }
        life -= damage;
    }

    @Override
    public void move() {
//        if (!this.consistence) {
//            this.currentDirection.changeLocation(this.location, this.movementSpeed);
//        } else {
//            final Location prev = this.location;
//            this.currentDirection.changeLocation(this.location, this.movementSpeed);
//            if (this.currentRoom.getEntities().stream().filter(e -> e instanceof Obstacles)
//                    .anyMatch(e -> this.location.locationsCollide(e.getLocation()))) {
//                this.location = prev;
//            }
//        }
        this.currentDirection.changeLocation(this.location, this.movementSpeed);

    }

    /**
     * @param currentDirection
     *            change the Entity Direction
     */
    public void setCurrentDirection(final Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     * @param currentRoom
     *            the new Entity's current Room
     */
    public void setCurrentRoom(final RoomInterface currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * @param location
     *            the new Entity's Location
     */
    public void setLocation(final Location location) {
        this.location = location;
    }

    /**
     * @param maxLife
     *            max Value that Entity could have
     */
    public void setMaxLife(final int maxLife) {
        this.maxLife = maxLife;
    }

    /**
     * @param movementSpeed
     *            new Entity's movements speed value
     */
    public void setMovementSpeed(final double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * @param weapon
     *            set a new Entity Weapon
     */
    public void setWeapon(final Optional<WeaponInterface> weapon) {
        this.weapon = weapon;
    }

}
