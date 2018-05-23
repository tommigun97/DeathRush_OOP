package view;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
 * This class is responsible for showing to the user the Settings Window. It
 * extends the current Scene.
 *
 */
public class SettingsWindow extends Scene {

    private static final SettingsWindow MAINSCENE = new SettingsWindow();

    private static final double FONT_SIZE = 46;

    private static final double BOTTOM_BOX_SPACING = 15;

    private static final double WIDTH = 800;
    private static final double HEIGHT = 800;
    private static final String LOWEST_RES = "1024x576";
    private static final double LOWEST_RES_WIDTH = 1024;
    private static final double LOWEST_RES_HEIGHT = 576;
    private static final double LOW_RES_WIDTH = 1280;
    private static final double LOW_RES_HEIGHT = 720;
    private static final String LOW_RES = "1280x720";
    private static final double MID_RES_WIDTH = 1600;
    private static final double MID_RES_HEIGHT = 900;
    private static final String MID_RES = "1600x900";
    private static final double HIGH_RES_WIDTH = 1920;
    private static final double HIGH_RES_HEIGHT = 1080;
    private static final String HIGH_RES = "1920x1080";
    private static final double WINDOW_SIZE = 800;

    private final List<Pair<String, Pair<Double, Double>>> listResolutions = new LinkedList<>();

    private static boolean fullScreen = false;
    private static SettingsWindow mainScene = new SettingsWindow();
    private static ChoiceBox<String> resolution;
    private static Stage mainStage;

    /**
     * Constructor for the scene. It sets up the scene.
     */
    public SettingsWindow() {
        super(new StackPane());

        final StackPane mainLayout = new StackPane();
        this.listResolutions.add(new Pair<>(LOWEST_RES, new Pair<>(LOWEST_RES_WIDTH, LOWEST_RES_HEIGHT)));
        this.listResolutions.add(new Pair<>(LOW_RES, new Pair<>(LOW_RES_WIDTH, LOW_RES_HEIGHT)));
        this.listResolutions.add(new Pair<>(MID_RES, new Pair<>(MID_RES_WIDTH, MID_RES_HEIGHT)));
        this.listResolutions.add(new Pair<>(HIGH_RES, new Pair<>(HIGH_RES_WIDTH, HIGH_RES_HEIGHT)));

        Text mainTitle = new Text("Settings");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, FONT_SIZE));
        mainTitle.setText("Settings");
        mainTitle.setId("title");

        final VBox options = new VBox();
        options.setPadding(new Insets(30));
        options.setTranslateX((WINDOW_SIZE / 2) - (300 / 2 + 50));
        options.setTranslateY((WINDOW_SIZE / 2) - 150);
        options.setSpacing(50);

        this.setBox();

        final HBox resolutionLayout = new HBox();
        resolutionLayout.setSpacing(10);
        final Text resolutionText = new Text("Game Resolution:");
        resolutionText.setFill(Color.RED);
        this.listResolutions.forEach(e -> {
            resolution.getItems().add(e.getFirst());
        });
        resolution.setValue(LOW_RES);
        resolutionLayout.getChildren().addAll(resolutionText, resolution);

        options.getChildren().addAll(resolutionLayout);
        mainLayout.getChildren().add(options);


        final VBox layout = new VBox(10);
        final Button back = new Button("Main Menu");
        final Button save = new Button("Save");
        final StackPane bottomLayout = new StackPane();
        final HBox bottomBox = new HBox();

        save.setId("menu-buttons");
        back.setId("menu-buttons");

        bottomLayout.setAlignment(Pos.BOTTOM_CENTER);
        bottomLayout.setPadding(new Insets(0, 0, 50, 0));
        bottomBox.setSpacing(BOTTOM_BOX_SPACING);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.getChildren().addAll(back, save);
        bottomLayout.getChildren().addAll(bottomBox);

        

        layout.getChildren().addAll(mainTitle, resolutionText, resolution);
        layout.setSpacing(10);
        layout.setPadding(new Insets(8));
        layout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(layout, bottomLayout);
        mainLayout.setId("mainPane");
        this.setRoot(mainLayout);
        this.getStylesheets().add("style.css");
        back.setOnAction(e -> {
            // listScores.getChildren().clear();
            mainStage.setScene(MainMenu.get(mainStage));
        });
        save.setOnAction(e -> {
            // this.resetScores();
        });
    }

    /**
     * It creates the dropbox menus (thread safe in the static contest).
     */
    private synchronized void setBox() {
        resolution = new ChoiceBox<>();
    }

    /**
     * Getter of this Scene.
     * 
     * @param mainWindow
     *            The Stage to place this Scene.
     * @return The current Scene.
     */
    static Scene get(final Stage mainWindow) {
        mainStage = mainWindow;
        mainStage.setTitle("Death Rush - Best Scores");
        return MAINSCENE;
    }

}
