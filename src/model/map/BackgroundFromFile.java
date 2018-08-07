package model.map;

import java.util.Random;
import model.room.RoomType;

public enum BackgroundFromFile {

	FIRST("1", RoomType.INTERMEDIATE, 1), SECOND("2", RoomType.INTERMEDIATE, 2),
	THIRD("3", RoomType.INTERMEDIATE, 3), FOURTH("4", RoomType.INTERMEDIATE, 4),
	FIVTH("5", RoomType.INTERMEDIATE, 5), SIXTH("6", RoomType.BOSS, 6),
	SEVENTH("7", RoomType.BOSS, 7), EIGHTH("8", RoomType.BOSS, 8);

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
						seqNumber == 7 ? SEVENTH.getPath() : seqNumber == 8 ? EIGHTH.getPath() : "0";
						
	}

	public String getPath() {
		return this.path;
	}
}
