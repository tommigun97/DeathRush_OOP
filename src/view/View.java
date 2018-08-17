package view;

import java.util.List;

import model.Location;
import utilities.Input;
import utilities.Pair;

/**
 * Interface for a generic View.
 */
public interface View {

    /**
     * It starts the application and shows the main menu.
     */
    void startView();

    /**
     * It prints on screen all the entities in their correct positions.
     * 
     * @param listEntities
     *            a list of the Entities that will be printed on screen
     * @param backgroundPath
     *            path of background to print
     */
    void draw(List<Pair<String, Location>> listEntities, String backgroundPath);

    /**
     * It returns a list with the inputs(movement) detected during a game.
     * 
     * @return a list of Input
     */
    List<Input> getMovementInput();

    /**
     * It returns a list with the inputs(shots) detected during a game.
     * 
     * @return a list of Input
     */
    List<Input> getShotInput();

    /**
     * This method call the Game Over screen.
     */
    void gameOver();

    /**
     * This method call the You Win Screen.
     */
    void youWin();

    /**
     * This method is used to build the status-bar into the GameScreen.
     * 
     * @param playerLife
     *            player life.
     * @param money
     *            player money.
     * @param time
     *            time played.
     * @param playerDamage
     *            player damage power-up
     * @param playerAttSpeed
     *            player attack speed power-up
     * @param playerMvSpeed
     *            player movement speed power-up
     */
    void updateInfoToDraw(int playerLife, int money, String time, String playerDamage, String playerAttSpeed,
            String playerMvSpeed);

    /**
     * This method play the selected song.
     * 
     * @param path song path.
     */
    void play(String path);
    /**
     * 
     * @param path song path
     */
    void changeSong(String path);

}
