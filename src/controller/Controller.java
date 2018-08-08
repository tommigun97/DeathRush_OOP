package controller;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import model.Direction;
import model.Model;
import model.ModelImpl;
import model.Time;
import model.entity.Player;
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
    private Optional<GameLoop> gameLoop = Optional.empty();
    private final InputHandler input;
    private Model model;
    private Time gameTime;
    private Score sc ;
    Player pg;
    private String playerName;

    /**
     * The constructor of the Class.
     */
    public Controller() {
        this.sc = new Score(FILENAME);
        this.input = InputHandler.getInputHandler();
        this.gameTime = new Time();
        this.model = new ModelImpl();
    }

    @Override
    public final void startGameLoop() throws IllegalStateException {
        this.gameLoop = Optional.of(new GameLoop(this, this.view, this.model));
        this.gameLoop.get().start();
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
    public final void pauseGameLoop() {
    	if(this.gameLoop.isPresent()) {  
    		this.gameLoop.get().pause();
    	}
    }

    @Override
    public final void abortGameLoop() {
    	if(this.gameLoop.isPresent()) {
    		this.gameLoop.get().abort();
    		this.gameLoop = Optional.empty();
    	}
    }

    @Override
    public final void resumeGameLoop() {
    	if(this.gameLoop.isPresent()) {
    		this.gameLoop.get().resumeGame();
    	}
    }

    @Override
    public final boolean isGameLoopRunning() {
    	if(!this.gameLoop.isPresent()) {
    		return false;
    	}
        return this.gameLoop.get().isRunning();
    }

    @Override
    public final boolean isGameLoopPaused() {
    	if(!this.gameLoop.isPresent()) {
    		return false;
    	}
        return this.gameLoop.get().isPaused();
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
            } else {
                this.model.update(Direction.NOTHING, shotDirectionList);
            }
        }
    }

    @Override
    public List<Pair<Pair<String, Integer>,String>> getCurrentHighScores() {
    	return this.sc.getScoreList();
    }
    
    @Override
    public boolean saveScoreGame() {
    	this.sc.addScore(new Pair<Pair<String, Integer>, String>(new Pair(this.playerName, this.model.getScore()), this.model.getTime()));
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
	public void selectPlayer(Player pg) {
		this.pg = pg;
	}
	
	@Override
	public Player getPlayer() {
		return this.pg;
	}

	@Override
	public void setPlayerName(String name) {
		this.playerName = name;
	}
	
    @Override
    public String getPlaterName() {
        // TODO Auto-generated method stub
        return this.playerName;
    }

}
