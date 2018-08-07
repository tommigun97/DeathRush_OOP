package model.map;

import java.util.Random;

import model.Area;
import model.entity.DoorStatus;
import model.entity.Entity;
import utilities.Pair;

/**
 * 
 *
 */
public enum Coordinates {
    /**
     * 
     */
    EAST("room/door_open_E.png", "room/door_closed_E.png", 0, 0.03, 0.5, new Area(0.10, 0.10)), 
    WEST("room/door_open_W.png", "room/door_closed_W.png", 1, 0.97, 0.5, new Area(0.10, 0.10)), 
   SOUTH("room/door_open_S.png", "room/door_closed_S.png", 0, 0.5, 0.97, new Area(0.07, 0.15)), 
    NORTH("room/door_open_N.png", "room/door_closed_N.png", 3, 0.5, 0.03, new Area(0.07, 0.15));

  

	private static final double CORRECTOR = 0.17;
	
	private final String open;
    private final String close;
    private final int coordinateId;
    private final double x;
    private final double y;
    private final Area area;

    private Coordinates(final String openImage, final String closedImage, final int coordinateId, final double x, final double y, final Area area) {
        this.open = openImage;
        this.close = closedImage;
        this.coordinateId = coordinateId;
        this.x = x;
        this.y = y;
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

	public double getY() {
        return y;
    }

    public double getX() {
        return x;
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

    public  String getImage(DoorStatus x) {
    	return x.equals(DoorStatus.OPEN) ? this.getOpen() : this.getClose();
    }
    
    public static Pair<Double, Double> reverseAfterCollisionDoor(Entity door){
    	 if (door.getLocation().getX() == EAST.getX() && door.getLocation().getY() == EAST.getY()) {
             return new Pair<>(WEST.getX() + CORRECTOR , WEST.getY());
         }
    	 if (door.getLocation().getX() == WEST.getX() && door.getLocation().getY() == WEST.getY()) {
             return new Pair<>(EAST.getX() - CORRECTOR , WEST.getY());
         }
    	 if (door.getLocation().getX() == NORTH.getX() && door.getLocation().getY() == NORTH.getY()) {
             return new Pair<>(SOUTH.getX() , SOUTH.getY() + CORRECTOR );
         }
    	 if (door.getLocation().getX() == SOUTH.getX() && door.getLocation().getY() == SOUTH.getY()) {
             return new Pair<>(NORTH.getX() , NORTH.getY() - CORRECTOR );
         }
    	 return null;
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
