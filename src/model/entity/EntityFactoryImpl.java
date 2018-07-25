package model.entity;

import model.Area;
import model.Direction;
import model.Location;
import model.room.Room;
import utilities.Pair;
import model.entity.DoorStatus;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity createPalyer(Pair<Double, Double> pos, Room currentRoom) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity createStalkerEnemy(double x, double y, Entity eToStalk, Room currentRoom) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity createBullet(double x, double y, Room currentRoom, Direction direction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Entity createDoor(final double x, final double y, final Room currentRoom, final DoorStatus status, final Room nextRoom, final String image) {
        return new EntityImpl.EntitiesBuilder()
                                .setLocation(new Location(x, y, new Area(0.5, 0.5)))
                                .with("currentRoom", currentRoom)
                                .with("doorStatus", status)
                                .with("nextRoom", nextRoom)
                                .with("image", image).build();
    }

}
