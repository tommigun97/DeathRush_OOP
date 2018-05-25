package view;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import controller.ControllerInterface;
import model.Location;
import utilities.Input;
import utilities.Pair;

/**
 * Main class of the View part of the application. It implements the method of
 * the ViewInterface.
 *
 */
public class View implements ViewInterface {

    private static ControllerInterface controller;
    private final InputHandler inputHandler = InputHandler.getInputHandler();
    private static GameScreen gameScreen;

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

    @Override
    public final void draw(final List<Pair<Pair<String, Double>, Location>> listEntities) {
        Platform.runLater(() -> View.gameScreen.drawOnScreen(listEntities));
    }

    @Override
    public final void updateInfo(final int hp, final int shields, final int score) {
        Platform.runLater(() -> View.gameScreen.updateInfo(hp, shields, score));
    }

    @Override
    public void showText(final int nLevel) {
        // Platform.runLater(() -> View.gameScreen.won(nLevel));
    }

    @Override
    public void showText(final String powerUp) {
        // Platform.runLater(() -> View.gameScreen.powerUp(powerUp));
    }

    /**
     * Setter for the Game Screen. It is necessary to save it in order to call some
     * methods in it.
     * 
     * @param gamescreen
     *            The GameScreen
     */
    static void setGameScreen(final GameScreen gamescreen) {
        View.gameScreen = gamescreen;
    }

    /**
     * Getter of the controller.
     * 
     * @return The controller of the game
     */
    static ControllerInterface getController() {
        return View.controller;
    }

    /**
     * Getter of the current movement.
     * 
     * @return The list of the current movement.
     */
    public final List<Input> getMovementInput() {
        return this.inputHandler.getMovementList();
    }

    /**
     * Getter of the current shots.
     * 
     * @return The list of the current shots.
     */
    public final List<Input> getShotInput() {
        return this.inputHandler.getShotList();
    }

}
