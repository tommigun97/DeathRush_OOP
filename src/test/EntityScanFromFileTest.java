package test;

import org.junit.Test;

import model.entity.CollisionSupervisorImpl;
import model.entity.EntityFactoryImpl;
import model.map.GameMap;
import model.map.Map;

public class EntityScanFromFileTest {

	private static final Map WORLD = new GameMap(new EntityFactoryImpl(new CollisionSupervisorImpl()));
	private String filePath;
	
	
	@Test
	void roomEnemyBuild() {
	}
}
