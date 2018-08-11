package view;

public interface Sound {

    public enum song {
        MENUSONG("/music/menuOP.wav"), GAMESONG("/music/gameOP.wav");

        private final String pathToSong;

        private song(String path) {
            this.pathToSong = path;
        }

        public String getPathToSong() {
            return this.pathToSong;
        }
    }

    void musicPlay(String pathToString);

    void musicStop();
}
