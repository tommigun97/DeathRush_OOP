package model.entity;

import model.Direction;

/**
 * Define an Object that calculate the Entity current Image.
 *
 */
public interface ImageCalculator {

    /**
     * @param d
     *            the direction where the entity moves
     * @return the path of the correct image
     */
    String getCurrentImage(Direction d);

    /**
     * return true when is time to refresh.

     * @param lastTimeSaved
     *            last time that this method is used
     * @return true if is time to refresh
     */
    default boolean refresh(long lastTimeSaved) {
        boolean b = System.currentTimeMillis() - lastTimeSaved >= 100;
        return b;
    }

}
