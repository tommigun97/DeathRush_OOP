package model.world;

import model.entity.Entity;
import model.entity.PlayerBehavior;
import model.room.Room;

/**
 * 
 * GameMap implementation.
 *
 */
public final class GameMapImpl implements GameMap {

    private static final int VISITED = 1;
    private static final int CURRENT = 2;
    private static final int NOTVISITED = 3;

    private final Room[][] path;
    private int [][] pathToView;
    private final int row;
    private final int column;
    private final Entity player;

    /**
     * Constructor.
     * 
     * @param gm
     *            game world
     * @param row
     *            matrix row
     * @param column
     *            matrix column
     * @param player
     *            game player
     */
    public GameMapImpl(final GameWorldImpl gm, final int row, final int column, final Entity player) {
        this.path = gm.getMatrixMap();
        this.row = row;
        this.column = column;
        this.player = player;
        this.pathToView = new int[this.row][this.column];

    }
    /**
     * build a map copy.
     * @param copy map copy
     */
    private void copyMatrix(int[][] copy) {
    	for(int i = 0; i < row; i++) {
    		for(int j = 0; j < column; j++) {
    			copy[i][j] = this.pathToView[i][j];
    		}
    	}
    }
    /**
     * Check method used to verify if a room is visited or not.
     * 
     * @param room
     * @return boolean true if is visited else false
     */
    private boolean checkVisited(final Room room) {
        return room.isVisited();
    }

    /**
     * Getter method to take player current room.
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
                        this.pathToView[i][j] = GameMapImpl.CURRENT;
                    } else {
                        this.pathToView[i][j] = checkVisited(this.path[i][j]) ? GameMapImpl.VISITED : GameMapImpl.NOTVISITED;
                    }

                }
            }
        }
    }

    @Override
    public int[][] getPathToView() {
    	int[][] copy = new int[row][column];
    	this.copyMatrix(copy);
        return copy;     
    }

    
}
