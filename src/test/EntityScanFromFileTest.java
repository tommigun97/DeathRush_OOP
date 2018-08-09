package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.Area;
import model.Location;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.Player;
import model.map.BackgroundFromFile;
import model.map.ScanEntityImpl;
import model.room.Room;
import model.room.RoomImpl.RoomBuilder;
import model.room.RoomType;
import utilities.Pair;


public class EntityScanFromFileTest {

	private String filePath = "roomsFile.1.txt";
	private RoomBuilder rb = new RoomBuilder();
	private ScanEntityImpl backF;
	private Set<Room> rooms = new HashSet<>();
	private Entity player;
	private EntityFactory ef = new EntityFactoryImpl(new CollisionSupervisorImpl());
	
	@Test
	void roomEnemyBuild() {
		this.rooms.add(rb.setRoomID(1).setComplited(false).setTypes(RoomType.INTERMEDIATE).build());
		this.player = ef.createPlayer(new Pair<Double, Double>(0.5, 1.0), Player.ANIS);
		this.backF = new ScanEntityImpl(this.rooms, this.player, new EntityFactoryImpl(new CollisionSupervisorImpl()));
		this.backF.populateRooms();
		
		
	}
}
