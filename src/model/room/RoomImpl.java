package model.room;

import java.util.Set;
import model.entity.Entity;
import model.entity.EntityType;
import model.world.Coordinates;

/**
 * Implementation of Room.
 *
 */
public final class RoomImpl implements Room {

    private final String image;
    private final int roomID;
    private final RoomType type;
    private boolean visited;
    private final Set<Entity> entitiesRoom;
    private final Set<Entity> doorsRoom;

    /**
     * Constructor of the class.
     * 
     * @param image
     *            room back ground
     * @param roomID
     *            room ID
     * @param type
     *            room type
     * @param entitiesRoom
     *            collection of entity
     * @param doorsRoom
     *            collection of door
     * @param visited
     *            true if the room is just visited
     */
    public RoomImpl(final String image, final int roomID, final RoomType type, final Set<Entity> entitiesRoom,
            final Set<Entity> doorsRoom, final boolean visited) {
        this.image = image;
        this.roomID = roomID;
        this.type = type;
        this.entitiesRoom = entitiesRoom;
        this.doorsRoom = doorsRoom;
        this.visited = visited;
    }

    @Override
    public boolean isComplited() {
        return (entitiesRoom.stream().filter(e -> e.getType() == EntityType.ENEMY).count() == 0);

    }

    @Override
    public void addDoor(final Entity door) {
        this.doorsRoom.add(door);
    }

    @Override
    public Set<Entity> getDoor() {
        return this.doorsRoom;
    }

    @Override
    public int getRoomID() {
        return roomID;
    }

    @Override
    public void openDoors() {
        this.doorsRoom.forEach(x -> x.changeObjectProperty("doorStatus", model.entity.DoorStatus.OPEN));
        this.doorsRoom.forEach(x -> x.setImage(((Coordinates) x.getObjectProperty("coordinate")).getOpen()));
    }

    @Override
    public void closeDoors() {
        this.doorsRoom.forEach(x -> x.changeObjectProperty("doorStatus", model.entity.DoorStatus.CLOSE));
        this.doorsRoom.forEach(x -> x.setImage(((Coordinates) x.getObjectProperty("coordinate")).getClose()));

    }

    @Override
    public void addEntity(final Entity entity) {
        this.entitiesRoom.add(entity);
    }

    @Override
    public void deleteEntity(final Entity entity) {
        this.entitiesRoom.remove(entity);
    }

    @Override
    public Set<Entity> getEntities() {
        return this.entitiesRoom;
    }

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public RoomType getType() {
        return this.type;
    }

    @Override
    public void setVisited(final boolean x) {
        this.visited = x;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    /**
     * Builder room.
     */
    public static class RoomBuilder {

        private String image;
        private int roomID;
        private RoomType type;
        private boolean visited;
        private Set<Entity> entitiesRoom;
        private Set<Entity> doorsRoom;

        /**
         * Setter for image.
         * 
         * @param image
         *            path of image
         * @return builder
         */
        public RoomBuilder setImage(final String image) {
            this.image = image;
            return this;
        }

        /**
         * setter for room id.
         * 
         * @param roomID
         *            room id
         * @return the builder
         */
        public RoomBuilder setRoomID(final int roomID) {
            this.roomID = roomID;
            return this;
        }

        /**
         * setter for room type.
         * 
         * @param type
         *            room type
         * @return the builder
         */
        public RoomBuilder setTypes(final RoomType type) {
            this.type = type;
            return this;
        }

        /**
         * setter for set entity.
         * 
         * @param entitiesRoom
         *            entity set
         * @return the builder
         */
        public RoomBuilder setEntities(final Set<Entity> entitiesRoom) {
            this.entitiesRoom = entitiesRoom;
            return this;
        }

        /**
         * setter for door set.
         * 
         * @param doorsRoom
         *            door set
         * @return the builder
         */
        public RoomBuilder setDoors(final Set<Entity> doorsRoom) {
            this.doorsRoom = doorsRoom;
            return this;
        }

        /**
         * setter true if room is just visited.
         * 
         * @param x
         *            true if room is just visited
         * @return the builder
         */
        public RoomBuilder setVisited(final boolean x) {
            this.visited = x;
            return this;
        }

        /**
         * 
         * @return the room.
         */
        public RoomImpl build() {
            return new RoomImpl(image, roomID, type, entitiesRoom, doorsRoom, visited);
        }
    }

}
