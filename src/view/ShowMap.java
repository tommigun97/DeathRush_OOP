package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A simple help box with the instructions.
 *
 */
public final class ShowMap {

    private static final double INFO_WIDTH = 520;
    private static final double INFO_HEIGHT = 650;

    private ShowMap() {
    };

    /**
     * It shows the box.
     */
    static void print() {
        final Stage window = new Stage();
        window.setResizable(false);
        window.centerOnScreen();
        window.setTitle("In Game Map");
        window.setMinWidth(INFO_WIDTH);
        window.setMinHeight(INFO_HEIGHT);

        final Text instructionTitle = new Text();
        instructionTitle.setText("In Game Map");
        instructionTitle.setId("credits-info");

        final VBox listInfo = new VBox(10);

        listInfo.getStylesheets().add("style.css");
        listInfo.setAlignment(Pos.CENTER);
        listInfo.setId("whiteTextInfo");
        listInfo.setPadding(new Insets(10));
        listInfo.getChildren().addAll(instructionTitle);

        final VBox layout = new VBox(10);
        final StackPane mainLayout = new StackPane();

        layout.getChildren().addAll(listInfo);
        layout.setSpacing(10);
        layout.setPadding(new Insets(8));
        layout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(layout);
        mainLayout.setId("helpPane");
        final Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
    }

}
