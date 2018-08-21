package model.world;

import model.room.Room;

/**
 * 
 * Scan entity from file, used to populate rooms randomly from file.
 *
 */
public interface ScanEntity {

    /**
     * load the boss.
     * 
     * @param room
     *            room to fill
     */
    void loadBoss(Room room);

    /**
     * load the entities.
     * 
     * @param room
     *            room to fill
     */
    void loadEntity(Room room);

}
