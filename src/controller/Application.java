package controller;

import view.ViewImpl;
import view.View;

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
        final Controller c = new ControllerImpl();
        final View v = new ViewImpl(c);
        c.setView(v);
        v.startView();

    }

    private Application() {
    }

}
