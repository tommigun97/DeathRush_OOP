package controller;

import view.View;
import view.ViewInterface;

/**
 * Class used to start the application.
 */
public final class Application {

    /**
     * Start a new application.
     * 
     * @param args
     *            .
     * 
     */

    public static void main(final String[] args) {
        final ControllerInterface c = new Controller();
        final ViewInterface v = new View(c);
        c.setView(v);
        v.startView();
    }

    private Application() {
    }

   
}
