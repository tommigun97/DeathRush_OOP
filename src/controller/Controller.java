package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.Direction;
import model.Model;
import utilities.Input;
import utilities.Pair;
import view.InputHandler;
import view.ViewInterface;

/**
 * Description of Controller.
 * 
 * 
 */
public class Controller implements ControllerInterface {

	static String FILENAME = "ScoreList";

	private ViewInterface view;
	private Optional<GameLoop> game;
	private final InputHandler input;
	private Model model;
	private Score sc = new Score(FILENAME);

	/**
	 * The constructor of the Class.
	 */
	public Controller() {
		this.game = Optional.empty();
		this.input = InputHandler.getInputHandler();
	}

	@Override
	public final void startGameLoop() throws IllegalStateException {
		if (!this.game.isPresent()) {
			throw new IllegalStateException();
		}
		final GameLoop gl = new GameLoop(this, this.view);
		this.game = Optional.of(gl);
		gl.start();
	}

	/**
	 * Setter of the View from the interface.
	 * 
	 * @param view
	 *            the current view.
	 */
	public void setView(final ViewInterface view) {
		this.view = view;
	}

	@Override
	public int getTimer() {
		return this.model.getTime();
	}

	@Override
	public final void pauseGameLoop() {
		if (this.game.isPresent()) {
			this.game.get().pause();
		}
	}

	@Override
	public final void abortGameLoop() {
		if (this.game.isPresent()) {
			this.game.get().abort();
			this.game = Optional.empty();
		}
	}

	@Override
	public final void resumeGameLoop() {
		if (this.game.isPresent()) {
			this.game.get().resumeGame();
		}
	}

	@Override
	public final boolean isGameLoopRunning() {
		if (!this.game.isPresent()) {
			return false;
		}
		return this.game.get().isRunning();
	}

	@Override
	public final boolean isGameLoopPaused() {
		if (!this.game.isPresent()) {
			return false;
		}
		return this.game.get().isPaused();
	}

	private Direction translateShot(final Input e) {
		Direction shot = Direction.NOTHING;
		if (e.equals(Input.SHOT_UP)) {
			shot = Direction.N;
		} else if (e.equals(Input.SHOT_DOWN)) {
			shot = Direction.S;
		} else if (e.equals(Input.SHOT_LEFT)) {
			shot = Direction.W;
		} else if (e.equals(Input.SHOT_RIGHT)) {
			shot = Direction.E;
		}
		return shot;
	}

	/**
	 * take Input List from view and call model to execute it.
	 */
	public void processInput() {
		List<Direction> shotDirectionList = new LinkedList<>();
		this.input.getShotList().forEach(x -> {
			shotDirectionList.add(translateShot(x));
		});
		if (this.input.getMovementList().size() > 2) {
			this.model.update(Direction.NOTHING, shotDirectionList);
		} else if (this.input.getMovementList().size() == 2) {
			if (this.input.getMovementList().contains(Input.W) && this.input.getMovementList().contains(Input.D)) {
				this.model.update(Direction.NE, shotDirectionList);
			} else if (this.input.getMovementList().contains(Input.W)
					&& this.input.getMovementList().contains(Input.A)) {
				this.model.update(Direction.NW, shotDirectionList);
			} else if (this.input.getMovementList().contains(Input.W)
					&& this.input.getMovementList().contains(Input.S)) {
				this.model.update(Direction.NOTHING, shotDirectionList);
			} else if (this.input.getMovementList().contains(Input.S)
					&& this.input.getMovementList().contains(Input.D)) {
				this.model.update(Direction.SE, shotDirectionList);
			} else if (this.input.getMovementList().contains(Input.S)
					&& this.input.getMovementList().contains(Input.A)) {
				this.model.update(Direction.SW, shotDirectionList);
			}
		} else if (this.input.getMovementList().size() < 2) {
			if (this.input.getMovementList().contains(Input.W)) {
				this.model.update(Direction.N, shotDirectionList);
			} else if (this.input.getMovementList().contains(Input.A)) {
				this.model.update(Direction.W, shotDirectionList);
			} else if (this.input.getMovementList().contains(Input.D)) {
				this.model.update(Direction.E, shotDirectionList);
			} else if (this.input.getMovementList().contains(Input.S)) {
				this.model.update(Direction.S, shotDirectionList);
			}
		}
	}

	@Override
	public boolean getCurrentHighScores(String namePlayer) {
		try {
			this.sc.saveOnFile(new Pair<String, Integer>(namePlayer, this.getTimer()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
