package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private static final int SQUARE_SIZE = 40;
    private static final Stage WINDOW = new Stage();

    private ShowMap() {
    };

    /**
     * It shows the box.
     */
    static void print() {
        int[][] map = ViewImpl.getController().getViewMap();
        WINDOW.setResizable(false);
        WINDOW.centerOnScreen();
        WINDOW.setTitle("In Game Map");
        WINDOW.setMinWidth(INFO_WIDTH);
        WINDOW.setMinHeight(INFO_HEIGHT);

        final Text instructionTitle = new Text();
        instructionTitle.setText("In Game Map");
        instructionTitle.setId("credits-info");

        final VBox listInfo = new VBox(10);

        int length = ViewImpl.getController().getYmap();
        int width = ViewImpl.getController().getXmap();

        GridPane grid = new GridPane();

        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {

                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setPrefHeight(SQUARE_SIZE);
                tf.setPrefWidth(SQUARE_SIZE);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);
                tf.setText(String.valueOf(map[y][x]));

                if (Integer.parseInt(tf.getText()) == 1) {
                    tf.setStyle("-fx-background-color: white;");
                    tf.setText(null);
                } else if (Integer.parseInt(tf.getText()) == 0) {
                    tf.setStyle("-fx-background-color: black;");
                    tf.setText(null);
                } else if (Integer.parseInt(tf.getText()) == 2) {
                    tf.setStyle("-fx-background-color: red;");
                    tf.setText(null);
                } else if (Integer.parseInt(tf.getText()) == 3) {
                    tf.setStyle("-fx-background-color: black;");
                    tf.setText(null);
                }

                // Iterate the Index using the loops
                GridPane.setRowIndex(tf, y);
                GridPane.setColumnIndex(tf, x);
                grid.getChildren().add(tf);
            }
        }

        listInfo.getStylesheets().add("style.css");
        listInfo.setAlignment(Pos.CENTER);
        listInfo.setId("whiteTextInfo");
        listInfo.setPadding(new Insets(10));
        listInfo.getChildren().addAll(instructionTitle, grid);

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
        WINDOW.setScene(scene);
        WINDOW.setOnCloseRequest(e -> {
            GameScreen.onCloseMap();
        });
        GameScreen.setMapActive(true);
        WINDOW.showAndWait();
    }

}
