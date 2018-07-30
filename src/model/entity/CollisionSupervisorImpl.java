package model.entity;

import java.util.Set;

import model.Location;
import model.room.Room;

/**
 * CollisionSupervisor implementation.
 *
 */
public final class CollisionSupervisorImpl implements CollisionSupervisor {

    @Override
    public void collisionWithBound(final Location prev, final Entity e) {
        if (e.getLocation().getY() <= e.getLocation().getArea().getHeight() / 2
                || e.getLocation().getY() >= 1 - e.getLocation().getArea().getHeight() / 2
                || e.getLocation().getX() <= e.getLocation().getArea().getWidth() / 2
                || e.getLocation().getX() >= 1 - e.getLocation().getArea().getWidth() / 2) {
            e.setLocation(prev);
        }

    }

    @Override
    public void collisionWithObstacles(final Entity e, final Set<Entity> allEntities, final Location prev) {
        allEntities.stream().filter(o -> o.getType().equals(EntityType.OBSTACLE)).forEach(o -> {
            if (this.collision(e, o)) {
                e.setLocation(prev);
            }
        });
    }

    @Override
    public void collisionWithBound(final Entity e, final Room currentRoom) {
        if (e.getLocation().getY() <= e.getLocation().getArea().getHeight() / 2
                || e.getLocation().getY() >= 1 - e.getLocation().getArea().getHeight() / 2
                || e.getLocation().getX() <= e.getLocation().getArea().getWidth() / 2
                || e.getLocation().getX() >= 1 - e.getLocation().getArea().getWidth() / 2) {
            currentRoom.deleteEntity(e);
        }

    }

    @Override
    public void collisionWithObstacles(final Entity e, final Room currentRoom) {
        currentRoom.getEntities().stream().filter(o -> o.getType().equals(EntityType.OBSTACLE)).forEach(o -> {
            if (this.collision(e, o)) {
                currentRoom.deleteEntity(e);
                System.out.println("Done");
            }
        });

    }

    @Override
    public void collisionBetweenEntities(final Entity e, final Set<Entity> others) {
        if (e.getType() == EntityType.ENEMY_BULLET || e.getType() == EntityType.PLAYER_BULLET
                || e.getType() == EntityType.OBSTACLE) {
            return;
        } else if (e.getType() == EntityType.PLAYER) {
            others.forEach(o -> {
                if (o.getType() == EntityType.ENEMY && collision(e, o)) {
                    e.changeIntProperty("Current Life",
                            e.getIntegerProperty("Current Life") - o.getIntegerProperty("Collision Damage"));
                }

                if (o.getType() == EntityType.ENEMY_BULLET && collision(e, o)) {
                    e.changeIntProperty("Current Life",
                            e.getIntegerProperty("Current Life") - o.getIntegerProperty("Shoot Damage"));
                    others.remove(o);
                }
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
//da testare
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
