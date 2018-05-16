package model.entity;

import java.util.Optional;

import model.Location;
import model.room.Room;
import utilities.Pair;

public class GameDoor  implements Door {
    
    
    private final String image;
    private Location loc;
    private final EntityType type;
    private final Pair<Room, Room> linking;
    private DoorStatus doorStatus;
    
    public GameDoor(final String image, Location loc, final EntityType type, Pair<Room, Room> linking, DoorStatus doorStatus) {
        this.image = image;
        this.loc = loc;
        this.type = type;
        this.linking = linking;
        this.doorStatus = doorStatus;
    }

    @Override
    public Pair<Room, Room> getLink() {
        return this.linking;
    }

    @Override
    public void setDoorStatus(DoorStatus status) {
        this.doorStatus = status;
    }

    @Override
    public DoorStatus getDoorStatus() {
        return this.doorStatus;
    }

    public String getImage() {
        return image;
    }

    public Location getLoc() {
        return loc;
    }

    public EntityType getType() {
        return type;
    }
    
    public static class GameDoorBuilder{
        
        private String image;
        private Location loc;
        private EntityType type;
        private Pair<Room, Room> linking;
        private DoorStatus doorStatus;
        
        public GameDoorBuilder setImage(final String image) {
            this.image = image;
            return this;
        }
        public GameDoorBuilder setLocation(final Location loc) {
            this.loc = loc;
            return this;
        }
        public GameDoorBuilder setType(final EntityType type) {
            this.type = type;
            return this;
        }
        public GameDoorBuilder setLink(final Pair<Room, Room> link) {
            this.linking = link;
            return this;
        }
        public GameDoorBuilder setStatus(DoorStatus doorStatus) {
            this.doorStatus = doorStatus;
            return this;
        }
        public GameDoor build() {
            return new GameDoor(image, loc, type, linking, doorStatus);
        }
        
    }

}
