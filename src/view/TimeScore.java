package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author ???
 *
 */
public class TimeScore extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../Main.fxml"));
        primaryStage.setTitle("Score View");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
