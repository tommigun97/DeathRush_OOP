package model.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.room.RoomType;

public enum BackgroundFromFile {

	 ZERO("rooms/0.txt", RoomType.VENDOR, 0), FIRST("rooms/1.txt", RoomType.INTERMEDIATE, 1), SECOND("rooms/2.txt", RoomType.INTERMEDIATE, 2),
	THIRD("rooms/3.txt", RoomType.INTERMEDIATE, 3), FOURTH("rooms/4.txt", RoomType.INTERMEDIATE, 4),
	FIVTH("rooms/5.txt", RoomType.INTERMEDIATE, 5), SIXTH("rooms/6.txt", RoomType.INTERMEDIATE, 6),
	SEVENTH("rooms/7.txt", RoomType.BOSS, 7), EIGHTH("rooms/8.txt", RoomType.BOSS, 8), NINETH("rooms/9.txt", RoomType.BOSS, 9);

	private final String path;
	private final RoomType type;
	private final int seqNumber;

	private BackgroundFromFile(final String path, final RoomType type, final int seqNumber) {
		this.path = path;
		this.type = type;
		this.seqNumber = seqNumber;
	}

	public  static String getRandomPath(RoomType type) {
		int seq  = type.equals(RoomType.INTERMEDIATE) ? new Random().nextInt(5) + 2 : type.equals(RoomType.FIRTS) ? 1 :
						type.equals(RoomType.VENDOR) ? 9 : 0 ;
		return getPathName(seq);
	}


	private static String getPathName(int seqNumber) {
		return seqNumber == 1 ? FIRST.getPath() : seqNumber == 2 ? SECOND.getPath() : seqNumber == 3 ? THIRD.getPath() : 
					seqNumber == 4 ? FOURTH.getPath() : seqNumber == 5 ? FIVTH.getPath() : seqNumber == 6 ? SIXTH.getPath() :
							seqNumber == 9 ? ZERO.getPath() : FIRST.getPath();
	} 

	public String getPath() {
		return this.path;
	}
	
	public static List<String> getBossPath(){
		List<String> listCopy  = Arrays.asList(BackgroundFromFile.SEVENTH.getPath(), 
				BackgroundFromFile.NINETH.getPath(), BackgroundFromFile.EIGHTH.getPath());
		return listCopy;
	}
}
