package model.map;

import java.util.Optional;
import java.util.Set;
import model.entity.Door.DoorStatus;
import model.entity.Door;
import model.room.Room;

public interface Map {

    Set<Door> getDoors();

    Optional<Room> getRoom(int x);

    Optional<Door> getDoor(Room x, Room z);

    void addLink(Room x, Room y, Coordinates z, DoorStatus statusLink);

    void addNewRoom(Room x);
}
