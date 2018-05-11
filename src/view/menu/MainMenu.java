package view.menu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author lorenzo casini
 *
 */
public class MainMenu extends Application {

    private static final double WIDTH = 800;
    private static final double HEINGHT = 600;
    private static final double SEP_LEN = 200;
    private static final double R_TITLE_W = 375;
    private static final double R_TITLE_H = 60;
    private static final double FONT_WEIGHT = 50;

    /**
     * This method create the basic component of the MainMenu (Title,BoxMenu).
     * 
     * @return StackPane for the Scene.
     */
    private Parent createContent() {
        StackPane root = new StackPane();

        root.setPrefSize(WIDTH, HEINGHT);

        // add background image

        /*
         * try (InputStream is = Files.newInputStream(Paths.get("image.png"))) {
         * ImageView img = new ImageView(new Image(is)); img.setFitWidth(IMG_BG_WIDTH);
         * img.setFitHeight(IMG_BG_HEIGHT); root.getChildren().add(img); } catch
         * (IOException e) { System.out.println("Couldn't load image"); }
         */

        Title title = new Title("DEATH RUSH");
        title.setAlignment(Pos.CENTER);

        MenuBox vbox = new MenuBox(new MenuItem("NEW GAME"), new MenuItem("BEST SCORE"), new MenuItem("SETTINGS"),
                new MenuItem("CREDIT"), new MenuItem("EXIT"));

        vbox.setAlignment(Pos.BOTTOM_CENTER);

        root.getChildren().addAll(title, vbox);

        return root;

    }

    /**
     * This method create the Scene.
     */
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("VIDEO GAME");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * 
     * This method extends the StackPane and create a personalized title.
     *
     */
    private static class Title extends StackPane {
        Title(final String name) {
            Rectangle bg = new Rectangle(R_TITLE_W, R_TITLE_H);
            bg.setStroke(Color.RED);
            bg.setStrokeWidth(2);
            bg.setFill(null);
            Text text = new Text(name);
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, FONT_WEIGHT));
            getChildren().addAll(bg, text);
        }
    }

    /**
     * 
     * This method extends the VBox anche create the separator for the MenuBox.
     *
     */
    private static class MenuBox extends VBox {

        MenuBox(final MenuItem... items) {
            getChildren().add(createSeperator());

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeperator());
            }
        }

        private Line createSeperator() {
            Line sep = new Line();
            sep.setEndX(SEP_LEN);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }

    }

    /**
     * 
     * This class extends the StackPane and create a simple effect for the MenuBox.
     * 
     * @author lorenzo casini
     */
    private static class MenuItem extends StackPane {
        MenuItem(final String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                    new Stop[] { new Stop(0, Color.DARKBLUE), new Stop(0.1, Color.BLACK), new Stop(0.9, Color.BLACK),
                            new Stop(1, Color.DARKBLUE)

                    });

            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));

            setAlignment(Pos.BOTTOM_CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setWidth(220);
                bg.setFill(gradient);
                text.setFill(Color.WHITE);

            });

            setOnMouseExited(event -> {
                bg.setWidth(200);
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });
            setOnMousePressed(event -> {
                bg.setFill(Color.DARKVIOLET);

            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
            });

        }
    }

    /**
     * 
     * @param args
     */
    public static void main(final String[] args) {

        launch(args);
    }
}
