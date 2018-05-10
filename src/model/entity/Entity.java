package model.entity;

import java.util.Optional;

import model.Location;

/**
 * Described every entities of the game.
 *
 */
public interface Entity {
    /**
     * @return image of the entities to draw
     */
    String getImage();

    /**
     * @param image
     *            path of the image
     */
    void setImage(String image);

    /**
     * @return where the entity is placed
     */
    Location getLocation();

    /**
     * @param loc
     *            new location
     */
    void setLocation(Location loc);

    /**
     * @param property
     *            that someone want get
     * @return the correct property
     */
    Object getProperty(String property);

    /**
     * @return the entity's behavior
     */
    Optional<Behavior> getBehaviour();

    /**
     * @return the type of the entity
     */
    EntityType getType();



}
