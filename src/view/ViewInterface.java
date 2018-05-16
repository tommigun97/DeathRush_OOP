package view;

import java.util.List;

import model.Location;
import utilities.Pair;

/**
 * Interface for the View Class.
 *
 */
public interface ViewInterface {

    void startView();

	void drawRoom(String roomBackGround);

	void drawEntities(List<Pair<String, Location>> entityToDrow);

	void updateInfoToDraw(List<Integer> palyerInfo, int money);

}
