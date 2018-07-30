package model.room;

import java.util.List;
import java.util.Set;
import model.entity.Entity;
import model.entity.EntityType;

/**
 * 
 *
 *
 */
public class RoomImpl implements Room {

    private final String image;
    private final int roomID;
    private boolean complited;
    private final RoomType type;
    private Set<Entity> entitiesRoom;
    private Set<Entity> doorsRoom;

    /**
     * 
     * @param image
     *            .
     * @param roomID
     *            .
     * @param complited
     *            .
     * @param type
     *            .
     * @param entitiesRoom
     *            .
     * @param doorsRoom
     *            .
     */
    public RoomImpl(final String image, final int roomID, final boolean complited, final RoomType type,
            final Set<Entity> entitiesRoom, final Set<Entity> doorsRoom) {
        this.image = image;
        this.roomID = roomID;
        this.complited = complited;
        this.type = type;
        this.entitiesRoom = entitiesRoom;
        this.doorsRoom = doorsRoom;
    }

    /**
     * @return true if room is complited false else
     */
    public boolean isComplited() {
        return (entitiesRoom.stream().filter(e -> e.getType() == EntityType.ENEMY).count() == 0);

    }

    /**
     * @param complited
     *            new complited status
     */
    public void setComplited(final boolean complited) {
        this.complited = complited;

    }

    /**
     * @param door
     *            add door to the room
     */
    public void addDoor(Entity door) {
        this.doorsRoom.add(door);
    }

    /**
     * @return Set<Entity> return Room's door
     */
    public Set<Entity> getDoor() {
        return this.doorsRoom;
    }

    /**
     * @return int return roomId
     */
    public int getRoomID() {
        return roomID;
    }

    @Override
    public final String toString() {
        return "" + this.roomID;
    }

    /**
     * 
     */
    @Override
    public void openDoors() {
        this.doorsRoom.forEach(x -> x.changeObjectProperty("doorStatus", model.entity.DoorStatus.OPEN));
    }

    /**
     * 
     */
    public void closeDoors() {
        this.doorsRoom.forEach(x -> x.changeObjectProperty("doorStatus", model.entity.DoorStatus.CLOSE));
    }

    @Override
    public final void addEntity(final Entity entity) {
        this.entitiesRoom.add(entity);
    }

    @Override
    public final void deleteEntity(final Entity entity) {
        this.entitiesRoom.remove(entity);
        System.out.println("oggetto eliminato");
    }

    @Override
    public final Set<Entity> getEntities() {
        return this.entitiesRoom;
    }

    /**
     * 
     * 
     *
     */
    public static class RoomBuilder {

        private String image;
        private int roomID;
        private boolean complited;
        private RoomType type;
        private Set<Entity> entitiesRoom;
        private Set<Entity> doorsRoom;

        /**
         * 
         * @param image
         *            .
         * @return .
         */
        public RoomBuilder setImage(final String image) {
            this.image = image;
            return this;
        }

        /**
         * 
         * @param roomID
         *            .
         * @return .
         */
        public RoomBuilder setRoomID(final int roomID) {
            this.roomID = roomID;
            return this;
        }

        /**
         * 
         * @param complited
         *            .
         * @return .
         */
        public RoomBuilder setComplited(final boolean complited) {
            this.complited = complited;
            return this;
        }

        /**
         * 
         * @param type
         *            .
         * @return .
         */
        public RoomBuilder setTypes(final RoomType type) {
            this.type = type;
            return this;
        }

        /**
         * 
         * @param entitiesRoom
         *            .
         * @return .
         */
        public RoomBuilder setEntities(final Set<Entity> entitiesRoom) {
            this.entitiesRoom = entitiesRoom;
            return this;
        }

        /**
         * 
         * @param doorsRoom
         *            .
         * @return .
         */
        public RoomBuilder setDoors(final Set<Entity> doorsRoom) {
            this.doorsRoom = doorsRoom;
            return this;
        }

        /**
         * 
         * @return .
         */
        public RoomImpl build() {
            return new RoomImpl(image, roomID, complited, type, entitiesRoom, doorsRoom);
        }
    }

    @Override
    public String getImage() {
        return this.image;
    }

}
