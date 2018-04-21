package model.entity;

import model.Location;

/**
 * @author Simone Del Gatto
 *
 */
public interface EntityIterface {

    /**
     * Getter for entity location.
     * 
     * @return Location
     */
    Location getLocation();

    /**
     * Method for get the Path of the entity image.
     * 
     * @return String
     */
    String getImage();

}
