package view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * Class that load image and optimize the images just loaded in a map.
 *
 */
public class ImagesMaker {
    private final Map<String, Image> map = new HashMap<>();

    /**
     * @param path
     *            image path
     * @return the image requested
     */
    public Image getImageFromPath(final String path) {
        try {
            if (!this.map.containsKey(path)) {
                this.map.put(path, new Image(ImagesMaker.class.getResourceAsStream("/" + path)));
            }
            return this.map.get(path);
        } catch (final Exception e) {
            System.out.println("Error while loading " + path);
        }
        return null;
    }
}
