package model.entity;

import model.room.Room;

/**
 * Enemy that summon bullet and other enemy.
 *
 */
public final class CompleteSummonerBehavior extends OnlyBulletSummonerBeahavior implements Behavior {

    private static final long F_ENTITY_EVOCATION = 8000; // millisec == 4 sec
    private static final double LONGER = 0.5; 
    private static final double SHORTER = 0.2;
    private static final double MIDDLEX = 0.8;
    private static final double MIDDLEY = 0.3;
    private long tEvocation;
    private final Entity toKill;

    /**
     * @param imgCalc
     *            image calculator
     * @param cs
     *            collision supervisor for collisions
     * @param currentRoom
     *            room where is placed
     * @param eFactory
     *            factory for summon enemy
     * @param toKill
     *            entity to kill
     */
    public CompleteSummonerBehavior(final ImageCalculator imgCalc, final CollisionSupervisor cs, final Room currentRoom,
            final EntityFactory eFactory, final Entity toKill) {
        super(imgCalc, cs, currentRoom, eFactory);
        this.tEvocation = System.currentTimeMillis();
        this.toKill = toKill;
    }

    @Override
    public void update() {
        super.update();
        if (System.currentTimeMillis() - this.tEvocation >= F_ENTITY_EVOCATION) {
            getCurrentRoom().addEntity(geteFactory().createMoscow(SHORTER, LONGER, toKill, getCurrentRoom()));
            getCurrentRoom().addEntity(geteFactory().createMoscow(LONGER, SHORTER, toKill, getCurrentRoom()));
            getCurrentRoom().addEntity(geteFactory().createMoscow(MIDDLEX, MIDDLEY, toKill, getCurrentRoom()));
            tEvocation = System.currentTimeMillis();
        }
    }

}
