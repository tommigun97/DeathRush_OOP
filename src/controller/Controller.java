package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.Direction;
import model.Model;
import model.ModelImpl;
import model.Time;
import utilities.Input;
import utilities.Pair;
import view.InputHandler;
import view.View;

/**
 * Description of Controller.
 * 
 * 
 */
public class Controller implements ControllerInterface {

    private static final String FILENAME = "ScoreList";
    private static final int N_SCORE = 10;

    private View view;
    private GameLoop gameLoop;
    private final InputHandler input;
    private Model model;
    private Time gameTime;
    private Score sc ;

    /**
     * The constructor of the Class.
     */
    public Controller() {
        this.sc = new Score(FILENAME);
        this.input = InputHandler.getInputHandler();
        this.gameTime = getTimer();
        this.model = new ModelImpl();
    }

    @Override
    public final void startGameLoop() throws IllegalStateException {
        this.gameLoop = new GameLoop(this, this.view, this.model);
        this.gameLoop.start();
    }

    /**
     * Setter of the View from the interface.
     * 
     * @param view
     *            the current view.
     */
    public void setView(final View view) {
        this.view = view;
    }

    @Override
    public final Time getTimer() {
        return new Time();
    }

    @Override
    public final void pauseGameLoop() {
       this.gameLoop.pause();
    }

    @Override
    public final void abortGameLoop() {
            this.gameLoop.abort();
    }

    @Override
    public final void resumeGameLoop() {
      
            this.gameLoop.resumeGame();
        
    }

    @Override
    public final boolean isGameLoopRunning() {        
        return this.gameLoop.isRunning();
    }

    @Override
    public final boolean isGameLoopPaused() {
        return this.gameLoop.isPaused();
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
     * 
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
            }
        }
    }

    @Override
    public final List<Pair<String, Integer>> getCurrentHighScores() {

        /*Pair<String, Integer> a = new Pair<>("tommi", 130);
        Pair<String, Integer> b = new Pair<>("Anis", 100);
        Pair<String, Integer> c = new Pair<>("kaso", 90);
        Pair<String, Integer> d = new Pair<>("simo", 80);

        this.sc.addScore(a);
        this.sc.addScore(b);
        this.sc.addScore(c);
        this.sc.addScore(d);

        System.out.println(this.sc.getScoreList());

        try {
            this.sc.saveOnFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return this.sc.getScoreList();
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

}
