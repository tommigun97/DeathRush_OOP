package model.entity;

import model.Location;
import model.RoomInterface;

/**
 * The Enemies of the players.
 *
 */
public abstract class Enemy extends AbstractLivenessEntity {

    public Enemy(final Location location, final RoomInterface currentRoom, final LivenessEntityType type) {
        super(location, currentRoom, type);
    }

    @Override
    public final void shoot() {
        if (this.getWeapon().isPresent()) {
            this.getWeapon().get().shoot(this.getCurrentDirection(), this.getCurrentRoom(), false, this.getLocation());
        }

    }

}
