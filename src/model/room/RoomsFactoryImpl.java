package model.room;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;
import model.room.RoomImpl.RoomBuilder;

public class RoomsFactoryImpl implements RoomsFactory {

    // Variables
    private RoomBuilder roomBuilder;

    // Constructor
    public RoomsFactoryImpl() {
        this.roomBuilder = new RoomBuilder();
    }

    @Override
    public Room vendorRoom(int roomID, boolean isCompleted, boolean isVisited) {
        return this.roomBuilder.setComplited(isCompleted).setRoomID(roomID).setEntities(new CopyOnWriteArraySet<>())
                .setDoors(new HashSet<>()).setTypes(RoomType.VENDOR).setVisited(isVisited).build();

    }

    @Override
    public Room FirstRoom(int roomID, boolean isCompleted, boolean isVisited) {
        return this.roomBuilder.setComplited(isCompleted).setRoomID(roomID).setEntities(new CopyOnWriteArraySet<>())
                .setDoors(new HashSet<>()).setTypes(RoomType.FIRTS).setVisited(isVisited).build();

    }

    @Override
    public Room IntermediateRoom(int roomID, boolean isCompleted, boolean isVisited) {
        return this.roomBuilder.setComplited(isCompleted).setRoomID(roomID).setEntities(new CopyOnWriteArraySet<>())
                .setDoors(new HashSet<>()).setTypes(RoomType.INTERMEDIATE).setVisited(isVisited).build();

    }

    @Override
    public Room BossRoom(int roomID, boolean isCompleted, boolean isVisited) {
        return this.roomBuilder.setComplited(isCompleted).setRoomID(roomID).setEntities(new CopyOnWriteArraySet<>())
                .setDoors(new HashSet<>()).setTypes(RoomType.BOSS).setVisited(isVisited).build();

    }

}
