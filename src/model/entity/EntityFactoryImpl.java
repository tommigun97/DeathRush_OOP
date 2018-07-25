package model.entity;

import model.Area;
import model.Direction;
import model.Location;
import model.room.Room;
import utilities.Pair;
import model.entity.DoorStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Area;
import model.Direction;
import model.Location;

import model.room.Room;
import utilities.Pair;
/**
 * Implementation of EntityFactory.
 */
public class EntityFactoryImpl implements EntityFactory {
    private static final Pair<Double, Double> STARTING_POSITION = new Pair<Double, Double>(0.50, 0.50);
    private final CollisionSupervisor cs;

    /**
     * @param cs
     *            collision supervisor used by some entities
     */
    public EntityFactoryImpl(CollisionSupervisor cs) {
        this.cs = cs;
    }

    @Override
    public Entity createPalyer(final Pair<Double, Double> pos, final Room currentRoom, final Player who) {
        PlayerBehavior pB = null;
        Area pA = null;
        double playerSpeed = 0;
        long shootFequency = 0;
        int shootDamage = 0;
        int startMaxLife = 0;
        if (who.equals(Player.SIMO)) {
            pB = new PlayerBehavior(
                    new CompleteImageSetCalculator(Player.SIMO.images(Direction.N), Player.SIMO.images(Direction.S),
                            Player.SIMO.images(Direction.E), Player.SIMO.images(Direction.W), Player.SIMO.standImage()),
                    cs);
            pA = Player.SIMO.getArea();
            playerSpeed = Player.SIMO.getSpeed();
            shootFequency = Player.SIMO.startingPlayerShootFrequency();
            shootDamage = Player.SIMO.shootingDamage();
            startMaxLife = Player.SIMO.getStartingMaxLife();
        } else if (who.equals(Player.ANIS)) {
            pB = new PlayerBehavior(
                    new CompleteImageSetCalculator(Player.ANIS.images(Direction.N), Player.ANIS.images(Direction.S),
                            Player.ANIS.images(Direction.E), Player.ANIS.images(Direction.W), Player.ANIS.standImage()),
                    cs);
            pA = Player.ANIS.getArea();
            playerSpeed = Player.ANIS.getSpeed();
            shootFequency = Player.ANIS.startingPlayerShootFrequency();
            shootDamage = Player.ANIS.shootingDamage();
            startMaxLife = Player.ANIS.getStartingMaxLife();
        } else if (who.equals(Player.TOMMI)) {
            pB = new PlayerBehavior(new CompleteImageSetCalculator(Player.TOMMI.images(Direction.N),
                    Player.TOMMI.images(Direction.S), Player.TOMMI.images(Direction.E),
                    Player.TOMMI.images(Direction.W), Player.TOMMI.standImage()), cs);
            pA = Player.TOMMI.getArea();
            playerSpeed = Player.TOMMI.getSpeed();
            shootFequency = Player.TOMMI.startingPlayerShootFrequency();
            shootDamage = Player.TOMMI.shootingDamage();
            startMaxLife = Player.TOMMI.getStartingMaxLife();
        } else {
            pB = new PlayerBehavior(
                    new CompleteImageSetCalculator(Player.KASO.images(Direction.N), Player.KASO.images(Direction.S),
                            Player.KASO.images(Direction.E), Player.KASO.images(Direction.W), Player.KASO.standImage()),
                    cs);
            pA = Player.KASO.getArea();
            playerSpeed = Player.KASO.getSpeed();
            shootFequency = Player.KASO.startingPlayerShootFrequency();
            shootDamage = Player.KASO.shootingDamage();
            startMaxLife = Player.KASO.getStartingMaxLife();
        }

        return new EntityImpl.EntitiesBuilder()
                .setLocation(new Location(STARTING_POSITION.getFirst(), STARTING_POSITION.getSecond(), pA))
                .setImage("error").setBehaviour(pB).with("Speed", playerSpeed).with("Max Life", startMaxLife)
                .with("Current Life", startMaxLife).with("Shoot Frequency", (long) shootFequency)
                .with("Shooting Damage", shootDamage).build();
    }

    @Override
    public Entity createStalkerEnemy(double x, double y, Entity eToStalk, Room currentRoom) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity createBullet(double x, double y, Room currentRoom, Direction direction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Entity createDoor(final double x, final double y, final Room currentRoom, final DoorStatus status, final Room nextRoom, final String image) {
        return new EntityImpl.EntitiesBuilder()
                                .setLocation(new Location(x, y, new Area(0.5, 0.5)))
                                .with("currentRoom", currentRoom)
                                .with("doorStatus", status)
                                .with("nextRoom", nextRoom)
                                .with("image", image).build();
    }

}