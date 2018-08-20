package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is responsible for showing to the user the Settings Window.
 */
public class SettingsWindow extends Scene {

    private static final SettingsWindow MAINSCENE = new SettingsWindow();

    private static final double BOTTOM_BOX_SPACING = 20;

    private static final double BUTTON_PADDING = 20;

    private static final double BUTTON_WIDTH = 250;

    private static final double FONT_SIZE = 46;

    private static final double BUTTON_TRANS = 200;

    private static final double LOW_RES_WIDTH = 1024;

    private static final double LOW_RES_HEIGHT = 576;

    private static final double MID_RES_WIDTH = 1280;

    private static final double MID_RES_HEIGHT = 720;

    private static final double HIGH_RES_WIDTH = 1920;

    private static final double HIGH_RES_HEIGHT = 1080;

    private static final double BOTTOM_LAYOUT_PADDING = 50;

    private static Stage mainStage;

    private final Button lowRes = new Button("Low (1024x576)");

    private final Button midRes = new Button("Mid (1280x720)");

    private final Button highRes = new Button("High (1920x1080)");

    private static boolean fullScreen = false;

    /**
     * Constructor for the scene.
     */
    public SettingsWindow() {
        super(new StackPane());

        final StackPane mainLayout = new StackPane();

        final Text mainTitle = new Text("Settings");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, FONT_SIZE));
        mainTitle.setText("Settings");
        mainTitle.setId("title");

        final VBox vboxButton = new VBox(lowRes, midRes, highRes);
        vboxButton.setPrefWidth(BUTTON_WIDTH);
        vboxButton.setAlignment(Pos.CENTER);
        vboxButton.setSpacing(10);
        vboxButton.setPadding(new Insets(BUTTON_PADDING));
        vboxButton.getChildren().forEach(e -> e.setId("menu-buttons"));
        lowRes.setOnAction(e -> {
            changeResolution(LOW_RES_WIDTH, LOW_RES_HEIGHT);
        });
        midRes.setOnAction(e -> {
            changeResolution(MID_RES_WIDTH, MID_RES_HEIGHT);
        });
        highRes.setOnAction(e -> {
            changeResolution(HIGH_RES_WIDTH, HIGH_RES_HEIGHT);
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
     * This method change the resolution of the in-game screen.
     * 
     * @param width
     *            .
     * @param weight
     *            .
     */
    private void changeResolution(final double width, final double height) {

        if (this.checkRes(width, height)) {
            GenericBox.display("Success", "Settings saved", "Ok");
            GameWorldView.setResolution(width, height, SettingsWindow.fullScreen);
        } else {
            GenericBox.display("Error", "Your screen is too small for this resolution!", "Back to settings");
        }

    }

    /**
     * Private method. It checks if the selected resolution is valid for the current
     * screen. If it's not valid it displays an error.
     * 
     * @param currentWidth
     *            The selected value of the width
     * @param currentHeight
     *            The selected value of the height.
     * @return True if the resolution is valid, false otherwise.
     */
    private boolean checkRes(final double currentWidth, final double currentHeight) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final double screenWidth = screenSize.getWidth();
        final double screenHeight = screenSize.getHeight();
        if (currentWidth > screenWidth || currentHeight > screenHeight) {
            return false;
        }
        if (currentWidth == screenWidth && currentHeight == screenHeight) {
            SettingsWindow.fullScreen = true;
        } else {
            SettingsWindow.fullScreen = false;
        }
        return true;
    }

    /**
     * 
     * @return true if the screen is in full size mode.
     */
    public static boolean getIsFullScreen() {
        return fullScreen;
    }

    /**
     * Getter of this Scene.
     * 
     * @param mainWindow
     *            The Stage to place this Scene.
     * @return The current Scene.
     */
    public static Scene get(final Stage mainWindow) {
        mainStage = mainWindow;
        mainStage.setTitle("Death Rush - Settings");
        return MAINSCENE;
    }

}
