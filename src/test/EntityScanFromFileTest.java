package test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;
import org.junit.Test;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.EntityType;
import model.entity.Player;
import model.room.Room;
import model.room.RoomImpl.RoomBuilder;
import model.world.ScanEntity;
import model.world.ScanEntityImpl;
import model.room.RoomType;
import utilities.Pair;

public class EntityScanFromFileTest {

	private RoomBuilder rb = new RoomBuilder();
	private EntityFactory ef = new EntityFactoryImpl(new CollisionSupervisorImpl());
	private Entity player = ef.createPlayer(new Pair<Double, Double>(0.5, 0.5), Player.ANIS);
	private ScanEntity scanF = new ScanEntityImpl(player, ef);

	@Test
	void testFirstRoom() {
		Room room = this.rb.setComplited(true).setRoomID(1).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.FIRTS).setVisited(true).build();		
		this.scanF.loadEntity(room);
		assertTrue(room.getEntities().size() == 0);
	}
	@Test
	void testVendorRoom() {
		Room room = this.rb.setComplited(true).setRoomID(1).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.VENDOR).setVisited(true).build();	
		this.scanF.loadEntity(room);
		assertTrue(room.getEntities().size() == 3);
		assertTrue(room.getEntities().stream().allMatch(x -> x.getType().equals(EntityType.POWER_UP)));
	}
	
	/*void TestBossRoom() {
		Room room = this.rb.setComplited(true).setRoomID(1).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.BOSS).setVisited(true).build();
		this.scanF.loadBoss(room);
		assertTrue(room.getEntities().stream().filter(e -> ((Boss)e.getBooleanProperty(property)).mapToInt(x -> 1).sum() == 1);
	}*/
	
	
}
