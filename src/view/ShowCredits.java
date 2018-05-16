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
 * This class create the credits box.
 */
public final class ShowCredits {

    private static final double INFO_WIDTH = 520;
    private static final double INFO_HEIGHT = 650;

    private ShowCredits() {
    };

    /**
     * Display the credits box.
     */
    static void display() {
        final Stage window = new Stage();
        window.setResizable(false);
        window.centerOnScreen();
        window.setTitle("CREDITS");
        window.setMinWidth(INFO_WIDTH);
        window.setMinHeight(INFO_HEIGHT);

        final Text label = new Text();
        label.setText("Developed and Created by");
        label.setId("titleInfo");
        final Text instructionTitle = new Text();
        instructionTitle.setText("How To Play");
        instructionTitle.setId("titleInfo");
        final Text otherCredits = new Text();
        otherCredits.setText("License");
        otherCredits.setId("titleInfo");

        final VBox listInfo = new VBox(10);
        final Label dev1 = new Label("Anis Lico");
        final Label dev2 = new Label("Tommaso Ghini");
        final Label dev3 = new Label("Simone Del Gatto");
        final Label dev4 = new Label("Lorenzo Casini");

        final Label instructions = new Label();
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setText(
                "W - Move up\nA - Move left\nS - Move down\nD - Move right\nUP/DONW/RIGHT/LEFT - Fire\nP - Pause\nESC - Exit");
        instructions.setId("whiteTextInfo");

        final Label creditsTo = new Label();
        creditsTo.setText("Some License");
        creditsTo.setId("whiteTextInfo");

        listInfo.getStylesheets().add("style.css");
        listInfo.setAlignment(Pos.CENTER);
        listInfo.setId("whiteTextInfo");
        listInfo.setPadding(new Insets(10));
        listInfo.getChildren().addAll(instructionTitle, instructions, label, dev1, dev2, dev3, dev4, otherCredits,
                creditsTo);

        final VBox layout = new VBox(10);
        final StackPane mainLayout = new StackPane();

        layout.getChildren().add(listInfo);
        layout.setSpacing(10);
        layout.setPadding(new Insets(8));
        layout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().add(layout);
        mainLayout.setId("infoPane");
        final Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
    }

}
