package model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.entity.Door;
import model.room.Room;
import model.room.Room.Coordinates;
import utilities.Pair;


public class GameMap implements Map{

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

    @Override
    public void addLink(Room x, Room y, Coordinates z) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addNewRoom(Room x) {
        // TODO Auto-generated method stub
        
    }
/*
    private final static int MAXROOM = 15;
    private final static int MAXDOOR = 4;
    
    private HashMap<Room, Set<Pair<Door, Coordinates>>> linked;
    private Room firstRoom;
    private int actualPosition;
    public int count;

    public GameMap() {
            this.buildMap();
            this.count = 0;
    }

    private void buildMap() {
            this.linked = new HashMap<Room, Set<Pair<Door, Coordinates>>>();
            firstRoom = new FirstRoom(this.count++);
            firstRoom.setComplited(true);
            this.actualPosition = this.count;
            this.initMap();
            Set<Door> x = this.getDoors();
            x.stream().forEach(d -> this.getDoor(d.getLink().getA(), d.getLink().getB()).get().setDoorStatus(Door.setDoor.OPEN));                   
                                                    
    }
    
    private boolean checkDoor(Room r, Coordinates x) {
            return this.linked.get(r).stream().anyMatch(y -> y.getB().equals(x));
    }

    
    private void initMap() {
            this.addLink(firstRoom, new RoomImpl(this.count++), Coordinates.NORTH);
            this.addLink(firstRoom, new RoomImpl(this.count++), Coordinates.SOUTH);
            this.addLink(firstRoom, new RoomImpl(this.count++), Coordinates.WEST);
            this.addLink(firstRoom, new RoomImpl(this.count++), Coordinates.EAST);
            this.linked.get(firstRoom)
                                            .stream()
                                            .forEach(d -> {
                                                    this.completeMap(new Random().nextInt(6) + 3, d.getA());
                                            });
            
    }
    
    
    private void completeMap(Integer x, Door d) {
            if(x != 0 && this.count < this.MAXROOM) {
                    Coordinates coor = Coordinates.getRandomCoordinate();
                    while(this.checkDoor(d.getLink().getY(), coor)) {
                            coor = Coordinates.getRandomCoordinate();
                    }
                    Room ro = new RoomImpl(this.count++);
                    this.addLink(this.getRoom(d.getLink().getA().getRoomID()).get(), ro, coor);
                    x--;
                    this.getDoor(this.getRoom(d.getLink().getB().getRoomID()).get(), ro).get().setDoorStatus(Door.setDoor.CLOSE);
                    this.completeMap(x, this.getDoor(this.getRoom(d.getLink().getY().getRoomID()).get(), ro).get());                        
            }
    }
    
    public void addNewRoom(Room x) {
            if (!this.linked.containsKey(x)) {
                    this.linked.put(x, new HashSet<>());
            }
    }

    public void addLink(Room x, Room y, Coordinates z) {
            if (!this.linked.containsKey(x))
                    this.linked.put(x, new HashSet<>());
            if (!this.linked.containsKey(y))
                    this.linked.put(y, new HashSet<>());
            Door d = new DoorImpl(x, y);
            Pair<Door, Coordinates> p = new Pair<>();
            p.setX(d);
            p.setY(z);
            this.linked.get(x).add(p);
            Pair<Door, Coordinates> p1 = new Pair<>();
            p1.setX(d);
            p1.setY(Coordinates.reversCoordinate(z));
            this.linked.get(y).add(p1);
    }
    
    public Optional<Door> getDoor(Room x, Room z) {
            return this.getDoors()
                            .stream()
                            .filter(d -> d.getLink().getX().getRoomID() == x.getRoomID() && d.getLink().getY().getRoomID() == z.getRoomID())
                            .findAny();
    }
    
    public void closeDoor(Room x, Room z) {
            this.getDoor(x, z).get().setDoorStatus(Door.setDoor.CLOSE);
    }
    
    public void openDoor(Room x, Room z) {
            this.getDoor(x, z).get().setDoorStatus(Door.setDoor.OPEN);
    }

    public Set<Door> getDoors() {
            Set<Door> doorSet = new HashSet<>();
            this.linked.values().stream().forEach(x -> x.stream().forEach(y -> doorSet.add(y.getX())));
            return doorSet;
    }

    public Optional<Room> getRoom(int x) {
            return this.linked.keySet().stream().filter(y -> y.getRoomID() == x).findAny();
            
    }
    
    public String toString() {
            return "" + this.linked;
    }
    */
}
