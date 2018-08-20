package model.entity;

import java.util.Optional;

import model.Area;
import model.Direction;
import model.room.Room;
import model.world.Coordinates;
import utilities.Pair;

/**
 * Factory for the creation of the entity.
 *
 */
public interface EntityFactory {

    /**
     * Create game's player.
     * 
     * @param pos
     *            starting player position in the space
     * @return the player
     * 
     * @param who
     *            what player gamer want
     */
    Entity createPlayer(Pair<Double, Double> pos, Player who);

    /**
     * Create a boss.
     * 
     * @param x
     *            x-axis position
     * @param y
     *            y-axis position
     * @param currentRoom
     *            room where the boss is placed
     * @param who
     *            how boss do you want create
     * @return the boss
     * 
     * @param eToStalk
     *            entity that some boss want stalk
     * 
     */
    Entity createBoss(double x, double y, Room currentRoom, Optional<Entity> eToStalk, Boss who);

    /**
     * Create spirit enemy.
     * 
     * @param x
     *            x-axis position
     * @param y
     *            y-axis position
     * 
     *            starting player position in the space
     * @param eToStalk
     *            entity to stalk
     * @param currentRoom
     *            room where the bullet is placed, needs to set the limit to move
     * @return the enemy
     * 
     * @param canShoot
     *            if enemy can shoot or not
     */
    Entity stalkerSpiritEnemy(double x, double y, Entity eToStalk, Room currentRoom, boolean canShoot);

    /**
     * Create a bullet.
     * @param x
     *            door x-axis position
     * @param y
     *            door y-axis position
     * @param currentRoom
     *            room where the bullet is placed, needs to set the limit to move
     * @param direction
     *            direction where the bullet run
     * @param damage
     *            bullet damage
     * @param speed
     *            bullet speed
     * @param bulletType
     *            bullet type
     * 
     * 
     * @return the bullet
     */
    Entity createBullet(double x, double y, Room currentRoom, Direction direction, EntityType bulletType, int damage,
            double speed);

    /**
     * Create a door.
     * @param x
     *            door x-axis position
     * @param y
     *            door y-axis position
     * 
     * @param nextRoom
     *            the room where the door leads
     * @param status
     *            if door is open or closed
     * @param image
     *            path for door image
     * @param coor
     *            where is set in the room
     * @param area
     *            door area
     * @return the door
     */
    Entity createDoor(double x, double y, DoorStatus status, Room nextRoom, String image, Coordinates coor, Area area);

    /**
     * Create a obstacle.
     * @param x
     *            door x-axis position
     * @param y
     *            door y-axis position
     * @return the obstacle
     */
    Entity createObstacle(double x, double y);

    /**
     * create moscow enemy.
     * @param x
     *            x-axis position
     * @param y
     *            y-axis position
     * @param eToStalk
     *            entity to stalk
     * @param currentRoom
     *            where the enemy are set
     * @return create an enemy looks like mosquito that follow the player
     */
    Entity createMoscow(double x, double y, Entity eToStalk, Room currentRoom);

    /**
     * create a power up.
     * @param x
     *            x-axis position
     * @param y
     *            y-axis position
     * @param currentRoom
     *            where the enemy are set
     * @param who
     *            what power up you want
     * @return the power up
     */
    Entity createPowerUp(double x, double y, Room currentRoom, PowerUp who);

}
