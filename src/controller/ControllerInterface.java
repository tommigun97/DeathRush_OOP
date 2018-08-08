package controller;

import java.util.List;

import model.Time;
import model.entity.Player;
import utilities.Pair;
import view.View;

/**
 * 
 *
 */
public interface ControllerInterface {
    

    /**
     * Make the Game Loop start to work.
     * 
     * @throws IllegalStateException
     *             .
     */
    void startGameLoop() throws IllegalStateException;

    void selectPlayer(Player pg);
    
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
    
    boolean saveScoreGame();
    
    void setPlayerName(String name);
    
    String getPlaterName();

}
