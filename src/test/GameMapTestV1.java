package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.stream.Collectors;
import org.junit.Test;
import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.entity.Player;
import model.room.Room;
import model.room.RoomType;
import model.world.GameWorldImpl;
import utilities.Pair;

/**
 * 
 * Test for GameWorld creation
 *
 */
public class GameMapTestV1 {

	private final static int MAXROOM = 20;
	private final static int MINROOM = 16;
	
	private final Entity p = new EntityFactoryImpl(new CollisionSupervisorImpl())
			.createPlayer(new Pair<Double, Double>(0.5, 0.5), Player.ANIS);
	private final CollisionSupervisor cs = new CollisionSupervisorImpl();
	private final EntityFactory ef = new EntityFactoryImpl(cs);
	private final GameWorldImpl map = new GameWorldImpl(ef, p);
	private final int x = map.getRowMatrix() / 2;
	private final int y = map.getColumnMatrix() / 2;

	/**
	 * Used to see the map
	 */
	@Test
	public void printmappa() {
		map.toString();
	}

	/**
	 * Test for initializing world
	 */
	@Test
	public void initialMap() {
		this.map.buildWorldGame();
		Room[][] matrix = this.map.getMatrixMap();
		assertTrue(matrix[x][y].getRoomID() == 1);
		assertTrue(matrix[x][y - 1].getRoomID() == 0);
		assertTrue(matrix[x + 1][y].getRoomID() == 2);
		assertTrue(matrix[x - 1][y].getRoomID() == 3);
		assertTrue(matrix[x][y + 1].getRoomID() == 4);
	}

	/**
	 * Test for counting roomcreated
	 */
	@Test
	public void countTypeRoom() {
		this.map.buildWorldGame();
		assertTrue(this.map.getRooms().size() <= MAXROOM);
		assertFalse(this.map.getRooms().size() < MINROOM);
		assertFalse(this.map.getRooms().stream().filter(x -> x.getType().equals(RoomType.BOSS)).mapToInt(e -> 1)
				.sum() != 3);
		assertTrue(this.map.getRooms().stream().map(e -> e.getType()).collect(Collectors.toList())
				.contains(RoomType.VENDOR));
		assertFalse(this.map.getRooms().stream().filter(e -> e.getType().equals(RoomType.FIRTS)).mapToInt(x -> 1)
				.sum() > 1);
		assertTrue(this.map.getRooms().stream().filter(e -> e.getType()
				.equals(RoomType.INTERMEDIATE)).mapToInt(e1 -> e1.getDoor().size()).sum() == 
					2 * this.map.getRooms().stream().filter(x -> x.getType().equals(RoomType.INTERMEDIATE)).collect(Collectors.toList()).size());
		assertEquals(this.map.getRooms().stream().filter(x -> x.getType().equals(RoomType.BOSS)).mapToInt(e -> 1).sum(), 3);
	}

}
