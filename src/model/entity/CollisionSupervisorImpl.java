package model.entity;

import java.util.List;

import model.Location;

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
    public void collisionWithObstacles(final Entity e, List<Entity> allEntities) {
        // TODO Auto-generated method stub
        
    }

}
