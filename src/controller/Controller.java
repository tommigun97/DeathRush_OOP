package controller;

import java.util.List;

import utilities.Pair;
import view.ViewInterface;

/**
 * Description of Controller.
 * 
 * @author tommi
 * 
 */
public class Controller implements ControllerInterface {

    private static String FILENAME = "ScoreList";

    // private Score sc = new Score(FILENAME);

    @Override
    public final void startGameLoop() {
        // TODO Auto-generated method stub

    }

    /*
     * public void setView(ViewInterface view) { this.view = view; }
     */

    @Override
    public final void pauseGameLoop() {
        // TODO Auto-generated method stub

    }

    @Override
    public final void abortGameLoop() {
        // TODO Auto-generated method stub

    }

    @Override
    public final void resumeGameLoop() {
        // TODO Auto-generated method stub

    }

    @Override
    public final boolean setPlayerName(final String namePlayer) {
        // this.sc.saveOnFile(new Pair<>(namePlayer, model.getTime));
        return false;
    }

    @Override
    public final boolean isGameLoopRunning() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public final boolean isGameLoopPaused() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public final List<Pair<String, Pair<Integer, Integer>>> getMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final void setView(final ViewInterface v) {
        this.setView(v);
    }

}
