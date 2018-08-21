package model.world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Optional;
import model.entity.Boss;
import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.ExistingEntities;
import model.entity.PowerUp;
import model.room.Room;
import model.room.RoomType;

/**
 * 
 * Implementation of ScanEntity.
 *
 */
public final class ScanEntityImpl implements ScanEntity {
    private static final double HEIGHT = 1;
    private static final double WEIGHT = 1;
    private static final char NOSCAN = '0';

    private String fileName;
    private final EntityFactory ef;
    private final Entity entityToStolk;
    private BufferedReader bufferReader;
    private final Iterator<String> bossIterator = BackgroundFromFile.getBossPath().iterator();

    /**
     * Constructor.
     * 
     * @param entityToStolk
     *            entity to Stalk
     * @param ef
     *            factory
     */
    public ScanEntityImpl(final Entity entityToStolk, final EntityFactory ef) {
        this.ef = ef;
        this.entityToStolk = entityToStolk;
    }

    /**
     * Method for taking number of file lines.
     * 
     * @return int number of lines
     * 
     * @throws IOException
     * 
     */
    private int calculateRow() throws IOException {
        int rowCount = 0;

        try {
            final InputStream in = ScanEntityImpl.class.getResourceAsStream(this.fileName);
            this.bufferReader = new BufferedReader(new InputStreamReader(in));
            while (this.bufferReader.readLine() != null) {
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.bufferReader.close();
        return rowCount;

    }

    /**
     * Setter method set the current file.
     * 
     * @param file
     *            file path
     */
    public void setFile(final String file) {
        this.fileName = file;
    }

    /**
     * method for adding entity to a selected room.
     * 
     * @param type
     *            type of entity
     * 
     * @param x
     *            room x position
     * @param y
     *            room y position
     *
     * @param currentRoom
     *            room where to add
     */
    private void scanFind(final char type, final double x, final double y, final Room currentRoom) {
        if (ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.ENEMY1)) {
            currentRoom.addEntity(this.ef.stalkerSpiritEnemy(x, y, this.entityToStolk, currentRoom, true));
        } else if (ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.ENEMY2)) {
            currentRoom.addEntity(this.ef.createFly(x, y, this.entityToStolk, currentRoom));
        } else if (ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.STOPPED)) {
            currentRoom.addEntity(this.ef.createObstacle(x, y));
        } else if ((ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.GUITAR))) {
            currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.CHITARRA));
        } else if ((ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.SUGAR))) {
            currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.ZUCCHERO));
        } else if ((ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.GUN))) {
            currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.PISTOLA));
        } else if ((ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.CIGARETS))) {
            currentRoom.addEntity(this.ef.createPowerUp(x, y, currentRoom, PowerUp.SIGARETTA));
        } else if ((ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.BOSS1))) {
            currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.BOSS_2));
        } else if ((ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.BOSS2))) {
            currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.BOSS_3));
        } else if ((ExistingEntities.getPropieties(String.valueOf(type)).equals(ExistingEntities.BOSS3))) {
            currentRoom.addEntity(this.ef.createBoss(x, y, currentRoom, Optional.of(this.entityToStolk), Boss.BOSS_1));
        }

    }

    @Override
    public void loadEntity(final Room x) {
        try {

            if (!x.getType().equals(RoomType.BOSS)) {
                this.setFile(BackgroundFromFile.getRandomPath(x.getType()));
            }
            InputStream in = ScanEntityImpl.class.getResourceAsStream(this.fileName);
            this.bufferReader = new BufferedReader(new InputStreamReader(in));
            final int column = this.bufferReader.readLine().length();
            final int row = calculateRow();
            final double columnProportion = WEIGHT / column;
            final double rowProportion = HEIGHT / row;
            in = ScanEntityImpl.class.getResourceAsStream(this.fileName);
            this.bufferReader = new BufferedReader(new InputStreamReader(in));
            for (int i = 0; i < row; i++) {
                final String line = this.bufferReader.readLine();
                for (int j = 0; j < column; j++) {
                    if (line != null) {
                        final char currentChar = line.charAt(j);
                        if (currentChar != NOSCAN) {
                            this.scanFind(currentChar, j * columnProportion, i * rowProportion, x);
                        }
                    }

                }
            }
            this.bufferReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadBoss(final Room room) {
        if (this.bossIterator.hasNext()) {
            this.fileName = bossIterator.next();
            this.loadEntity(room);
        }
    }

}
