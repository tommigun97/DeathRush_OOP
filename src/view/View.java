package view;

import controller.ControllerInterface;
import view.View;

public class View implements ViewInterface {

    private static ControllerInterface controller;

    /**
     * Constructor of the class. It saves the controller of the game.
     * 
     * @param c
     *            The controller of the game.
     */
    public View(final ControllerInterface c) {
        this.setController(c);
    }

    /**
     * Setter of the controller (thread safe)
     * 
     * @param c
     *            The controller of the game
     */
    private synchronized void setController(final ControllerInterface c) {
        View.controller = c;
    }

    @Override
    public void startView() {
        // TODO Auto-generated method stub

    }

}
