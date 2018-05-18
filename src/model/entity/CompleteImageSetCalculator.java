package model.entity;

import java.util.List;
import java.util.Optional;

import model.Direction;

/**
 * Image calculator for Entity that have different Image for every direction.
 *
 */
public final class CompleteImageSetCalculator implements ImageCalculator {
    private static final int STAND_POSITION = 2;
    private Optional<Direction> prevDir;
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
     *            list image for weast direction movement
     */
    public CompleteImageSetCalculator(final List<String> northImages, final List<String> southImages,
            final List<String> eastImages, final List<String> weastImages) {
        this.prevDir = Optional.empty();
        this.northImages = northImages;
        this.southImages = southImages;
        this.eastImages = eastImages;
        this.weastImages = weastImages;
        c = 0;
    }

    @Override
    public String getCurrentImage(final Optional<Direction> d) {
        String i = "";
        if (!d.isPresent() && !prevDir.isPresent()) {
            return southImages.get(STAND_POSITION);
        } else if (!d.isPresent()) {
            i = prevDir.get().equals(Direction.S) || prevDir.get().equals(Direction.SW)
                    || prevDir.get().equals(Direction.SE) ? this.southImages.get(STAND_POSITION) : i;
            i = prevDir.get().equals(Direction.N) || prevDir.get().equals(Direction.NW)
                    || prevDir.get().equals(Direction.NW) ? this.northImages.get(STAND_POSITION) : i;
            i = prevDir.get().equals(Direction.W) ? this.weastImages.get(STAND_POSITION) : i;
            i = prevDir.get().equals(Direction.E) ? this.eastImages.get(STAND_POSITION) : i;
        } else {
            i = d.get().equals(Direction.N) || d.get().equals(Direction.NW) || d.get().equals(Direction.NE)
                    ? this.northImages.get(c)
                    : i;
            i = d.get().equals(Direction.S) || d.get().equals(Direction.SW) || d.get().equals(Direction.SE)
                    ? this.southImages.get(c)
                    : i;
            i = d.get().equals(Direction.E) ? this.eastImages.get(c) : i;
            c = c == 0 ? 1 : 0;
            this.prevDir = d;
        }
        return i;
    }

}
