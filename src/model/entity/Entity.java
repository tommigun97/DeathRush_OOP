package model.entity;

import model.Location;

/**
 * The entities of the game.
 *
 */
public interface Entity {

    /**
     * Method for get the Path of the entity image.
     * 
     * @return String
     */
    String getImage();

    /**
     * Getter for entity location.
     * 
     * @return Location
     */
    Location getLocation();

}
