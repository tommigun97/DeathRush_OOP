package model;

import java.util.Random;

/**
 * The Enumeration for the direction that are used for entities movement:
 * North(N) South(S) West(W) East(E) There are also the combinations of two
 * directions.
 */
public enum Direction {

    /**
     * No direction.
     */
    NOTHING {
        @Override
        public void changeLocation(final Location loc, final double speed) {

        }
    },
    /**
     * North.
     */
    N {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            loc.setY(loc.getY() - speed);
        }
    },
    /**
     * South.
     */
    S {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            loc.setY(loc.getY() + speed);
        }
    },
    /**
     * West.
     */
    W {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            loc.setX(loc.getX() - speed);
        }
    },
    /**
     * East.
     */
    E {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            loc.setX(loc.getX() + speed);
        }
    },
    /**
     * NorthWeast.
     */
    NW {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            Direction.N.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
            Direction.W.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
        }
    },
    /**
     * NorthEast.
     */
    NE {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            Direction.N.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
            Direction.E.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
        }
    },
    /**
     * SouthWeast.
     */
    SW {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            Direction.S.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
            Direction.W.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
        }
    },
    /**
     * SouthWeast.
     */
    SE {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            Direction.S.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
            Direction.E.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
        }
    };
    private static final double OBLIQUITY_MODIFIER = 0.7071;

    /**
     * Method for move in a certain direction.
     * 
     * @param loc
     *            Location to modifier
     * @param speed
     *            Entity movement speed of the entity
     */
    public abstract void changeLocation(Location loc, double speed);

    /**
     * Get a random direction.
     * 
     * @return a random direction
     */
    public static Direction randomDirection() {
        final int i = new Random().nextInt(4);
        return i == 0 ? N : i == 1 ? S : i == 2 ? E : W;
    }

}
