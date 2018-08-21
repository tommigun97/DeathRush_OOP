package model.world;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import model.room.Room;
import model.room.RoomType;
import model.room.RoomsFactory;
import model.room.RoomsFactoryImpl;
import utilities.Pair;
import model.entity.DoorStatus;
import model.entity.Entity;
import model.entity.EntityFactory;

/**
 * 
 * Implementation of GameWorld.
 * 
 */
public final class GameWorldImpl implements GameWorld {

    private static final int X = 14;
    private static final int Y = 14;
    private static final int MIDDLEX = X / 2;
    private static final int MIDDLEY = Y / 2;
    private static final int FIRSTROOM = 1;
    private static final int VENDORROOM = 0;

    private Room[][] matrixMap;
    private Set<Room> roomSet;
    private Set<Entity> doorSet;
    private GameMap mapForView;
    private final ScanEntity scanE;
    private final RoomsFactory rf;
    private final EntityFactory entityFactory;
    private final Entity player;
    private int roomCount;

    /**
     * Constructor.
     * 
     * @param entityFactory
     *            factory for the entities
     * @param player
     *            game player
     */
    public GameWorldImpl(final EntityFactory entityFactory, final Entity player) {
        this.rf = new RoomsFactoryImpl();
        this.entityFactory = entityFactory;
        this.player = player;
        this.scanE = new ScanEntityImpl(this.player, this.entityFactory);
    }

    /**
     * Reset all the game world.
     * 
     */
    private void resetGameWorld() {
        this.matrixMap = new Room[X][Y];
        this.roomSet = new HashSet<>();
        this.doorSet = new HashSet<>();
    }

    /**
     * Check method see if a r already has a door in those coordinated x.
     * 
     * @param r
     *            Room
     * 
     * @param x
     *            Coordinate
     * 
     * @return true if has else false
     */
    private boolean checkDoor(final Room r, final Coordinates x) {
        return r.getDoor().stream().anyMatch(y -> y.getObjectProperty("coordinate").equals(x));
    }

    /**
     * Check method see if matrixMap has a room in those position.
     * 
     * @param x
     *            Row
     * 
     * @param y
     *            Column
     * 
     * @return true if has else not
     */
    private boolean checkNextRoom(final int x, final int y) {
        return this.matrixMap[x][y] == null;
    }

    /**
     * Check Method to verify if the map has a building loop.
     * 
     * @param x
     *            Row
     * 
     * @param y
     *            Column
     * 
     * @return true if has a loop else false
     */
    private boolean checkLoop(final int x, final int y) {
        return this.matrixMap[x + 1][y] != null && this.matrixMap[x][y + 1] != null && this.matrixMap[x - 1][y] != null
                && this.matrixMap[x][y - 1] != null;
    }

    /**
     * Add a link between two Rooms.
     * 
     * @param x
     *            First Room
     * 
     * @param y
     *            Second Room
     * 
     * @param z
     *            Coordinate to link rooms
     * 
     * @param statusLink
     *            Link status(Open or Close)
     * 
     */
    private void addLink(final Room x, final Room y, final Coordinates z, final DoorStatus statusLink) {
        if (this.roomSet.contains(x) && this.roomSet.contains(y)) {
            final Entity a = this.entityFactory.createDoor(z.getX(), z.getY(), statusLink, y, z.getImage(statusLink), z,
                    z.getArea());
            final Entity b = this.entityFactory.createDoor(Coordinates.reversCoordinate(z).getX(),
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
     * Initialize the first room of the game.
     * 
     */
    private void initializeMapBuilding() {
        this.resetGameWorld();
        final Room roomA = this.rf.firstRoom(FIRSTROOM, true);
        this.addNewRoom(roomA);
        this.matrixMap[MIDDLEX][MIDDLEY] = roomA;
        Room roomB = this.rf.vendorRoom(VENDORROOM, false);
        this.matrixMap[MIDDLEX][MIDDLEY - 1] = roomB;
        this.addNewRoom(roomB);
        this.addLink(roomA, roomB, Coordinates.WEST, DoorStatus.OPEN);
        roomB = this.rf.intermediateRoom(2, false);
        this.matrixMap[MIDDLEX + 1][MIDDLEY] = roomB;
        this.addNewRoom(roomB);
        this.addLink(roomA, roomB, Coordinates.SOUTH, DoorStatus.OPEN);
        roomB = this.rf.intermediateRoom(3, false);
        this.addNewRoom(roomB);
        this.addLink(roomA, roomB, Coordinates.NORTH, DoorStatus.OPEN);
        this.matrixMap[MIDDLEX - 1][MIDDLEY] = roomB;
        roomB = this.rf.intermediateRoom(4, false);
        this.addNewRoom(roomB);
        this.addLink(roomA, roomB, Coordinates.EAST, DoorStatus.OPEN);
        this.matrixMap[MIDDLEX][MIDDLEY + 1] = roomB;
        this.roomCount = 4;
        this.completePath(MIDDLEX + 1, MIDDLEY, new Random().nextInt(2) + 4);
        this.completePath(MIDDLEX - 1, MIDDLEY, new Random().nextInt(2) + 4);
        this.completePath(MIDDLEX, MIDDLEY + 1, new Random().nextInt(2) + 4);
    }

    /**
     * Starting from one position, complete the route randomly.
     * 
     * @param x
     *            Row
     * 
     * @param y
     *            Column
     * 
     * @param roomsSinglePath
     *            number of rooms to be created in the path
     */
    private void completePath(final int x, final int y, final int roomsSinglePath) {
        int row = x;
        int column = y;
        int rooms = roomsSinglePath;
        boolean mapOK = true;
        Room current = this.matrixMap[row][column];
        while (rooms > 0 && mapOK) {

            final Coordinates c = Coordinates.getRandomCoordinate();
            final Pair<Integer, Integer> movement = Coordinates.getMovementFromCoordinates(c);
            if (!this.checkLoop(row, column)) {
                if (!checkDoor(current, c)
                        && this.checkNextRoom(row + movement.getFirst(), column + movement.getSecond())) {
                    this.roomCount++;
                    final Room next = rooms == 1 ? this.rf.bossRoom(this.roomCount, false)
                            : this.rf.intermediateRoom(this.roomCount, false);
                    this.matrixMap[row + movement.getFirst()][column + movement.getSecond()] = next;
                    this.addNewRoom(next);
                    this.addLink(current, next, c, DoorStatus.CLOSE);
                    row = row + movement.getFirst();
                    column = column + movement.getSecond();
                    rooms--;
                    current = next;
                }
            } else {
                mapOK = false;
            }
        }
        if (!mapOK) {
            this.initializeMapBuilding();
        }

    }

    /**
     * Add entity to Vendor, First and intermediate rooms.
     */
    private void populateNormalRoom() {
        this.roomSet.stream().filter(e -> !e.getType().equals(RoomType.BOSS)).forEach(x -> this.scanE.loadEntity(x));
    }

    /**
     * Add entity to bosses room.
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
    public Optional<Room> getRoom(final int x) {
        return this.roomSet.stream().filter(y -> y.getRoomID() == x).findFirst();
    }

    private void addNewRoom(final Room x) {
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
