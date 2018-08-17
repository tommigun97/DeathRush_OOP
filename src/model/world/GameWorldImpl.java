package model.world;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import model.room.Room;
import model.room.RoomType;
import model.room.RoomImpl.RoomBuilder;
import model.world.ScanEntity;
import model.world.ScanEntityImpl;
import utilities.Pair;
import model.entity.DoorStatus;
import model.entity.Entity;
import model.entity.EntityFactory;

public class GameWorldImpl implements GameWorld {

	
	private final static int X = 14;
	private final static int Y = 14;
	private final static int MIDDLEX = X / 2;
	private final static int MIDDLEY = Y / 2;

	private Room[][] matrixMap;
	private Set<Room> roomSet;
	private Set<Entity> doorSet;
	private GameMap mapForView;
	private ScanEntity scanE;
	private RoomBuilder roomBuilder;
	private EntityFactory entityFactory;
	private Entity player;
	private int roomCount;

	public GameWorldImpl(EntityFactory entityFactory, Entity player) {
		this.roomBuilder = new RoomBuilder();
		this.entityFactory = entityFactory;
		this.player = player;
		this.scanE = new ScanEntityImpl(this.player, this.entityFactory);
	}
	
	private void resetGameWorld() {
		this.matrixMap = new Room[X][Y];
		this.roomSet = new HashSet<>();
		this.doorSet = new HashSet<>();
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
	private void initializeMapBuilding() {
		this.resetGameWorld();
		Room roomA = this.roomBuilder.setComplited(true).setRoomID(1).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.FIRTS).setVisited(true).build();
		this.addNewRoom(roomA);
		this.matrixMap[MIDDLEX][MIDDLEY] = roomA;
		Room roomB = this.roomBuilder.setComplited(false).setEntities(new CopyOnWriteArraySet<>()).setRoomID(2)
				.setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE).setVisited(false).build();
		this.matrixMap[MIDDLEX + 1][MIDDLEY] = roomB;
		this.addNewRoom(roomB);
		System.out.println(this.roomSet);
		this.addLink(roomA, roomB, Coordinates.SOUTH, DoorStatus.OPEN);
		roomB = this.roomBuilder.setComplited(false).setRoomID(3).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE).setVisited(false).build();
		this.addNewRoom(roomB);
		this.addLink(roomA, roomB, Coordinates.NORTH, DoorStatus.OPEN);
		this.matrixMap[MIDDLEX - 1][MIDDLEY] = roomB;
		roomB = this.roomBuilder.setComplited(false).setRoomID(4).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE).setVisited(false).build();
		this.addNewRoom(roomB);
		this.addLink(roomA, roomB, Coordinates.EAST, DoorStatus.OPEN);
		this.matrixMap[MIDDLEX][MIDDLEY + 1] = roomB;
		roomB = this.roomBuilder.setComplited(false).setRoomID(0).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.VENDOR).setVisited(false).build();
		this.addNewRoom(roomB);
		this.addLink(roomA, roomB, Coordinates.WEST, DoorStatus.OPEN);
		this.matrixMap[MIDDLEX][MIDDLEY - 1] = roomB;
		this.roomCount = 4;
		this.completePath(MIDDLEX + 1, MIDDLEY, new Random().nextInt(2) + 4);
		this.completePath(MIDDLEX - 1, MIDDLEY, new Random().nextInt(2) + 4);
		this.completePath(MIDDLEX, MIDDLEY + 1, new Random().nextInt(2) + 4);
	}

	private void completePath(int x, int y, int roomsSinglePath) {

		boolean mapOK = true;
		Room current = this.matrixMap[x][y];
		while (roomsSinglePath > 0 && mapOK) {

			final Coordinates c = Coordinates.getRandomCoordinate();
			final Pair<Integer, Integer> movement = Coordinates.getMovementFromCoordinates(c);
			if (!this.checkLoop(x, y)) {
				if (!checkDoor(current, c) && this.checkNextRoom(x + movement.getFirst(), y + movement.getSecond())) {
					RoomType t = roomsSinglePath == 1 ? RoomType.BOSS : RoomType.INTERMEDIATE;
					this.roomCount++;
					final Room next = this.roomBuilder.setComplited(false).setRoomID(this.roomCount)
							.setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>()).setVisited(false)
							.setTypes(t).build();
					this.matrixMap[x + movement.getFirst()][y + movement.getSecond()] = next;
					this.addNewRoom(next);
					this.addLink(current, next, c, DoorStatus.CLOSE);
					x = x + movement.getFirst();
					y = y + movement.getSecond();
					roomsSinglePath--;
					current = next;
				}
			} else {
				mapOK = false;
			}
		}
		if(!mapOK) {
			this.initializeMapBuilding();
		}
		
	}
	
	public void buildWorldGame() {
		this.initializeMapBuilding();
		this.populateNormalRoom();
		this.populateBossRoom();
		this.mapForView = new GameMapImpl(this, this.Y, this.X, this.player);
	}

	
	@Override
	public Set<Room> getRooms() {
		return this.roomSet;
	}

	@Override
	public Room[][] getPath() {
		return this.matrixMap;
	}

	@Override
	public Set<Entity> getDoors() {
		return this.doorSet;
	}

	@Override
	public Optional<Room> getRoom(int x) {
		return this.roomSet.stream().filter(y -> y.getRoomID() == x).findFirst();
	}

	

	private void addNewRoom(Room x) {
		this.roomSet.add(x);
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
	
	private void populateNormalRoom() {
		this.roomSet.stream().filter(e -> !e.getType().equals(RoomType.BOSS)).forEach(x -> this.scanE.loadEntity(x));
	}

	private void populateBossRoom() {
		this.roomSet.stream().filter(e -> e.getType().equals(RoomType.BOSS)).forEach(x -> this.scanE.loadBoss(x));
	}
	
	public void matrixViewUpdate() {
		this.mapForView.buildPath();
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

}
