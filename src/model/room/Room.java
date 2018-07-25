package model.room;

import java.util.Set;

import model.entity.Door;
import model.entity.Entity;

/**
 * 
 *
 */
public interface Room {
    /**
     * 
     * @param doorList
     *            .
     */
    void addDoor(Door doorList);
    void addDoor(Entity doorList);

    /**
     * 
     * @return .
     */
    Set<Door> getDoor();
    Set<Entity> getDoor();

    /**
     * 
     * @return .
     */
    int getRoomID();

    /**
     * 
     * @return .
     */
    boolean isComplited();

    /**
     * 
     * @param complited
     *            .
     */
    void setComplited(boolean complited);
    
    /**
     * 
     * #@return
     * 
     */
    void addEntity(Entity entity);
    /**
     * 
     * #@return
     * 
     */
    void deleteEntity(Entity entity);
    
}
