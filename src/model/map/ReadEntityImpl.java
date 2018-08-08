package model.map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import model.map.BackgroundFromFile;
import model.entity.Boss;
import model.entity.Entity;
import model.entity.EntityFactory;
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
    	this.rooms.forEach(x -> this.vuildEntityInRoom(x));
    	this.rooms.forEach(x -> System.out.println("IDRoom " + x.getRoomID() + "" + x.getEntities().size()));
    }
	
	private void vuildEntityInRoom(Room x) {
		try  {
			RoomType roomT = x.getType();
			this.file = BackgroundFromFile.getRandomPath(roomT);
			in = new DataInputStream(new FileInputStream(this.file));
			int column = in.readLine().length();
			int row = calculateRow();
            System.out.println("Colonna" +column);
            System.out.println("riga" + row);
            System.out.println("File " +this.file );
            System.out.println("IDRoom" + x.getRoomID()+ "\n");
            double columnProportion = WEIGHT / column;
            double rowProportion = HEIGHT / row;
            String line;
            char currentChar;
            in = new DataInputStream(new FileInputStream(this.file));
            for (int i = 0; i<row; i++ ) {
                line = in.readLine();
                for(int j = 0; j<column; j++) {
                	currentChar = line.charAt(j);
                 	if(currentChar != NOSCAN) {
                 		this.scanFind(currentChar, i*rowProportion, j*columnProportion, x);
                 	}
                 }
            }
        }catch(Exception e) {
        }
	
	}

	/*
	 * try { RoomType roomT = x.getType(); this.file =
	 * BackgroundFromFile.getRandomPath(roomT); System.out.println(file); bf = new
	 * BufferedReader(new FileReader(file)); int column = bf.readLine().length();
	 * int row = calculateRow(file); double columnProportion = WEIGHT / column;
	 * double rowProportion = HEIGHT / row; String line; char currentChar; for (int
	 * i = 0; i<row; i++ ) { line = bf.readLine(); for(int j = 0; j<column; j++) {
	 * currentChar = line.charAt(j); if(currentChar != NOSCAN) {
	 * this.scanFind(currentChar, i*rowProportion, j*columnProportion, x); } } }
	 * 
	 * } catch (Exception e) { try { throw e; } catch (Exception e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } }
	 */

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
		if (type == '1') {
			currentRoom.addEntity(this.ef.isaacStalkerEnemy(x, y, this.entityToStolk, currentRoom, true));
		} else if (type == '2') {
			currentRoom.addEntity(this.ef.createMoscow(x, y, this.entityToStolk, currentRoom));
		} else if (type == '3') {
			currentRoom.addEntity(this.ef.createObstacle(x, y));
		} else if (type == '4') {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.CIATTO));
		} else if (type == '5') {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.CROATTI));
		} else if (type == '6') {
			currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.THOR));
		}

	}
}