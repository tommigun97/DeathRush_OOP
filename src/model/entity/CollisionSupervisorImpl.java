package model.entity;

import java.util.Set;

import model.Location;
import model.room.Room;

/**
 * CollisionSupervisor implementation.
 *
 */
public final class CollisionSupervisorImpl implements CollisionSupervisor {

    private static final double CORRECTOR_X = 0.03;
    private static final double CORRECTOR_Y = 0.07;
    private static final double TIME_TO_ACCEPT_COLLISION = 1000;// ms

    private long t = 0;

    @Override
    public void collisionWithBound(final Location prev, final Entity e) {
        if (e.getLocation().getY() - CORRECTOR_Y <= e.getLocation().getArea().getHeight() / 2
                || e.getLocation().getY() + CORRECTOR_Y >= 1 - e.getLocation().getArea().getHeight() / 2
                || e.getLocation().getX() - CORRECTOR_X <= e.getLocation().getArea().getWidth() / 2
                || e.getLocation().getX() + CORRECTOR_X >= 1 - e.getLocation().getArea().getWidth() / 2) {
            e.setLocation(prev);
        }

    }

    @Override
    public void collisionWithObstacles(final Entity e, final Set<Entity> allEntities, final Location prev) {
        System.out.println(allEntities);
        allEntities.stream().filter(o -> o.getType().equals(EntityType.OBSTACLE)).forEach(o -> {
            if (this.collision(e, o)) {
                e.setLocation(prev);
            }
        });
    }

    @Override
    public void collisionWithBound(final Entity e, final Room currentRoom) {
        if (e.getLocation().getY() - CORRECTOR_Y <= e.getLocation().getArea().getHeight() / 2
                || e.getLocation().getY() + CORRECTOR_Y >= 1 - e.getLocation().getArea().getHeight() / 2
                || e.getLocation().getX() - CORRECTOR_X <= e.getLocation().getArea().getWidth() / 2
                || e.getLocation().getX() + CORRECTOR_X >= 1 - e.getLocation().getArea().getWidth() / 2) {
            currentRoom.deleteEntity(e);
        }

    }

    @Override
    public void collisionWithObstacles(final Entity e, final Room currentRoom) {
        currentRoom.getEntities().stream().filter(o -> o.getType().equals(EntityType.OBSTACLE)).forEach(o -> {
            if (this.collision(e, o)) {
                currentRoom.deleteEntity(e);
            }
        });

    }

    @Override
    public void collisionBetweenEntities(final Entity e, final Set<Entity> others) {
        // System.out.println("collisione " + System.currentTimeMillis() - t);
        if (e.getType() == EntityType.ENEMY_BULLET || e.getType() == EntityType.PLAYER_BULLET
                || e.getType() == EntityType.OBSTACLE) {
            return;
        } else if (e.getType() == EntityType.PLAYER && System.currentTimeMillis() - t >= TIME_TO_ACCEPT_COLLISION) {
            others.forEach(o -> {
                if (o.getType() == EntityType.ENEMY && collision(e, o)) {
                    e.changeIntProperty("Current Life",
                            e.getIntegerProperty("Current Life") - o.getIntegerProperty("Collision Damage"));
                    t = System.currentTimeMillis();
                }

                if (o.getType() == EntityType.ENEMY_BULLET && collision(e, o)) {
                    e.changeIntProperty("Current Life",
                            e.getIntegerProperty("Current Life") - o.getIntegerProperty("Shoot Damage"));
                    others.remove(o);
                    t = System.currentTimeMillis();
                }
                System.out.println(e.getIntegerProperty("Current Life"));

            });
        } else if (e.getType() == EntityType.ENEMY) {
            others.forEach(o -> {
                if (o.getType() == EntityType.PLAYER_BULLET && collision(e, o)) {
                    e.changeIntProperty("Current Life",
                            e.getIntegerProperty("Current Life") - o.getIntegerProperty("Shoot Damage"));
                    others.remove(o);
                }
            });

        }

    }

    // da testare con Anis
    @Override
    public void collisionWithDoors(final Entity p, final Set<Entity> doors) {
        doors.forEach(d -> {
            if (collision(p, d) && d.getObjectProperty("doorStatus") == DoorStatus.OPEN) {
                ((PlayerBehavior) p.getBehaviour().get()).setCurrentRoom((Room) d.getObjectProperty("nextRoom"));
            }
        });
    }

    private boolean collision(final Entity entity, final Entity otherEntity) {
        if (otherEntity.getLocation().equals(entity.getLocation())) {
            return true;
        }

        final double var1 = Math.abs(otherEntity.getLocation().getX() - entity.getLocation().getX());
        final double var2 = Math.abs(otherEntity.getLocation().getY() - entity.getLocation().getY());

        if (var1 < (otherEntity.getLocation().getArea().getWidth() + entity.getLocation().getArea().getWidth()) / 2
                && var2 < (otherEntity.getLocation().getArea().getHeight() + entity.getLocation().getArea().getHeight())
                        / 2) {
            return true;
        }

        return false;
    }

}
