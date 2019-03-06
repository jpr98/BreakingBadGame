/*
 * Assets class loads images and sound into the game
 */
package breakingbad;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {
    //  IMAGES
    public static BufferedImage background;
    public static BufferedImage paddle;
    public static BufferedImage brick;
    public static BufferedImage brick2;
    public static BufferedImage brick3;
    public static BufferedImage brickSprites;
    public static BufferedImage brickBroke[];
    public static BufferedImage ball;
    public static BufferedImage GameOver;
    public static BufferedImage youWin;
    //  SOUNDS
    public static SoundClip explosion;
    public static SoundClip blip;

    /**
     * Assets initializer, to be called in Game
     */
    public static void init() {
        //  IMAGES
        background = ImageLoader.loadImage("/breakingbad/images/background.jpeg");
        paddle = ImageLoader.loadImage("/breakingbad/images/paddle.png");
        brick = ImageLoader.loadImage("/breakingbad/images/meth.png");
        brick2 = ImageLoader.loadImage("/breakingbad/images/meth2.png");
        brick3 = ImageLoader.loadImage("/breakingbad/images/meth3.png");
        brickSprites = ImageLoader.loadImage("/breakingbad/images/methSpriteSheet.png");
        ball = ImageLoader.loadImage("/breakingbad/images/ball.png");
        GameOver = ImageLoader.loadImage("/breakingbad/images/GameOver.jpg");
        youWin = ImageLoader.loadImage("/breakingbad/images/youWin.png");
        //  SOUNDS
        explosion = new SoundClip("/breakingbad/sounds/explosion.wav");
        blip = new SoundClip("/breakingbad/sounds/blip.wav");
        //  SPRITESHEET
        SpreadSheet spritesheet = new SpreadSheet(brickSprites);

        //  Here, the spritesheet gets cropped for the animation
        brickBroke = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            brickBroke[i] = spritesheet.crop(0, i * 44, 100, 44);
        }


    }
}
