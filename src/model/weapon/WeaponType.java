package model.weapon;

import model.entity.BulletType;

/**
 * Enum that described the weapon that are used in the game.
 *
 */
public enum WeaponType {
    ;
    private double frequncy;
    private HowToShoot howToShoot;
    private BulletType bullet;

    /**
     * @param frequncy
     *            Frequency of the shoot in milliseconds
     * @param howToShoot
     *            how the gun shot the bullet
     * @param bullet
     *            the type of bullet shoot
     */
    WeaponType(final double frequncy, final HowToShoot howToShoot, final BulletType bullet) {
        this.frequncy = frequncy;
        this.howToShoot = howToShoot;
        this.bullet = bullet;
    }


    
    
    
    
    
    
    /**
     * @return the frequency
     */
    public double getFrequncy() {
        return frequncy;
    }

    /**
     * @return how o shoot a bullet
     */
    public HowToShoot getHowToShoot() {
        return howToShoot;
    }

    /**
     * @return bullet's type shooted
     */
    public BulletType getBullet() {
        return bullet;
    }

}
