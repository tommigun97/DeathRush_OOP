package model.world;

/**
 * 
 * Represents GameMap.
 */
public interface GameMap {

    /**
     * Getter method to take matrix map that player should see.
     * 
     * @return int[][] int matrix map
     */
    int[][] getPathToView();

    /**
     * Build a int matrix map.
     */
    void buildPath();
}
