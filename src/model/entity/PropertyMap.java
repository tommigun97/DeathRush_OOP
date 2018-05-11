package model.entity;

/**
 * data structure that contain entity's property.
 *
 */
public interface PropertyMap {

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
     * @return the object property
     */

    Object getObjectProperty(String property);

    /**
     * @param property
     *            the property chosen
     * @return the boolean property
     */

    boolean getBooleanProperty(String property);

    /**
     * @param property
     *            property's name
     * @param value
     *            property's value
     */
    void putProperty(String property, int value);
    /**
     * @param property
     *            property's name
     * @param value
     *            property's value
     */
    void changeProperty(String property, int value);

    /**
     * @param property
     *            property's name
     * @param value
     *            property's value
     */
    void putProperty(String property, double value);
    /**
     * @param property
     *            property's name
     * @param value
     *            property's value
     */
    void changeProperty(String property, double value);

    /**
     * @param property
     *            property's name
     * @param value
     *            property's value
     */
    void putProperty(String property, boolean value);
    /**
     * @param property
     *            property's name
     * @param value
     *            property's value
     */
    void changeProperty(String property, boolean value);

    /**
     * @param property
     *            property's name
     * @param value
     *            property's value
     */
    void putProperty(String property, Object value);
    /**
     * @param property
     *            property's name
     * @param value
     *            property's value
     */
    void changeProperty(String property, Object value);

    /**
     * @param property
     *            property to delete
     */
    void deleteProperty(String property);

}
