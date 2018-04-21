package view;

import java.util.List;

import model.Location;
import utilities.Pair;

/**
 * Define the duties of generic View.
 *
 */
public interface ViewInterface {

    /**
     * Method for draw Entity.
     * 
     * @param entityToDraw
     *            Entities to draw
     */
    void draw(List<Pair<String, Location>> entityToDraw);

    /**
     * Start view.
     */
    void start();

}
