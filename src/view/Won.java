package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class Won extends Scene {

    private static final Won MAINSCENE = new Won();

    private static final double BOTTOM_BOX_SPACING = 20;

    private static final double BUTTON_PADDING = 20;

    private static final double BUTTON_WIDTH = 250;

    private static final double FONT_SIZE = 46;

    private static final double BUTTON_TRANS = 200;

    private static final double BOTTOM_LAYOUT_PADDING = 50;

    private final TextField insertName = new TextField();

    private final Button saveName = new Button("Save Score !");

    private static Stage mainStage;

    /**
     * Constructor for the scene.
     */
    public Won() {
        super(new StackPane());

        final StackPane mainLayout = new StackPane();

        final Text mainTitle = new Text("won");
        mainTitle.setFont(Font.font(null, FontWeight.BOLD, FONT_SIZE));
        mainTitle.setText("You Win");
        mainTitle.setId("title");

        final Label insertNameLabel = new Label("Insert your name:");
        insertNameLabel.setId("insert-name");

        final VBox vboxButton = new VBox(insertNameLabel, insertName, saveName);
        vboxButton.setPrefWidth(BUTTON_WIDTH);
        vboxButton.setAlignment(Pos.CENTER);
        vboxButton.setSpacing(10);
        vboxButton.setPadding(new Insets(BUTTON_PADDING));
        vboxButton.getChildren().forEach(e -> e.setId("menu-buttons"));
        saveName.setOnAction(e -> {
            System.out.println(insertName.getText());
            ViewImpl.getController().setPlayerName(insertName.getText());
            ViewImpl.getController().saveScoreGame();
            GenericBox.display("Success", "Name and Score saved ! ", "Ok");
            mainStage.setScene(MainMenu.get(mainStage));
        });

        final VBox layout = new VBox(10);
        final StackPane bottomLayout = new StackPane();
        final HBox bottomBox = new HBox();

        bottomLayout.setAlignment(Pos.BOTTOM_CENTER);
        bottomLayout.setPadding(new Insets(0, 0, BOTTOM_LAYOUT_PADDING, 0));
        bottomBox.setSpacing(BOTTOM_BOX_SPACING);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setTranslateY(BUTTON_TRANS);
        bottomLayout.getChildren().add(bottomBox);

        layout.getChildren().addAll(mainTitle, vboxButton, bottomBox);
        layout.setSpacing(10);
        layout.setPadding(new Insets(8));
        layout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(layout);
        mainLayout.setId("mainPane");
        this.setRoot(mainLayout);
        this.getStylesheets().add("style.css");
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
        mainStage.setTitle("Death Rush - You Win");
        return MAINSCENE;
    }

}
