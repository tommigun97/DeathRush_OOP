package model;

import java.util.ArrayList;
import java.util.List;
import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactoryImpl;
import model.entity.EntityType;
import model.entity.Player;
import model.entity.PlayerBehavior;
import model.room.Room;
import model.world.GameWorld;
import model.world.GameWorldImpl;
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
    private GameWorld map;
    private TimeImpl time;

    @Override
    public String getRoomBackGround() {
        return "room/background.png";
    }

    @Override
    public List<Pair<String, Location>> getEntitiesToDrow() {
        final List<Pair<String, Location>> l = new ArrayList<>();
        currentRoom.getEntities().forEach(e -> {
            l.add(new Pair<String, Location>(e.getImage(), e.getLocation()));
        });
        currentRoom.getDoor().forEach(d -> l.add(new Pair<String, Location>(d.getImage(), d.getLocation())));

        l.add(new Pair<String, Location>(this.player.getImage(), this.player.getLocation()));
        return l;
    }

    @Override
    public void update(final Direction direction, final List<Direction> shoot) {
        ((PlayerBehavior) player.getBehaviour().get()).setCurrentDirection(direction);
        player.getBehaviour().get().update();
        shoot.forEach(d -> ((PlayerBehavior) player.getBehaviour().get()).shoot(d));
        currentRoom.getEntities().forEach(e -> {
            if (e.getBehaviour().isPresent()) {
                e.getBehaviour().get().update();
            }
        });
        this.cs.collisionBetweenEntities(this.player, this.currentRoom.getEntities());
        this.currentRoom.getEntities().forEach(e -> cs.collisionBetweenEntities(e, this.currentRoom.getEntities()));
        this.currentRoom.getEntities().forEach(e -> {
            if (e.getType() == EntityType.ENEMY && e.getIntegerProperty("Current Life") <= 0) {
                this.currentRoom.deleteEntity(e);
                this.player.changeIntProperty("Money",
                        player.getIntegerProperty("Money") + e.getIntegerProperty("Reward"));
            }
        });
        this.cs.collisionWithPowerUp(player, currentRoom.getEntities(), currentRoom);
        if (this.currentRoom.isComplited()) {
            this.currentRoom.openDoors();
            final Room r = currentRoom;
            this.cs.collisionWithDoors(this.player, currentRoom.getDoor());
            this.currentRoom = ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom();
            if (!r.equals(currentRoom)) {
                this.currentRoom.closeDoors();

            }
        }
        if (this.player.getIntegerProperty("Current Life") <= 0) {
            this.time.pause();
            this.gameStatus = GameStatus.Over;
        }
        if (map.allRoomAreCompleted()) {
            this.time.pause();
            this.gameStatus = GameStatus.Won;
        }

    }

    @Override
    public void start(final Player who) {
        final EntityFactoryImpl eFactory = new EntityFactoryImpl(this.cs);
        this.gameStatus = GameStatus.Running;
        this.cs = new CollisionSupervisorImpl();
        this.player = eFactory.createPlayer(STARTING_POSITION, who);
        this.map = new GameWorldImpl(eFactory, player);
        this.map.buildWorldGame();
        this.time = new TimeImpl();
        this.currentRoom = map.getRoom(DEFAULT_INIT_ROOM_ID).get();
        ((PlayerBehavior) player.getBehaviour().get()).setCurrentRoom(currentRoom);
        this.map.toString();
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
    public String getPlayerDamage() {
        return String.valueOf((this.player.getIntegerProperty("Shooting Damage")));
    }

    @Override
    public String getPlayerAttSpeed() {
        return String.valueOf(this.player.getObjectProperty("Shoot Frequency"));
    }

    @Override
    public String getPlayerMvSpeed() {
        return String.valueOf(this.player.getDoubleProperty("Speed"));
    }

    @Override
    public int getMoney() {
        return this.player.getIntegerProperty("Money");
    }

    @Override
    public String getTime() {
        return this.time.getCurrentTime();
    }

    @Override
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    @Override
    public void resumeTime() {
        this.time.resume();
    }

    @Override
    public int getScore() {
        return this.time.getTotalSecond();
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
    public int[][] getMapToView() {
        return this.map.getMatrixView();
    }

    @Override
    public int getXmap() {
        return this.map.getColumnMatrix();
    }

    @Override
    public int getYmap() {
        return this.map.getRowMatrix();
    }

    @Override
    public void mapUpdate() {
        this.map.matrixViewUpdate();

    }
}
