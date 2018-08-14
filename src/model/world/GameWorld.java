package model.world;

import java.util.Optional;
import java.util.Set;
import model.entity.DoorStatus;
import model.entity.Entity;
import model.room.Room;

public interface GameWorld {

    Set<Entity> getDoors();

    Optional<Room> getRoom(int x);
    
    Set<Room> getRooms();
     
    Room[][] getPath();

    int[][] getMatrixView();
    
    int getColumnMatrix();
    
    int getRowMatrix();
    
    void buildWorldGame();
    
    void matrixViewUpdate();
    /**
     * @return true if all room are completed
     */
    boolean allRoomAreCompleted();
}
