package model.entity;

public enum EntityPropieties {

    ENEMY1(1), 
    ENEMY2(2), 
    STOPPED(3), 
    BOSS1(4), 
    BOSS2(5), 
    BOSS3(6), 
    VENDOR(7); 
     
    private final int entityID; 
    
     
    private EntityPropieties(final int entityID) { 
        this.entityID = entityID; 
    } 
    
    
}
