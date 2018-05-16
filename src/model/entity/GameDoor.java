package model.entity;

import model.Location;
import model.room.Room;
import utilities.Pair;

/**
 * 
 * @author Anis
 *
 */
public class GameDoor implements Door {

    private final String image;
    private Location loc;
    private final EntityType type;
    private final Pair<Room, Room> linking;
    private DoorStatus doorStatus;

    /**
     * 
     * @param image
     *            .
     * @param loc
     *            .
     * @param type
     *            .
     * @param linking
     *            .
     * @param doorStatus
     *            .
     */
    public GameDoor(final String image, final Location loc, final EntityType type, final Pair<Room, Room> linking,
            final DoorStatus doorStatus) {
        this.image = image;
        this.loc = loc;
        this.type = type;
        this.linking = linking;
        this.doorStatus = doorStatus;
    }

    @Override
    public final Pair<Room, Room> getLink() {
        return this.linking;
    }

    @Override
    public final void setDoorStatus(final DoorStatus status) {
        this.doorStatus = status;
    }

    @Override
    public final DoorStatus getDoorStatus() {
        return this.doorStatus;
    }

    /**
     * 
     * @return .
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @return .
     */
    public Location getLoc() {
        return loc;
    }

    /**
     * 
     * @return .
     */
    public EntityType getType() {
        return type;
    }

    /**
     * 
     * @author Anis
     *
     */
    public static class GameDoorBuilder {

        private String image;
        private Location loc;
        private EntityType type;
        private Pair<Room, Room> linking;
        private DoorStatus doorStatus;

        /**
         * 
         * @param image
         *            .
         * @return .
         */
        public GameDoorBuilder setImage(final String image) {
            this.image = image;
            return this;
        }

        /**
         * 
         * @param loc
         *            .
         * @return .
         */
        public GameDoorBuilder setLocation(final Location loc) {
            this.loc = loc;
            return this;
        }

        /**
         * 
         * @param type
         *            .
         * @return .
         */
        public GameDoorBuilder setType(final EntityType type) {
            this.type = type;
            return this;
        }

        /**
         * 
         * @param link
         *            .
         * @return .
         */
        public GameDoorBuilder setLink(final Pair<Room, Room> link) {
            this.linking = link;
            return this;
        }

        /**
         * 
         * @param doorStatus
         *            .
         * @return .
         */
        public GameDoorBuilder setStatus(final DoorStatus doorStatus) {
            this.doorStatus = doorStatus;
            return this;
        }

        /**
         * 
         * @return .
         */
        public GameDoor build() {
            return new GameDoor(image, loc, type, linking, doorStatus);
        }

    }

}
