package controller;

import view.View;
import view.ViewInterface;

public class DeathRush {

	public static void main(final String[] args) {
		final ControllerInterface c = new Controller();
		final ViewInterface v = new View(c);
		c.setView(v);
		v.start();
	}
}
