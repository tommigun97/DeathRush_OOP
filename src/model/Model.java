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
    
    int getScore();

	String getPlayerDamage();

	String getPlayerAttSpeed();

	String getPlayerMvSpeed();
	
	int[][] getMapToView();
	
    
    int getXmap();
    
    int getYmap();
    
    void mapUpdate();
}
