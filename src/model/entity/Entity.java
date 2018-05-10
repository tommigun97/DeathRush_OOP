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
     * @return the entity's behavior
     */
    Optional<Behavior> getBehaviour();

    /**
     * @return the type of the entity
     */
    EntityType getType();

    /**
     * @param property
     *            the property chosen
     * @return the int property
     */
    int getIntegerProperty(String property);

    /**
     * @param property
     *            the property chosen
     * @return the double property
     */

    double getDoubleProperty(String property);

    /**
     * @param property
     *            the property chosen
     * @return the boolean property
     */

    boolean getBooleanProperty(String property);

    /**
     * @param property
     *            property to change
     * @param value
     *            property's new value
     */
    void changeIntProperty(String property, int value);

    /**
     * @param property
     *            property to change
     * @param value
     *            property's new value
     */
    void changeDoubleProperty(String property, double value);

    /**
     * @param property
     *            property to change
     * @param value
     *            property's new value
     */
    void changeBooleanProperty(String property, boolean value);

}
