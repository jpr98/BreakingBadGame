/*
 * ImageLoader is a helper class to load mages into the game
 */
package breakingbad;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    /**
     * Load an imageit
     * @param path
     * @return BufferedImage
     */
    public static BufferedImage loadImage(String path) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ioe) {
            System.out.println("Error loading image " + path + ioe.toString());
            System.exit(1);
        }
        return bi;
    }
}
