package model.world;

import java.util.Random;

import model.Area;
import model.entity.DoorStatus;
import model.entity.Entity;
import utilities.Pair;

/**
 * 
 * Enumeration for door Coordinates.
 */
public enum Coordinates {
    /**
     * West coordinate.
     */
    WEST("room/door_open_W.png", "room/door_closed_W.png", 0, 0.03, 0.5, new Area(0.08, 0.13)),
    /**
     * East coordinate.
     */
    EAST("room/door_open_E.png", "room/door_closed_E.png", 1, 0.97, 0.5, new Area(0.08, 0.13)),
    /**
     * South coordinate.
     */
    SOUTH("room/door_open_S.png", "room/door_closed_S.png", 0, 0.5, 0.97, new Area(0.08, 0.15)),
    /**
     * North coordinate.
     */
    NORTH("room/door_open_N.png", "room/door_closed_N.png", 3, 0.5, 0.03, new Area(0.08, 0.15));

    private static final double CORRECTOR = 0.17;
    private final String open;
    private final String close;
    private final int coordinateId;
    private final double x;
    private final double y;
    private final Area area;

    /**
     * Constructor.
     * @param openImage door open image
     * @param closedImage close open image
     * @param coordinateId coordinate id
     * @param x coordinate in cartesian plane
     * @param y coordinate in cartesian plane
     * @param area door area
     */
    Coordinates(final String openImage, final String closedImage, final int coordinateId, final double x,
            final double y, final Area area) {
        this.open = openImage;
        this.close = closedImage;
        this.coordinateId = coordinateId;
        this.x = x;
        this.y = y;
        this.area = area;
    }

    /**
     * Getter method for Coordinate ID.
     * 
     * @return coordinate ID
     * 
     */
    public int getCoordinateId() {
        return this.coordinateId;
    }

    /**
     * Getter method for filename of door open image in the asked coordinate.
     * 
     * @return image for the door
     */

    public String getOpen() {
        return open;
    }

    /**
     * Getter method for filename of door closed image in the asked coordinate.
     * 
     * @return image for the Door
     */
    public String getClose() {
        return close;
    }

    /**
     * Getter method for y coordinate where to paint the door in the asked
     * coordinate.
     * 
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Getter method for x coordinate where to paint the door in the asked
     * coordinate.
     * 
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Getter method used for take the area of door in the asked Coordinate.
     * 
     * @return door area
     */
    public Area getArea() {
        return area;
    }

    /**
     * Static method used to get a random coordinate.
     * 
     * @return Random Coordinates
     */
    public static Coordinates getRandomCoordinate() {
        final int r = new Random().nextInt(4);
        return r == 0 ? NORTH : r == 1 ? SOUTH : r == 2 ? WEST : EAST;
    }

    /**
     * Static method for taking the new movment after select Coordinate.
     * 
     * @param c
     *            Coordinate required
     * @return a pair that indicates the movment in a cartesian plane
     */
    public static Pair<Integer, Integer> getMovementFromCoordinates(final Coordinates c) {
        return c.equals(NORTH) ? new Pair<Integer, Integer>(-1, 0)
                : c.equals(SOUTH) ? new Pair<Integer, Integer>(1, 0)
                        : c.equals(WEST) ? new Pair<Integer, Integer>(0, -1) : new Pair<Integer, Integer>(0, 1);
    }

    /**
     * 
     * @param x
     *            Doors Status
     * @return images with the required door status
     */
    public String getImage(final DoorStatus x) {
        return x.equals(DoorStatus.OPEN) ? this.getOpen() : this.getClose();
    }

    /**
     * Static method used to reverse a player Location after Collision door.
     * 
     * @param door
     *            door
     * @return new Location reversed
     */
    public static Pair<Double, Double> reverseAfterCollisionDoor(final Entity door) {
        if (door.getLocation().getX() == EAST.getX() && door.getLocation().getY() == EAST.getY()) {
            return new Pair<>(WEST.getX() + CORRECTOR, WEST.getY());
        } else if (door.getLocation().getX() == WEST.getX() && door.getLocation().getY() == WEST.getY()) {
            return new Pair<>(EAST.getX() - CORRECTOR, WEST.getY());
        } else if (door.getLocation().getX() == NORTH.getX() && door.getLocation().getY() == NORTH.getY()) {
            return new Pair<>(SOUTH.getX(), SOUTH.getY() - CORRECTOR);
        } else {
            return new Pair<>(NORTH.getX(), NORTH.getY() + CORRECTOR);
        }
    }

    /**
     * Static method used to reverse a coordinate.
     * 
     * @param x
     *            Coordinate to reverse
     * @return Coordinate reversed
     */
    public static Coordinates reversCoordinate(final Coordinates x) {
        return x == Coordinates.NORTH ? SOUTH
                : x == Coordinates.SOUTH ? Coordinates.NORTH : x == Coordinates.EAST ? Coordinates.WEST : EAST;
    }
}
