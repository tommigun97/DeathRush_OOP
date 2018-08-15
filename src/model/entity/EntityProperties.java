package model.entity;

public enum EntityProperties {

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
	
	private EntityProperties(String id){
		this.id = id;
	}
	
	
	public static EntityProperties getPropieties(String id) {
		return id.equals("1") ? EntityProperties.ENEMY1 : id.equals("2") ? EntityProperties.ENEMY2 :
					id.equals("3") ? EntityProperties.STOPPED : id.equals("4") ? EntityProperties.BOSS1 :
						id.equals("5") ? EntityProperties.BOSS2 : id.equals("6") ? EntityProperties.BOSS3 :
							id.equals("7") ? EntityProperties.GUN : id.equals("8") ? EntityProperties.GUITAR :
								id.equals("9") ? EntityProperties.SUGAR : id.equals("a") ? EntityProperties.SIGARETS : null;
	}
	

    
}
