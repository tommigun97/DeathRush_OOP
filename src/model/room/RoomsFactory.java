package model.room;

/**
 * 
 * A factory for different room
 *
 */
public interface RoomsFactory {

    /**
     * Create a vendor Room.
     * 
     * @param roomID
     * 
     * @param isCompleted
     * 
     * @param isVisited
     * 
     * @return Room Vendor Room
     */
    Room vendorRoom(int roomID, boolean isCompleted, boolean isVisited);

    /**
     * Create a first Room.
     * 
     * @param roomID
     * 
     * @param isCompleted
     * 
     * @param isVisited
     * 
     * @return Room First Room
     */
    Room FirstRoom(int roomID, boolean isCompleted, boolean isVisited);

    /**
     * Create a Intermediate Room.
     * 
     * @param roomID
     * 
     * @param isCompleted
     * 
     * @param isVisited
     * 
     * @return Room Intermediate Room
     */
    Room IntermediateRoom(int roomID, boolean isCompleted, boolean isVisited);

    /**
     * Create a Boss Room.
     * 
     * @param roomID
     * 
     * @param isCompleted
     * 
     * @param isVisited
     * 
     * @return Room Boss Room
     */
    Room BossRoom(int roomID, boolean isCompleted, boolean isVisited);

}
