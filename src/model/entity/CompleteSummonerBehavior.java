package model.entity;

import model.room.Room;

public class CompleteSummonerBehavior extends OnlyBulletSummonerBeahavior implements Behavior {

    private static final long F_ENTITY_EVOCATION = 8000; // millisec == 4 sec
    private long tEvocation;
    private Entity toKill;

    public CompleteSummonerBehavior(ImageCalculator imgCalc, CollisionSupervisor cs, Room currentRoom, EntityFactory eFactory,
            Entity toKill) {
        super(imgCalc, cs, currentRoom, eFactory);
        this.tEvocation = System.currentTimeMillis();
        this.toKill = toKill;
    }

    @Override
    public void update() {
        super.update();
        if (System.currentTimeMillis() - this.tEvocation >= F_ENTITY_EVOCATION) {
            getCurrentRoom().addEntity(geteFactory().createMoscow(0.2, 0.5, toKill, getCurrentRoom()));
            getCurrentRoom().addEntity(geteFactory().createMoscow(0.5, 0.2, toKill, getCurrentRoom()));
            getCurrentRoom().addEntity(geteFactory().createMoscow(0.8, 0.3, toKill, getCurrentRoom()));
            tEvocation = System.currentTimeMillis();
        }
    }

}
