package controller;

import java.util.List;

import model.Direction;
import view.ViewInterface;

/**
 * 
 * @author tommi
 *
 */
public interface ControllerInterface {

    /**
     * Make the Game Loop start to work.
     * 
     * @throws IllegalStateException .
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
     * Save the score (time) and the name player.
     * 
     * @param namePlayer
     *            .
     * @return True if the operation was successful, false otherwise.
     */
    boolean getCurrentHighScores(String namePlayer);

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
     * @return 
     * 
     */
    void processInput();

	int getTimer();

}
