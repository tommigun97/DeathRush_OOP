package model.entity;

import java.util.List;

import model.Direction;

/**
 * Image calculator for Entity that have different Image for every direction.
 *
 */
public final class CompleteImageSetCalculator implements ImageCalculator {
    private static final int STAND_POSITION = 2;
    private Direction prevDir;
    private final List<String> northImages;
    private final List<String> southImages;
    private final List<String> eastImages;
    private final List<String> weastImages;
    private int c;

    /**
     * @param northImages
     *            list image for north direction movement
     * @param southImages
     *            list image for south direction movement
     * @param eastImages
     *            list image for east direction movement
     * @param weastImages
     *            list image for west direction movement
     */
    public CompleteImageSetCalculator(final List<String> northImages, final List<String> southImages,
            final List<String> eastImages, final List<String> weastImages) {
        this.prevDir = Direction.NOTHING;
        this.northImages = northImages;
        this.southImages = southImages;
        this.eastImages = eastImages;
        this.weastImages = weastImages;
        c = 0;
    }

    @Override
    public String getCurrentImage(final Direction d) {
        String i = "";
        if (d.equals(Direction.NOTHING) && prevDir.equals(Direction.NOTHING)) {
            return southImages.get(STAND_POSITION);
        } else if (d.equals(Direction.NOTHING)) {
            i = prevDir.equals(Direction.S) || prevDir.equals(Direction.SW)
                    || prevDir.equals(Direction.SE) ? this.southImages.get(STAND_POSITION) : i;
            i = prevDir.equals(Direction.N) || prevDir.equals(Direction.NW)
                    || prevDir.equals(Direction.NW) ? this.northImages.get(STAND_POSITION) : i;
            i = prevDir.equals(Direction.W) ? this.weastImages.get(STAND_POSITION) : i;
            i = prevDir.equals(Direction.E) ? this.eastImages.get(STAND_POSITION) : i;
        } else {
            i = d.equals(Direction.N) || d.equals(Direction.NW) || d.equals(Direction.NE)
                    ? this.northImages.get(c)
                    : i;
            i = d.equals(Direction.S) || d.equals(Direction.SW) || d.equals(Direction.SE)
                    ? this.southImages.get(c)
                    : i;
            i = d.equals(Direction.E) ? this.eastImages.get(c) : i;
            c = c == 0 ? 1 : 0;
            this.prevDir = d;
        }
        return i;
    }

}
