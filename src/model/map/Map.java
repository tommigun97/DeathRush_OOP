package model.map;

import java.util.Optional;
import java.util.Set;

import model.entity.Door;
import model.room.Room;
import model.room.Room.Coordinates;

public interface Map {
    
    Set<Door> getDoors();
    
    Optional<Room> getRoom(int x);

    Optional<Door> getDoor(Room x, Room z);
    
    void addLink(Room x, Room y, Coordinates z);
    
    void addNewRoom(Room x);
}
