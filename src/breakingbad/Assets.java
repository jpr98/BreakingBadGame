/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;

    /**
     * Assets initializer, to be called in Game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/background.png");
    }
}
