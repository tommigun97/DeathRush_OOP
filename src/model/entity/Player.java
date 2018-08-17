package model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Area;
import model.Direction;

/**
 * Enum for player statistic.
 *
 */
public enum Player {
    /**
     * Simo.
     *
     */
    SIMO {

        @Override
        public List<String> images(final Direction d) {

            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("simo/2.png", "simo/3.png"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("simo/2.png", "simo/3.png"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("simo/4.png", "simo/5.png"))
                                    : new ArrayList<>(Arrays.asList("simo/4.png", "simo/5.png"));
        }

        @Override
        public String standImage() {
            return "simo/1.png";
        }

        @Override
        public Area getArea() {
            return new Area(0.03, 0.12);
        }

        @Override
        public int getStartingMaxLife() {
            return 20;
        }

        @Override
        public double getSpeed() {
            return 0.008;
        }

        @Override
        public long startingPlayerShootFrequency() {
            return 450;
        }

        @Override
        public int shootingDamage() {
            return 1000;
        }

    },
    /**
     * Anis.
     *
     */
    ANIS {

        @Override
        public List<String> images(final Direction d) {

            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("anis/2.png", "anis/3.png"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("anis/2.png", "anis/3.png"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("anis/4.png", "anis/5.png"))
                                    : new ArrayList<>(Arrays.asList("anis/4.png", "anis/5.png"));
        }

        @Override
        public String standImage() {
            return "anis/1.png";
        }

        @Override
        public Area getArea() {
            return new Area(0.03, 0.12);
        }

        @Override
        public int getStartingMaxLife() {
            return 15;
        }

        @Override
        public double getSpeed() {
            return 0.009;
        }

        @Override
        public long startingPlayerShootFrequency() {
            return 400;
        }

        @Override
        public int shootingDamage() {
            return 90;
        }
    },

    /**
     * Kaso.
     *
     */
    KASO {
        @Override
        public List<String> images(final Direction d) {

            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("kaso/2.png", "kaso/3.png"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("kaso/2.png", "kaso/3.png"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("kaso/4.png", "kaso/5.png"))
                                    : new ArrayList<>(Arrays.asList("kaso/4.png", "kaso/5.png"));
        }

        @Override
        public String standImage() {
            return "kaso/1.png";
        }

        @Override
        public Area getArea() {
            return new Area(0.04, 0.11);
        }

        @Override
        public int getStartingMaxLife() {
            return 18;
        }

        @Override
        public double getSpeed() {
            return 0.007;
        }

        @Override
        public long startingPlayerShootFrequency() {
            return 500;
        }

        @Override
        public int shootingDamage() {
            return 180;
        }
    },

    /**
     * Tommi.
     *
     */
    TOMMI {
        @Override
        public List<String> images(final Direction d) {

            return d.equals(Direction.N) ? new ArrayList<>(Arrays.asList("tommi/2.png", "tommi/3.png"))
                    : d.equals(Direction.S) ? new ArrayList<>(Arrays.asList("tommi/2.png", "tommi/3.png"))
                            : d.equals(Direction.E) ? new ArrayList<>(Arrays.asList("tommi/4.png", "tommi/5.png"))
                                    : new ArrayList<>(Arrays.asList("tommi/4.png", "tommi/5.png"));
        }

        @Override
        public String standImage() {
            return "tommi/1.png";
        }

        @Override
        public Area getArea() {
            return new Area(0.05, 0.13);
        }

        @Override
        public int getStartingMaxLife() {
            return 15;
        }

        @Override
        public double getSpeed() {
            return 0.008;
        }

        @Override
        public long startingPlayerShootFrequency() {
            return 300;
        }

        @Override
        public int shootingDamage() {
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
    public abstract long startingPlayerShootFrequency();

    /**
     * @return shooting damage
     */
    public abstract int shootingDamage();
}
