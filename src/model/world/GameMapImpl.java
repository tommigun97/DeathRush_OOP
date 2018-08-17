package model.world;

import model.entity.Entity;
import model.entity.PlayerBehavior;
import model.room.Room;
/**
 * 
 * The GAMEMAP that is seen by user during the game
 * It is build when the gameloop start.
 */
public class GameMapImpl implements GameMap{

	/*GameMap Static variables*/
	private static final int VISITED = 1;
	private static final int CURRENT = 2;
	private static final int NOTVISITED = 3;

	
	/*GameMap variables */
	private final Room[][] matrixMap;
	private int pathToView[][];
	private final int row;
	private final int column;
	private final Entity player;

	/*CONSTRUCTOR*/
	
	/**
	 * Complete Constructor for GameMap
	 * @param gm gameworld where is saved the map for getting matrixMap
	 * @param row	number of MatrixMap row
	 * @param column  number of MatrixMap Column
	 * @param player  game player
	 */
	public GameMapImpl(final GameWorldImpl gm, int row, int column, Entity player) {
		this.matrixMap = gm.getPath();
		this.row = row;
		this.column = column;
		this.player = player;
		this.pathToView = new int[this.row][this.column];
	
	}
	
	
	/**
	 * verify if the room is visited
	 * @param room
	 * @return true if the room is visited else false
	 */
	private boolean checkVisited(Room room) {
		return room.isVisited();
	}
	
	
	/**
	 * Getter method for player CurrentRoom
	 * @return the IDRoom of Player CurrentRoom
	 */
	private int getCurrentRoom() {
		return ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom() == null ? 1
				: ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom().getRoomID();
	}

	public void buildMatrixToView() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (this.matrixMap[i][j] != null) {
					if(this.matrixMap[i][j].getRoomID() == this.getCurrentRoom()) {
						this.pathToView[i][j] = this.CURRENT;
						System.out.println("[GameMapImpl] " + this.path[i][j] + " visited " + checkVisited(this.path[i][j]));
					}else {
						this.pathToView[i][j] = checkVisited(this.path[i][j]) ? this.VISITED : this.NOTVISITED;
						System.out.println("[GameMapImpl] " + this.path[i][j] + " visited " + checkVisited(this.path[i][j]));

					}

				}
			}
		}
	}

	public int[][] getMatrixMap() {
		return pathToView;
	}


}
