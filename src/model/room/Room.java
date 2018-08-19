package model.room;

import java.util.Set;
import model.entity.Entity;
/**
 * This class is used to create room where entities are put.
 * 
 */
public interface Room {

	/**
	 * Add door to the room
	 * @param Entity door
	 * 				door to add
	 */
    void addDoor(Entity door);
    
    /**
     * Getter method for getting doors
     * @return set<Entity>
     * 				return all room's door
     */
    Set<Entity> getDoor();

    /**
     * Getter method for roomID
     * @return int
     * 				return an int describing roomID
     */
    int getRoomID();

    /**
     * Method 
     * @return	boolean
     * 				if the room is visited return true else false
     */
    boolean isComplited();
    
    /**
     * Setter method 
     * @param boolean complited
     * 
     */
    void setComplited(boolean complited);

    /**
     * Method for adding entity to the room
     * @param Entity entity
     * 				Entity to add
     */
    void addEntity(Entity entity);

    /**
     * Method for deleting entity to the room
     * @param Entity entity
     * 				Entity to delete
     */
    void deleteEntity(Entity entity);

    /**
     * Getter method to take all entities in room
     * @return	Set<Entity>
     * 				all room's entities
     */
    Set<Entity> getEntities();

    
    /**
     * method for changing status door
     * open all room's door
     */
    void openDoors();

    /**
     * method for changing status door
     * close all room's door
     */
    void closeDoors();

    /**
     * Getter method for rooms image
     * @return	String
     * 				filename of room's image
     */
    String getImage();

    /**
     * Getter method for room type
     * @return	RoomType
     * 				room type
     */
    RoomType getType();

    /**
     * Setter method for setting up room visited
     * @param boolean x
     * 				true if is visited else false
     */
    void setVisited(boolean x);

    /**
     * Getter method to take visited
     * @return	boolean 
     */
    boolean isVisited();
}
