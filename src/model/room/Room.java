package model.room;

import java.util.Set;

import model.entity.Door;

/**
 * 
 *
 */
public interface Room {
    /**
     * 
     * @param doorList
     *            .
     */
    void addDoor(Door doorList);

    /**
     * 
     * @return .
     */
    Set<Door> getDoor();

    /**
     * 
     * @return .
     */
    int getRoomID();

    /**
     * 
     * @return .
     */
    boolean isComplited();

    /**
     * 
     * @param complited
     *            .
     */
    void setComplited(boolean complited);
}
