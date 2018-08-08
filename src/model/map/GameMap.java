package model.map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import model.room.Room;
import model.room.RoomType;
import model.room.RoomImpl.RoomBuilder;
import utilities.Pair;
import model.entity.DoorStatus;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.Player;
import model.map.ReadEntity;
import model.map.ReadEntityImpl;

public class GameMap implements Map {

    private final static int MAXROOM = 15;
    private final static int X = 20;
    private final static int Y = 20;
    private final static int MIDDLEX = X / 2;
    private final static int MIDDLEY = Y / 2;

    private Room[][] path;
    private Set<Room> rooms;
    private Set<Entity> doors;
    private Room firstRoom;
    private ReadEntityImpl readE;
    private RoomBuilder rBuilder;
    private EntityFactory entityF;
    private Entity player;
    
    private int stanzeTotali;

    public GameMap(EntityFactory entityFactory, Entity player) {
        this.rooms = new HashSet<>();
        this.doors = new HashSet<>();
        this.rBuilder = new RoomBuilder();
        this.entityF = entityFactory;
        this.player = player;
         
        

        this.initMap();
    }

    @Override
    public Set<Room> getRooms() {
        return this.rooms;
    }

    @Override
    public Room[][] getPath() {
        return this.path;
    }
    
    private void initMap() {
    	this.path = new Room[X][Y];
        Room a = this.rBuilder.setComplited(true).setRoomID(1).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>()).setTypes(RoomType.FIRTS)
                .build();
        
        this.path[MIDDLEX][MIDDLEY] = a;
        Room b = this.rBuilder.setComplited(false).setEntities(new CopyOnWriteArraySet<>()).setRoomID(2).setDoors(new HashSet<>())
                .setTypes(RoomType.INTERMEDIATE).build();
        this.path[MIDDLEX + 1][MIDDLEY] = b;
        this.addNewRoom(a);
        this.addNewRoom(b);
        this.addLink(a, b, Coordinates.SOUTH, DoorStatus.OPEN);
        b = this.rBuilder.setComplited(false).setRoomID(3).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE)
                .build();
        this.addNewRoom(b);
        this.addLink(a, b, Coordinates.NORTH, DoorStatus.OPEN);
        this.path[MIDDLEX - 1][MIDDLEY] = b;
        b = this.rBuilder.setComplited(false).setRoomID(4).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE)
                .build();
        this.addNewRoom(b);
        this.addLink(a, b, Coordinates.EAST, DoorStatus.OPEN);
        this.path[MIDDLEX][MIDDLEY + 1] = b;
        b = this.rBuilder.setComplited(false).setRoomID(0).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE)
                .build();
        this.addNewRoom(b);
        this.addLink(a, b, Coordinates.WEST, DoorStatus.OPEN);
        this.path[MIDDLEX][MIDDLEY - 1] = b;
        this.stanzeTotali = 4;
        this.completePath(MIDDLEX + 1, MIDDLEY, new Random().nextInt(2) + 4);
        this.completePath(MIDDLEX - 1, MIDDLEY, new Random().nextInt(2) + 4);
        this.completePath(MIDDLEX, MIDDLEY + 1, new Random().nextInt(2) + 4);
        this.readE = new ReadEntityImpl(this.getRooms(), player , this.entityF);
        this.readE.populateRooms();
    }
    //prima mi ha dato una null pointer exception
    private boolean checkDoor(final Room r, Coordinates x) {
    	boolean b = false;
    	try {
            b = r.getDoor().stream().anyMatch(y -> y.getObjectProperty("coordinate").equals(x));

    	}catch(Exception e) {
    		this.initMap();
    	}
    	return b;
    }

    private boolean checkNextRoom(int x, int y) {
        return this.path[x][y] == null;
    }

    private boolean checkLoop(int x, int y) {
    	if(this.path[x + 1][y] != null &&
    	    this.path[x][y + 1] != null &&
    	    this.path[x-1][y] != null &&
    	    this.path[x][y - 1] != null ) {
    		return true;
    	}
    	return false;
    }
    private void completePath(int x, int y, int r) {
        Coordinates c;
        Room current;
        Room next;
        Pair<Integer, Integer> movement;
        while (r > 0) {
            c = Coordinates.getRandomCoordinate();
            current = this.path[x][y];
            movement = Coordinates.getMovementFromCoordinates(c);
            if(!this.checkLoop(x, y)) {	           
            	if (!checkDoor(current, c) && this.checkNextRoom(x + movement.getFirst(), y + movement.getSecond())) {
            		RoomType t = r == 1 ? RoomType.BOSS : RoomType.INTERMEDIATE;
	                this.stanzeTotali++;
	                next = this.rBuilder.setComplited(false).setRoomID(this.stanzeTotali).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>())
	                        .setTypes(t).build();
	               this.path[x + movement.getFirst()][y + movement.getSecond()] = next;
	                this.addNewRoom(next);
	                this.addLink(current, next, c, DoorStatus.CLOSE);
	                x = x + movement.getFirst();
	                y = y + movement.getSecond();
	                r--;
	                current = next;
	                next = null;
	            }
            }else {
            	this.initMap();
            }
        }
    }

    @Override
    public Set<Entity> getDoors() {
        return this.doors;
    }

    @Override
    public Optional<Room> getRoom(int x) {
        return this.rooms.stream().filter(y -> y.getRoomID() == x).findFirst();
    }

    private void addLink(Room x, Room y, Coordinates z, DoorStatus statusLink) {
        if (this.rooms.contains(x) && this.rooms.contains(y)) {
            Entity a = this.entityF.createDoor(z.getX(), z.getY(), statusLink, y, z.getImage(statusLink), z, z.getArea());
            Entity b = this.entityF.createDoor(Coordinates.reversCoordinate(z).getX(), 
            		Coordinates.reversCoordinate(z).getY(), statusLink, x, Coordinates.reversCoordinate(z).getImage(statusLink), 
            		Coordinates.reversCoordinate(z),Coordinates.reversCoordinate(z).getArea());
            this.doors.add(a);
            this.doors.add(b);
            x.addDoor(a);
            y.addDoor(b);
        }

    }

    private void addNewRoom(Room x) {
        this.rooms.add(x);
    }

    public String toString() {
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (path[i][j] == null) {

                    System.out.print(" - ");
                } else {
                    System.out.print(" " + this.path[i][j].getRoomID() );

                }

            }
            System.out.println("\n");

        }
        return "printOK";
    }

   
}
