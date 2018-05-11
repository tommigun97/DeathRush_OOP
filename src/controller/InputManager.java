package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utilities.Input;

public class InputManager implements KeyListener {

	private static final InputManager INPUT_MANAGER = new InputManager();

	private boolean up, down, left, right;
	private boolean shotUp, shotDown, shotLeft, shotRight;
	private boolean pause;
	private boolean map;
	private List<Input> inputList;

	public InputManager() {
		this.inputList = new ArrayList<>();
	}

	static InputManager getInputHandler() {
		return InputManager.INPUT_MANAGER;
	}

	public List<Input> getInputList() {
		return Collections.unmodifiableList(this.inputList);
	}

	public void addInput() {
		this.inputList.clear();
		if (this.up) {
			this.inputList.add(Input.W);
		}
		if (this.down) {
			this.inputList.add(Input.S);
		}
		if (this.left) {
			this.inputList.add(Input.A);
		}
		if (this.right) {
			this.inputList.add(Input.D);
		}
		if (this.shotUp) {
			this.inputList.add(Input.SHOT_UP);
		}
		if (this.shotDown) {
			this.inputList.add(Input.SHOT_DOWN);
		}
		if (this.shotLeft) {
			this.inputList.add(Input.SHOT_LEFT);
		}
		if (this.shotRight) {
			this.inputList.add(Input.SHOT_RIGHT);
		}
		if (this.pause) {
			this.inputList.add(Input.ESC);
		}
		if (this.map) {
			this.inputList.add(Input.M);
		}
	}

	public void execute(final KeyEvent key, final boolean active) {
		if (key.getKeyCode() == KeyEvent.VK_W) {
			this.up = active;
		} else if (key.getKeyCode() == KeyEvent.VK_S) {
			this.down = active;
		} else if (key.getKeyCode() == KeyEvent.VK_A) {
			this.left = active;
		} else if (key.getKeyCode() == KeyEvent.VK_D) {
			this.right = active;
		} else if (key.getKeyCode() == KeyEvent.VK_UP) {
			this.shotUp = active;
		} else if (key.getKeyCode() == KeyEvent.VK_DOWN) {
			this.shotDown = active;
		} else if (key.getKeyCode() == KeyEvent.VK_LEFT) {
			this.shotLeft = active;
		} else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.shotRight = active;
		} else if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.pause = active;
		} else if (key.getKeyCode() == KeyEvent.VK_M) {
			this.map = active;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.execute(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.execute(e, false);
	}

	public void clearAll() {
		this.down = false;
		this.left = false;
		this.right = false;
		this.up = false;
		this.shotDown = false;
		this.shotLeft = false;
		this.shotRight = false;
		this.shotUp = false;
		this.pause = false;
		this.map = false;
		this.inputList.clear();
	}

}
