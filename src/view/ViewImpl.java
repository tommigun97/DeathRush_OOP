package view;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import controller.Controller;
import model.Location;
import utilities.Input;
import utilities.Pair;

/**
 * Main class of the View part of the application. It implements the method of
 * the ViewInterface.
 *
 */
public class ViewImpl implements View {

    private static Controller controller;
    private final InputHandler inputHandler = InputHandler.getInputHandler();
    private static GameWorldView gameScreen;
    private Sound sound;

    /**
     * Constructor of the class. It saves the controller of the game.
     * 
     * @param c
     *            The controller of the game.
     */
    public ViewImpl(final Controller c) {
        this.setController(c);
    }

    /**
     * Setter of the controller (thread safe).
     * 
     * @param c
     *            The controller of the game
     */
    private synchronized void setController(final Controller c) {
        ViewImpl.controller = c;
    }

    @Override
    public final void startView() {
        Application.launch(MainWindow.class);

    }

    @Override
    public final void draw(final List<Pair<String, Location>> listEntities, final String backgroundPath) {
        Platform.runLater(() -> ViewImpl.gameScreen.drawOnScreen(listEntities, backgroundPath));
    }

    @Override
    public final void updateInfoToDraw(final int hp, final int money, final String time, final String damage,
            final String attackSpeed, final String mvSpeed) {
        Platform.runLater(() -> ViewImpl.gameScreen.updateInfo(hp, money, time, damage, attackSpeed, mvSpeed));
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
    static void setGameScreen(final GameWorldView gamescreen) {
        ViewImpl.gameScreen = gamescreen;
    }

    /**
     * Getter of the controller.
     * 
     * @return The controller of the game
     */
    static Controller getController() {
        return ViewImpl.controller;
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

    /**
     * Call the Game Over Screen from the GameScreen.
     */
    public final void gameOver() {
        Platform.runLater(() -> ViewImpl.gameScreen.gameOver());
        Platform.runLater(() -> ViewImpl.getController().changeSong(Sound.song.MENUSONG.getPathToSong()));
    }

    /**
     * Call the You Win inside the GameScreen.
     */
    public void youWin() {
        Platform.runLater(() -> ViewImpl.gameScreen.youWin());
        Platform.runLater(() -> ViewImpl.getController().changeSong(Sound.song.MENUSONG.getPathToSong()));
    }

    public void play(String path) {
        this.sound = new SoundImpl();
        this.sound.musicPlay(path);
    }

    public void changeSong(String path) {
        this.sound.musicPlay(path);
    }

}
