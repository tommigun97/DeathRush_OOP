package view;

import javafx.scene.Scene;

/**
 * Behavior of the game scenes.
 *
 */
public interface GameScene {

    void resize(double width,  double heigth);
    Scene getScene();
}
