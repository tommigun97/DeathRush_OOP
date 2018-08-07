package model.entity;

import model.Direction;
import utilities.Pair;

/**
 * Image calculator for entities that are draw by only two images and it needs
 * that for every frame the images switch.
 *
 */
public final class TwoImageCalculator implements ImageCalculator {

    private final Pair<String, String> images;
    private int c;
    private String currentImage;
    private long lastTimeSaved;

    /**
     * @param xImg
     *            one of the two images
     * @param yImg
     *            one of the two images
     */
    public TwoImageCalculator(final String xImg, final String yImg) {
        this.images = new Pair<String, String>(xImg, yImg);
        c = 0;
        lastTimeSaved = 0;
        currentImage = images.getFirst();
    }

    @Override
    public String getCurrentImage(final Direction d) {
        if (refresh(lastTimeSaved)) {
            c = c == 0 ? 1 : 0;
            lastTimeSaved = System.currentTimeMillis();
            return c == 0 ? images.getFirst() : images.getSecond();
        } else {
            return currentImage;
        }
    }

}
