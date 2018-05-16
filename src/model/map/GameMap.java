package model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.entity.Door;
import model.entity.EntitityImpl.EntitiesBuilder;
import model.entity.GameDoor.GameDoorBuilder;
import model.room.Room;
import model.room.RoomType;
import model.room.RoomImpl.RoomBuilder;
import utilities.Pair;
import model.entity.Door.DoorStatus;

public class GameMap implements Map {

    private final static int MAXROOM = 15;
    private final static int MAXDOOR = 4;

    private HashMap<Room, Set<Pair<Door, Coordinates>>> map;
    private Set<Door> doors;
    private Room firstRoom;
    private RoomBuilder rBuilder;
    private GameDoorBuilder dBuilder;

    public GameMap() {
        this.map = new HashMap<>();
        this.doors = new HashSet<>();
        this.firstRoom = rBuilder.setRoomID(0).setComplited(true).setTypes(RoomType.FIRTS).build();
        this.initMap(firstRoom);
    }

    @Override
    public Set<Door> getDoors() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Room> getRoom(int x) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Door> getDoor(Room x, Room z) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addNewRoom(Room x) {
        if (!this.map.containsKey(x)) {
            this.map.put(x, new HashSet<>());
        }
    }

    private void initMap(Room firstroom) {
        this.addLink(firstRoom, this.rBuilder.setRoomID(1).setTypes(RoomType.INTERMEDIATE).build(), Coordinates.NORTH,
                DoorStatus.OPEN);
        this.addLink(firstRoom, this.rBuilder.setRoomID(4).setTypes(RoomType.INTERMEDIATE).build(), Coordinates.SOUTH,
                DoorStatus.OPEN);
        this.addLink(firstRoom, this.rBuilder.setRoomID(2).setTypes(RoomType.INTERMEDIATE).build(), Coordinates.WEST,
                DoorStatus.OPEN);
        this.addLink(firstRoom, this.rBuilder.setRoomID(3).setTypes(RoomType.INTERMEDIATE).build(), Coordinates.EAST,
                DoorStatus.OPEN);

    }

    public void addLink(Room x, Room y, Coordinates z, DoorStatus statusLink) {
        if (!this.map.containsKey(x))
            this.map.put(x, new HashSet<>());
        if (!this.map.containsKey(y))
            this.map.put(y, new HashSet<>());
        Door d = dBuilder.setLink(new Pair<Room, Room>(x, y)).setStatus(statusLink).build();
        this.map.get(x).add(new Pair<Door, Coordinates>(d, z));
        this.map.get(y).add(new Pair<Door, Coordinates>(d, Coordinates.reversCoordinate(z)));
    }
}
/*
 * 
 * 
 * private HashMap<Room, Set<Pair<Door, Coordinates>>> linked; private Room
 * firstRoom; private int actualPosition; public int count;
 * 
 * public GameMap() { this.buildMap(); this.count = 0; }
 * 
 * private void buildMap() { this.linked = new HashMap<Room, Set<Pair<Door,
 * Coordinates>>>(); firstRoom = new FirstRoom(this.count++);
 * firstRoom.setComplited(true); this.actualPosition = this.count;
 * this.initMap(); Set<Door> x = this.getDoors(); x.stream().forEach(d ->
 * this.getDoor(d.getLink().getA(),
 * d.getLink().getB()).get().setDoorStatus(Door.setDoor.OPEN));
 * 
 * }
 * 
 * private boolean checkDoor(Room r, Coordinates x) { return
 * this.linked.get(r).stream().anyMatch(y -> y.getB().equals(x)); }
 * 
 * 
 * private void initMap() { this.addLink(firstRoom, new RoomImpl(this.count++),
 * Coordinates.NORTH); this.addLink(firstRoom, new RoomImpl(this.count++),
 * Coordinates.SOUTH); this.addLink(firstRoom, new RoomImpl(this.count++),
 * Coordinates.WEST); this.addLink(firstRoom, new RoomImpl(this.count++),
 * Coordinates.EAST); this.linked.get(firstRoom) .stream() .forEach(d -> {
 * this.completeMap(new Random().nextInt(6) + 3, d.getA()); });
 * 
 * }
 * 
 * 
 * private void completeMap(Integer x, Door d) { if(x != 0 && this.count <
 * this.MAXROOM) { Coordinates coor = Coordinates.getRandomCoordinate();
 * while(this.checkDoor(d.getLink().getY(), coor)) { coor =
 * Coordinates.getRandomCoordinate(); } Room ro = new RoomImpl(this.count++);
 * this.addLink(this.getRoom(d.getLink().getA().getRoomID()).get(), ro, coor);
 * x--; this.getDoor(this.getRoom(d.getLink().getB().getRoomID()).get(),
 * ro).get().setDoorStatus(Door.setDoor.CLOSE); this.completeMap(x,
 * this.getDoor(this.getRoom(d.getLink().getY().getRoomID()).get(), ro).get());
 * } }
 * 
 * 
 * 
 * 
 * 
 * public Optional<Door> getDoor(Room x, Room z) { return this.getDoors()
 * .stream() .filter(d -> d.getLink().getX().getRoomID() == x.getRoomID() &&
 * d.getLink().getY().getRoomID() == z.getRoomID()) .findAny(); }
 * 
 * public void closeDoor(Room x, Room z) { this.getDoor(x,
 * z).get().setDoorStatus(Door.setDoor.CLOSE); }
 * 
 * public void openDoor(Room x, Room z) { this.getDoor(x,
 * z).get().setDoorStatus(Door.setDoor.OPEN); }
 * 
 * public Set<Door> getDoors() { Set<Door> doorSet = new HashSet<>();
 * this.linked.values().stream().forEach(x -> x.stream().forEach(y ->
 * doorSet.add(y.getX()))); return doorSet; }
 * 
 * public Optional<Room> getRoom(int x) { return
 * this.linked.keySet().stream().filter(y -> y.getRoomID() == x).findAny(); }
 * 
 * public String toString() { return "" + this.linked; }
 */
