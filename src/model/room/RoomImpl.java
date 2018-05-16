package model.room;


import java.util.List;

import java.util.Set;

import model.entity.Door;
import model.entity.Entity;

import model.entity.Door.DoorStatus;


/**
 * 
 *
 *
 */
public class RoomImpl implements Room {

    private final String image;
    private final int roomID;
    private boolean complited;
    private final RoomType type;
    private List<Entity> entitiesRoom;
    private Set<Door> doorsRoom;
    /**
     * 
     * @param roomID 
     *     Rooms Id
     */
    public RoomImpl(final String image, final int roomID, boolean complited, final RoomType type, List<Entity> entitiesRoom, Set<Door> doorsRoom) {
        this.image = image;
        this.roomID = roomID;
        this.complited = complited;
        this.type = type;
        this.entitiesRoom = entitiesRoom;
        this.doorsRoom = doorsRoom;
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
    public void addDoor(Door door) {
        this.doorsRoom.add(door);
    }
    /**
     *  @return Set<Door>
     *          return Room's door
     */
     public  Set<Door> getDoor(){
         return this.doorsRoom;
     }
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
     
    public void openDoors() {
        this.doorsRoom.forEach(x -> x.setDoorStatus(DoorStatus.OPEN));
    }
    public void closeDoors() {
        this.doorsRoom.forEach(x -> x.setDoorStatus(DoorStatus.CLOSE));
    }
    

     public static class RoomBuilder{
         
         private String image;
         private int roomID;
         private boolean complited;
         private RoomType type;
         private List<Entity> entitiesRoom;
         private Set<Door> doorsRoom;
     
         public RoomBuilder setImage(final String image) {
             this.image = image;
             return this;
         }
         
         public RoomBuilder setRoomID(final int roomID) {
             this.roomID = roomID;
             return this;
         }
         
         public RoomBuilder setComplited(final boolean complited) {
             this.complited = complited;
             return this;
         }
         public RoomBuilder setTypes(final RoomType type) {
             this.type = type;
             return this;
         }
         public RoomBuilder setEnemies(final List<Entity> entitiesRoom) {
             this.entitiesRoom = entitiesRoom;
             return this;  
         }
         public RoomBuilder setDoors(final Set<Door> doorsRoom) {
             this.doorsRoom = doorsRoom;
             return this;
         }
         
         public RoomImpl build() {
             return new RoomImpl(image, roomID, complited, type, entitiesRoom, doorsRoom);
         }
     }
         
}
