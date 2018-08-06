package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.platform.commons.util.CollectionUtils;

import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.EntityType;
import model.entity.Player;
import model.entity.PlayerBehavior;
import model.map.GameMap;
import model.map.Map;
import model.room.Room;
import utilities.Pair;

/**
 * Model implementation.
 *
 */
public final class ModelImpl implements Model {

    private static final int DEFAULT_INIT_ROOM_ID = 1;
    private static final Pair<Double, Double> STARTING_POSITION = new Pair<Double, Double>(0.50, 0.50);

    private Room currentRoom;
    private Entity player;
    private GameStatus gameStatus;
    private CollisionSupervisor cs;
    private EntityFactory eFactory;
    private Map map;
    private Time time;

    @Override
    public String getRoomBackGround() {
        return "room/background.png";
    }

    @Override
    public List<Pair<String, Location>> getEntitiesToDrow() {
        List<Pair<String, Location>> l = new ArrayList<>();
        currentRoom.getEntities().forEach(e -> {
            l.add(new Pair<String, Location>(e.getImage(), e.getLocation()));
        });

        l.add(new Pair<String, Location>(this.player.getImage(), this.player.getLocation()));
        return l;
    }

    @Override
    public List<Pair<String, Pair<Integer, Integer>>> getMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(final Direction direction, final List<Direction> shoot) {
        // il giocatore si muove
        ((PlayerBehavior) player.getBehaviour().get()).setCurrentDirection(direction);
        player.getBehaviour().get().update();
        // il giocatore spara
        shoot.forEach(d -> ((PlayerBehavior) player.getBehaviour().get()).shoot(d));
        // vengono aggiornate tutte le altre entità della stanza
        currentRoom.getEntities().forEach(e -> {
            if (e.getBehaviour().isPresent()) {
                e.getBehaviour().get().update();
            }
        });
        // collisioni tra entità
        this.cs.collisionBetweenEntities(this.player, this.currentRoom.getEntities());
        this.currentRoom.getEntities().forEach(e -> cs.collisionBetweenEntities(e, this.currentRoom.getEntities()));
        // eliminazione di entità morte e acquisto della ricompensa
        this.currentRoom.getEntities().forEach(e -> {
            if (e.getType() == EntityType.ENEMY && e.getIntegerProperty("Current Life") == 0) {
                this.currentRoom.deleteEntity(e);
                this.player.changeIntProperty("Money",
                        player.getIntegerProperty("Money") + e.getIntegerProperty("Reward"));
            }
        });
        // collisioni con le porte
        if (this.currentRoom.isComplited()) {
            this.currentRoom.openDoors();
            this.cs.collisionWithDoors(this.player, currentRoom.getDoor());
            this.currentRoom = ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom();
        }
        if (this.player.getIntegerProperty("Current Life") == 0) {
        	this.time.pause();
            this.gameStatus = GameStatus.Over;
        } // manca l'if per dire quando il gioco è definitivamente completato

    }

    @Override
    public void start(final Player who) {
        this.gameStatus = GameStatus.Running;
        this.cs = new CollisionSupervisorImpl();
        this.eFactory = new EntityFactoryImpl(this.cs);
        this.map = new GameMap(eFactory);
        this.currentRoom = map.getRoom(DEFAULT_INIT_ROOM_ID).get();
        this.player = eFactory.createPlayer(STARTING_POSITION, this.currentRoom, who);
        this.time = new Time();
        time.start();
   
    }

    @Override
    public void stopTime() {
       this.time.pause();
    }

    @Override
    public int getPlayerLife() {
        return this.player.getIntegerProperty("Current Life");
    }

    @Override
    public int getMoney() {
        return this.player.getIntegerProperty("Money");
    }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    @Override
    public List<Integer> getPlayerInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Getter for the player it is used only for debug.
     * 
     * @return player
     */
    public Entity getPlayer() {
        return this.player;
    }

    /**
     * Getter for the current room it is used only for debug.
     * 
     * @return the current room
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

	@Override
	public void resumeTime() {
		this.time.resume();
	}

	@Override
	public int getScore() {
		return this.time.getTotalSecond();
	}

	@Override
	public boolean isComplited() {
		/*if(this.map.getRooms().stream().anyMatch(x -> x.isComplited() == false)) {
			return false;
		}*/
		return true;
	}

}
