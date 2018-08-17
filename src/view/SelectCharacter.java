package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.entity.Player;

/**
 * This class is responsible for showing to the user the Select Character
 * Window.
 */
public class SelectCharacter extends Scene {

    private static final SelectCharacter MAINSCENE = new SelectCharacter();

    private static final int BOTTOM_BOX_SPACING = 20;

    private static final int BUTTON_PADDING = 20;

    private static final int BUTTON_WIDTH = 250;

    private static final int FONT_SIZE = 46;

    private static final int BUTTON_TRANS = 60;

    private static final int BOTTOM_LAYOUT_PADDING = 50;

    private static final int MENU_ICON_SIZE = 60;

    private static Stage mainStage;

    private final Button pgSimo = new Button("Simo");

    private final Button pgCaso = new Button("Caso");

    private final Button pgTommi = new Button("Tommi");

    private final Button pgAnis = new Button("Anis");

    /**
     * Constructor for the scene.
     */
    public SelectCharacter() {
        super(new StackPane());

        final StackPane mainLayout = new StackPane();

        Text mainTitle = new Text("Select your Character");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, FONT_SIZE));
        mainTitle.setText("Select your Character");
        mainTitle.setId("title-character");

        Image imageSimo = new Image(getClass().getResourceAsStream("/simo/1.png"), MENU_ICON_SIZE, MENU_ICON_SIZE, true,
                true);
        pgSimo.setGraphic(new ImageView(imageSimo));

        Image imageCaso = new Image(getClass().getResourceAsStream("/kaso/1.png"), MENU_ICON_SIZE, MENU_ICON_SIZE, true,
                true);
        pgCaso.setGraphic(new ImageView(imageCaso));

        Image imageTommi = new Image(getClass().getResourceAsStream("/tommi/1.png"), MENU_ICON_SIZE, MENU_ICON_SIZE,
                true, true);
        pgTommi.setGraphic(new ImageView(imageTommi));

        Image imageAnis = new Image(getClass().getResourceAsStream("/anis/1.png"), MENU_ICON_SIZE, MENU_ICON_SIZE, true,
                true);
        pgAnis.setGraphic(new ImageView(imageAnis));

        final VBox vboxButton = new VBox(pgSimo, pgCaso, pgTommi, pgAnis);
        vboxButton.setPrefWidth(BUTTON_WIDTH);
        vboxButton.setAlignment(Pos.CENTER);
        vboxButton.setSpacing(10);
        vboxButton.setPadding(new Insets(BUTTON_PADDING));
        vboxButton.getChildren().forEach(e -> e.setId("menu-buttons"));
        pgSimo.setOnAction(e -> {
            initGame(Player.SIMO);
        });
        pgCaso.setOnAction(e -> {
            initGame(Player.KASO);
        });
        pgTommi.setOnAction(e -> {
            initGame(Player.TOMMI);
        });
        pgAnis.setOnAction(e -> {
            initGame(Player.ANIS);
        });

        final VBox layout = new VBox(10);
        final Button back = new Button("Main Menu");
        final StackPane bottomLayout = new StackPane();
        final HBox bottomBox = new HBox();
        back.setId("menu-buttons");

        bottomLayout.setAlignment(Pos.BOTTOM_CENTER);
        bottomLayout.setPadding(new Insets(0, 0, BOTTOM_LAYOUT_PADDING, 0));
        bottomBox.setSpacing(BOTTOM_BOX_SPACING);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setTranslateY(BUTTON_TRANS);
        bottomBox.getChildren().add(back);
        bottomLayout.getChildren().add(bottomBox);

        layout.getChildren().addAll(mainTitle, vboxButton, bottomBox);
        layout.setSpacing(10);
        layout.setPadding(new Insets(8));
        layout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(layout);
        mainLayout.setId("mainPane");
        this.setRoot(mainLayout);
        this.getStylesheets().add("style.css");
        back.setOnAction(e -> {
            mainStage.setScene(MainMenu.get(mainStage));
        });
    }

    /**
     * Private method. When called initiate the whole GameWorld.
     */
    private void initGame(final Player pg) {
        final GameWorldView gameScreen = new GameWorldView();
        InputHandler.getInputHandler().emptyList();
        ViewImpl.getController().selectPlayer(pg);
        ViewImpl.getController().startGameLoop();
        ViewImpl.setGameScreen(gameScreen);
        mainStage.setScene(gameScreen.get(mainStage));
        mainStage.setFullScreen(gameScreen.isFullScreen());
        ViewImpl.getController().changeSong(Sound.SONG.GAMESONG.getPathToSong());

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
        mainStage.setTitle("Death Rush - Select Character");
        return MAINSCENE;
    }

}
