package model.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import model.map.BackgroundFromFile;
import model.entity.Boss;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.room.Room;
import model.room.RoomType;

public class ReadEntityImpl implements ReadEntity {

    private final static double HEIGHT = 1;
    private final static double WEIGHT = 1;
    private final static char NOSCAN = '0';

    private String file;
    private Set<Room> rooms;
    private Set<Entity> entitiesRead;
    private EntityFactory ef;
    private Entity entityToStolk;

    public ReadEntityImpl(Set<Room> rooms, Entity entityToStolk) {
    	this.ef = ef;
    	this.entitiesRead = new HashSet<>();
        this.rooms = rooms;
        this.entityToStolk = entityToStolk;
    }

    public void populateRoom() {
    	this.rooms.forEach(x -> {
            try {
            	RoomType roomT = x.getType();
            	this.file = BackgroundFromFile.getRandomPath(roomT);
                BufferedReader bf = new BufferedReader(new FileReader(file));
                int column = bf.readLine().length();
                int row = calculateRow(file);
                double columnProportion = WEIGHT / column;
                double rowProportion = HEIGHT / row;
                bf.reset();
                String line;
                char currentChar;
                for (int i = 0; i<row; i++ ) {
                	line = bf.readLine();
                	for(int j = 0; j<column; j++) {
                		currentChar = line.charAt(j);
                		if(currentChar  != NOSCAN) {
                			this.scanFind(currentChar, i*rowProportion, j*columnProportion, x);
                		}
                	}
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
    	});

    
    }

    private int calculateRow(String filename) throws IOException {
        int rowCount = 0;
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            while (bf.readLine() != null) {
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
    	if(type == '1') {
    		this.entitiesRead.add(this.ef.isaacStalkerEnemy(x, y, this.entityToStolk, currentRoom, true));
    	}else if(type == '2') {
    		this.entitiesRead.add(this.ef.createMoscow(x, y, this.entityToStolk,currentRoom));
    	}else if( type == '3') {
    		this.entitiesRead.add(this.ef.createObstacle(x, y));
    	}else if(type == '4') {
    		this.entitiesRead.add(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.CIATTO));
    	}else if(type == '5') {
    		this.entitiesRead.add(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.CROATTI));
    	}else if(type == '6') {
    		this.entitiesRead.add(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.THOR));
    	}
    }
}