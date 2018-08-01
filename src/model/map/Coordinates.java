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
    NORTH("North", 0, 0.5, 0), SOUTH("South", 1, 0.5, 1), WEST("West", 0, 0, 0.5), EAST("East", 3, 1, 0.5);

    private final String name;
    private final int coordinateId;
    private final double height;
    private final double weight;

    private Coordinates(final String name, final int coordinateId, final double height, final double weight) {
        this.name = name;
        this.coordinateId = coordinateId;
        this.height = height;
        this.weight = weight;
    }

    /**
     * 
     * @return .
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return .
     */
    public int getCoordinateId() {
        return this.coordinateId;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
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
        return c.equals(NORTH) ? new Pair<Integer, Integer>(0, 1)
                : c.equals(SOUTH) ? new Pair<Integer, Integer>(0, -1)
                        : c.equals(WEST) ? new Pair<Integer, Integer>(-1, 0) : new Pair<Integer, Integer>(1, 0);
    }

    /**
     * 
     * @param x
     *            .
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
