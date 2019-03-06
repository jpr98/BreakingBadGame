/*
 * Animation class makes the sprites move when a certain action happens
 */
package breakingbad;

import java.awt.image.BufferedImage;

/**
 *
 * @author juanpabloramos
 */
public class Animation {
    private int speed;
    private int index;
    private long lastTime;
    private long timer;
    private BufferedImage[] frames;

    public Animation(BufferedImage[] frames, int speed){
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * Gets the current frame
     */
    public BufferedImage getCurrentFrame(){
        return frames[index];   
    }

    /**
     * Makes changes to objects each frame
     */
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }
}
