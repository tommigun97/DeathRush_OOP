package model.room;


import java.util.Set;

import model.entity.Door;



/**
 * 
 *
 *
 */
public abstract class AbstractRoom implements Room {

    private final int roomID;
    private boolean complited;
    /**
     * 
     * @param roomID 
     *     Rooms Id
     */
    public AbstractRoom(final int roomID) {
        this.roomID = roomID;
    }

    /**
     *  @return true if room is complited false else
     */
    public boolean isComplited() {
        return complited;
    }
    /**
     *  @param complited
     *          new complited status
     */
    public void setComplited(final boolean complited) {
        this.complited = complited;
    }
    /**
     *  @param door
     *          add door to the room
     */
    public abstract void addDoor(Door door);
    /**
     *  @return Set<Door>
     *          return Room's door
     */
     public abstract Set<Door> getDoor();
     /**
      *  @return int
      *         return roomId
      */
     public int getRoomID() {
         return roomID;
     }
     @Override
    public final String toString() {
         return "" + this.roomID;
     }

}
