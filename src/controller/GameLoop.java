package controller;

import model.GameStatus;
import model.Model;
import view.ViewInterface;

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
	private volatile Status state;
	private final ViewInterface view;
	private final ControllerInterface controller;
	private Model model;

	/**
	 * @param controller
	 *            .
	 * @param view
	 *            .
	 */
	public GameLoop(final ControllerInterface controller, final ViewInterface view) {
		this.controller = controller;
		this.view = view;
	}

	/**
	 * Missing JavaDoc.
	 */
	public void run() {
		long previous = System.currentTimeMillis();
		this.setState(Status.RUNNING);
		while (!this.isInState(Status.KILLED)) {
			if (this.model.getGameStatus().equals(GameStatus.Running)) {
				long current = System.currentTimeMillis();
				int elapsed = (int) (current - previous);
				controller.processInput();
				updateGame(elapsed);
				waitForNextFrame(current);
				previous = current;
			} else if (this.model.getGameStatus().equals(GameStatus.Over)) {
				this.setState(Status.KILLED);
			}
		}
		this.controller.getCurrentHighScores("tommi");
		this.controller.abortGameLoop();
	}

	/**
	 * 
	 * @param current
	 *            .
	 */
	protected void waitForNextFrame(final long current) {
		long dt = System.currentTimeMillis() - current;
		if (dt < period) {
			try {
				Thread.sleep(period - dt);
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 
	 * @param elapsed
	 *            .
	 */
	public void updateGame(final int elapsed) {
		this.model.start();
		this.view.drawRoom(this.model.getRoomBackGround());
		this.view.drawEntities(this.model.getEntityToDrow());
		this.view.updateInfoToDraw(this.model.getPalyerInfo(), this.model.getMoney(),
				this.model.getTime().transformSecondInTime());
		checkEvents();
	}

	private void checkEvents() {
		if (this.model.getPalyerLife() <= 0) {
			this.setState(Status.KILLED);
		}
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
			this.setState(Status.PAUSED);
		}
	}

	/**
	 * 
	 */
	public void resumeGame() {
		if (this.isInState(Status.PAUSED)) {
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
