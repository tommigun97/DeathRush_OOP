package model.map;

import java.util.Random;

import utilities.Pair;

/**
 * 
 *
 */
public enum Coordinates {
    /**
     * 
     */
    NORTH("North", 0), SOUTH("South", 1), WEST("West", 2), EAST("East", 3);

    private final String name;
    private final int coordinateId;

    private Coordinates(final String name, final int coordinateId) {
        this.name = name;
        this.coordinateId = coordinateId;
    }
/**
 * 
 * @return .
 */
    public String getName() {
        return name;
    }
/**
 * 
 * @return .
 */
    public int getCoordinateId() {
        return coordinateId;
    }
/**
 * 
 * @return .
 */
    public static Coordinates getRandomCoordinate() {
        int r = new Random().nextInt(4);
        return r == 0 ? NORTH : r == 1 ? SOUTH : r == 2 ? WEST : EAST;
    }
    
    public static Pair<Integer, Integer> getMovementFromCoordinates(Coordinates c) {
        return c.equals(NORTH) ? new Pair<Integer, Integer>(0, 1) :
               c.equals(SOUTH) ? new Pair<Integer, Integer>(0, -1) :
               c.equals(WEST) ? new Pair<Integer, Integer>(-1, 0) :
               new Pair<Integer, Integer>(1, 0);
    }
/**
 * 
 * @param x .
 * @return .
 */
    public static Coordinates reversCoordinate(final Coordinates x) {
        if (x.equals(NORTH)) {
            return SOUTH;
        }
        if (x.equals(SOUTH)) {
            return NORTH;
        }
        if (x.equals(EAST)) {
            return WEST;
        }
        if (x.equals(WEST)) {
            return EAST;
        }
        return x;
    }
}
