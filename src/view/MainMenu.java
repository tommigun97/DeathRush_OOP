package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * 
 * 
 *
 */
public final class MainMenu extends Scene {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final MainMenu MAINSCENE = new MainMenu();
    private static final double BUTTON_WIDTH = 230;
    private static final double BUTTON_PADDING = 10;
    private static final double FONT_SIZE = 46;
    private static Stage mainStage;
    private final Button newGame = new Button("New Game");
    private final Button bestScores = new Button("Best Score");
    private final Button settings = new Button("Settings");
    private final Button credits = new Button("Credits");
    private final Button exit = new Button("Exit");

    private MainMenu() {
        super(new StackPane(), WIDTH, HEIGHT);

        Text mainTitle = new Text("Death Rush");
        mainTitle.setText("Death Rush");
        mainTitle.setId("title");

        final VBox vbox = new VBox(newGame, bestScores, settings, credits, exit);
        vbox.setPrefWidth(BUTTON_WIDTH);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(BUTTON_PADDING));
        // buttons id for style.css
        this.newGame.setMinWidth(vbox.getPrefWidth());
        this.newGame.setId("menu-buttons");
        this.newGame.setOnAction(e -> {
            InputHandler.getInputHandler().emptyList();
            //View.getController().startGameLoop();
            final GameScreen gameScreen = new GameScreen();
            View.setGameScreen(gameScreen);
            mainStage.setScene(gameScreen.get(mainStage));
            mainStage.setFullScreen(gameScreen.isFullScreen());
        });
        this.bestScores.setMinWidth(vbox.getPrefWidth());
        this.bestScores.setId("menu-buttons");
        this.bestScores.setOnAction(e -> {
            mainStage.setScene(BestScore.get(MainMenu.mainStage));
        });
        this.settings.setMinWidth(vbox.getPrefWidth());
        this.settings.setId("menu-buttons");
        this.settings.setOnAction(e -> {
            mainStage.setScene(SettingsWindow.get(MainMenu.mainStage));
            // SettingsWindow.update();
        });
        this.credits.setMinWidth(vbox.getPrefWidth());
        this.credits.setId("menu-buttons");
        this.credits.setOnAction(e -> {
            mainStage.setScene(ShowCredits.get(MainMenu.mainStage));
        });
        this.exit.setMinWidth(vbox.getPrefWidth());
        this.exit.setId("menu-buttons");
        ExitHandler.getExitHandler();
        this.exit.setOnAction(e -> ExitHandler.closeGame(mainStage));
        final StackPane layout = new StackPane();
        layout.getChildren().addAll(mainTitle, vbox);
        layout.setId("mainPane");
        this.setRoot(layout);
        this.getStylesheets().add("style.css");
    }

    /**
     * Getter of this Scene.
     * 
     * @param mainWindow
     *            The Stage to place this Scene.
     * @return The current Scene.
     */
    public static MainMenu get(final Stage mainWindow) {
        mainStage = mainWindow;
        mainStage.setFullScreen(false);
        mainStage.setWidth(WIDTH);
        mainStage.setHeight(HEIGHT);
        mainStage.centerOnScreen();
        mainStage.setTitle("DEATH RUSH  v0.1");
        return MAINSCENE;
    }

}
