package model.world;

import model.entity.Entity;
import model.entity.PlayerBehavior;
import model.room.Room;

public class GameMapImpl {

	private static final int VISITED = 1;
	private static final int CURRENT = 2;
	private static final int NOTVISITED = 3;

	private final Room[][] path;
	private int pathToView[][];
	private final int row;
	private final int column;
	private final Entity player;

	public GameMapImpl(final GameWorldImpl gm, int row, int column, Entity player) {
		this.path = gm.getPath();
		this.row = row;
		this.column = column;
		this.player = player;
		this.pathToView = new int[this.row][this.column];
	
	}

	public void buildPath() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (this.path[i][j] != null) {
					if(this.path[i][j].getRoomID() == this.checkCurrentRoom()) {
						this.pathToView[i][j] = this.CURRENT;
					}else {
						this.pathToView[i][j] = checkVisited(this.path[i][j]) ? this.VISITED : this.NOTVISITED;

					}

				}
			}
		}
	}

	private boolean checkVisited(Room room) {
		return room.isVisited();
	}

	private int checkCurrentRoom() {
		return ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom() == null ? 1
				: ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom().getRoomID();
	}

	public int[][] getPathToView() {
		return pathToView;
	}


}
