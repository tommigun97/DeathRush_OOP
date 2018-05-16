package model.entity;

import model.Direction;
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
     * @param currentRoom
     *            the starting room where player is placed
     * @return the player
     */
    Entity createPalyer(Pair<Double, Double> pos, Room currentRoom);

    /**
     * Create enemy that stalk the player.
     * 
     * @param pos
     *            starting player position in the space
     * @param currentRoom
     *            the room where enemy is placed
     * @return the enemy
     */
    Entity createStalkerEnemy(Pair<Double, Double> pos, Room currentRoom);

    /**
     * @param pos starting bulletPosition
     * @param currentRoom room where the bullet is placed
     * @param direction direction where the bullet run
     * @return the bullet
     */
    //forse sarà necessario aggiungergli un danno come parametro in modo da differenziare i vari proiettili
    Entity createBullet(Pair<Double, Double> pos, Room currentRoom, Direction direction);

    /**
     * @param pos
     *            position of the door in the room
     * @param currentRoom
     *            room where the door is placed
     * @param nextRoom
     *            the room where the door leads
     * @return the door
     */
    Entity createDoor(Pair<Double, Double> pos, Room currentRoom, Room nextRoom);

}
