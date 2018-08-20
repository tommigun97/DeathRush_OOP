package test;

//CHECKSTYLE: MagicNumber OFF
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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

/**
 * 
 * Class for testing scan.
 *
 */
public class EntityScanFromFileTest {

    private final RoomBuilder rb = new RoomBuilder();
    private final EntityFactory ef = new EntityFactoryImpl(new CollisionSupervisorImpl());
    private final Entity player = ef.createPlayer(new Pair<Double, Double>(0.5, 0.5), Player.ANIS);
    private final ScanEntity scanF = new ScanEntityImpl(player, ef);

    /**
     * Test for firstRoom Scanning.
     */
    @Test
    public void testFirstRoom() {
        final Room room = this.rb.setRoomID(1).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>())
                .setTypes(RoomType.FIRTS).setVisited(true).build();
        this.scanF.loadEntity(room);
        assertSame(room.getEntities().size(), 0);
    }

    /**
     * Test for Vendor Scanning.
     */
    @Test
    public void testVendorRoom() {
        final Room room = this.rb.setRoomID(1).setEntities(new CopyOnWriteArraySet<>()).setDoors(new HashSet<>())
                .setTypes(RoomType.VENDOR).setVisited(true).build();
        this.scanF.loadEntity(room);
        assertSame(room.getEntities().stream().filter(x -> x.getType() == EntityType.POWER_UP)
                .collect(Collectors.toSet()).size(), 3);
        assertFalse(room.getEntities().stream().filter(x -> x.getType() == EntityType.ENEMY)
                .collect(Collectors.toList()).size() > 0);
    }

    /**
     * Test for BossRoom Scanning.
     */
    @Test
    public void testBossRooms() {
        final GameWorld gm = new GameWorldImpl(this.ef, this.player);
        gm.buildWorldGame();
        final Set<Room> rooms = gm.getRooms();
        assertEquals(rooms.stream().filter(x -> x.getType() == RoomType.BOSS).map(y -> y.getEntities())
                .collect(Collectors.toSet()).size(), 3);
    }

}
