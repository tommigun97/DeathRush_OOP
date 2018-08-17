package model.room;

import java.util.Set;
import model.entity.Entity;
/**
 * This class is used to create room.
 * 
 *
 */
public interface Room {

    void addDoor(Entity doorList);

    Set<Entity> getDoor();

    int getRoomID();

    boolean isComplited();

    void setComplited(boolean complited);

    void addEntity(Entity entity);

    void deleteEntity(Entity entity);

    Set<Entity> getEntities();

    void openDoors();

    void closeDoors();

    String getImage();

    RoomType getType();

    void setVisited(boolean x);

    boolean isVisited();
}
