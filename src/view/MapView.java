package view;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import model.room.Room;

public class MapView extends Scene {

    private static final double ROOM_X = 0.1;
    private static final double ROOM_Y = 0.1;

    private static final Rectangle ROOM = new Rectangle();
    private static final Rectangle DOOR = new Rectangle();

    private MapView(Room[][] path, int x, int y) {
        super(new StackPane());
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (path[i][j] != null) {

                }
            }
        }
    }

}
