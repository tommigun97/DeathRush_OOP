package model.entity;

/**
 * Entities that could dies.
 *
 */
public interface DeadlyEntity {
    /**
     * Entity check if it has a collision with others entities in the Room.
     */
    void checkCollisions();

}
