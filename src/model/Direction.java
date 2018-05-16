package model;

/**
 * The Enumeration for the direction that are used for entities movement:
 * North(N) South(S) West(W) East(E) There are also the combinations of two
 * directions.
 */
public enum Direction {
	
	/**
	*
	*/
	NOTHING {
		@Override
		public void changeLocation(Location loc, double speed) {
			
		}
	},
    /**
     * 
     */
    N {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            loc.setY(loc.getY() - speed);
        }
    },
    /**
     * 
     */
    S {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            loc.setY(loc.getY() + speed);
        }
    },
    /**
     * 
     */
    W {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            loc.setX(loc.getX() - speed);
        }
    },
    /**
     * 
     */
    E {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            loc.setX(loc.getX() + speed);
        }
    },
    /**
     * 
     */
    NW {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            Direction.N.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
            Direction.W.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
        }
    },
    /**
     * 
     */
    NE {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            Direction.N.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
            Direction.E.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
        }
    },
    /**
     * 
     */
    SW {
        @Override
        public void changeLocation(final Location loc, final double speed) {
            Direction.S.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
            Direction.W.changeLocation(loc, speed * OBLIQUITY_MODIFIER);
        }
    },
    /**
     * 
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

}
