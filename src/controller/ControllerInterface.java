package controller;

import java.util.List;

import utilities.Pair;
import view.ViewInterface;

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

    /**
     * Set the GUI of the game.
     * 
     * @param view
     *            .
     */
    void setView(ViewInterface view);

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
    List<Pair<String, Integer>> getCurrentHighScores();

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
     * 
     * @return the model time
     */
    Time getTimer();
    /**
     * This method clear the score list.
     * 
     * @return true when the list is clear.
     */
    boolean emptyScores();

}
