package model.entity;

import model.Direction;
import utilities.Pair;

/**
 * Image calculator for entities that are draw by only two images.
 *
 */
public final class TwoImageCalculator implements ImageCalculator {

    private final Pair<String, String> images;
    private int c;

    /**
     * @param xImg one of the two images
     * @param yImg one of the two images
     */
    public TwoImageCalculator(final String xImg, final String yImg) {
        this.images = new Pair<String, String>(xImg, yImg);
        c = 0;
    }

    @Override
    public String getCurrentImage(final Direction d) {
        c = c == 0 ? 1 : 0;
        return c == 0 ? images.getFirst() : images.getSecond();
    }

}
