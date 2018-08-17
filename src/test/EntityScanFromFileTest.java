package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import org.junit.Test;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.EntityType;
import model.entity.Player;
import model.room.Room;
import model.room.RoomImpl.RoomBuilder;
import model.world.GameWorld;
import model.world.GameWorldImpl;
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
	public void testFirstRoom() {
		Room room = this.rb.setComplited(true).setRoomID(1).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.FIRTS).setVisited(true).build();
		this.scanF.loadEntity(room);
		assertTrue(room.getEntities().size() == 0);
	}

	@Test
	public void testVendorRoom() {
		Room room = this.rb.setComplited(true).setRoomID(1).setEntities(new CopyOnWriteArraySet<>())
				.setDoors(new HashSet<>()).setTypes(RoomType.VENDOR).setVisited(true).build();
		this.scanF.loadEntity(room);
		assertTrue(room.getEntities().stream().filter(x -> x.getType() == EntityType.POWER_UP)
				.collect(Collectors.toSet()).size() == 3);
		assertFalse(room.getEntities().stream().filter(x -> x.getType() == EntityType.ENEMY)
				.collect(Collectors.toList()).size() > 0);
	}

	@Test
	public void TestBossRooms() {
		GameWorld gm = new GameWorldImpl(this.ef, this.player);
		gm.buildWorldGame();
		Set<Room> rooms = gm.getRooms();
		assertEquals(rooms.stream().filter(x -> x.getType() == RoomType.BOSS).map(y -> y.getEntities())
				.collect(Collectors.toSet()).size(), 3);
	}

}
