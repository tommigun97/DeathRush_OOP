package controller;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.Direction;
import model.Model;
import model.ModelImpl;
import model.TimeImpl;
import model.entity.Player;
import utilities.Input;
import utilities.Pair;
import view.InputHandler;
import view.View;

/**
 * Controller implementation.
 * 
 * 
 */
public class ControllerImpl implements Controller {

    private static final String FILENAME = "ScoreList";
    private View view;
    private Optional<GameLoop> gameLoop = Optional.empty();
    private final InputHandler input;
    private final Model model;
    private final ScoreImpl sc;
    private Player pg;
    private String playerName;

    /**
     * The constructor of the Class.
     */
    public ControllerImpl() {
        this.sc = new ScoreImpl(FILENAME);
        this.input = InputHandler.getInputHandler();
        new TimeImpl();
        this.model = new ModelImpl();
    }

    /**
     * this method is used to translate the input relative to the shot of the
     * player.
     * 
     * @param e
     * @return the direction of the shot
     */
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

    @Override
    public final void startGameLoop() throws IllegalStateException {
        this.gameLoop = Optional.of(new GameLoop(this, this.view, this.model));
        this.gameLoop.get().start();

    }

    @Override
    public final void setView(final View view) {
        this.view = view;

    }

    @Override
    public final void pauseGameLoop() {
        if (this.gameLoop.isPresent()) {
            this.gameLoop.get().pause();
        }
    }

    @Override
    public final void abortGameLoop() {
        if (this.gameLoop.isPresent()) {
            this.gameLoop.get().abort();
            this.gameLoop = Optional.empty();
        }
    }

    @Override
    public final void resumeGameLoop() {
        if (this.gameLoop.isPresent()) {
            this.gameLoop.get().resumeGame();
        }
    }

    @Override
    public final boolean isGameLoopRunning() {
        if (!this.gameLoop.isPresent()) {
            return false;
        }
        return this.gameLoop.get().isRunning();
    }

    @Override
    public final boolean isGameLoopPaused() {
        if (!this.gameLoop.isPresent()) {
            return false;
        }
        return this.gameLoop.get().isPaused();
    }

    @Override
    public final void processInput() {
        final List<Direction> shotDirectionList = new LinkedList<>();
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
            } else if (this.input.getMovementList().contains(Input.A)
                    && this.input.getMovementList().contains(Input.D)) {
                this.model.update(Direction.NOTHING, shotDirectionList);
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
            } else {
                this.model.update(Direction.NOTHING, shotDirectionList);
            }
        }
    }

    @Override
    public final List<Pair<Pair<String, Integer>, String>> getCurrentHighScores() {
        return this.sc.getScoreList();
    }

    @Override
    public final boolean saveScoreGame() {
        this.sc.addScore(new Pair<Pair<String, Integer>, String>(
                new Pair<String, Integer>(this.playerName, this.model.getScore()), this.model.getTime()));
        try {
            this.sc.saveOnFile();
        } catch (IllegalStateException | IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public final boolean emptyScores() {
        this.sc.deleteAllScore();
        try {
            this.sc.saveOnFile();
        } catch (IllegalStateException | IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public final void selectPlayer(final Player pg) {
        this.pg = pg;
    }

    @Override
    public final Player getPlayer() {
        return this.pg;
    }

    @Override
    public final void setPlayerName(final String name) {
        this.playerName = name;
    }

    @Override
    public final String getPlaterName() {
        return this.playerName;
    }

    @Override
    public final int[][] getViewMap() {
        return this.model.getMapToView();
    }

    @Override
    public final int getXmap() {
        return this.model.getXmap();
    }

    @Override
    public final int getYmap() {
        return this.model.getYmap();
    }

    @Override
    public final void mapUpdate() {
        this.model.mapUpdate();

    }

    @Override
    public final void playSong(final String s) {
        this.view.play(s);
    }

    @Override
    public final void changeSong(final String s) {
        this.view.changeSong(s);
    }

}
