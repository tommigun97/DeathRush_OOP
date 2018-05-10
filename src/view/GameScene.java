package view;

import javafx.scene.Scene;

/**
 * Behavior of the game scenes.
 *
 */
public interface GameScene {
    /**
     * 
     * @param width
     * @param heigth
     */
    void resize(double width, double heigth);

    /**
     * 
     * @return
     */
    Scene getScene();
}
