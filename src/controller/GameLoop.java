package controller;

import java.util.LinkedList;
import java.util.List;
import model.Direction;
import model.Location;
import utilities.Input;
import utilities.Pair;
import view.ViewInterface;

/**
 * This is the heart of the game. It will refresh game's frame every second. The
 * GameLoop synchronizes View and Model.
 */

public class GameLoop extends Thread {

	/**
	 * Enum to describe the possible states of the GameLoop
	 *
	 */
	private enum Status {
		RUNNING, PAUSED, KILLED, READY;
	}

	private long period = 20;
	private volatile Status state;
	private final ViewInterface view;
	private final ControllerInterface controller;
	private final InputManager input;
	private ModelInterface model;

	public GameLoop(final ControllerInterface controller, final ViewInterface view) {
		this.controller = controller;
		this.view = view;
		this.input = InputManager.getInputHandler();
		this.model = new Model();
	}

	public void run() {
		long previous = System.currentTimeMillis();
		this.setState(Status.RUNNING);
		while (!this.isInState(Status.KILLED)) {
			if (this.model.getGameStatus().equals(GameStatus.Running)) {
				long current = System.currentTimeMillis();
				int elapsed = (int) (current - previous);
				processInput();
				updateGame(elapsed);
				render();
				waitForNextFrame(current);
				previous = current;
			} else if (this.model.getGameStatus().equals(GameStatus.Over)) {
				this.setState(Status.KILLED);
			}
		}
		this.controller.abortGameLoop();
	}

	protected void waitForNextFrame(long current) {
		long dt = System.currentTimeMillis() - current;
		if (dt < period) {
			try {
				Thread.sleep(period - dt);
			} catch (Exception ex) {
			}
		}
	}

	public void processInput() {
		this.input.addInput();
		input.getInputList().forEach(x -> {
			if (x.equals(Input.W)) {
				this.model.getPlayer().move();
			} else if (x.equals(Input.S)) {
				this.model.getPlayer().move();
			} else if (x.equals(Input.A)) {
				this.model.getPlayer().move();
			} else if (x.equals(Input.D)) {
				this.model.getPlayer().move();
			} else if (x.equals(Input.SHOT_UP)) {
				this.model.getPlayer().shot();
			} else if (x.equals(Input.SHOT_DOWN)) {
				this.model.getPlayer().shot();
			} else if (x.equals(Input.SHOT_LEFT)) {
				this.model.getPlayer().shot();
			} else if (x.equals(Input.SHOT_RIGHT)) {
				this.model.getPlayer().shot();
			} else if (x.equals(Input.ESC)) {
				this.controller.pauseGameLoop();
				this.view.showPauseMenu();
			} else if (x.equals(Input.M)) {
				this.view.showMap();
			}
		});
	}

	public void render() {
		this.view.render();
	}

	public void updateGame(int elapsed) {
		this.model.getWorld().updateState(elapsed);
		checkEvents();
	}

	private void checkEvents() {

	}

	private synchronized boolean isInState(final Status state) {
		return this.state.equals(state);
	}

	private synchronized void setState(final Status state) {
		this.state = state;
	}

	public void abort() {
		this.setState(Status.KILLED);
	}

	public void pause() {
		if (this.isInState(Status.RUNNING)) {
			this.setState(Status.PAUSED);
		}
	}

	public void resumeGame() {
		if (this.isInState(Status.PAUSED)) {
			this.setState(Status.RUNNING);
		}
	}

	public boolean isPaused() {
		return this.isInState(Status.PAUSED);
	}

	public boolean isRunning() {
		return this.isInState(Status.RUNNING);
	}

}
