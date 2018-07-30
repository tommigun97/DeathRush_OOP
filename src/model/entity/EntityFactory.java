package model.entity;

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
     * @param currentRoom
     *            the starting room where player is placed
     * @return the player
     */
    Entity createPalyer(Pair<Double, Double> pos, Room currentRoom, Player who); // ricorda questo oggetto deve creare nuovi oggetti
                                                                     // quindi nel suo behavior ci dovrà essere un campo
                                                                     // eFactory

    /**
     * Create enemy that stalk the player.
     * 
     * @param x
     *            door x-axis position
     * @param y
     *            door y-axis position
     * 
     *            starting player position in the space
     * @param eToStalk
     *            entity to stalk
     * @param currentRoom
     *            room where the bullet is placed, needs to set the limit to move
     * @return the enemy
     */
    Entity createStalkerEnemy(double x, double y, Entity eToStalk, Room currentRoom);

    /**
     * @param x
     *            door x-axis position
     * @param y
     *            door y-axis position
     * @param currentRoom
     *            room where the bullet is placed, needs to set the limit to move
     * @param direction
     *            direction where the bullet run
     * @return the bullet
     */
    Entity createBullet(double x, double y, Room currentRoom, Direction direction);
    // forse sarà necessario aggiungergli un danno come parametro in modo da
    // differenziare i vari proiettili, il danno sarà preso dalle caratteristiche
    // dell'entità a cui è associato tramite il suo beahvior

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
    Entity createDoor(double x, double y, DoorStatus status, Room nextRoom, String image, Coordinates coor);

}
