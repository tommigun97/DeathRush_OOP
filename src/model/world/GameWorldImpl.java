package model.world;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import model.room.Room;
import model.room.RoomType;
import model.room.RoomsFactory;
import model.room.RoomsFactoryImpl;
import model.room.RoomImpl.RoomBuilder;
import model.world.ScanEntity;
import model.world.ScanEntityImpl;
import utilities.Pair;
import model.entity.DoorStatus;
import model.entity.Entity;
import model.entity.EntityFactory;

/**
 * 
 * Implementation of GameWorld
 * 
 */
public class GameWorldImpl implements GameWorld {

	//Static variables
	private final static int X = 14;
	private final static int Y = 14;
	private final static int MIDDLEX = X / 2;
	private final static int MIDDLEY = Y / 2;
	private final static int FIRSTROOM = 1;
	private final static int VENDORROOM = 0;

	//Variables
	private Room[][] matrixMap;
	private Set<Room> roomSet;
	private Set<Entity> doorSet;
	private GameMap mapForView;
	private ScanEntity scanE;
	private RoomsFactory rf;
	private EntityFactory entityFactory;
	private Entity player;
	private int roomCount;

	//Constructor
	public GameWorldImpl(EntityFactory entityFactory, Entity player) {
		this.rf= new RoomsFactoryImpl();
		this.entityFactory = entityFactory;
		this.player = player;
		this.scanE = new ScanEntityImpl(this.player, this.entityFactory);
	}
	
	/**
	 * Reset all the game world
	 * 
	 */
	private void resetGameWorld() {
		this.matrixMap = new Room[X][Y];
		this.roomSet = new HashSet<>();
		this.doorSet = new HashSet<>();
	}

	/**
	 * Check method see if a r already has a door in those coordinated x
	 * 
	 * @param r
	 * 			Room
	 * 
	 * @param x
	 * 			Coordinate
	 * 
	 * @return true if has else false
	 */
	private boolean checkDoor(final Room r, Coordinates x) {
		boolean b = r.getDoor().stream().anyMatch(y -> y.getObjectProperty("coordinate").equals(x));
		return b;
	}

	/**
	 * Check method see if matrixMap has a room in those position
	 * 
	 * @param x
	 * 			Row 
	 * 
	 * @param y
	 * 			Column
	 * 
	 * @return true if has else not
	 */
	private boolean checkNextRoom(int x, int y) {
		return this.matrixMap[x][y] == null;
	}

	/**
	 * Check Method to verify if the map has a building loop
	 * 
	 * @param x
	 * 			Row
	 * 
	 * @param y
	 * 			Column
	 * 
	 * @return true if has a loop else false
	 */
	private boolean checkLoop(int x, int y) {
		if (this.matrixMap[x + 1][y] != null && this.matrixMap[x][y + 1] != null && this.matrixMap[x - 1][y] != null
				&& this.matrixMap[x][y - 1] != null) {
			return true;
		}
		return false;
	}

	/**
	 * Add a link between two Rooms
	 * 
	 * @param x
	 * 			First Room
	 * 
	 * @param y
	 * 			Second Room
	 * 
	 * @param z
	 * 			Coordinate to link rooms
	 * 
	 * @param statusLink
	 * 		Link status(Open or Close)
	 * 
	 */
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
	
	/**
	 * Initialize the first room of the game
	 * 
	 */
	private void initializeMapBuilding() {
		this.resetGameWorld();
		Room roomA = this.rf.FirstRoom(FIRSTROOM, true, true);
		this.addNewRoom(roomA);
		this.matrixMap[MIDDLEX][MIDDLEY] = roomA;
		Room roomB = this.rf.vendorRoom(VENDORROOM, true, false);
		this.addNewRoom(roomB);
		this.addLink(roomA, roomB, Coordinates.WEST, DoorStatus.OPEN);
		roomB = this.rf.IntermediateRoom(2, false, false);
		this.matrixMap[MIDDLEX + 1][MIDDLEY] = roomB;
		this.addNewRoom(roomB);
		System.out.println(this.roomSet);
		this.addLink(roomA, roomB, Coordinates.SOUTH, DoorStatus.OPEN);
		roomB = this.rf.IntermediateRoom(3, false, false);
		this.addNewRoom(roomB);
		this.addLink(roomA, roomB, Coordinates.NORTH, DoorStatus.OPEN);
		this.matrixMap[MIDDLEX - 1][MIDDLEY] = roomB;
		roomB = this.rf.IntermediateRoom(4, false, false);
		this.addNewRoom(roomB);
		this.addLink(roomA, roomB, Coordinates.EAST, DoorStatus.OPEN);
		this.matrixMap[MIDDLEX][MIDDLEY + 1] = roomB;
		this.matrixMap[MIDDLEX][MIDDLEY - 1] = roomB;
		this.roomCount = 4;
		this.completePath(MIDDLEX + 1, MIDDLEY, new Random().nextInt(2) + 4);
		this.completePath(MIDDLEX - 1, MIDDLEY, new Random().nextInt(2) + 4);
		this.completePath(MIDDLEX, MIDDLEY + 1, new Random().nextInt(2) + 4);
	}

	/**
	 * Starting from one position, complete the route randomly 
	 * 
	 * @param x
	 * 			Row
	 * 
	 * @param y
	 * 			Column
	 * 
	 * @param roomsSinglePath
	 * 			number of rooms to be created in the path
	 */
	private void completePath(int x, int y, int roomsSinglePath) {

		boolean mapOK = true;
		Room current = this.matrixMap[x][y];
		while (roomsSinglePath > 0 && mapOK) {

			final Coordinates c = Coordinates.getRandomCoordinate();
			final Pair<Integer, Integer> movement = Coordinates.getMovementFromCoordinates(c);
			if (!this.checkLoop(x, y)) {
				if (!checkDoor(current, c) && this.checkNextRoom(x + movement.getFirst(), y + movement.getSecond())) {
					this.roomCount++;
					final Room  next = roomsSinglePath == 1 ? this.rf.BossRoom(this.roomCount, false, false) :
													this.rf.IntermediateRoom(this.roomCount, false, false);
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
	
	/**
	 * Add entity to Vendor, First and intermediate rooms
	 */
	private void populateNormalRoom() {
		this.roomSet.stream().filter(e -> !e.getType().equals(RoomType.BOSS)).forEach(x -> this.scanE.loadEntity(x));
	}
	
	/**
	 * Add entity to bosses room
	 */
	private void populateBossRoom() {
		this.roomSet.stream().filter(e -> e.getType().equals(RoomType.BOSS)).forEach(x -> this.scanE.loadBoss(x));
	}
	
	@Override
	public void buildWorldGame() {
		this.initializeMapBuilding();
		this.populateNormalRoom();
		this.populateBossRoom();
		this.mapForView = new GameMapImpl(this, GameWorldImpl.Y, GameWorldImpl.Y, this.player);
	}

	
	@Override
	public Set<Room> getRooms() {
		return this.roomSet;
	}

	@Override
	public Room[][] getMatrixMap() {
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
		return GameWorldImpl.X;
	}

	@Override
	public int getRowMatrix() {
		return GameWorldImpl.Y;
	}
	
	@Override
	public void matrixViewUpdate() {
		this.mapForView.buildPath();
	}

}
