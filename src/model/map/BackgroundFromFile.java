package model.map;

import java.util.Random;
import model.room.RoomType;

public enum BackgroundFromFile {

	FIRST("roomsFile/1.txt", RoomType.INTERMEDIATE, 1), SECOND("roomsFile/2.txt", RoomType.INTERMEDIATE, 2),
	THIRD("roomsFile3/3.txt", RoomType.INTERMEDIATE, 3), FOURTH("roomsFile/4.txt", RoomType.INTERMEDIATE, 4),
	FIVTH("roomsFile/5.txt", RoomType.INTERMEDIATE, 5), SIXTH("roomsFile/6.txt", RoomType.BOSS, 6),
	SEVENTH("roomsFile/7.txt", RoomType.BOSS, 7), EIGHTH("roomsFile/8.txt", RoomType.BOSS, 8);

	private final String path;
	private final RoomType type;
	private final int seqNumber;

	private BackgroundFromFile(final String path, final RoomType type, final int seqNumber) {
		this.path = path;
		this.type = type;
		this.seqNumber = seqNumber;
	}

	public  static String getRandomPath(RoomType type) {
		int seq  = type.equals(RoomType.INTERMEDIATE) ? new Random().nextInt(4) + 1 : 
					type.equals(RoomType.BOSS) ? new Random().nextInt(3) + 5 : 0;
		return getPath(seq);
	}

	private static String getPath(int seqNumber) {
		return seqNumber == 1 ? FIRST.getPath() : seqNumber == 2 ? SECOND.getPath() : seqNumber == 3 ? THIRD.getPath() : 
					seqNumber == 4 ? FOURTH.getPath() : seqNumber == 5 ? FIVTH.getPath() : seqNumber == 6  ? SIXTH.getPath() : 
						seqNumber == 7 ? SEVENTH.getPath() : seqNumber == 8 ? EIGHTH.getPath() : "";
						
	}

	public String getPath() {
		return this.path;
	}
}
