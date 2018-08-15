package model.entity;

public enum EntityPropieties {

    ENEMY1("1"), 
    ENEMY2("2"), 
    STOPPED("3"), 
    BOSS1("4"), 
    BOSS2("5"), 
    BOSS3("6"), 
    GUN("7"),
    GUITAR("8"),
    SUGAR("9"),
    SIGARETS("a");
	
	private final String id;
	
	private EntityPropieties(String id){
		this.id = id;
	}
	
	
	public static EntityPropieties getPropieties(String id) {
		return id.equals("1") ? EntityPropieties.ENEMY1 : id.equals("2") ? EntityPropieties.ENEMY2 :
					id.equals("3") ? EntityPropieties.STOPPED : id.equals("4") ? EntityPropieties.BOSS1 :
						id.equals("5") ? EntityPropieties.BOSS2 : id.equals("6") ? EntityPropieties.BOSS3 :
							id.equals("7") ? EntityPropieties.GUN : id.equals("8") ? EntityPropieties.GUITAR :
								id.equals("9") ? EntityPropieties.SUGAR : id.equals("a") ? EntityPropieties.SIGARETS : null;
	}
	

    
}
