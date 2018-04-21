package controller;

/**
 * This is the heart of the game. It will refresh game's frame
 * every second. The GameLoop synchronizes View and Model.
 */

public class GameLoop extends Thread {
	
	/**
	 * Enum to describe the possible states of the GameLoop
	 * @author tommi
	 *
	 */
	private enum Status {
		RUNNING, PAUSED, KILLED, READY;
	}

	private volatile Status state;
	private final int fps;
	//private final ViewInterface view;
	//private final ControllerInterface controller;
	//private final InputInterface input;
	private volatile int time;
	//private volatile ModelInterface model;
	
	
	public GameLoop(final int fps, final ControllerInterface controller
			/*, final ViewInferface view, final InputInterface input*/) {
		this.fps = fps;
		//this.controller = controller;
		/*this.view = view;
		 * this.input = input;
		 */
	}
	
	public void run() {
		long previous = System.currentTimeMillis();
		long lag = 0;
		this.setState(Status.RUNNING);
		while(!this.isInState(Status.KILLED)) {
			long current = System.currentTimeMillis();
			long elapsed = current - previous;
			previous = current;
			lag += elapsed;
			//processInput();
			while(lag >= this.fps) {
				//update();
				lag -= this.fps;
			}
			//render(lag/this.fps);
		}
		
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
	
	public int getScore() {
		if (this.isInState(Status.KILLED)) {
            throw new IllegalStateException();
        }
		return this.time;
	}
}
