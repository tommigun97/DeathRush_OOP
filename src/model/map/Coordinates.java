package model.map;

import java.util.Random;

import model.Area;
import utilities.Pair;

/**
 * 
 *
 */
public enum Coordinates {
    /**
     * 
     */
    NORTH("room/door_open_N.png", "room/door_closed_N.png", 0, 0.03, 0.5, new Area(0.10, 0.10)), 
    SOUTH("room/door_open_S.png", "room/door_closed_S.png", 1, 0.97, 0.5, new Area(0.10, 0.10)), 
    WEST("room/door_open_W.png", "room/door_closed_W.png", 0, 0.5, 0.99, new Area(0.07, 0.15)), 
    EAST("room/door_open_E.png", "room/door_closed_E.png", 3, 0.5, 0.01, new Area(0.07, 0.15));

  

	private final String open;
    private final String close;
    private final int coordinateId;
    private final double height;
    private final double weight;
    private final Area area;

    private Coordinates(final String openImage, final String closedImage, final int coordinateId, final double height, final double weight, final Area area) {
        this.open = openImage;
        this.close = closedImage;
        this.coordinateId = coordinateId;
        this.height = height;
        this.weight = weight;
        this.area = area;
    }

    /**
     * 
     * @return .
     */
    

    /**
     * 
     * @return .
     */
    public int getCoordinateId() {
        return this.coordinateId;
    }

    public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}

	public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
    public Area getArea() {
  		return area;
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
