package view;

import controller.ControllerInterface;
import javafx.application.Application;
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
        Application.launch(MainWindow.class);
    }

    public static void setGameScreen(GameScreen gameScreen) {
        // TODO Auto-generated method stub
        
    }

    public static Object getController() {
        // TODO Auto-generated method stub
        return null;
    }

}
