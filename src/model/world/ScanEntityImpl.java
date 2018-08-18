package model.world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Optional;
import model.entity.Boss;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityProperties;
import model.entity.PowerUp;
import model.room.Room;
import model.room.RoomType;
import model.world.BackgroundFromFile;

public class ScanEntityImpl implements ScanEntity {

	private final static double HEIGHT = 1;
	private final static double WEIGHT = 1;
	private final static char NOSCAN = '0';

	private String fileName;
	private EntityFactory ef;
	private Entity entityToStolk;
	private BufferedReader bufferReader;
	private Iterator<String> bossIterator = BackgroundFromFile.getBossPath().iterator();

	public ScanEntityImpl(Entity entityToStolk, EntityFactory ef) {
		this.ef = ef;
		this.entityToStolk = entityToStolk;
	}

	private int calculateRow() throws IOException {
		int rowCount = 0;

		try {
			final InputStream in = ScanEntityImpl.class.getResourceAsStream(this.fileName);
			this.bufferReader = new BufferedReader(new InputStreamReader(in));
			while (this.bufferReader.readLine() != null) {
				rowCount++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.bufferReader.close();
		return rowCount;

	}

	public void setFile(String file) {
		this.fileName = file;
	}

	private void scanFind(char type, double x, double y, Room currentRoom) {
		if (EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.ENEMY1)) {
			currentRoom.addEntity(this.ef.stalkerSpiritEnemy(x, y, this.entityToStolk, currentRoom, true));
		} else if (EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.ENEMY2)) {
			currentRoom.addEntity(this.ef.createMoscow(x, y, this.entityToStolk, currentRoom));
		} else if (EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.STOPPED)) {
			currentRoom.addEntity(this.ef.createObstacle(x, y));
		} else if ((EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.GUITAR))) {
			currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.CHITARRA));
		} else if ((EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.SUGAR))) {
			currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.ZUCCHERO));
		} else if ((EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.GUN))) {
			currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.PISTOLA));
		} else if ((EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.SIGARETS))) {
			currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.SIGARETTA));
		} else if ((EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.BOSS1))) {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.BOSS_2));
		} else if ((EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.BOSS2))) {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.BOSS_3));
		} else if ((EntityProperties.getPropieties(String.valueOf(type)).equals(EntityProperties.BOSS3))) {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.BOSS_1));
		}

	}

	public void loadEntity(Room x) {
		try {

			if (!x.getType().equals(RoomType.BOSS)) {
				this.setFile(BackgroundFromFile.getRandomPath(x.getType()));
			}
			InputStream in = ScanEntityImpl.class.getResourceAsStream(this.fileName);
			this.bufferReader = new BufferedReader(new InputStreamReader(in));
			final int column = this.bufferReader.readLine().length();
			final int row = calculateRow();
			final double columnProportion = WEIGHT / column;
			final double rowProportion = HEIGHT / row;
			in = ScanEntityImpl.class.getResourceAsStream(this.fileName);
			this.bufferReader = new BufferedReader(new InputStreamReader(in));
			for (int i = 0; i < row; i++) {
				final String line = this.bufferReader.readLine();
				for (int j = 0; j < column; j++) {
					if (line != null) {
						final char currentChar = line.charAt(j);
						if (currentChar != NOSCAN) {
							this.scanFind(currentChar, j * columnProportion, i * rowProportion, x);
						}
					}

				}
			}
			this.bufferReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadBoss(Room room) {
		if (this.bossIterator.hasNext()) {
			this.fileName = bossIterator.next();
			this.loadEntity(room);
		}
	}

}