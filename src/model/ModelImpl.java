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

    // questo qui è un costruttore che non verrà effettivamente utilizzato
    // nell'applicazione ma mi è utile solo a fini di debug
    public ModelImpl(final Room currentRoom, final Entity player, final GameStatus gameStatus) {
        super();
        // this.currentRoom = currentRoom;
        this.player = player;
        this.gameStatus = gameStatus;
        this.cs = new CollisionSupervisorImpl();
        this.eFactory = new EntityFactoryImpl(cs);
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
        return l;
    }

    @Override
    public List<Pair<String, Pair<Integer, Integer>>> getMap() {
        // TODO Auto-generated method stub
        return null;
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
        //manca tutto il controllo delle collisioni e il da farsi quando il Player muore oppure quando il Player collide con una porta in cui la stanza è completata

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stopTime() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getPlayerLife() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMoney() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Time getTime() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameStatus getGameStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Integer> getPlayerInfo() {
        // TODO Auto-generated method stub
        return null;
    }

}
