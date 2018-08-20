package model.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import model.room.RoomType;

/**
 * 
 * Enumeration of room files
 */
public enum BackgroundFromFile {

	
	ZERO("/rooms/0.room"), FIRST("/rooms/1.room"),
	SECOND("/rooms/2.room"), THIRD("/rooms/3.room"),
	FOURTH("/rooms/4.room"), FIVTH("/rooms/5.room"),
	SIXTH("/rooms/6.room"), SEVENTH("/rooms/7.room"),
	EIGHTH("/rooms/8.room"), NINETH("/rooms/9.room");

	//Variables
	private final String path;

	//Enum Constructor
	private BackgroundFromFile(final String path) {
		this.path = path;

	}

	/**
	 * Static enumeration method used to take a random room path
	 * @param type
	 * 			Rooms type
	 * @return	String 
	 * 				path 
	 */
	public static String getRandomPath(RoomType type) {
		int seq = type.equals(RoomType.INTERMEDIATE) ? new Random().nextInt(5) + 2
				: type.equals(RoomType.FIRTS) ? 1 : type.equals(RoomType.VENDOR) ? 9 : 0;
		return getPathName(seq);
	}

	/**
	 * 
	 * @param seqNumber
	 * @return String
	 * 			path
	 */
	private static String getPathName(int seqNumber) {
		return seqNumber == 1 ? BackgroundFromFile.FIRST.getPath()
				: seqNumber == 2 ? BackgroundFromFile.SECOND.getPath()
						: seqNumber == 3 ? BackgroundFromFile.THIRD.getPath()
								: seqNumber == 4 ? BackgroundFromFile.FOURTH.getPath()
										: seqNumber == 5 ? BackgroundFromFile.FIVTH.getPath()
												: seqNumber == 6 ? BackgroundFromFile.SIXTH.getPath()
														: seqNumber == 9 ? BackgroundFromFile.ZERO.getPath()
																: BackgroundFromFile.FIRST.getPath();
	}
	/**
	 * Getter method to take Enumeration path
	 * @return
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Getter method to take only bosses Enumeration path
	 * @return List<String>
	 * 				path of file roomType boss
	 */
	public static List<String> getBossPath() {
		List<String> listCopy = Arrays.asList(BackgroundFromFile.SEVENTH.getPath(), BackgroundFromFile.NINETH.getPath(),
				BackgroundFromFile.EIGHTH.getPath());
		return listCopy;
	}
}
