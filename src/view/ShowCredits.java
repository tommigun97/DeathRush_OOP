package view;

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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * This class is responsible for showing to the user the High Score screen. It
 * extends the current Scene.
 *
 */
public class ShowCredits extends Scene {

    private static final ShowCredits MAINSCENE = new ShowCredits();

    private static final double FONT_SIZE = 46;

    private static final double BOTTOM_BOX_SPACING = 15;

    private static Stage mainStage;

    /**
     * Constructor for the scene. It sets up the scene.
     */
    public ShowCredits() {
        super(new StackPane());

        Text mainTitle = new Text("Death Rush - Credits");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, FONT_SIZE));
        mainTitle.setText("Credits");
        mainTitle.setId("title");

        final Text label = new Text();
        label.setText("Developed and Created by");
        label.setId("credits-info");
        final Text instructionTitle = new Text();
        instructionTitle.setText("How To Play");
        instructionTitle.setId("credits-info");

        final VBox listInfo = new VBox(10);
        final Label dev1 = new Label("Anis Lico");
        dev1.setId("credits-text");
        final Label dev2 = new Label("Tommaso Ghini");
        dev2.setId("credits-text");
        final Label dev3 = new Label("Simone Del Gatto");
        dev3.setId("credits-text");
        final Label dev4 = new Label("Lorenzo Casini");
        dev4.setId("credits-text");
        final Label teresa = new Label("Special thanks to Teresa Ghini for the in-game images");
        teresa.setId("credits-teresa");
        final Label instructions = new Label();
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setText(
                "W - Move up\nA - Move left\nS - Move down\nD - Move right\nUP/DONW/RIGHT/LEFT - Fire\nP - Pause\nESC - Exit");
        instructions.setId("credits-text");

        listInfo.getStylesheets().add("style.css");
        listInfo.setAlignment(Pos.CENTER);
        listInfo.setPadding(new Insets(10));
        listInfo.getChildren().addAll(instructionTitle, instructions, label, dev1, dev2, dev3, dev4, teresa);

        final VBox layout = new VBox(10);
        final Button back = new Button("Main Menu");
        final StackPane bottomLayout = new StackPane();
        final HBox bottomBox = new HBox();

        back.setId("menu-buttons");

        bottomLayout.setAlignment(Pos.BOTTOM_CENTER);
        bottomLayout.setPadding(new Insets(0, 0, 30, 0));
        bottomBox.setSpacing(BOTTOM_BOX_SPACING);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.getChildren().add(back);
        bottomLayout.getChildren().add(bottomBox);

        final StackPane mainLayout = new StackPane();

        layout.getChildren().addAll(mainTitle, listInfo);
        layout.setSpacing(10);
        layout.setPadding(new Insets(8));
        layout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(layout, bottomLayout);
        mainLayout.setId("mainPane");
        this.setRoot(mainLayout);
        this.getStylesheets().add("style.css");
        back.setOnAction(e -> {
            mainStage.setScene(MainMenu.get(mainStage));
        });
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
        mainStage.setTitle("Death Rush - Credits");
        return MAINSCENE;
    }

}
