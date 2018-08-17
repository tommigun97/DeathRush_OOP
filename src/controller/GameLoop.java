package controller;

import model.GameStatus;
import model.Model;
import view.View;

/**
 * This is the heart of the game. It will refresh game's frame every second. The
 * GameLoop synchronizes View and Model.
 */

public class GameLoop extends Thread {

    /**
     * Enum to describe the possible states of the GameLoop.
     */
    private enum Status {
    RUNNING, PAUSED, KILLED, READY;
    }

    private static final long PERIOD = 20;

    private long period = PERIOD;
    private volatile Status state = Status.READY;
    private final View view;
    private final Controller controller;
    private Model model;

    /**
     * The constructor of the game loop will be able to run the game.
     * 
     * @param controller
     *            set controller.
     * @param view
     *            set view.
     * @param model
     *            set model.
     */
    public GameLoop(final Controller controller, final View view, final Model model) {
        this.controller = controller;
        this.view = view;
        this.model = model;
    }

    /**
     * This method is the heart of the game, it's used to update every beat Missing
     * JavaDoc.
     */
    public void run() {
        this.setState(Status.RUNNING);
        this.model.start(controller.getPlayer());
        while (!this.isInState(Status.KILLED)) {
            if (this.isInState(Status.RUNNING)) {

                long time = System.currentTimeMillis();
                if (this.model.getGameStatus().equals(GameStatus.Running)) {
                    controller.processInput();
                    updateGame();
                } else if (this.model.getGameStatus().equals(GameStatus.Over)) {
                    this.view.gameOver();
                    this.setState(Status.KILLED);
                }
                if (this.model.getGameStatus().equals(GameStatus.Won)) {
                    this.view.youWin();
                    this.setState(Status.KILLED);
                }
                long wait = time - System.currentTimeMillis();
                if (wait < period) {
                    try {
                        Thread.sleep(period - wait);
                    } catch (Exception ex) {
                    }
                }
            }
        }

    }

    /**
     * This method is called from the run method to update the view by the model.
     */
    public void updateGame() {
        this.view.draw(this.model.getEntitiesToDrow(), this.model.getRoomBackGround());
        this.view.updateInfoToDraw(this.model.getPlayerLife(), this.model.getMoney(), this.model.getTime(),
                this.model.getPlayerDamage(), this.model.getPlayerAttSpeed(), this.model.getPlayerMvSpeed());
    }

    /**
     * Ask if the Status state is the current state.
     * 
     * @param state
     * @return true if the state is equal to the current.
     */
    private synchronized boolean isInState(final Status state) {
        return this.state.equals(state);
    }

    /**
     * Method used to set the state of the game.
     * 
     * @param state
     */
    private synchronized void setState(final Status state) {
        this.state = state;
    }

    /**
     * Kill the gameLoop.
     */
    public void abort() {
        this.setState(Status.KILLED);
    }

    /**
     * Set in pause the gameLoop.
     */
    public void pause() {
        if (this.isInState(Status.RUNNING)) {
            this.model.stopTime();
            this.setState(Status.PAUSED);
        }
    }

    /**
     * Resume the gameLoop.
     */
    public void resumeGame() {
        if (this.isInState(Status.PAUSED)) {
            this.model.resumeTime();
            this.setState(Status.RUNNING);
        }
    }

    /**
     * Ask if the gameLoop status is equal to Paused.
     * 
     * @return true if the game is in pause
     */
    public boolean isPaused() {
        return this.isInState(Status.PAUSED);
    }

    /**
     * Ask if the gameLoop status is equal to Running.
     * 
     * @return true if the game is Running
     */
    public boolean isRunning() {
        return this.isInState(Status.RUNNING);
    }

}
