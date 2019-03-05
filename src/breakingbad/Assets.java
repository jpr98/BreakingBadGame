/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage background;
    public static BufferedImage paddle;
    public static BufferedImage brick;
    public static BufferedImage brick2;
    public static BufferedImage brick3;
    public static BufferedImage ball;

    /**
     * Assets initializer, to be called in Game
     */
    public static void init() {
        background = ImageLoader.loadImage("/breakingbad/images/background.jpeg");
        paddle = ImageLoader.loadImage("/breakingbad/images/paddle.png");
        brick = ImageLoader.loadImage("/breakingbad/images/meth.png");
        brick2 = ImageLoader.loadImage("/breakingbad/images/meth2.png");
        brick3 = ImageLoader.loadImage("/breakingbad/images/meth3.png");
        ball = ImageLoader.loadImage("/breakingbad/images/ball.png");
    }
}
