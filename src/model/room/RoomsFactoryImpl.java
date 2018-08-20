package model.room;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;
import model.room.RoomImpl.RoomBuilder;

/**
 * Room factory implementation.
 *
 */
public final class RoomsFactoryImpl implements RoomsFactory {

    private final RoomBuilder roomBuilder;

    /**
     * constructor.
     */
    public RoomsFactoryImpl() {
        this.roomBuilder = new RoomBuilder();
    }

    @Override
    public Room vendorRoom(final int roomID, final boolean isVisited) {
        return this.roomBuilder.setRoomID(roomID).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>())
                .setTypes(RoomType.VENDOR).setVisited(isVisited).build();

    }

    @Override
    public Room firstRoom(final int roomID, final boolean isVisited) {
        return this.roomBuilder.setRoomID(roomID).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>())
                .setTypes(RoomType.FIRTS).setVisited(isVisited).build();

    }

    @Override
    public Room intermediateRoom(final int roomID, final boolean isVisited) {
        return this.roomBuilder.setRoomID(roomID).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>())
                .setTypes(RoomType.INTERMEDIATE).setVisited(isVisited).build();

    }

    @Override
    public Room bossRoom(final int roomID, final boolean isVisited) {
        return this.roomBuilder.setRoomID(roomID).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>())
                .setTypes(RoomType.BOSS).setVisited(isVisited).build();

    }

}
