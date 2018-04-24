package controller;

import java.util.List;
import utilities.Pair;
import view.View;
import view.ViewInterface;

public interface ControllerInterface {

	
	/**
	 *  Make the Game Loop start to work
	 *  
	 */
	public void startGameLoop();
	
	/**
	 * Set the GUI of the game
	 * @param view
	 */
	public void setView(ViewInterface view);
	
	/**
	 * Set Game loop in pause
	 */
	public void pauseGameLoop();
	
	/***
	 * Kill the Game loop
	 */
	public void abortGameLoop();
	
	/**
	 * Resume Game loop from pause
	 */
	public void resumeGameLoop();
	
	/**
	 * Save the score (time) and the name player
	 * @param namePlayer
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean setPlayerName(String namePlayer);
	
	  /**
     * Checks if there is a running game (existing and not paused).
     *
     * @return True if there is a running GameLoop, false otherwise.
     */
	public boolean isGameLoopRunning();
	
	/**
     * Checks if there is a paused game (existing and not running).
     *
     * @return True if there is a paused GameLoop, false otherwise.
     */
	public boolean isGameLoopPaused();
	
	public List<Pair<String, Pair<Integer, Integer>>> getMap();
	
	
	
}
