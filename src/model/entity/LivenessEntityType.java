package model.entity;

import java.util.Optional;

import model.Direction;
import model.weapon.WeaponInterface;
import utilities.Pair;

enum Side {
    Dx, Sx;
}

/**
 * 
 *
 */
public enum LivenessEntityType {
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

    /**
     * @return
     */
    public int getMaxLife() {
        return maxLife;
    }

    /**
     * @return
     */
    public Optional<WeaponInterface> getWeapon() {
        return weapon;
    }

    /**
     * @return
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * @param currentDirection
     * @return
     */
    public String imageToDrow(final Direction direction) {
        //TODO
        Pair<String, String> images = new Pair<>(null, null);
            if (direction == Direction.N || direction == Direction.NW || direction == Direction.NE) {
                images = this.northImages;
            } else if (direction == Direction.S || direction == Direction.SW
                    || direction == Direction.SE) {
                images = this.southImages;
            } else if (direction == Direction.W) {
                images = this.westImages;
            } else if (direction == Direction.E) {
                images = this.eastImages;
            }
        this.changeCurrentSide();return this.currentSide==Side.Dx?images.getSecond():images.getFirst();

    }

    private void changeCurrentSide() {
        this.currentSide = currentSide == Side.Dx ? Side.Sx : Side.Dx;
    }

}
