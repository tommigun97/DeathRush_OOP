package model.world;

import model.entity.Entity;
import model.entity.PlayerBehavior;
import model.room.Room;

/**
 * 
 * GameMap implementation
 *
 */
public class GameMapImpl implements GameMap {

	// Static variables
	private static final int VISITED = 1;
	private static final int CURRENT = 2;
	private static final int NOTVISITED = 3;
	// Variables
	private final Room[][] path;
	private int pathToView[][];
	private final int row;
	private final int column;
	private final Entity player;

	// Constructor
	public GameMapImpl(final GameWorldImpl gm, int row, int column, Entity player) {
		this.path = gm.getMatrixMap();
		this.row = row;
		this.column = column;
		this.player = player;
		this.pathToView = new int[this.row][this.column];

	}

	/**
	 * Check method used to verify if a room is visited or not
	 * 
	 * @param room
	 * @return boolean true if is visited else false
	 */
	private boolean checkVisited(Room room) {
		return room.isVisited();
	}

	/**
	 * Getter method to take player current room
	 * 
	 * @return int player current room
	 */
	private int getPlayerCurrentRoom() {
		return ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom() == null ? 1
				: ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom().getRoomID();
	}

	@Override
	public void buildPath() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (this.path[i][j] != null) {
					if (this.path[i][j].getRoomID() == this.getPlayerCurrentRoom()) {
						this.pathToView[i][j] = this.CURRENT;
						System.out.println(
								"[GameMapImpl] " + this.path[i][j] + " visited " + checkVisited(this.path[i][j]));
					} else {
						this.pathToView[i][j] = checkVisited(this.path[i][j]) ? this.VISITED : this.NOTVISITED;
						System.out.println(
								"[GameMapImpl] " + this.path[i][j] + " visited " + checkVisited(this.path[i][j]));

					}

				}
			}
		}
	}

	@Override
	public int[][] getPathToView() {
		return pathToView;
	}

}
