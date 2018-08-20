package view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilities.Pair;

/**
 * This class is responsible for showing to the user the High Score screen. It
 * extends the current Scene.
 *
 */
public class BestScore extends Scene {

    private static final BestScore MAINSCENE = new BestScore();

    private static final double FONT_SIZE = 46;

    private static final double BOTTOM_BOX_SPACING = 15;

    private static final double BOTTOM_LAYOUT_PADDING = 50;

    private static VBox listScores;

    private static Stage mainStage;

    /**
     * Constructor for the scene.
     */
    public BestScore() {
        super(new StackPane());

        final Text mainTitle = new Text("Scores");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, FONT_SIZE));
        mainTitle.setText("Scores");
        mainTitle.setId("title");

        listScores = new VBox();
        listScores.getStylesheets().add("style.css");
        listScores.setAlignment(Pos.CENTER);
        listScores.setPadding(new Insets(10));

        final VBox layout = new VBox(10);
        final Button back = new Button("Main Menu");
        final Button reset = new Button("Reset Scores");
        final StackPane bottomLayout = new StackPane();
        final HBox bottomBox = new HBox();

        reset.setId("menu-buttons");
        back.setId("menu-buttons");

        bottomLayout.setAlignment(Pos.BOTTOM_CENTER);
        bottomLayout.setPadding(new Insets(0, 0, BOTTOM_LAYOUT_PADDING, 0));
        bottomBox.setSpacing(BOTTOM_BOX_SPACING);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.getChildren().addAll(back, reset);
        bottomLayout.getChildren().add(bottomBox);

        final StackPane mainLayout = new StackPane();

        layout.getChildren().addAll(mainTitle, listScores);
        layout.setSpacing(10);
        layout.setPadding(new Insets(8));
        layout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(layout, bottomLayout);
        mainLayout.setId("mainPane");
        this.setRoot(mainLayout);

        this.getStylesheets().add("style.css");
        back.setOnAction(e -> {
            listScores.getChildren().clear();
            mainStage.setScene(MainMenu.get(mainStage));
        });
        reset.setOnAction(e -> {
            this.resetScores();
        });
    }

    /**
     * This method show the Score , if there are no Score ,show a static message.
     * "No Best Score Present ,yet"
     */
    private static void showScores() {

        final List<Pair<Pair<String, Integer>, String>> scoreList = ViewImpl.getController().getCurrentHighScores();
        if (scoreList.isEmpty()) {
            listScores.getChildren().add(new Label("No Best Score Present ,yet"));
            listScores.getChildren().get(0).setId("score-list");
        } else {
            for (int i = 0; i < scoreList.size(); i++) {
                final Label player = new Label();
                player.setId("score-list");
                player.setText(scoreList.get(i).getFirst().getFirst() + " - " + scoreList.get(i).getSecond());
                listScores.getChildren().add(player);
            }
        }
        ViewImpl.getController().getCurrentHighScores();
    }

    /**
     * This method reset the Score list , after user confirmation.
     */
    private void resetScores() {
        final Boolean answer = MessageBox.display("Alert", "Are you sure you want to reset the Score Board?");

        if (answer) {
            if (ViewImpl.getController().emptyScores()) {
                BestScore.listScores.getChildren().clear();
            } else {
                GenericBox.display("Error", "An error occurred while emptying the scores", "Continue");
            }
            mainStage.setScene(BestScore.get(mainStage));
        }

    }

    /**
     * Getter of this Scene.
     * 
     * @param mainWindow
     *            The Stage to place this Scene.
     * @return The current Scene.
     */
    public static Scene get(final Stage mainWindow) {
        showScores();
        mainStage = mainWindow;
        mainStage.setTitle("Death Rush - Best Scores");
        return MAINSCENE;
    }

}
