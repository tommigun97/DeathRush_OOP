package model.weapon;

import model.Direction;
import model.Location;
import model.RoomInterface;
import model.entity.BulletType;

/**
 * Normal Weapon.
 *
 */
public class BasicWeapon implements WeaponInterface {

    private final BulletType bullet;
    private final HowToShoot howToShoot;
    private double frequency;

    /**
     * Constructor for the weapon.
     * 
     * @param type
     *            the type of the weapon
     */
    public BasicWeapon(final WeaponType type) {
        this.bullet = type.getBullet();
        this.howToShoot = type.getHowToShoot();
        this.frequency = type.getFrequncy();

    }

    @Override
    public final void shoot(final Direction direction, final RoomInterface room, final boolean isPlayerProjectile,
            final Location startPosition) {
        // add the check of frequency
        this.howToShoot.shoot(direction, room, isPlayerProjectile, startPosition, bullet);

    }

    @Override
    public final void setFrequency(final double frequency) {
        this.frequency = frequency;

    }

}
