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
    public static BufferedImage brickSprites;
    public static BufferedImage brickBroke[];
    public static BufferedImage ball;
    public static SoundClip explosion;
    public static SoundClip blip;

    /**
     * Assets initializer, to be called in Game
     */
    public static void init() {
        background = ImageLoader.loadImage("/breakingbad/images/background.jpeg");
        paddle = ImageLoader.loadImage("/breakingbad/images/paddle.png");
        brick = ImageLoader.loadImage("/breakingbad/images/meth.png");
        brick2 = ImageLoader.loadImage("/breakingbad/images/meth2.png");
        brick3 = ImageLoader.loadImage("/breakingbad/images/meth3.png");
        brickSprites = ImageLoader.loadImage("/breakingbad/images/methSpriteSheet.png");
        ball = ImageLoader.loadImage("/breakingbad/images/ball.png");
        explosion = new SoundClip("/breakingbad/sounds/explosion.wav");
        blip = new SoundClip("/breakingbad/sounds/blip.wav");
        
        SpreadSheet spritesheet = new SpreadSheet(brickSprites);


        brickBroke = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            brickBroke[i] = spritesheet.crop(0, i * 44, 100, 44);
        }


    }
}
