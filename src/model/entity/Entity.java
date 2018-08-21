package model.entity;

import java.util.Optional;

import model.Location;

/**
 * Described every entities of the game.
 *
 */
public interface Entity {
    /**
     * Getter for entity's image.
     * 
     * @return image of the entities to draw
     */
    String getImage();

    /**
     * Setter for entity's image.
     * 
     * @param image
     *            path of the image
     */
    void setImage(String image);

    /**
     * Getter for entity's Location.
     * 
     * @return where the entity is placed
     */
    Location getLocation();

    /**
     * Setter for entity's Location.
     * 
     * @param loc
     *            new location
     */
    void setLocation(Location loc);

    /**
     * Getter for entity's behavior.
     * 
     * @return the entity's behavior
     */
    Optional<Behavior> getBehaviour();

    /**
     * Getter for entity's type.
     * 
     * @return the type of the entity
     */
    EntityType getType();

    /**
     * Getter for entity's integer property.
     * 
     * @param property
     *            the property chosen
     * @return the int property
     */
    int getIntegerProperty(String property);

    /**
     * Getter for entity's double property.
     * 
     * @param property
     *            the property chosen
     * @return the double property
     */
    double getDoubleProperty(String property);

    /**
     * Getter for entity's boolean property.
     * 
     * @param property
     *            the property chosen
     * @return the boolean property
     */
    boolean getBooleanProperty(String property);

    /**
     * Getter for entity's object property.
     * 
     * @param property
     *            the property chosen
     * @return the Object property
     */
    Object getObjectProperty(String property);

    /**
     * Setter for entity's integer property.
     * 
     * @param property
     *            property to change
     * @param value
     *            property's new value
     */
    void changeIntProperty(String property, int value);

    /**
     * Setter for entity's double property.
     * 
     * @param property
     *            property to change
     * @param value
     *            property's new value
     */
    void changeDoubleProperty(String property, double value);

    /**
     * Setter for entity's boolean property.
     * 
     * @param property
     *            property to change
     * @param value
     *            property's new value
     */
    void changeBooleanProperty(String property, boolean value);

    /**
     * Setter for entity's object property.
     * 
     * @param property
     *            property to change
     * @param value
     *            property's new value
     */
    void changeObjectProperty(String property, Object value);

}
