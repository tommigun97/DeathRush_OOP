package model.world;

/**
 * 
 * @author anisl
 *   GameMap interface
 *   Define default methods for the gamemap in the model
 */
public interface GameMap {

	/**
	 * getter method for matrixMap
	 * @return int matrix
	 */
	int[][] getMatrixMap();

	/**
	 * build method for setting matrix map to view in game
	 */
	void buildMatrixToView();
}
