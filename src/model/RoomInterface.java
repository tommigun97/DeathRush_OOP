package model;

import model.entity.EntityIterface;

/**
 * description of a current room.
 *
 */
public interface RoomInterface {
    /**
     * Add an entity to the Room
     * 
     * @param entity
     *            the Entities to add
     */
    void addEntities(EntityIterface entity);
}
