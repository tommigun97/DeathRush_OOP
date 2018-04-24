package model.entity;

import model.Direction;
import model.Location;
import model.RoomInterface;

/**
 * 
 *
 */
public class Player extends AbstractLivenessEntity {
    private int coin;

    /**
     * @param location Starting Player Location
     * @param currentRoom Starting Player's Room
     * @param type Player's type
     */
    public Player(final Location location, final RoomInterface currentRoom,
            final LivenessEntityType type, final Direction currentDiretion) {
        super(location, currentRoom, type, currentDiretion);
        this.coin = 0;
    }

    @Override
    public void checkCollisions() {
        // TODO Auto-generated method stub
    }

    /**
     * @return current Player's coin
     */
    public int getCoin() {
        return this.coin;
    }

    /**
     * @param increment Value for increasing coin
     */
    public void increaseCoin(final int increment) {
        if (increment <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.coin += increment;
        }
    }

    /**
     * @param lost how much the coins are decreased
     */
    public void loseCoin(final int lost) {
        if (lost <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.coin -= lost;
        }
    }

    @Override
    public final void shoot() {
        if (this.getWeapon().isPresent()) {
            this.getWeapon().get().shoot(this.getCurrentDirection(), this.getCurrentRoom(), true, this.getLocation());
        }
    }

}
