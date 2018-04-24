package model.entity;

import java.util.Optional;

import model.Direction;
import model.weapon.BasicWeapon;
import model.weapon.WeaponInterface;
import utilities.Pair;

/**
 * 
 *
 */
public enum LivenessEntityType {
    DEFAULT_PLAYER(4, null, 0.3, Side.Dx, null, null, null, null, "", true),
    DEFAULT_ENEMY(4, null, 0.3, Side.Dx, null, null, null, null, "", true),

    ;
    private int maxLife;
    private Optional<WeaponInterface> weapon;
    private double movementSpeed;
    private Side currentSide;
    private Pair<String, String> northImages;
    private Pair<String, String> southImages;
    private Pair<String, String> westImages;
    private Pair<String, String> eastImages;
    private String stand;
    private boolean consistence;

    LivenessEntityType(final int maxLife, final WeaponInterface weapon, final double movementSpeed,
            final Side currentSide, final Pair<String, String> northImages, final Pair<String, String> southImages,
            final Pair<String, String> westImages, final Pair<String, String> eastImages, final String stand,
            final boolean consistance) {
        this.maxLife = maxLife;
        this.weapon = Optional.ofNullable(weapon);
        this.movementSpeed = movementSpeed;
        this.currentSide = currentSide;
        this.northImages = northImages;
        this.southImages = southImages;
        this.westImages = westImages;
        this.eastImages = eastImages;
        this.stand = stand;
        this.consistence = consistance;
    }

    public boolean isConsistence() {
        return consistence;
    }

    private void changeCurrentSide() {
        this.currentSide = currentSide == Side.Dx ? Side.Sx : Side.Dx;
    }

    /**
     * @return
     */
    public int getMaxLife() {
        return maxLife;
    }

    /**
     * @return
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * @return
     */
    public Optional<WeaponInterface> getWeapon() {
        return weapon;
    }

    /**
     * @param currentDirection
     * @return
     */
    public String imageToDrow(final Direction direction) {
        // TODO
        Pair<String, String> images = new Pair<>(null, null);
        if (direction == Direction.N || direction == Direction.NW || direction == Direction.NE) {
            images = this.northImages;
        } else if (direction == Direction.S || direction == Direction.SW || direction == Direction.SE) {
            images = this.southImages;
        } else if (direction == Direction.W) {
            images = this.westImages;
        } else if (direction == Direction.E) {
            images = this.eastImages;
        }
        this.changeCurrentSide();
        return this.currentSide == Side.Dx ? images.getSecond() : images.getFirst();

    }

}

enum Side {
    Dx, Sx;
}
