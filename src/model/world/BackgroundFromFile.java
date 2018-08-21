package model.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import model.room.RoomType;

//CHECKSTYLE: MagicNumber OFF
/**
 * 
 * Enumeration of room files.
 */
public enum BackgroundFromFile {

    /**
     * type zero.
     */
    ZERO("/rooms/0.room"),
    /**
     * type one.
     */
    FIRST("/rooms/1.room"),
    /**
     * type second.
     */
    SECOND("/rooms/2.room"),
    /**
     * type third.
     */
    THIRD("/rooms/3.room"),
    /**
     * type fourth.
     */
    FOURTH("/rooms/4.room"),
    /**
     * type fifth.
     */
    FIfTH("/rooms/5.room"),
    /**
     * type sixth.
     */
    SIXTH("/rooms/6.room"),
    /**
     * type seventh.
     */
    SEVENTH("/rooms/7.room"),
    /**
     * type eighth.
     */
    EIGHTH("/rooms/8.room"),
    /**
     * type ninth.
     */
    NINTH("/rooms/9.room");

    private final String path;

    /**
     * Constructor.
     * 
     * @param path
     *            image path
     */
    BackgroundFromFile(final String path) {
        this.path = path;

    }

    /**
     * Return the enumeration of path.
     * 
     * @param seqNumber
     * @return String path
     */
    private static String getPathName(final int seqNumber) {
        return seqNumber == 1 ? BackgroundFromFile.FIRST.getPath()
                : seqNumber == 2 ? BackgroundFromFile.SECOND.getPath()
                        : seqNumber == 3 ? BackgroundFromFile.THIRD.getPath()
                                : seqNumber == 4 ? BackgroundFromFile.FOURTH.getPath()
                                        : seqNumber == 5 ? BackgroundFromFile.FIfTH.getPath()
                                                : seqNumber == 6 ? BackgroundFromFile.SIXTH.getPath()
                                                        : seqNumber == 9 ? BackgroundFromFile.ZERO.getPath()
                                                                : BackgroundFromFile.FIRST.getPath();
    }

    /**
     * Static enumeration method used to take a random room path.
     * 
     * @param type
     *            Rooms type
     * @return String path
     */
    public static String getRandomPath(final RoomType type) {
        final int seq = type.equals(RoomType.INTERMEDIATE) ? new Random().nextInt(5) + 2
                : type.equals(RoomType.FIRTS) ? 1 : type.equals(RoomType.VENDOR) ? 9 : 0;
        return getPathName(seq);
    }

    /**
     * Getter method to take Enumeration path.
     * 
     * @return file path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Getter method to take only bosses Enumeration path.
     * 
     * @return List<String> path of file roomType boss
     */
    public static List<String> getBossPath() {
        return Arrays.asList(BackgroundFromFile.SEVENTH.getPath(), BackgroundFromFile.NINTH.getPath(),
                BackgroundFromFile.EIGHTH.getPath());
    }
}
