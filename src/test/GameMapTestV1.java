package test;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.map.GameMap;


public class GameMapTestV1 {

    @Test
    public void printmappa() {
        CollisionSupervisor cs = new CollisionSupervisorImpl();
        EntityFactory ef = new EntityFactoryImpl(cs);
        GameMap map = new GameMap(ef);
        map.printmappa();
    }
}
