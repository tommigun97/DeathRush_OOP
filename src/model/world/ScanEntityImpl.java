package model.world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import model.entity.Boss;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityProperties;
import model.entity.PowerUp;
import model.room.Room;
import model.room.RoomType;
import model.world.BackgroundFromFile;
import utilities.Pair;

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
	
	private void scanFind(char type, double x, double y, Room currentRoom) {
		if (EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.ENEMY1)) {
			currentRoom.addEntity(this.ef.isaacStalkerEnemy(x, y, this.entityToStolk, currentRoom, true));
		} else if (EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.ENEMY2)) {
			currentRoom.addEntity(this.ef.createMoscow(x, y, this.entityToStolk, currentRoom));
		} else if (EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.STOPPED)) {
			currentRoom.addEntity(this.ef.createObstacle(x, y));
		} else if ((EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.GUITAR))) {
			currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.CHITARRA));
		} else if ((EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.SUGAR))) {
			currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.ZUCCHERO));
		} else if ((EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.GUN))) {
			currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.PISTOLA));
		} else if ((EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.SIGARETS))) {
			currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.SIGARETTA));
		} else if ((EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.BOSS1))) {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.CIATTO));
		} else if ((EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.BOSS2))) {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.CROATTI));
		} else if ((EntityPropieties.getPropieties(String.valueOf(type)).equals(EntityPropieties.BOSS3))) {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.THOR));
		}

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
	
	private void setFile(String file) {
		this.fileName = file;
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