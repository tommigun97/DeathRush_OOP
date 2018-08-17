package view;

/**
 * 
 * This interface provide basic method for music manipulation.
 *
 */
public interface Sound {
    /**
     * This enumeration contain the path of the song.
     *
     */
    enum SONG {
    MENUSONG("/music/menuOP.wav"), GAMESONG("/music/gameOP.wav");

        private final String pathToSong;

        SONG(final String path) {
            this.pathToSong = path;
        }

        public String getPathToSong() {
            return this.pathToSong;
        }
    }

    /**
     * When called play the track selected.
     * 
     * @param pathToString
     *            song path.
     */
    void musicPlay(String pathToString);

    /**
     * When called stop the track selected.
     */
    void musicStop();
}
