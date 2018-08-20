package model.entity;

public enum ExistingEntities {

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
	
	private ExistingEntities(String id){
		this.id = id;
	}
	
	
	public static ExistingEntities getPropieties(String id) {
		return id.equals("1") ? ExistingEntities.ENEMY1 : id.equals("2") ? ExistingEntities.ENEMY2 :
					id.equals("3") ? ExistingEntities.STOPPED : id.equals("4") ? ExistingEntities.BOSS1 :
						id.equals("5") ? ExistingEntities.BOSS2 : id.equals("6") ? ExistingEntities.BOSS3 :
							id.equals("7") ? ExistingEntities.GUN : id.equals("8") ? ExistingEntities.GUITAR :
								id.equals("9") ? ExistingEntities.SUGAR : id.equals("a") ? ExistingEntities.SIGARETS : null;
	}
	

    
}
