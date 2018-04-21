package model.weapon;

import model.Direction;
import model.Location;
import model.RoomInterface;
import model.entity.BulletType;

/**
 * Method that described what happen when someone shoot with a weapon.
 *
 */
public interface HowToShoot {
    /**
     * @param direction
     *            Direction of Bullets
     * @param room
     *            Room where the entity Bullet is added
     * @param isPlayerProjectile
     *            identify player Bullets
     * @param startPosition
     *            location where the Bullet shoot started
     * @param type
     *            type of bullets
     */
    void shoot(Direction direction, RoomInterface room, boolean isPlayerProjectile, Location startPosition,
            BulletType type);

}
