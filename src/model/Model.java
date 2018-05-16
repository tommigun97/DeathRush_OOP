package model;

import java.util.List;

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
    List<Pair<String, Location>> getEntityToDrow();

    /**
     * @return map configuration
     */
    List<Pair<String, Pair<Integer, Integer>>> getMap();

    /**
     * @param directions
     *            direction where the player have to move
     * @param shoots
     *            number of shot that player should shoot
     */
    void update(Direction directions, List<Direction> shoot);

    /**
     * Initialize the game world.
     */
    void start();

    /**
     * stop the time.
     */
    void stopTime();

    /**
     * @return the player life
     */
    int getPalyerLife();

    /**
     * @return the player money
     */
    int getMoney();
    // Time getTime();
    GameStatus getGameStatus();
    // non esistono ancora la classe time e l'enumerazione game status

    /**
     * @return a list of the player ability
     */
	List<Integer> getPalyerInfo();
}
