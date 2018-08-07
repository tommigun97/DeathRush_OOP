package model.room;

import java.util.List;
import java.util.Set;
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
    
    void addDoor(Entity doorList);

    /**
     * 
     * @return .
     */
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

    Set<Entity> getEntities();
    
    void openDoors();
    
    String getImage();
    
    RoomType getType();
}
