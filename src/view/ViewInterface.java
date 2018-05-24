package view;

import java.util.List;

import model.Location;
import utilities.Pair;

/**
 * Interface for the View Class.
 *
 */
public interface ViewInterface {
    /**
     * 
     */
    void startView();

    /**
     * 
     * @param roomBackGround
     *            .
     */

    void drawRoom(String roomBackGround);

    /**
     * 
     * @param entityToDrow
     *            .
     */
    void drawEntities(List<Pair<String, Location>> entityToDrow);

    /**
     * 
     * @param palyerInfo
     *            .
     * @param money
     *            .
     * @param time 
     */

    void updateInfoToDraw(List<Integer> palyerInfo, int money, List<Integer> time);


}
