package view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Window of the game.
 *
 */
public class MainWindow extends Application {

    private static final double STARTING_WIDTH = 800;
    private static final double STARTING_HEIGHT = 800;
    private final Stage mainWidow = new Stage();

    @Override
    public final void start(final Stage arg0) throws Exception {
        this.mainWidow.setWidth(STARTING_WIDTH);
        this.mainWidow.setHeight(STARTING_HEIGHT);
        this.mainWidow.setResizable(false);
        View.setMainWindow(this.mainWidow);
        this.mainWidow.show();
    }

}
