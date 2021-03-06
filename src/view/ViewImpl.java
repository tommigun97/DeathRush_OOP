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

    /**
     * Setter for the Game Screen. It is necessary to save it in order to call some
     * methods in it.
     * 
     * @param gamescreen
     *            The GameScreen
     */
    public static void setGameScreen(final GameWorldView gamescreen) {
        ViewImpl.gameScreen = gamescreen;
    }

    /**
     * Getter of the controller.
     * 
     * @return The controller of the game
     */
    public static Controller getController() {
        return ViewImpl.controller;
    }

    @Override
    public final List<Input> getMovementInput() {
        return this.inputHandler.getMovementList();
    }

    @Override
    public final List<Input> getShotInput() {
        return this.inputHandler.getShotList();
    }

    @Override
    public final void gameOver() {
        Platform.runLater(() -> ViewImpl.gameScreen.gameOver());
        Platform.runLater(() -> ViewImpl.getController().changeSong(Sound.SONG.MENUSONG.getPathToSong()));
    }

    @Override
    public final void youWin() {
        Platform.runLater(() -> ViewImpl.gameScreen.youWin());
        Platform.runLater(() -> ViewImpl.getController().changeSong(Sound.SONG.MENUSONG.getPathToSong()));
    }

    @Override
    public final void play(final String path) {
        this.sound = new SoundImpl();
        this.sound.musicPlay(path);
    }

    @Override
    public final void changeSong(final String path) {
        this.sound.musicPlay(path);
    }

}
