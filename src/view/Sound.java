package view;

public interface Sound extends Runnable {
	 
	public enum song{
		MENUSONG("music/menu.mp3"),
		GAMESONG("music/game.mp3");
		
		private final String pathToSong;
		
		private song(String path) {
			this.pathToSong = path;
		}
		
		public String getPathToSong() {
			return this.pathToSong;
		}
	}
	
	void changeSong(String path);
	void playSound();
	void setCurrentFileSoundPath(String currentFileSoundPath);
}
