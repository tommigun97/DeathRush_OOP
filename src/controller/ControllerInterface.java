package controller;

import java.util.List;

import utilities.Pair;
import view.ViewInterface;

/**
 * 
 * @author tommi
 *
 */
public interface ControllerInterface {
    /**
     * Start the View.
     * 
     * @param v 
     * The provided ViewInterface
     */
    void setView(ViewInterface v);

    /**
     * Make the Game Loop start to work.
     * 
     */
    void startGameLoop();

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
     * Save the score (time) and the name player.
     * 
     * @param namePlayer
     * @return True if the operation was successful, false otherwise.
     */
    boolean setPlayerName(String namePlayer);

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

    List<Pair<String, Pair<Integer, Integer>>> getMap();

}
