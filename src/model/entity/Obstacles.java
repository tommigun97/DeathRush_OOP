package model.entity;

import model.Location;

/**
 * Object that consistance entities can't ignore.
 *
 */
public class Obstacles implements Entity {

    private final Location location;
    private final String image;

    public Obstacles(Location location, String image) {
        super();
        this.location = location;
        this.image = image;
    }

    @Override
    public final String getImage() {
        return image;
    }

    @Override
    public final Location getLocation() {
        return location;
    }

}
