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
import javafx.stage.Stage;
import model.Area;
import model.Location;
import utilities.Pair;

/**
 * This class create the GameWorld and everything inside the GameWold , it
 * extend the Scene class.
 *
 */
public class GameWorldView extends Scene {

    // Constant for object dimension
    private static final double FONT_SIZE = 15;
    private static final double SPACING = 12;
    private static final double MAX_BUTTON_WIDTH = 280;
    private static final double MAX_BUTTON_HEIGHT = 50;
    private static final double BASIC_BUTTON_WIDTH = 110;
    private static final double BASIC_BUTTON_HEIGHT = 25;
    private static final double BASIC_RES_WIDTH = 1280;
    private static final double BASIC_RES_HEIGHT = 720;
    private static final double IMG_DIM = 20;
    private static final String PAUSE = "Pause";
    private static final String RESUME = "Resume";
    private static double resConstantWidth = 1;
    private static double resConstantHeight = 1;
    private static double inGameWidth = BASIC_RES_WIDTH;
    private static double inGameHeight = BASIC_RES_HEIGHT;

    // Screen Object
    private final Button infoButton = new Button("Info");
    private static final Button PAUSEBUTTON = new Button(PAUSE);
    private final Label hp = new Label();
    private final Label coin = new Label();
    private final Label damage = new Label();
    private final Label mvspeed = new Label();
    private final Label attspeed = new Label();
    private final Label timePlayed = new Label();

    // Stage Object
    private Stage mainStage;
    private final Pane backgroundLayer = new Pane();
    private final Group root = new Group();
    private final ImagesMaker iMaker = new ImagesMaker();
    private final HBox infoBox = new HBox();
    private static boolean mapActive;

    /**
     * Constructor of GameWorldView create the main Scene.
     */
    public GameWorldView() {
        super(new StackPane());

        final HBox buttonGame = new HBox();
        PAUSEBUTTON.setId("menu-buttons");
        PAUSEBUTTON.setDefaultButton(false);
        PAUSEBUTTON.setFocusTraversable(false);
        PAUSEBUTTON.setOnAction(e -> {
            this.pause();
        });
        infoButton.setId("menu-buttons");
        infoButton.setFocusTraversable(false);
        infoButton.setOnAction(e -> GameHelp.display());
        timePlayed.setId("status-bar");
        Image imageHeart = new Image(getClass().getResourceAsStream("/status/heart.gif"), IMG_DIM, IMG_DIM, true, true);
        hp.setGraphic(new ImageView(imageHeart));
        hp.setId("status-bar");
        Image imageCoin = new Image(getClass().getResourceAsStream("/status/coin.gif"), IMG_DIM, IMG_DIM, true, true);
        coin.setGraphic(new ImageView(imageCoin));
        coin.setId("status-bar");
        damage.setId("status-bar");
        attspeed.setId("status-bar");
        mvspeed.setId("status-bar");
        buttonGame.getChildren().addAll(PAUSEBUTTON, infoButton, timePlayed, hp, coin, damage, attspeed, mvspeed);
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
     * setter for map active.
     * 
     * @param b
     *            new mapActive value
     */
    public static void setMapActive(final boolean b) {
        mapActive = b;
    }

    /**
     * Private method. Manages the user inputs.
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
            } else if (event.getCode() == KeyCode.M && !mapActive) {
                this.showMap();
            }
            inputHandler.press(event.getCode());
        });
        this.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            inputHandler.release(event.getCode());
        });
    }

    /**
     * Private method. It's called when the user wants to see the in game map.
     * 
     */
    private void showMap() {
        ViewImpl.getController().pauseGameLoop();
        PAUSEBUTTON.setText(RESUME);
        ViewImpl.getController().mapUpdate();
        ShowMap.print();
    }

    /**
     * Method for do the correct operations when map is closing.
     */
    public static void onCloseMap() {
        InputHandler.getInputHandler().emptyList();
        ViewImpl.getController().resumeGameLoop();
        PAUSEBUTTON.setText(PAUSE);
        mapActive = false;
    }

    /**
     * It draws on screen the entities that are present on the current frame.
     * 
     * @param listEntities
     *            List of the active entities.
     * @param backgroundPath
     *            back ground path image
     */
    public void drawOnScreen(final List<Pair<String, Location>> listEntities, final String backgroundPath) {
        this.backgroundLayer.getChildren().clear();
        printImage(backgroundLayer, backgroundPath, new Location(0.50, 0.50, new Area(1, 1)));
        listEntities.forEach(e -> {
            printImage(this.backgroundLayer, e.getFirst(), e.getSecond());
        });
    }

    /**
     * Private method. It print all images inside the GameWorld.
     * 
     * @param p
     *            Pane to print.
     * @param path
     *            image path.
     * @param loc
     *            location of the image inside the pane.
     */
    private void printImage(final Pane p, final String path, final Location loc) {
        final ImageView image = new ImageView(this.iMaker.getImageFromPath(path));
        image.setPreserveRatio(false);
        image.setFitHeight(loc.getArea().getHeight() * GameWorldView.inGameHeight);
        image.setFitWidth(loc.getArea().getWidth() * GameWorldView.inGameWidth);
        p.getChildren().add(image);
        image.setX((loc.getX() - loc.getArea().getWidth() / 2) * GameWorldView.inGameWidth);
        image.setY((loc.getY() - loc.getArea().getHeight() / 2) * GameWorldView.inGameHeight);
    }

    /**
     * This method update the players status bar info.
     * 
     * @param hp
     *            current player life.
     * @param money
     *            current player money value.
     * @param time
     *            current time passed from the start of the game.
     * @param damage
     *            current player attack damage.
     * @param attackSpeed
     *            current player attack speed value.
     * @param mvSpeed
     *            current player movement speed.
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
     * Private method. When called from the user this stop/resume the GameLoop.
     * 
     */
    private void pause() {
        if (!mapActive) {
            if (ViewImpl.getController().isGameLoopPaused()) {
                InputHandler.getInputHandler().emptyList();
                ViewImpl.getController().resumeGameLoop();
                PAUSEBUTTON.setText(PAUSE);
            } else {
                ViewImpl.getController().pauseGameLoop();
                PAUSEBUTTON.setText(RESUME);
            }
        }
    }

    /**
     * Getter of this Scene.
     * 
     * @param mainWindow
     *            The Stage to place this Scene.
     * @return The current Scene.
     */
    GameWorldView get(final Stage mainWindow) {
        this.mainStage = mainWindow;
        this.mainStage.setWidth(GameWorldView.inGameWidth);
        this.mainStage.setHeight(GameWorldView.inGameHeight);
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
        resConstantWidth = GameWorldView.inGameWidth / BASIC_RES_WIDTH;
        resConstantHeight = GameWorldView.inGameHeight / BASIC_RES_HEIGHT;
    }

    /**
     * Private method. It's called every time a new Game Screen is created. It
     * resizes everything in it according to the current resolution.
     */
    private void resize() {
        this.infoBox.setMinWidth(inGameWidth);
        this.infoBox.setMaxSize((MAX_BUTTON_WIDTH * resConstantWidth), (MAX_BUTTON_HEIGHT * resConstantHeight));
        this.infoBox.setMinHeight((MAX_BUTTON_HEIGHT * resConstantHeight));
        this.infoBox.setSpacing(SPACING * resConstantWidth);
        this.infoButton.setPrefSize(BASIC_BUTTON_WIDTH * resConstantWidth, BASIC_BUTTON_HEIGHT * resConstantHeight);
        this.infoButton.setPrefSize(BASIC_BUTTON_WIDTH * resConstantWidth, BASIC_BUTTON_HEIGHT * resConstantHeight);
        PAUSEBUTTON.setPrefSize(BASIC_BUTTON_WIDTH * resConstantWidth, BASIC_BUTTON_HEIGHT * resConstantHeight);
        this.infoButton.setFont(Font.font(FONT_SIZE * resConstantHeight));
        this.infoButton.setOnMouseEntered(e -> this.infoButton.setFont(Font.font(FONT_SIZE * resConstantHeight)));
        PAUSEBUTTON.setOnMouseEntered(e -> PAUSEBUTTON.setFont(Font.font(FONT_SIZE * resConstantHeight)));
        PAUSEBUTTON.setFont(Font.font(FONT_SIZE * resConstantHeight));
        this.hp.setFont(Font.font(FONT_SIZE * resConstantHeight));
        this.coin.setFont(Font.font(FONT_SIZE * resConstantHeight));
        this.damage.setFont(Font.font(FONT_SIZE * resConstantHeight));
        this.attspeed.setFont(Font.font(FONT_SIZE * resConstantHeight));
        this.mvspeed.setFont(Font.font(FONT_SIZE * resConstantHeight));
        this.timePlayed.setFont(Font.font(FONT_SIZE * resConstantHeight));
    }

    /**
     * Getter of the status of the Stage.
     * 
     * @return True if the current window (Stage) is in full screen mode.
     */
    boolean isFullScreen() {
        return SettingsWindow.getIsFullScreen();
    }

    /**
     * Private method. When called this method send a message to the user to inform
     * about the current action if the user choose "yes" the GameLoop is stopped and
     * the GameWorld destroyed and the user will redirect to the main MainMenu
     * screen.
     */
    private void backMenu() {
        final Boolean answer = MessageBox.display("Alert", "Are you sure you want to go back to the menu?");
        if (answer) {
            ViewImpl.getController().abortGameLoop();
            InputHandler.getInputHandler().emptyList();
            this.mainStage.setScene(MainMenu.get(this.mainStage));
            ViewImpl.getController().changeSong(Sound.SONG.MENUSONG.getPathToSong());
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
        ViewImpl.getController().changeSong(Sound.SONG.MENUSONG.getPathToSong());
    }

    /**
     * Call the YouWin screen.
     */
    public void youWin() {
        this.mainStage.setScene(Won.get(this.mainStage));
        ViewImpl.getController().changeSong(Sound.SONG.MENUSONG.getPathToSong());
    }

}
