 package controller;

import view.ViewImpl;
import model.entity.CollisionSupervisor;
import model.entity.CollisionSupervisorImpl;
import model.entity.EntityFactory;
import model.entity.EntityFactoryImpl;
import model.world.GameWorldImpl;
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
        final ControllerInterface c = new Controller();
        final View v = new ViewImpl(c);
        c.setView(v);
        v.startView();
        
       /*CollisionSupervisor cs = new CollisionSupervisorImpl();
       EntityFactory ef = new EntityFactoryImpl(cs);
       GameMap map = new GameMap(ef);
       map.printmappa();*/
    }

    private Application() {
    }

}
