package view;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Area;
import model.Location;
import utilities.Pair;

/**
 * This class is responsible for handling the game screen and everything in it.
 * It extends Scene.
 *
 */
public class GameScreen extends Scene {

    private static final double BASIC_FONT = 18;
    private static final double BASIC_BUTTON_WIDTH = 110;
    private static final double BASIC_BUTTON_HEIGHT = 25;
    private static final double BASIC_RES_WIDTH = 1280;
    private static final double BASIC_RES_HEIGHT = 720;
    private static final String PAUSE = "Pause";
    private static final String RESUME = "Resume";
    private Stage mainStage;
    private static double resConstantWidth = 1;
    private static double resConstantHeight = 1;
    private static double inGameWidth = BASIC_RES_WIDTH;
    private static double inGameHeight = BASIC_RES_HEIGHT;
    private final Group root = new Group();
    private final Pane backgroundLayer = new Pane();
    private final ImagesMaker iMaker = new ImagesMaker();
    private final HBox infoBox = new HBox();
    private final Button pauseButton = new Button(PAUSE);
    private final Button infoButton = new Button("Info");
    private final Label hp = new Label();
    private final Label coin = new Label();
    private final Label damage = new Label();
    private final Label mvspeed = new Label();
    private final Label attspeed = new Label();
    private final Label timePlayed = new Label();
    private int mapCheck = 0;

    /**
     * Constructor for GameScreen. It sets up the scene.
     */
    public GameScreen() {
        super(new StackPane());

        final HBox buttonGame = new HBox();
        pauseButton.setId("menu-buttons");
        pauseButton.setDefaultButton(false);
        pauseButton.setFocusTraversable(false);
        pauseButton.setOnAction(e -> {
            this.pause();
        });
        infoButton.setId("menu-buttons");
        infoButton.setFocusTraversable(false);
        infoButton.setOnAction(e -> GameHelp.display());
        timePlayed.setId("status-bar");
        Image imageHeart = new Image(getClass().getResourceAsStream("/status/heart.gif"), 20, 20, true, true);
        hp.setGraphic(new ImageView(imageHeart));
        hp.setId("status-bar");
        Image imageCoin = new Image(getClass().getResourceAsStream("/status/coin.gif"), 20, 20, true, true);
        coin.setGraphic(new ImageView(imageCoin));
        coin.setId("status-bar");
        damage.setId("status-bar");
        attspeed.setId("status-bar");
        mvspeed.setId("status-bar");
        buttonGame.getChildren().addAll(pauseButton, infoButton, timePlayed, hp, coin, damage, attspeed, mvspeed);
        buttonGame.setSpacing(10);
        buttonGame.setAlignment(Pos.TOP_CENTER);
        buttonGame.setPadding(new Insets(10, inGameWidth, 0, 0));

        final HBox topLayout = new HBox();
        final VBox topBox = new VBox();
        topBox.getChildren().addAll(buttonGame, topLayout);
        topBox.getStylesheets().add("style.css");
        topLayout.setPadding(new Insets(10, 0, 0, 0));
        topLayout.setSpacing(4);
        topBox.setId("gameScreen");

        this.root.getChildren().addAll(this.backgroundLayer, topBox);
        this.getInput();
        this.resize();
        this.setRoot(this.root);
    }

    /**
     * Private method responsible for getting the inputs from the user.
     */
    private void getInput() {
        final InputHandler inputHandler = InputHandler.getInputHandler();
        inputHandler.emptyList();
        this.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                ViewImpl.getController().pauseGameLoop();
                this.backMenu();
            } else if (event.getCode() == KeyCode.P) {
                this.pause();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                ViewImpl.getController().pauseGameLoop();
                ExitHandler.getExitHandler();
                ExitHandler.closeGame(this.mainStage);
            } else if (event.getCode() == KeyCode.M) {
            	
                this.showMap();
            }
            inputHandler.press(event.getCode());
        });
        this.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            inputHandler.release(event.getCode());
        });
    }

    /**
     * Private method. It's called when the user wants to pause or resume the game.
     * 
     */
    private void pause() {
        if (ViewImpl.getController().isGameLoopPaused()) {
            InputHandler.getInputHandler().emptyList();
            ViewImpl.getController().resumeGameLoop();
            this.pauseButton.setText(PAUSE);
        } else {
            ViewImpl.getController().pauseGameLoop();
            this.pauseButton.setText(RESUME);
        }

    }

    /**
     * Private method. It's called when the user wants to see the in game map.
     * 
     */
    private void showMap() {
       this.pause();
       ViewImpl.getController().mapUpdate();
       ShowMap.print();
    }

    /**
     * It draws on screen the entities that are present on the current frame.
     * 
     * @param listEntities
     *            List of the active entities.
     */
    void drawOnScreen(final List<Pair<String, Location>> listEntities, final String backgroundPath) {
        this.backgroundLayer.getChildren().clear();
        printImage(backgroundLayer, backgroundPath, new Location(0.50, 0.50, new Area(1, 1)));
        listEntities.forEach(e -> {
            printImage(this.backgroundLayer, e.getFirst(), e.getSecond());
        });

        /*
         * printImage(backgroundLayer, "room/background.png", new Location(0.50, 0.50,
         * new Area(1, 1))); printImage(backgroundLayer, "room/door_open_N.png", new
         * Location(0.50, 0.03, new Area(0.10, 0.10))); printImage(backgroundLayer,
         * "room/door_open_S.png   ", new Location(0.50, 0.97, new Area(0.10, 0.10)));
         * printImage(backgroundLayer, "room/door_open_E.png", new Location(0.99, 0.50,
         * new Area(0.07, 0.15))); printImage(backgroundLayer, "room/door_open_W.png",
         * new Location(0.01, 0.50, new Area(0.07, 0.15))); printImage(backgroundLayer,
         * "room/background.png", new Location(0.50, 0.50, new Area(1, 1)));
         * printImage(backgroundLayer, "room/door_closed_N.png", new Location(0.50,
         * 0.03, new Area(0.10, 0.10))); printImage(backgroundLayer,
         * "room/door_closed_S.png", new Location(0.50, 0.97, new Area(0.10, 0.10)));
         * printImage(backgroundLayer, "room/door_closed_E.png", new Location(0.99,
         * 0.50, new Area(0.07, 0.15))); printImage(backgroundLayer,
         * "room/door_closed_W.png", new Location(0.01, 0.50, new Area(0.07, 0.15)));
         */

    }

    private void printImage(final Pane l, final String path, final Location loc) {
        final ImageView image = new ImageView(this.iMaker.getImageFromPath(path));
        image.setPreserveRatio(false);
        image.setFitHeight(loc.getArea().getHeight() * GameScreen.inGameHeight);
        image.setFitWidth(loc.getArea().getWidth() * GameScreen.inGameWidth);
        l.getChildren().add(image);
        image.setX((loc.getX() - loc.getArea().getWidth() / 2) * GameScreen.inGameWidth);
        image.setY((loc.getY() - loc.getArea().getHeight() / 2) * GameScreen.inGameHeight);
    }

    /**
     * It updates the view with the current information about the player. If the
     * game is over (hp <= 0) it displays the Game Over screen.
     * 
     * 
     * @param hpValue
     *            Current hp of the player.
     * @param shieldsValue
     *            Current level status of the shields.
     * @param scoreValue
     *            Current score.
     */
    void updateInfo(final int hp, final int money, final String time, final String damage, final String attackSpeed,
            final String mvSpeed) {

        this.timePlayed.setText(time);
        this.hp.setText(String.valueOf(hp));
        this.coin.setText(String.valueOf(money));
        this.damage.setText("Damage:" + damage);
        this.attspeed.setText("Attack Speed:" + attackSpeed);
        this.mvspeed.setText("Movement Speed:" + mvSpeed);
    }

    /**
     * Getter of this Scene.
     * 
     * @param mainWindow
     *            The Stage to place this Scene.
     * @return The current Scene.
     */
    GameScreen get(final Stage mainWindow) {
        this.mainStage = mainWindow;
        this.mainStage.setWidth(GameScreen.inGameWidth);
        this.mainStage.setHeight(GameScreen.inGameHeight);
        this.mainStage.centerOnScreen();
        this.mainStage.setTitle("Death Rush - v0.1");
        this.mainStage.setFullScreen(SettingsWindow.getIsFullScreen());
        return this;
    }

    /**
     * Setter for the current resolution.
     * 
     * @param width
     *            Current width of the game screen
     * @param height
     *            Current height of the game screen
     * @param fullScreen
     *            The mode of the screen.
     */
    static synchronized void setResolution(final double width, final double height, final boolean fullScreen) {
        inGameWidth = width;
        inGameHeight = height;
        resConstantWidth = GameScreen.inGameWidth / BASIC_RES_WIDTH;
        resConstantHeight = GameScreen.inGameHeight / BASIC_RES_HEIGHT;
    }

    /**
     * Private method. It's called every time a new Game Screen is created. It
     * resizes everything in it according to the current resolution.
     */
    private void resize() {
        this.infoBox.setMinWidth(inGameWidth);
        this.infoBox.setMaxSize((280 * resConstantWidth), (50 * resConstantHeight));
        this.infoBox.setMinHeight((50 * resConstantHeight));
        this.infoBox.setSpacing(12 * resConstantWidth);
        this.infoButton.setPrefSize(BASIC_BUTTON_WIDTH * resConstantWidth, BASIC_BUTTON_HEIGHT * resConstantHeight);
        this.infoButton.setPrefSize(BASIC_BUTTON_WIDTH * resConstantWidth, BASIC_BUTTON_HEIGHT * resConstantHeight);
        this.pauseButton.setPrefSize(BASIC_BUTTON_WIDTH * resConstantWidth, BASIC_BUTTON_HEIGHT * resConstantHeight);
        this.infoButton.setFont(Font.font(15 * resConstantHeight));
        this.infoButton.setOnMouseEntered(e -> this.infoButton.setFont(Font.font(15 * resConstantHeight)));
        this.pauseButton.setOnMouseEntered(e -> this.pauseButton.setFont(Font.font(15 * resConstantHeight)));
        this.pauseButton.setFont(Font.font(15 * resConstantHeight));
        this.hp.setFont(Font.font(15 * resConstantHeight));
        this.coin.setFont(Font.font(15 * resConstantHeight));
        this.damage.setFont(Font.font(15 * resConstantHeight));
        this.attspeed.setFont(Font.font(15 * resConstantHeight));
        this.mvspeed.setFont(Font.font(15 * resConstantHeight));
        this.timePlayed.setFont(Font.font(15 * resConstantHeight));
    }

    /**
     * Getter of the status of the Stage.
     * 
     * @return True if the current window (Stage) is in full screen mode
     */
    boolean isFullScreen() {
        return SettingsWindow.getIsFullScreen();
    }

    /**
     * Private method. Called when the user wants to go back to the main menu while
     * the game is running. It prompts a dialog box where the user can choose to go
     * back to the menu or not.
     */
    private void backMenu() {
        final Boolean answer = MessageBox.display("Alert", "Are you sure you want to go back to the menu?");
        if (answer) {
            ViewImpl.getController().abortGameLoop();
            InputHandler.getInputHandler().emptyList();
            this.mainStage.setScene(MainMenu.get(this.mainStage));
        } else {
            InputHandler.getInputHandler().emptyList();
            ViewImpl.getController().resumeGameLoop();
        }
    }

    /**
     * Call the GameOver screen.
     */
    public void gameOver() {
        this.mainStage.setScene(GameOver.get(this.mainStage));
    }

    /**
     * Call the You Win screen.
     */
    public void youWin() {
        this.mainStage.setScene(Won.get(this.mainStage));
    }

}
