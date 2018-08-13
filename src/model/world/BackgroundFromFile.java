package model.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.room.RoomType;
/**
 * 
 * @author anisl
 *	Enumeration of poosible Entities Room 
 */
public enum BackgroundFromFile {

	 ZERO("/rooms/0.room", RoomType.VENDOR, 0), FIRST("/rooms/1.room", RoomType.INTERMEDIATE, 1), SECOND("/rooms/2.room", RoomType.INTERMEDIATE, 2),
	THIRD("/rooms/3.room", RoomType.INTERMEDIATE, 3), FOURTH("/rooms/4.room", RoomType.INTERMEDIATE, 4),
	FIVTH("/rooms/5.room", RoomType.INTERMEDIATE, 5), SIXTH("/rooms/6.room", RoomType.INTERMEDIATE, 6),
	SEVENTH("/rooms/7.room", RoomType.BOSS, 7), EIGHTH("/rooms/8.room", RoomType.BOSS, 8), NINETH("/rooms/9.room", RoomType.BOSS, 9);

	private final String path;
	private final RoomType type;
	private final int seqNumber;

	/**
	 * 
	 * @param path path for resouces file
	 * @param type type of resources file room
	 * @param seqNumber sequence number of file
	 */
	private BackgroundFromFile(final String path, final RoomType type, final int seqNumber) {
		this.path = path;
		this.type = type;
		this.seqNumber = seqNumber;
	}

	/**
	 * Returns a random path for the room's entities 
	 * 
	 * @param type 
	 * 		  type of room to populate with entities
	 * @return
	 * 		  String that indicate the path in resources
	 */
	public  static String getRandomPath(RoomType type) {
		int seq  = type.equals(RoomType.INTERMEDIATE) ? new Random().nextInt(5) + 2 : type.equals(RoomType.FIRTS) ? 1 :
						type.equals(RoomType.VENDOR) ? 9 : 0 ;
		return getPathName(seq);
	}

	/**
	 * Private method returns the path of enumeration that have the selected seqNumber
	 * @param seqNumber
	 * 			seqNumber of selected enumeration
	 * @return
	 * 			String that indicate the path of selected enumeration
	 */
	private static String getPathName(int seqNumber) {
		return seqNumber == 1 ? FIRST.getPath() : seqNumber == 2 ? SECOND.getPath() : seqNumber == 3 ? THIRD.getPath() : 
					seqNumber == 4 ? FOURTH.getPath() : seqNumber == 5 ? FIVTH.getPath() : seqNumber == 6 ? SIXTH.getPath() :
							seqNumber == 9 ? ZERO.getPath() : FIRST.getPath();
	} 

	/**
	 * Getter method of path
	 * @return
	 * 			String that indicate the path in resources
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * 
	 *	returns a list containing the Boss file paths
	 * @return
	 * 		 	a list of String that indicate the path for Bosses file in resouces
	 */
	public static List<String> getBossPath(){
		List<String> listCopy  = Arrays.asList(BackgroundFromFile.SEVENTH.getPath(), 
				BackgroundFromFile.NINETH.getPath(), BackgroundFromFile.EIGHTH.getPath());
		return listCopy;
	}
}
