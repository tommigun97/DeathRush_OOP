package model.entity;

import java.util.List;

import model.Direction;

/**
 * Image calculator for Entity that have different Image for every direction.
 *
 */
public final class CompleteImageSetCalculator implements ImageCalculator {
    private final List<String> northImages;
    private final List<String> southImages;
    private final List<String> eastImages;
    private final List<String> weastImages;
    private final String stand;
    private int c;
    private String currentImage;
    private long lastTimeSaved;

    /**
     * @param northImages
     *            list image for north direction movement
     * @param southImages
     *            list image for south direction movement
     * @param eastImages
     *            list image for east direction movement
     * @param weastImages
     *            list image for west direction movement
     * @param stand
     *            image for standing position
     */
    public CompleteImageSetCalculator(final List<String> northImages, final List<String> southImages,
            final List<String> eastImages, final List<String> weastImages, final String stand) {
        this.northImages = northImages;
        this.southImages = southImages;
        this.eastImages = eastImages;
        this.weastImages = weastImages;
        this.stand = stand;
        c = 0;
        this.currentImage = stand;
        lastTimeSaved = 0;
    }

    @Override
    public String getCurrentImage(final Direction d) {
        if (refresh(lastTimeSaved)) {
            currentImage = "";
            if (d.equals(Direction.NOTHING)) {
                currentImage = this.stand;
            } else {
                currentImage = d.equals(Direction.E) || d.equals(Direction.NW) || d.equals(Direction.NE)
                        ? this.eastImages.get(c)
                        : currentImage;
                currentImage = d.equals(Direction.W) || d.equals(Direction.SW) || d.equals(Direction.SE)
                        ? this.weastImages.get(c)
                        : currentImage;
                currentImage = d.equals(Direction.N) ? this.northImages.get(c) : currentImage;
                currentImage = d.equals(Direction.S) ? this.southImages.get(c) : currentImage;
                c = c == 0 ? 1 : 0;
            }
            lastTimeSaved = System.currentTimeMillis();
            return currentImage;
        } else {
            return currentImage;
        }
    }

}
