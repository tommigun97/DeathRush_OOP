package view;

import javafx.stage.Stage;

/**
 * This class manage the exit action.
 *
 */
public final class ExitHandler {

    private static final ExitHandler EXITHANDLER = new ExitHandler();
    //Constructor of the ExitHandler Class, implementing Singleton.
    private ExitHandler() {
    };

    /**
     * Getter of the singleton.
     * 
     * @return The singleton instance of the class.
     */
    static ExitHandler getExitHandler() {
        return ExitHandler.EXITHANDLER;
    }

    /**
     * This class create a MessageBox with yes/no button for handling the request of the user to exit.
     * 
     * @param mainWindow current window in game.
     */
    static void closeGame(final Stage mainWindow) {
        final Boolean answer = MessageBox.display("ATTENTION !", "Do you want to leave the game?");
        if (View.getController().isGameLoopPaused()) {
            if (answer) {
                View.getController().abortGameLoop();
                System.exit(0);
                mainWindow.close();
            } else {
                View.getController().resumeGameLoop();
            }
        }
        if (answer) {
            System.exit(0);
            mainWindow.close();
        }
    }

}
