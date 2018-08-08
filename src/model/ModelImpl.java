package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.platform.commons.util.CollectionUtils;

import model.entity.Boss;
import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.EntityType;
import model.entity.Player;
import model.entity.PlayerBehavior;
import model.entity.PowerUp;
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
        final List<Pair<String, Location>> l = new ArrayList<>();
        currentRoom.getEntities().forEach(e -> {
            l.add(new Pair<String, Location>(e.getImage(), e.getLocation()));
        });
        currentRoom.getDoor().forEach(d -> l.add(new Pair<String, Location>(d.getImage(), d.getLocation())));

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
        System.out.println(player.getLocation());
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
        //collisioni con i powerUp
        this.cs.collisionWithPowerUp(player, currentRoom.getEntities(), currentRoom);
        // collisioni con le porte
        if (this.currentRoom.isComplited()) {
            this.currentRoom.openDoors();
            this.cs.collisionWithDoors(this.player, currentRoom.getDoor());
            this.currentRoom = ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom();
        }
        if (this.player.getIntegerProperty("Current Life") <= 0) {
            this.time.pause();
            this.gameStatus = GameStatus.Over;
        } // manca l'if per dire quando il gioco è definitivamente completato

    }

    @Override
    public void start(final Player who) {
        this.gameStatus = GameStatus.Running;
        this.cs = new CollisionSupervisorImpl();
        this.eFactory = new EntityFactoryImpl(this.cs);
        this.player = eFactory.createPlayer(STARTING_POSITION, who);
        this.map = new GameMap(eFactory, player);
        this.time = new Time();
        this.currentRoom = map.getRoom(DEFAULT_INIT_ROOM_ID).get();
        ((PlayerBehavior) player.getBehaviour().get()).setCurrentRoom(currentRoom);
        time.start();
        // prove
//        ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom()
//                .addEntity(eFactory.createBoss(0.3, 0.3, currentRoom, Optional.of(player), Boss.THOR));
         //        ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom()
//        .addEntity(eFactory.createObstacle(0.3, 0.3));
        ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom()
      .addEntity(eFactory.createPowerUp(0.6, 0.6, currentRoom, PowerUp.ZUCCHERO));
        ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom()
        .addEntity(eFactory.createPowerUp(0.7, 0.7, currentRoom, PowerUp.CHITARRA));
        ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom()
        .addEntity(eFactory.createPowerUp(0.1, 0.9, currentRoom, PowerUp.SIGARETTA));
        ((PlayerBehavior) this.player.getBehaviour().get()).getCurrentRoom()
        .addEntity(eFactory.createPowerUp(0.9, 0.1, currentRoom, PowerUp.PISTOLA));
        
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
        /*
         * if(this.map.getRooms().stream().anyMatch(x -> x.isComplited() == false)) {
         * return false; }
         */
        return true;
    }

}
