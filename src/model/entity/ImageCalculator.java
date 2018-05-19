package model.entity;

import java.util.Optional;

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

}
