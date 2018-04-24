package model.entity;

/**
 * Entities that could collision with others.
 *
 */
public interface CollisionedEntity {
    /**
     * Entity checks if it has collisions with others entities in the Room.
     */
    void checkCollisions();

}
