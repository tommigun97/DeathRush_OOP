package model;
/**
 * 
 *
 */
public enum GameStatus {
    /**
     * Player win if every room is completed.
     */
    Won,
    /**
     * Player loose if his life reach 0 or below.
     */
    Over,
    /**
     * The game is running.
     */
    Running;
}
