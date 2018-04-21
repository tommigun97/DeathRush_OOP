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
    public String imageToDrow(final Optional<Direction> direction) {
        //TODO
        Pair<String, String> images = new Pair<>(null, null);
        if (!direction.isPresent()) {
            return this.stand;
        } else {
            if (direction.get() == Direction.N || direction.get() == Direction.NW || direction.get() == Direction.NE) {
                images = this.northImages;
            } else if (direction.get() == Direction.S || direction.get() == Direction.SW
                    || direction.get() == Direction.SE) {
                images = this.southImages;
            } else if (direction.get() == Direction.W) {
                images = this.westImages;
            } else if (direction.get() == Direction.E) {
                images = this.eastImages;
            }
        }
        this.changeCurrentSide();
        return this.currentSide == Side.Dx ? images.getSecond() : images.getFirst();
    }

    private void changeCurrentSide() {
        this.currentSide = currentSide == Side.Dx ? Side.Sx : Side.Dx;
    }
    
    

}
