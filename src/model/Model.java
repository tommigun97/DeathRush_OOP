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
     * getter for player's life.
     * 
     * @return the player life
     */
    int getPlayerLife();

    /**
     * getter for player's money.
     * 
     * @return the player money
     */
    int getMoney();

    /**
     * getter for Time.
     * 
     * @return string that described the current time.
     */
    String getTime();

    /**
     * Getter for game status.
     * 
     * @return status of the game
     */
    GameStatus getGameStatus();

    /**
     * getter for the score.
     * 
     * @return how many second the game still go
     */
    int getScore();

    /**
     * getter for player's damage attack.
     * 
     * @return player damage
     */
    String getPlayerDamage();

    /**
     * Getter for player's speed attack.
     * 
     * @return player attack frequency
     */
    String getPlayerAttSpeed();

    /**
     * Getter for player's movement speed.
     * 
     * @return player movement speed
     */
    String getPlayerMvSpeed();

    /**
     * Getter for the map.
     * 
     * @return matrix that looks like the game map
     */
    int[][] getMapToView();

    /**
     * getter for matrix column.
     * 
     * @return matrix columns
     */
    int getXmap();

    /**
     * getter for matrix row.
     * 
     * @return matrix rows
     */
    int getYmap();

    /**
     * update the map. ask that the matrix that show the map is updated.
     */
    void mapUpdate();
}
