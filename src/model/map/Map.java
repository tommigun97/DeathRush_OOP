package model.map;

import java.util.Optional;
import java.util.Set;
import model.entity.DoorStatus;
import model.entity.Entity;
import model.room.Room;

public interface Map {

    Set<Entity> getDoors();

    Optional<Room> getRoom(int x);
    
    Set<Room> getRooms();
     
    Room[][] getPath();
    
    /**
     * @return true if all room are completed
     */
    boolean allRoomAreCompleted();
}
