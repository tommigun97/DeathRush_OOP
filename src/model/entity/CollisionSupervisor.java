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
     * entities and act accordingly.
     * 
     * @param e
     *            entity that could collide with something
     * @param allEntities
     *            the other entities
     */
    void collisionWithObstacles(Entity e, Set<Entity> allEntities);

}
