package model;

import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.util.CollectionUtils;

import controller.Time;
import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.EntityType;
import model.entity.Player;
import model.entity.PlayerBehavior;
import model.room.Room;
import utilities.Pair;

/**
 * Model implementation.
 *
 */
public final class ModelImpl implements Model {

    private Room currentRoom;
    private Entity player;
    private GameStatus gameStatus;
    private CollisionSupervisor cs;
    private EntityFactory eFactory;
    
    

    public ModelImpl(Room currentRoom, Entity player, CollisionSupervisor cs, EntityFactory eFactory) {
        super();
        this.currentRoom = currentRoom;
        this.player = player;
        this.cs = cs;
        this.eFactory = eFactory;
    }

    @Override
    public String getRoomBackGround() {
        // manca il background nelle room
        return null;
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
        }
        if (this.player.getIntegerProperty("Current Life") == 0) {
            this.gameStatus = GameStatus.Over;
        } //manca l'if per dire quando il gioco è definitivamente completato

    }

    @Override
    public void start(final Player who) {
        this.gameStatus = GameStatus.Running;
        this.cs = new CollisionSupervisorImpl();
        this.eFactory = new EntityFactoryImpl(this.cs);
        // manca da inizializzare la mappa e da creare il giocatore in base a cosa è
        // inserito dall'utente

    }

    @Override
    public void stopTime() {
        // TODO Auto-generated method stub

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
    public Time getTime() {
        // TODO Auto-generated method stub
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

}
