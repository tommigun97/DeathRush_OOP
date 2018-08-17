package model;

import java.util.List;

import model.entity.Player;
import utilities.Pair;

/**
 * Game Model Interface.
 *
 */
public interface Model {

    /**
     * @return the path of room background
     */
    String getRoomBackGround();

    /**
     * @return path of entities image and the relative location
     */
    List<Pair<String, Location>> getEntitiesToDrow();

    /**
     * @param direction
     *            direction where the player have to move.
     * @param shoot
     *            number of shot that player should shoot.
     */
    void update(Direction direction, List<Direction> shoot);

    /**
     * Initialize the game world.
     * 
     * @param who
     *            the Player selected
     */
    void start(Player who);

    /**
     * stop the time.
     */
    void stopTime();

    /**
     * time restart to run.
     */
    void resumeTime();

    /**
     * @return the player life
     */
    int getPlayerLife();

    /**
     * @return the player money
     */
    int getMoney();

    /**
     * 
     * @return .
     */
    String getTime();

    /**
     * 
     * @return .
     */
    GameStatus getGameStatus();

    /**
     * @return how many second the game still go
     */
    int getScore();

    /**
     * @return player damage
     */
    String getPlayerDamage();

    /**
     * @return player attack frequency
     */
    String getPlayerAttSpeed();

    /**
     * @return player movement speed
     */
    String getPlayerMvSpeed();

    /**
     * @return matrix that looks like the game map
     */
    int[][] getMapToView();

    /**
     * @return matrix columns
     */
    int getXmap();

    /**
     * @return matrix rows
     */
    int getYmap();

    /**
     * ask that the matrix that show the map is updated.
     */
    void mapUpdate();
}
