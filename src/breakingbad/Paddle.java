package breakingbad;

import java.awt.*;
import java.awt.Rectangle;

public class Paddle extends Item {
    private int height;
    private int width;
    private int xSpeed;
    private Game game;

    public Paddle(int x, int y, int height, int width, Game game) {
        super(x, y);
        this.height = height;
        this.width = width;
        this.game = game;
        
    }

    /**
     * Sets object for height
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * Sets object for width
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Sets object for xSpeed
     * @param xSpeed
     */
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
    /**
     * Return object for height
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * Return object for width
     * @return width
     */
    public int getWidth() {
        return width;
    }
    /**
     * Return object for xSpeed
     * @return xSpeed
     */
    public int getXSpeed() {
        return xSpeed;
    }

    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
        /** Moving player's paddle */
        if (game.getKeyManager().left) {
            setX(getX()-5);
        }
        if (game.getKeyManager().right ) {
            setX(getX()+5);
        }
        /** Checking for collisions with walls */
        if (getX()+getWidth() > game.getWidth()) {
            setX(game.getWidth()-getWidth());
        } else if (getX() < 0) {
            setX(0);
        }
    }

    /**
     * Renders de proper assets for the paddle
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.paddle, getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Returns a rectangle of the objects perimeter
     * Used for checking collisions
     */
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
}