package model.entity;

import model.Direction;
import model.Location;

/**
 * Bullet used by the Player and the Enemies.
 *
 */
public class Bullet implements Entity {
    private final boolean playerBulletIdentifier;
    private final BulletType type;
    private final Location location;
    private final Direction direction;

    /**
     * @param isPlayerBullet
     *            identify the bullets of the player to other
     * @param startPosition
     *            where the Bullet is shoot
     * @param direction
     *            DIrection of the Bullet
     * @param type
     *            type of bullet
     */
    public Bullet(final BulletType type, final boolean isPlayerBullet, final Location startPosition,
            final Direction direction) {
        super();
        this.type = type;
        this.playerBulletIdentifier = isPlayerBullet;
        this.location = startPosition;
        this.direction = direction;
    }

    /**
     * @return Damage of Bullet
     */
    public int getDamage() {
        return this.type.getDamage();
    }

    @Override
    public final String getImage() {
        return type.getImage();
    }

    @Override
    public final Location getLocation() {
        return this.location;
    }

    /**
     * @return true if the is a player bullet.
     */
    public boolean isPlayerBullet() {
        return playerBulletIdentifier;
    }

    /**
     * Update the position of the bullet using its direction.
     */
    public void move() {
        this.direction.changeLocation(this.location, this.type.getSpeed());
    }

}
