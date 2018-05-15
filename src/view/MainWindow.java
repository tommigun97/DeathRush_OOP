package view;

import javafx.application.Application;
import javafx.stage.Stage;
import view.menu.MainMenu;

/**
 * Class responsible for creating the main window (Stage) of the application.
 * 
 *
 */
public class MainWindow extends Application {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private final Stage mainWindow = new Stage();

    /**
     * Constructor of the class. It sets up the Stage.
     */
    public MainWindow() {
    }

    /**
     * It starts the JavaFX application.
     */
    @Override
    public void start(final Stage primaryStage) {
        this.mainWindow.setHeight(HEIGHT);
        this.mainWindow.setWidth(WIDTH);
        this.mainWindow.centerOnScreen();
        this.mainWindow.setResizable(false);
        this.mainWindow.setScene(MainMenu.get(this.mainWindow));
        this.mainWindow.show();
    }
}
