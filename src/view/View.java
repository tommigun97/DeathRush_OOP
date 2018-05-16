package view;

import java.util.List;

import controller.ControllerInterface;
import javafx.application.Application;
import model.Location;
import utilities.Pair;

/**
 * 
 * @author lorenzo
 *
 */
public class View implements ViewInterface {

    private static ControllerInterface controller;
    // private final InputHandler inputHandler = InputHandler.getInputHandler();

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
     * Setter of the controller (thread safe).
     * 
     * @param c
     *            The controller of the game
     */
    private synchronized void setController(final ControllerInterface c) {
        View.controller = c;
    }

    @Override
    public final void startView() {
        Application.launch(MainWindow.class);
    }

    /**
     * Setter for the Game Screen. It is necessary to save it in order to call some
     * methods in it.
     * 
     * @param gamescreen
     *            The GameScreen
     */
    /*
     * static void setGameWindow(final GameWindow gamescreen) { View.gameWindow =
     * gamescreen; }
     */

    /**
     * Getter of the controller.
     * 
     * @return The controller of the game
     */
    static ControllerInterface getController() {
        return View.controller;
    }

    @Override
    public void drawRoom(final String roomBackGround) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawEntities(final List<Pair<String, Location>> entityToDrow) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateInfoToDraw(final List<Integer> palyerInfo, final int money) {
        // TODO Auto-generated method stub

    }

}
