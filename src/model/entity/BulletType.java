package model.entity;

/**
 * enum for the description of bullets.
 *
 */
public enum BulletType {
    ;
    private final double speed;
    private final int damage;
    private final String image;

    /**
     * @param speed
     *            Speed of bullet
     * @param damage
     *            Damage of Bullet
     * @param startPosition
     *            where the Bullet is shoot
     * @param direction
     *            Direction of the Bullet
     * @param image
     *            Image of bullet
     */
    BulletType(final double speed, final int damage, final String image) {
        this.speed = speed;
        this.damage = damage;
        this.image = image;
    }

    /**
     * @return damage of bullet
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return image of bullet
     */
    public String getImage() {
        return image;
    }

    /**
     * @return speed of bullet
     */
    public double getSpeed() {
        return speed;
    }

}
