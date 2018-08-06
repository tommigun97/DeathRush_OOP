package model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Area;
import model.Direction;

public enum Boss {
    THOR {

        @Override
        public List<String> images(final Direction d) {

            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("n_sx", "n_dx"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("s_sx", "s_dx"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("e_sx", "e_dx"))
                                    : new ArrayList<>(Arrays.asList("w_sx", "w_dx"));
        }

        @Override
        public String standImage() {
            return "stand";
        }

        @Override
        public Area getArea() {
            return new Area(0.1, 0.1);
        }

        @Override
        public int getStartingMaxLife() {
            return 10;
        }

        @Override
        public double getSpeed() {
            return 0.04;
        }

        @Override
        public long startingBossShootFrequency() {
            return 10;
        }

        @Override
        public int shootingDamage() {
            return 1;
        }

        @Override
        public int collisionDamage() {
            return 2;
        }

        @Override
        public int reward() {
            return 50;
        }
    },
    CIATTO {

        @Override
        public List<String> images(final Direction d) {
            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("n_sx", "n_dx"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("s_sx", "s_dx"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("e_sx", "e_dx"))
                                    : new ArrayList<>(Arrays.asList("w_sx", "w_dx"));
        }

        @Override
        public String standImage() {
            return "stand";
        }

        @Override
        public Area getArea() {
            return new Area(0.1, 0.1);
        }

        @Override
        public int getStartingMaxLife() {
            return 10;
        }

        @Override
        public double getSpeed() {
            return 0.2;
        }

        @Override
        public long startingBossShootFrequency() {
            return 10;
        }

        @Override
        public int shootingDamage() {
            return 1;
        }

        @Override
        public int collisionDamage() {
            return 2;
        }

        @Override
        public int reward() {
            return 50;
        }
    },

    CROATTI {
        @Override
        public List<String> images(final Direction d) {
            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("n_sx", "n_dx"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("s_sx", "s_dx"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("e_sx", "e_dx"))
                                    : new ArrayList<>(Arrays.asList("w_sx", "w_dx"));
        }

        @Override
        public String standImage() {
            return "stand";
        }

        @Override
        public Area getArea() {
            return new Area(0.1, 0.1);
        }

        @Override
        public int getStartingMaxLife() {
            return 10;
        }

        @Override
        public double getSpeed() {
            return 0.2;
        }

        @Override
        public long startingBossShootFrequency() {
            return 10;
        }

        @Override
        public int shootingDamage() {
            return 1;
        }

        @Override
        public int collisionDamage() {
            return 2;
        }

        @Override
        public int reward() {
            return 50;
        }
    };

    /**
     * 
     * @param d
     *            direction required
     * @return images for specific direction
     */
    public abstract List<String> images(Direction d);

    /**
     * @return path for stand image
     */
    public abstract String standImage();

    /**
     * @return the player area
     */
    public abstract Area getArea();

    /**
     * @return starting max Life
     */
    public abstract int getStartingMaxLife();

    /**
     * @return movement speed
     */
    public abstract double getSpeed();

    /**
     * @return shoot frequency
     */
    public abstract long startingBossShootFrequency();

    /**
     * @return shooting damage
     */
    public abstract int shootingDamage();

    /**
     * @return collision damage.
     */
    public abstract int collisionDamage();

    /**
     * @return reward.
     */
    public abstract int reward();

}
