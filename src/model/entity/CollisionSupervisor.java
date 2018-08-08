package model.entity;

import java.util.Set;

import model.Location;
import model.room.Room;

/**
 * This is the component of the game that have to check the collision between
 * Entities and also act accordingly.
 *
 */
public interface CollisionSupervisor {

    /**
     * the methods check if entity collide with the bound and in case of collision
     * reset entity previous position.
     * 
     * @param prev
     *            Entity position before the movement
     * @param e
     *            the Entity
     */
    void collisionWithBound(Location prev, Entity e);

    /**
     * the methods check if entity collide with the bound and in case of collision
     * remove the entity from the room.
     * 
     * @param e
     *            the entity
     * @param currentRoom
     *            room where Entity is set
     */
    void collisionWithBound(Entity e, Room currentRoom);

    /**
     * the methods check if entity collide with some obstacles in the list of other
     * entities and reset entity previous position.
     * 
     * @param e
     *            entity that could collide with something
     * @param allEntities
     *            the other entities
     * @param prev
     *            Entity position before the movement
     */
    void collisionWithObstacles(Entity e, Set<Entity> allEntities, Location prev);

    /**
     * the methods check if entity collide with some obstacles in the list of other
     * entities and delete the entity from room in case of collision.
     * 
     * @param e
     *            entity that could collide with something
     * @param currentRoom
     *            the room where entity is setted
     */
    void collisionWithObstacles(Entity e, Room currentRoom);

    /**
     * @param e
     *            entity to check if it collide with something
     * @param others
     *            other entities
     */
    void collisionBetweenEntities(Entity e, Set<Entity> others);

    /**
     * if the player collide with a door when it killed every enemy it could change
     * room.
     * 
     * @param p
     *            player
     * @param doors
     *            room doors
     */
    void collisionWithDoors(Entity p, Set<Entity> doors);

    /**
     * the methods check if entity collide with some obstacles in the list of other
     * entities and delete the entity from room in case of collision.
     * 
     * @param p
     *            player
     * @param other
     *            entities
     */
    void collisionWithPowerUp(Entity p, Set<Entity> other, Room current);

}
