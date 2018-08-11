package controller;

import model.GameStatus;
import model.Model;
import model.entity.Player;
import utilities.Pair;
import view.Sound;
import view.SoundImpl;
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
    private final ControllerInterface controller;
    private Model model;

    /**
     * @param controller
     *            .
     * @param view
     *            .
     */
    public GameLoop(final ControllerInterface controller, final View view, final Model model) {
        this.controller = controller;
        this.view = view;
        this.model = model;
    }

    /**
     * Missing JavaDoc.
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
        /*
         * if (this.model.isComplited()) { this.controller.saveScoreGame(); }
         */
        System.out.println(this.controller.getPlaterName());
        // this.controller.saveScoreGame();
        // this.controller.abortGameLoop();

    }

    /**
     * 
     * @param elapsed.
     */
    public void updateGame() {
        this.view.draw(this.model.getEntitiesToDrow(), this.model.getRoomBackGround());
        this.view.updateInfoToDraw(this.model.getPlayerLife(), this.model.getMoney(), this.model.getTime(),
                this.model.getPlayerDamage(), this.model.getPlayerAttSpeed(), this.model.getPlayerMvSpeed());
    }

    private synchronized boolean isInState(final Status state) {
        return this.state.equals(state);
    }

    private synchronized void setState(final Status state) {
        this.state = state;
    }

    /**
     * 
     */
    public void abort() {
        this.setState(Status.KILLED);
    }

    /**
     * 
     */
    public void pause() {
        if (this.isInState(Status.RUNNING)) {
            this.model.stopTime();
            this.setState(Status.PAUSED);
        }
    }

    /**
     * 
     */
    public void resumeGame() {
        if (this.isInState(Status.PAUSED)) {
            this.model.resumeTime();
            this.setState(Status.RUNNING);
        }
    }

    /**
     * 
     * @return .
     */
    public boolean isPaused() {
        return this.isInState(Status.PAUSED);
    }

    /**
     * 
     * @return .
     */
    public boolean isRunning() {
        return this.isInState(Status.RUNNING);
    }

}
