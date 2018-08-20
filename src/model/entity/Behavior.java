package model.entity;

/**
 * Component that described the behavior of the entity.
 *
 */
public interface Behavior {
    /**
     * Set the entity.
     * @param e
     *            entity that the behavior is attached
     */
    void setEntity(Entity e); 

    /**
     * Update entity status.
     * method for update the entity status.
     */
    void update();

}
