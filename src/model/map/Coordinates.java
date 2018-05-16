package model.map;

import java.util.Random;

public enum Coordinates {
    
    NORTH("North", 0),
    SOUTH("South", 1),
    WEST("West", 2),
    EAST("East", 3);

    private final String name;
    private final int coordinateId;

            private Coordinates(String name, int coordinateId) {
                    this.name = name;
                    this.coordinateId = coordinateId;
            }

            public String getName() {
                    return name;
            }

            public int getCoordinateId() {
                    return coordinateId;
            }

            public static Coordinates getRandomCoordinate() {
                    int r = new Random().nextInt(4);
                    return r == 0 ? NORTH : r == 1 ? SOUTH : r == 2 ? WEST : EAST;
            }
            
            public static Coordinates reversCoordinate(Coordinates x) {
                    if(x.equals(NORTH)) {
                            return SOUTH;
                    }
                    if(x.equals(SOUTH)) {
                            return NORTH;
                    }
                    if(x.equals(EAST)) {
                            return WEST;
                    }
                    if(x.equals(WEST)) {
                            return EAST;
                    }
                    return x;
            }
}
