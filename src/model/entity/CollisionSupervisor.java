package model.entity;

import java.util.List;
import model.Location;

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
     */
    void collisionWithBound(Entity e); // manca il riferimento alla stanza dalla quale l'enitità deve essere rimossa

    /**
     * the methods check if entity collide with some obstacles in the list of other
     * entities and act accordingly.
     * 
     * @param e
     *            entity that could collide with something
     * @param allEntities
     *            the other entities
     */
    void collisionWithObstacles(Entity e, List<Entity> allEntities);

}
