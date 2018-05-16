package view;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    private static VBox listScores;
    private static Stage mainStage;

    /**
     * Constructor for the scene. It sets up the scene.
     */
    public BestScore() {
        super(new StackPane());


        Text mainTitle = new Text("Best Scores");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, 46));
        mainTitle.setText("Best Scores");

        final DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DODGERBLUE);
        dropShadow.setRadius(25);
        dropShadow.setSpread(0.25);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        mainTitle.setEffect(dropShadow);

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
        bottomLayout.setPadding(new Insets(0, 0, 100, 0));
        bottomBox.setSpacing(15);
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
     * Private method. If there are no Scores it shows a simple Label with a
     * message, otherwise the list of the highScores.
     */
    private static void showScores() {
       /* final List<Pair<String, Integer>> listScores = View.getController().getCurrentHighScores();
        if (listScores.isEmpty()) {
            listHighScores.getChildren().add(new Label("No HighScores yet"));
        } else {
            for (int i = 0; i < listScores.size(); i++) {
                final Label player = new Label();
                player.setId("whiteText");
                player.setText(listScores.get(i).getFirst() + " - " + listScores.get(i).getSecond());
                listHighScores.getChildren().add(player);
            }
        }*/
    }

    /**
     * Getter of this Scene.
     * 
     * @param mainWindow
     *            The Stage to place this Scene.
     * @return The current Scene.
     */
    static Scene get(final Stage mainWindow) {
        showScores();
        mainStage = mainWindow;
        mainStage.setTitle("Death Rush - Best Scores");
        return MAINSCENE;
    }

    /**
     * Private method. It resets the High Scores after asking a confirmation to
     * the user.
     */
    private void resetScores() {
        final Boolean answer = MessageBox.display("Alert", "Are you sure you want to reset the Score Board?");
        /*if (answer) {
            if (View.getController().emptyHighScores()) {
                BestScore.listHighScores.getChildren().clear();
            } else {
                GenericBox.display(BoxType.ERROR, "Error", "An error occurred while emptying the scores", "Continue");
            }
            mainStage.setScene(BestScore.get(mainStage));
        }*/
    }

}
