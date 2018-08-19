package model.world;

import model.room.Room;

/**
 * 
 * Scan entity from file, used to populate rooms randomly from file
 *
 */
public interface ScanEntity {
	
	/**
	 * 
	 * @param room
	 */
	void loadBoss(Room room);
	
	/**
	 * 
	 * @param x
	 */
	void loadEntity(Room room);

}
