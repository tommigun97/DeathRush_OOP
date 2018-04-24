package model;

import java.util.List;

import model.entity.Entity;

/**
 * description of a current room.
 *
 */
public interface RoomInterface {
    /**
     * Add an entity to the Room.
     * 
     * @param entity
     *            the Entities to add
     */
    void addEntities(Entity entity);

    /**
     * @return List of the entities in the room
     */
    List<Entity> getEntities();
}
