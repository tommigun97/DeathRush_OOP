package controller;

import java.util.List;

import model.entity.Player;
import utilities.Pair;
import view.View;

/**
 * 
 *
 */
public interface Controller {
    

    /**
     * Make the Game Loop start to work.
     * 
     * @throws IllegalStateException
     *             .
     */
    void startGameLoop() throws IllegalStateException;

    /**
     * A method to choose the selected player
     * @param pg
     */
	void selectPlayer(Player pg);
    
	/**
	 * 
	 * @return the entity player in game
	 */
    Player getPlayer();
    
    /**
     * Set the GUI of the game.
     * 
     * @param view
     *            .
     */
    void setView(View view);

    /**
     * Set Game loop in pause.
     */
    void pauseGameLoop();

    /***
     * Kill the Game loop.
     */
    void abortGameLoop();

    /**
     * Resume Game loop from pause.
     */
    void resumeGameLoop();

    /**
     * Returns the scoreList. If the current list cannot be loaded, an empty list is
     * returned. The returned list is a defensive copy.
     *
     * @return A List of scores (Pair<String, Integer>, a player name and a score)
     */
    List<Pair<Pair<String, Integer>, String>> getCurrentHighScores();

    /**
     * Checks if there is a running game (existing and not paused).
     *
     * @return True if there is a running GameLoop, false otherwise.
     */
    boolean isGameLoopRunning();

    /**
     * Checks if there is a paused game (existing and not running).
     *
     * @return True if there is a paused GameLoop, false otherwise.
     */
    boolean isGameLoopPaused();

    /**
     * take Input List from view and call model to execute it.
     */
    void processInput();

    /**
     * This method clear the score list.
     * 
     * @return true when the list is clear.
     */
    boolean emptyScores();
    
    /**
     * A method to save the score on the Score manager
     * @return true if there is no error in loading save
     */
    boolean saveScoreGame();
    
    /**
     * Set the player name for the Score List
     * @param name
     */
    void setPlayerName(String name);
    
    /**
     * 
     * @return The name of the player
     */
    String getPlaterName();
    
    /**
     * 
     * @return the map by a call from model
     */
    int[][] getViewMap();
    
    /**
     * 
     * @return the column x of the map matrix
     */
    int getXmap();
    
    /**
     * 
     * @return the column y of the map matrix
     */
    int getYmap();
    
   /**
    * Update the map 
    */
    void mapUpdate();
    
    /**
     * Start the song s
     * @param s
     */
    void playSong(String s);
    
    /**
     * Method to change the song
     * @param s
     */
    void changeSong(String s);

}
