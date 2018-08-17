package model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Area;
import model.Direction;

/**
 * Enum for boss statistics.
 *
 */
public enum Boss {
    /**
     * First Boss.
     *
     */
    BOSS_1 {

        @Override
        public List<String> images(final Direction d) {

            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("boss1/2.png", "boss1/3.png"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("boss1/2.png", "boss1/3.png"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("boss1/4.png", "boss1/5.png"))
                                    : new ArrayList<>(Arrays.asList("boss1/4.png", "boss1/5.png"));
        }

        @Override
        public String standImage() {
            return "boss1/1.png";
        }

        @Override
        public Area getArea() {
            return new Area(0.1, 0.2);
        }

        @Override
        public int getStartingMaxLife() {
            return 4000;
        }

        @Override
        public double getSpeed() {
            return 0.005;
        }

        @Override
        public long startingBossShootFrequency() {
            return 500;
        }

        @Override
        public int shootingDamage() {
            return 1;
        }

        @Override
        public int collisionDamage() {
            return 1;
        }

        @Override
        public int reward() {
            return 75;
        }
    },
    /**
     * Second Boss.
     *
     */
    BOSS_2 {

        @Override
        public List<String> images(final Direction d) {
            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("boss2/2.png", "boss2/3.png"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("boss2/2.png", "boss2/3.png"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("boss2/4.png", "boss2/5.png"))
                                    : new ArrayList<>(Arrays.asList("boss2/4.png", "boss2/5.png"));

        }

        @Override
        public String standImage() {
            return "boss2/1.png";
        }

        @Override
        public Area getArea() {
            return new Area(0.08, 0.2);
        }

        @Override
        public int getStartingMaxLife() {
            return 3750;
        }

        @Override
        public double getSpeed() {
            return 0.005;
        }

        @Override
        public long startingBossShootFrequency() {
            return 500;
        }

        @Override
        public int shootingDamage() {
            return 1;
        }

        @Override
        public int collisionDamage() {
            return 1;
        }

        @Override
        public int reward() {
            return 65;
        }
    },

    /**
     * Third boss.
     *
     */
    BOSS_3 {
        @Override
        public List<String> images(final Direction d) {
            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("boss3/2.png", "boss3/3.png"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("boss3/2.png", "boss3/3.png"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("boss3/4.png", "boss3/5.png"))
                                    : new ArrayList<>(Arrays.asList("boss3/4.png", "boss3/5.png"));
        }

        @Override
        public String standImage() {
            return "boss3/1.png";
        }

        @Override
        public Area getArea() {
            return new Area(0.04, 0.15);
        }

        @Override
        public int getStartingMaxLife() {
            return 3500;
        }

        @Override
        public double getSpeed() {
            return 0.006;
        }

        @Override
        public long startingBossShootFrequency() {
            return 1000;
        }

        @Override
        public int shootingDamage() {
            return 1;
        }

        @Override
        public int collisionDamage() {
            return 1;
        }

        @Override
        public int reward() {
            return 55;
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
