package model.entity;

import model.room.Room;
import utilities.Pair;

/**
 * 
 * @author anisl
 *
 */
public interface Door {
    /**
     * 
     * @author anisl
     *
     */
   enum DoorStatus{
       OPEN, CLOSE;
   }

    /**
     * Method for getting Room that are link by Door.
     * 
     * @return Pair<Room, Room>
     */
    Pair<Room, Room> getLink();

    /**
     * 
     * @param status
     *            Door status
     */
    void setDoorStatus(DoorStatus status);

    /**
     * 
     * @return setDoor
     */
    DoorStatus getDoorStatus();
}
