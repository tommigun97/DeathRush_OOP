package model.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import model.room.Room;
import model.room.RoomType;
import model.room.RoomImpl.RoomBuilder;
import model.world.ScanEntity;
import model.world.ScanEntityImpl;
import utilities.Pair;
import model.entity.Boss;
import model.entity.DoorStatus;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.Player;

public class GameWorldImpl implements GameWorld {

	private final static int X = 12;
	private final static int Y = 12;
	private final static int MIDDLEX = X / 2;
	private final static int MIDDLEY = Y / 2;

	private Room[][] matrixMap;
	private Set<Room> roomSet;
	private Set<Entity> doorSet;
	private GameMapImpl mapForView;
	private ScanEntity scanE;
	private RoomBuilder roomBuilder;
	private EntityFactory entityFactory;
	private Entity player;
	private int roomCount;

	public GameWorldImpl(EntityFactory entityFactory, Entity player) {
		this.roomSet = new HashSet<>();
		this.doorSet = new HashSet<>();
		this.roomBuilder = new RoomBuilder();
		this.entityFactory = entityFactory;
		this.player = player;
		this.buildWorldGame();
	}

	@Override
	public Set<Room> getRooms() {
		return this.roomSet;
	}

	@Override
	public Room[][] getPath() {
		return this.matrixMap;
	}

	private void buildWorldGame() {
		this.initializeMapBuilding();
		this.scanE = new ScanEntityImpl(this.getRooms(), player, this.entityFactory);
		this.scanE.populateRooms();
		this.mapForView = new GameMapImpl(this, this.Y, this.X, this.player);
		this.printMatrixMap();
	}

	private void initializeMapBuilding() {
		this.matrixMap = new Room[X][Y];
		Room a = this.roomBuilder.setComplited(true).setRoomID(1).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.FIRTS).setVisited(true).build();
		this.addNewRoom(a);
		this.matrixMap[MIDDLEX][MIDDLEY] = a;
		Room b = this.roomBuilder.setComplited(false).setEntities(new CopyOnWriteArraySet<>()).setRoomID(2)
				.setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE).setVisited(false).build();
		this.matrixMap[MIDDLEX + 1][MIDDLEY] = b;
		this.addNewRoom(b);
		//System.out.println(this.roomSet);
		this.addLink(a, b, Coordinates.SOUTH, DoorStatus.OPEN);
		b = this.roomBuilder.setComplited(false).setRoomID(3).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE).setVisited(false).build();
		this.addNewRoom(b);
		this.addLink(a, b, Coordinates.NORTH, DoorStatus.OPEN);
		this.matrixMap[MIDDLEX - 1][MIDDLEY] = b;
		b = this.roomBuilder.setComplited(false).setRoomID(4).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE).setVisited(false).build();
		this.addNewRoom(b);
		this.addLink(a, b, Coordinates.EAST, DoorStatus.OPEN);
		this.matrixMap[MIDDLEX][MIDDLEY + 1] = b;
		b = this.roomBuilder.setComplited(false).setRoomID(0).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.VENDOR).setVisited(false).build();
		this.addNewRoom(b);
		this.addLink(a, b, Coordinates.WEST, DoorStatus.OPEN);
		this.matrixMap[MIDDLEX][MIDDLEY - 1] = b;
		this.roomCount = 4;
		this.completePath(MIDDLEX + 1, MIDDLEY, new Random().nextInt(2) + 4);
		this.completePath(MIDDLEX - 1, MIDDLEY, new Random().nextInt(2) + 4);
		this.completePath(MIDDLEX, MIDDLEY + 1, new Random().nextInt(2) + 4);
	}

	private boolean checkDoor(final Room r, Coordinates x) {
			boolean b = r.getDoor().stream().anyMatch(y -> y.getObjectProperty("coordinate").equals(x));
			return b;
	}

	private boolean checkNextRoom(int x, int y) {
		return this.matrixMap[x][y] == null;
	}

	private boolean checkLoop(int x, int y) {
		if (this.matrixMap[x + 1][y] != null && this.matrixMap[x][y + 1] != null && this.matrixMap[x - 1][y] != null
				&& this.matrixMap[x][y - 1] != null) {
			return true;
		}
		return false;
	}

	private void completePath(int x, int y, int r) {
		Coordinates c;
		Room current;
		Room next;
		Pair<Integer, Integer> movement;
		current = this.matrixMap[x][y];
		while (r > 0) {
			c = Coordinates.getRandomCoordinate();
			movement = Coordinates.getMovementFromCoordinates(c);
			if (!this.checkLoop(x, y)) {
				if (!checkDoor(current, c) && this.checkNextRoom(x + movement.getFirst(), y + movement.getSecond())) {
					RoomType t = r == 1 ? RoomType.BOSS : RoomType.INTERMEDIATE;
					this.roomCount++;
					next = this.roomBuilder.setComplited(false).setRoomID(this.roomCount)
							.setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>()).setVisited(false).setTypes(t).build();
					this.matrixMap[x + movement.getFirst()][y + movement.getSecond()] = next;
					this.addNewRoom(next);
					this.addLink(current, next, c, DoorStatus.CLOSE);
					x = x + movement.getFirst();
					y = y + movement.getSecond();
					r--;
					current = next;
					next = null;
				}
			} else {
				this.initializeMapBuilding();
			}
		}
	}

	@Override
	public Set<Entity> getDoors() {
		return this.doorSet;
	}

	@Override
	public Optional<Room> getRoom(int x) {
		return this.roomSet.stream().filter(y -> y.getRoomID() == x).findFirst();
	}

	private void addLink(Room x, Room y, Coordinates z, DoorStatus statusLink) {
		if (this.roomSet.contains(x) && this.roomSet.contains(y)) {
			Entity a = this.entityFactory.createDoor(z.getX(), z.getY(), statusLink, y, z.getImage(statusLink), z,
					z.getArea());
			Entity b = this.entityFactory.createDoor(Coordinates.reversCoordinate(z).getX(),
					Coordinates.reversCoordinate(z).getY(), statusLink, x,
					Coordinates.reversCoordinate(z).getImage(statusLink), Coordinates.reversCoordinate(z),
					Coordinates.reversCoordinate(z).getArea());
			this.doorSet.add(a);
			this.doorSet.add(b);
			x.addDoor(a);
			y.addDoor(b);
		}

	}

	private void addNewRoom(Room x) {
		this.roomSet.add(x);
	}

	public void printMatrixMap() {
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (matrixMap[i][j] == null) {
					System.out.print(" - ");
				} else {
					System.out.print(" " + this.matrixMap[i][j].getRoomID());
				}
			}
			System.out.println("\n");
		}
	}

	@Override
	public boolean allRoomAreCompleted() {
		return this.roomSet.stream().allMatch(r -> r.isComplited());
	}

	@Override
	public int[][] getMatrixView() {
		return this.mapForView.getPathToView();
	}

	@Override
	public int getColumnMatrix() {
		return this.X;
	}

	@Override
	public int getRowMatrix() {
		return this.Y;
	}

	public void matrixViewUpdate() {
		this.mapForView.buildPath();
	}

}
