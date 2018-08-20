package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class create a MessageBox to interact with the user.
 *
 */
public final class GenericBox {

    private static final double MIN_WIDTH = 350;

    private GenericBox() {
    };

    /**
     * Display the MessageBox.
     * 
     * @param title
     *            The title of the MessageBox.
     * @param message
     *            The message inside the MessageBox.
     * @param buttonMessage
     *            The text inside the buttons
     */
    public static void display(final String title, final String message, final String buttonMessage) {
        final Stage window = new Stage();
        window.setResizable(false);
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(MIN_WIDTH);
        final Label label = new Label();
        label.setText(message);
        label.setId("message-text");

        final Button yesButton = new Button("Continue");
        yesButton.setOnAction(e -> {
            window.close();
        });
        yesButton.setId("menu-buttons");

        final VBox layout = new VBox(10);
        layout.setMinWidth(MIN_WIDTH);
        final HBox layoutButton = new HBox(50);

        layoutButton.getChildren().addAll(yesButton);
        layoutButton.setSpacing(10);
        layoutButton.setPadding(new Insets(8));
        layoutButton.setAlignment(Pos.CENTER);

        layout.setMinWidth(MIN_WIDTH);
        layout.getChildren().addAll(label, layoutButton);
        layout.setAlignment(Pos.CENTER);
        final Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.showAndWait();
    }

}
