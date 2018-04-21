package view;

import java.util.List;

import javafx.stage.Stage;
import model.Location;
import utilities.Pair;

/**
 * @author Simone Del Gatto
 *
 */
public class View implements ViewInterface {

    private static Stage mainWindow;
    private static GameScene currentScene;
    /**
     * Setter for the main window.
     * 
     * @param mainWindow
     *            the value of mainWindow
     */
    public static void setMainWindow(final Stage mainWindow) {
        View.mainWindow = mainWindow;
    }

    /**
     * @param width
     *            the new width of the graphic interface
     * @param height
     *            the new height of the graphic interface
     */
    public static void resize(final double width, final double height) {
        // TODO
    }

    @Override
    public void draw(final List<Pair<String, Location>> entityToDraw) {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

}
