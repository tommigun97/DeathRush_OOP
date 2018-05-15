package controller;

import java.util.List;
import java.util.Optional;
import utilities.Pair;
import view.View;
import view.ViewInterface;

/**
 * Description of Controller.
 * 
 * @author tommi
 * 
 */
public class Controller implements ControllerInterface {

	static String FILENAME = "ScoreList";

	private ViewInterface view;
	private Optional<GameLoop> game;
	private Score sc = new Score(FILENAME);

	public Controller() {
		this.game = Optional.empty();
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

	public void setView(ViewInterface view) {
		this.view = view;
	}

	// @Override
	// public Timer getTimer() {
	// return this.model.getTimer();
	// }

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
	public final boolean setPlayerName(final String namePlayer) {
		//this.sc.saveOnFile(new Pair<>(namePlayer, model.getTime()));
		return false;
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

    @Override
    public final void setView(final ViewInterface v) {
        this.view(v);
    }

    private void view(ViewInterface v) {
        // TODO Auto-generated method stub
        
    }
}
