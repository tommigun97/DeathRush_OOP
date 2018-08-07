package model.map;

import java.util.Random;
import model.room.RoomType;

public enum BackgroundFromFile {

	FIRST("roomsFile.1.txt", RoomType.INTERMEDIATE, 1), SECOND("roomsFile.2.txt", RoomType.INTERMEDIATE, 2),
	THIRD("roomsFile.3.txt", RoomType.INTERMEDIATE, 3), FOURTH("roomsFile.4.txt", RoomType.INTERMEDIATE, 4),
	FIVTH("roomsFile.5.txt", RoomType.INTERMEDIATE, 5), SIXTH("roomsFile.6.txt", RoomType.BOSS, 6),
	SEVENTH("roomsFile.7.txt", RoomType.BOSS, 7), EIGHTH("roomsFile.8.txt", RoomType.BOSS, 8);

	private final String path;
	private final RoomType type;
	private final int seqNumber;

	private BackgroundFromFile(final String path, final RoomType type, final int seqNumber) {
		this.path = path;
		this.type = type;
		this.seqNumber = seqNumber;
	}

	public  static String getRandomPath(RoomType type) {
		int seq  = type.equals(RoomType.INTERMEDIATE) ? new Random().nextInt(5) + 1 : new Random().nextInt(3) +5;
		return getPath(seq);
	}

	private static String getPath(int seqNumber) {
		return seqNumber == 1 ? "roomsFile.1.txt" : seqNumber == 2 ? "roomsFile.2.txt" : seqNumber == 3 ? "roomsFile.3.txt" : 
					seqNumber == 4 ? "roomsFile.4.txt" : seqNumber == 5 ? "roomsFile.5.txt" : seqNumber == 6  ? "roomsFile.6.txt" : 
						seqNumber == 7 ? "roomsFile.7.txt" : "roomsFile.8.txt";
						
	}

}
