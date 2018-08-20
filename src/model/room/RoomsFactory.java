package model.room;

/**
 * 
 * A factory for different room.
 *
 */
public interface RoomsFactory {

    /**
     * Create a vendor Room.
     * 
     * @param roomID
     *            room id
     * 
     * @param isVisited
     *            true if is just visited
     * 
     * @return Room Vendor Room
     */
    Room vendorRoom(int roomID, boolean isVisited);

    /**
     * Create a first Room.
     * 
     * @param roomID
     *            room id
     * 
     * @param isVisited
     *            true if is just visited
     * 
     * @return Room First Room
     */
    Room firstRoom(int roomID, boolean isVisited);

    /**
     * Create a Intermediate Room.
     * 
     * @param roomID
     *            room id
     * 
     * @param isVisited
     *            true if is just visited
     * 
     * @return Room Intermediate Room
     */
    Room intermediateRoom(int roomID, boolean isVisited);

    /**
     * Create a Boss Room.
     * 
     * @param roomID
     *            room id
     * 
     * @param isVisited
     *            true if is just visited
     * 
     * @return Room Boss Room
     */
    Room bossRoom(int roomID, boolean isVisited);

}
