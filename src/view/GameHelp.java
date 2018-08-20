package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * A simple help box with the instructions.
 *
 */
public final class GameHelp {

    private static final double INFO_WIDTH = 520;
    private static final double INFO_HEIGHT = 650;

    private GameHelp() {
    };

    /**
     * It shows the box.
     */
    public static void display() {
        final Stage window = new Stage();
        window.setResizable(false);
        window.centerOnScreen();
        window.setTitle("In Game Help");
        window.setMinWidth(INFO_WIDTH);
        window.setMinHeight(INFO_HEIGHT);

        final Text instructionTitle = new Text();
        instructionTitle.setText("Instructions");
        instructionTitle.setId("credits-info");

        final VBox listInfo = new VBox(10);

        final Label instructions = new Label();
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setText(
                "W - Move up\nA - Move left\nS - Move down\nD - Move right\nUP/DONW/RIGHT/LEFT - Fire\nM - Show Map\nP - Pause\nBACK - Back to Menu\nESC - Exit");
        instructions.setId("credits-text");

        listInfo.getStylesheets().add("style.css");
        listInfo.setAlignment(Pos.CENTER);
        listInfo.setId("whiteTextInfo");
        listInfo.setPadding(new Insets(10));
        listInfo.getChildren().addAll(instructionTitle, instructions);

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
