 package model.world;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import model.entity.EntityPropieties;
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
	private File file;
	private Set<Room> rooms;
	private Set<Entity> entitiesRead;
	private EntityFactory ef;
	private Entity entityToStolk;
	private BufferedReader bufferReader;

	public ScanEntityImpl(Set<Room> rooms, Entity entityToStolk, EntityFactory ef) {
		this.ef = ef;
		this.entitiesRead = new HashSet<>();
		this.rooms = rooms;
		this.entityToStolk = entityToStolk;

	}

	public void populateRooms() {
		this.rooms.stream().filter(z -> !z.getType().equals(RoomType.BOSS)).forEach(x -> this.buildEntityInRoom(x));
		this.populateBoss();
		
	}

	private void buildEntityInRoom(Room x) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			if(!x.getType().equals(RoomType.BOSS)) {
				this.setFile(BackgroundFromFile.getRandomPath(x.getType()));
			}
			this.file = new File(classLoader.getResource(this.fileName).getFile());
			this.bufferReader = new BufferedReader(new FileReader(this.file));
			int column = this.bufferReader.readLine().length();
			int row = calculateRow();
			double columnProportion = WEIGHT / column;
			double rowProportion = HEIGHT / row;
			String line;
			char currentChar;
			this.bufferReader = new BufferedReader(new FileReader(this.file));
			for (int i = 0; i < row; i++) {
				line = this.bufferReader.readLine();
				for (int j = 0; j < column; j++) {
					currentChar = line.charAt(j);
					if (currentChar != NOSCAN) {
						this.scanFind(currentChar, j * columnProportion, i * rowProportion, x);
					}
				}
			}
			this.bufferReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int calculateRow() throws IOException {
		int rowCount = 0;

		try {
			this.bufferReader = new BufferedReader(new FileReader(this.file));
			while (this.bufferReader.readLine() != null) {
				rowCount++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bufferReader.close();
		return rowCount;

	}

	public void setFile(String file) {
		this.fileName = file;
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

	private void populateBoss() {
		List<String> bossSet = BackgroundFromFile.getBossPath();
		Iterator<String> it = bossSet.iterator();
		this.rooms.stream().filter(z -> z.getType().equals(RoomType.BOSS))
				.forEach(e -> {
					if(it.hasNext()) {
						this.fileName = it.next();
						this.buildEntityInRoom(e);
					}
				});
	}

}