/*
 * Brick class manages the objects to be destroyed in the game,
 * sets their sizes and draws different states of them
 */
package breakingbad;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author juanpabloramos
 */
public class Brick extends Item {

    private int lives, width, height;
    private Animation animationBroke;

    public Brick(int x, int y, int width, int height, int lives) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.lives = lives;
        this.animationBroke = new Animation(Assets.brickBroke, 200);
    }

    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
        this.animationBroke.tick();
    }

    /**
     * Draw images for each frame
     */
    @Override
    public void render(Graphics g) {
        //  In case of having ceros lives play explosion sound
        if(lives == 0 ){
            Assets.explosion.play();
        }
        //  This is a delay for the animation run just once
        if(lives <= 0 && lives > -45){
            g.drawImage(animationBroke.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            lives--;
        }
        //  Depending on number of lives the color of the brick changes
        if(lives == 1){
            g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);}
        if(lives == 2){
            g.drawImage(Assets.brick2, getX(), getY(), getWidth(), getHeight(), null);}
        if(lives == 3){
            g.drawImage(Assets.brick3, getX(), getY(), getWidth(), getHeight(), null);}
    }

    /**
     * Creates rectangle object for the brick's size
     */
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Sets object for lives
     * @param lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
    /**
     * Sets object for height
     * @param height
     */
    public void setHeight(int height){
        this.height = height;
    }
    /**
     * Sets object for width
     * @param width
     */
    public void setWidth(int width){
        this.width = width;
    }
    /**
     * Return object for lives
     * @return lives
     */
    public int getLives() {
        return lives;
    }
    /**
     * Return object for height
     * @return height
     */
    public int getHeight(){
        return height;
    }
    /**
     * Return object for width
     * @return width
     */
    public int getWidth(){
        return width;
    }
}
