package model.entity;

import java.util.Optional;

import model.Area;
import model.Direction;
import model.map.Coordinates;
import model.room.Room;
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
     */
    Entity createBoss(double x, double y, Room currentRoom,Optional<Entity> eToStalk, Boss who);

    /**
     * Create enemy that stalk the player.
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
     */
    Entity isaacStalkerEnemy(double x, double y, Entity eToStalk, Room currentRoom, boolean canShoot);

    /**
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
     * @return the bullet
     */
    Entity createBullet(double x, double y, Room currentRoom, Direction direction, EntityType bulletType, int damage,
            double speed, final EntityType who);

    /**
     * @param x
     *            door x-axis position
     * @param y
     *            door y-axis position
     * 
     * @param currentRoom
     *            room where the door is placed
     * @param nextRoom
     *            the room where the door leads
     * @return the door
     */
    Entity createDoor(double x, double y, DoorStatus status, Room nextRoom, String image, Coordinates coor, Area area);

    /**
     * @param x
     *            door x-axis position
     * @param y
     *            door y-axis position
     * @return the obstacle
     */
    Entity createObstacle(double x, double y);

    /**
     * @param x
     *            x-axis position
     * @param y
     *            y-axis position
     * @return create an enemy looks like mosquito that follow the player
     */
    Entity createMoscow(double x, double y, Entity eToStalk, Room currentRoom);

}
