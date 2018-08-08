 package model.map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import model.map.BackgroundFromFile;
import model.entity.Boss;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityPropieties;
import model.entity.PowerUp;
import model.room.Room;
import model.room.RoomType;
import utilities.Pair;

public class ReadEntityImpl implements ReadEntity {

	private final static double HEIGHT = 1;
	private final static double WEIGHT = 1;
	private final static char NOSCAN = '0';

	private String file;
	private Set<Room> rooms;
	private Set<Entity> entitiesRead;
	private EntityFactory ef;
	private Entity entityToStolk;
	private DataInputStream in;

	public ReadEntityImpl(Set<Room> rooms, Entity entityToStolk, EntityFactory ef) {
		this.ef = ef;
		this.entitiesRead = new HashSet<>();
		this.rooms = rooms;
		this.entityToStolk = entityToStolk;

	}

	public void populateRooms() {
		this.rooms.forEach(x -> this.buildEntityInRoom(x));
		this.rooms.forEach(x -> System.out.println("IDRoom " + x.getRoomID() + "" + x.getEntities().size()));
		this.populateBoss();
	}

	private void buildEntityInRoom(Room x) {
		try {
			RoomType roomT = x.getType();
			this.file = BackgroundFromFile.getRandomPath(roomT);
			in = new DataInputStream(new FileInputStream(this.file));
			int column = in.readLine().length();
			int row = calculateRow();
			System.out.println("Colonna" + column);
			System.out.println("riga" + row);
			System.out.println("File " + this.file);
			System.out.println("IDRoom" + x.getRoomID() + "\n");
			System.out.println("TypeRoom" + x.getType());
			double columnProportion = WEIGHT / column;
			double rowProportion = HEIGHT / row;
			String line;
			char currentChar;
			in = new DataInputStream(new FileInputStream(this.file));
			for (int i = 0; i < row; i++) {
				line = in.readLine();
				for (int j = 0; j < column; j++) {
					currentChar = line.charAt(j);
					if (currentChar != NOSCAN) {
						this.scanFind(currentChar, j * columnProportion, i * rowProportion, x);
					}
				}
			}
		} catch (Exception e) {
		}

	}

	private int calculateRow() throws IOException {
		int rowCount = 0;
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(this.file));
			while (in.readLine() != null) {
				rowCount++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;

	}

	public void setFile(String file) {
		this.file = file;
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

		}

	}

	private void populateBoss() {
		List<Boss> bossSet = Arrays.asList(Boss.CIATTO, Boss.CROATTI, Boss.THOR);
		Iterator<Boss> it = bossSet.iterator();
		this.rooms.stream().filter(z -> z.getType().equals(RoomType.BOSS))
				.forEach(e -> {
					if(it.hasNext()) {
						e.addEntity(ef.createBoss(0.5, 0.5, e, Optional.of(this.entityToStolk), it.next()));

					}
				});
	}

}