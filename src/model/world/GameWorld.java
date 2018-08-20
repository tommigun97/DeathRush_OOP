package model.world;

import java.util.Optional;

import java.util.Set;
import model.entity.Entity;
import model.room.Room;

/**
 * 
 * Game World.
 *
 */
public interface GameWorld {

    /**
     * Getter method to take all doors in game.
     * 
     * @return Set<Entity> door set.
     * 
     */
    Set<Entity> getDoors();

    /**
     * Getter method to take a single room.
     * 
     * @param x
     *            roomID
     * 
     * @return Optional<Room> room with roomID = x
     * 
     */
    Optional<Room> getRoom(int x);

    /**
     * Getter method to take all rooms.
     * 
     * @return Set<Room>
     * 
     */
    Set<Room> getRooms();

    /**
     * Getter method to take matrix map.
     * 
     * @return Room[][] matrix map matrix that represents game world
     * 
     */
    Room[][] getMatrixMap();

    /**
     * Getter method to take a matrix to show the player.
     * 
     * @return int[][] matrix map
     * 
     */
    int[][] getMatrixView();

    /**
     * Getter method to take matrix map column.
     * 
     * @return int
     * 
     */
    int getColumnMatrix();

    /**
     * Getter method to take matrix map row.
     * 
     * @return int
     * 
     */
    int getRowMatrix();

    /**
     * Initiale the game world.
     * 
     */
    void buildWorldGame();

    /**
     * Update matrix map view.
     * 
     */
    void matrixViewUpdate();

    /**
     * @return true if all room are completed
     * 
     */
    boolean allRoomAreCompleted();
}
