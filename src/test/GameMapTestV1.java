package test;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.Test;

import model.Location;
import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.Player;
import model.room.Room;
import model.world.GameWorldImpl;
import utilities.Pair;


public class GameMapTestV1 {

    @Test
    public void printmappa() {
        Entity p = new EntityFactoryImpl(new CollisionSupervisorImpl()).createPlayer(new Pair<Double, Double>(0.5, 0.5), Player.ANIS);
        CollisionSupervisor cs = new CollisionSupervisorImpl();
        EntityFactory ef = new EntityFactoryImpl(cs);
        GameWorldImpl map = new GameWorldImpl(ef, p);
        
       
        map.toString();
      
    }
}
