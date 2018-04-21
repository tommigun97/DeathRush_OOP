package model.entity;

import model.Direction;
import model.Location;
import model.RoomInterface;

/**
 * Enemies that stalk the player.
 *
 */
public class StalkerEnemy extends Enemy {

    private Player entityToStalk;

    public StalkerEnemy(final Location location, final RoomInterface currentRoom, final LivenessEntityType type,
            final Player entityToStalk) {
        super(location, currentRoom, type);
        this.entityToStalk = entityToStalk;
    }

    @Override
    public final void move() {
        Direction d;
        if (this.entityToStalk.getLocation().getY() > this.getLocation().getY()) { // si muove a nord
            d = this.entityToStalk.getLocation().getX() > this.getLocation().getX() ? Direction.NE
                    : this.entityToStalk.getLocation().getX() < this.getLocation().getX() ? Direction.NW : Direction.N;
        } else if (this.entityToStalk.getLocation().getY() < this.getLocation().getY()) { // si muove a sud
            d = this.entityToStalk.getLocation().getX() > this.getLocation().getX() ? Direction.SE
                    : this.entityToStalk.getLocation().getX() < this.getLocation().getX() ? Direction.SW : Direction.S;
        } else { // e sullo stesso piano delle y bisogna capire solo se deve andare a est o a
                 // ovest
            d = this.entityToStalk.getLocation().getX() > this.getLocation().getX() ? Direction.E : Direction.W;
        }
        this.setCurrentDirection(d);
        super.move();

    }

    @Override
    public void checkCollisions() {
        // TODO Auto-generated method stub

    }

}
