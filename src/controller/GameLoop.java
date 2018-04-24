package controller;

import java.util.LinkedList;
import java.util.List;
import model.Direction;
import model.Location;
import utilities.Pair;
import view.ViewInterface;

/**
 * This is the heart of the game. It will refresh game's frame
 * every second. The GameLoop synchronizes View and Model.
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
	private final InputInterface input;
	private volatile ModelInterface model;
	
	
	public GameLoop(final ControllerInterface controller
			, final ViewInterface view, final InputInterface input) {
		this.controller = controller;
		this.view = view;
		this.input = input;
		this.model = new Model();
		 
	}
	
	public void run(){
		long previous = System.currentTimeMillis();
		this.setState(Status.RUNNING);
		while(!this.isInState(Status.KILLED)){
			if(this.model.getGameStatus().equals(GameStatus.Running)) {
				long current = System.currentTimeMillis();
				int elapsed = (int)(current - previous);
				processInput();
				updateGame(elapsed);		
				render();
				waitForNextFrame(current);
				previous = current;
			}
			else if(this.model.getGameStatus().equals(GameStatus.Over)) {
				this.setState(Status.KILLED);
			}
		}
		this.controller.abortGameLoop();
	}
	
	protected void waitForNextFrame(long current){
		long dt = System.currentTimeMillis() - current;
		if (dt < period){
			try {
				Thread.sleep(period-dt);
			} catch (Exception ex){}
		}
	}
	
	public void processInput() {
		this.model.
	}
	
	public void render() {
		this.view.render();
	}
	
	public void updateGame(int elapsed) {
		this.model.getWorld.updateState(elapsed);
		checkEvents();
	}
	
	private void checkEvents() {
		final List<Pair<Pair<String, Double>, Location>> toDraw = new LinkedList<>();
		final Location player = this.model.getPlayerLocation();
		toDraw.add(new Pair<>(new Pair<>("Image.png", 0d), new Location(player)));
		this.model.getEntitiesToDraw().forEach(x-> {
			toDraw.add(new Pair<>(new Pair<>(EntityType.getImage(x), x.getLocation())));
		});
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
		if(this.isInState(Status.RUNNING)) {
			this.setState(Status.PAUSED);
		}
	}
	
	public void resumeGame() {
		if(this.isInState(Status.PAUSED)) {
			this.setState(Status.RUNNING);
		}
	}
	
}
