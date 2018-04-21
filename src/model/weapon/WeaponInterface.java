package model.weapon;

import model.Direction;
import model.Location;
import model.RoomInterface;

/**
 *
 *
 */
public interface WeaponInterface {

    /**
     * Shoot a bullet.
     * @param direction Direction of Bullets
     * @param room Room where the entity Bullet is added
     * @param isPlayerProjectile identify player Bullets
     * @param startPosition location where the Bullet shoot started
     */
    void shoot(Direction direction, RoomInterface room, boolean isPlayerProjectile, Location startPosition);

    /**
     * @param frequency Frequency of the shoots
     */
    void setFrequency(double frequency);

}
