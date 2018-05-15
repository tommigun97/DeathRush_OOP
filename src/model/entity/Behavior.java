package model.entity;

/**
 * Component that described the behavior of the entity.
 *
 */
public interface Behavior {
    /**
     * @param e
     *            entity that the behavior is attached
     */
    void setEntity(Entity e);

    /**
     * method for update the entity status.
     */
    void update();

}
