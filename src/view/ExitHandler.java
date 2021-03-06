package view;

import javafx.stage.Stage;

/**
 * This class manage the exit action.
 *
 */
public final class ExitHandler {

    private static final ExitHandler EXIT = new ExitHandler();

    // Constructor of the ExitHandler Class, implementing Singleton.
    private ExitHandler() {
    };

    /**
     * Getter of the singleton.
     * 
     * @return The singleton instance of the class.
     */
    public static ExitHandler getExitHandler() {
        return ExitHandler.EXIT;
    }

    /**
     * This class create a MessageBox with yes/no button for handling the request of
     * the user to exit.
     * 
     * @param mainWindow
     *            current window in game.
     */
    public static void closeGame(final Stage mainWindow) {
        final Boolean answer = MessageBox.display("ATTENTION !", "Do you want to leave the game?");
        if (ViewImpl.getController().isGameLoopPaused()) {
            if (answer) {
                ViewImpl.getController().abortGameLoop();
                System.exit(0);
                mainWindow.close();
            } else {
                ViewImpl.getController().resumeGameLoop();
            }
        }
        if (answer) {
            System.exit(0);
            mainWindow.close();
        }
    }

}
