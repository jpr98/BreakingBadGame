/**
 * Ball class manages the projectile of the game and its interactions
 */
package breakingbad;

import java.awt.*;
import java.awt.Rectangle;

class Ball extends Item {
    
    private int width;
    private int height;
    private int xDir;
    private int yDir;
    private Game game;
    private boolean started;
    
    /**
     * Constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public Ball(int x, int y, int width, int height, Game game) {
        super(x,y);
        this.game = game;
        setXDir(2);
        setYDir(-2);
        setHeight(height);
        setWidth(width);
        setStarted(false);
        
    }

    /**
     * Makes changes to objects each frame
     */
    @Override
    public void tick() {
        if (game.getKeyManager().up) {
            setStarted(true);
        }
        if (isStarted()) {
            setY(getY()+yDir);
            setX(getX()+xDir);
        } else {
            setX(game.getPaddle().getX()+(game.getPaddle().getWidth()/2)-10);
            setY(game.getHeight() - 106);
        }
        /** Checking for collisions with walls */
        if (getX()+getWidth() > game.getWidth()) {
            xDir *= -1;
        } else if (getX() < 0) {
            xDir *= -1;
        }
        if (getY() < 0) {
            yDir *= -1;
        } else if (getY()+getHeight() > game.getHeight()) {
            yDir *= -1;
            //  Se quita una vida si toca la pared inferior
            game.setLives(game.getLives() -1);
            setStarted(false);
        }
    }

    /**
     * Creates a Rectangle object the size of the ball
     * @return rectangle
     */
    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Looks for an intersection between ball and Paddle object
     * @return boolean
     */
    public boolean intersecta(Object obj){
        return obj instanceof Paddle && getPerimetro().intersects(((Paddle)obj).getPerimetro());
    }

    /**
     * Looks for an intersection between ball and Brick object
     * @return boolean
     */
    public boolean intersectaBrick(Object obj){
        if (((Brick)obj).getLives() < -50) {
            return false;
        }
        return obj instanceof Brick && getPerimetro().intersects(((Brick)obj).getPerimetro());
    }

    /**
     * Draw images for each frame
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Sets the ball's height
     * @return height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the ball's width
     * @return width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the ball's x direction
     * @return xDir
     */
    public void setXDir(int xDir) {
	    this.xDir = xDir;
    }

    /**
     * Sets the ball's y direction
     * @return yDir
     */
    public void setYDir(int yDir) {
	    this.yDir = yDir;
    }

    /**
     * Sets the ball start flag
     * @return started
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * Gets the ball's heigth
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the ball's width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the ball's x direction
     * @return xDir
     */
    public int getXDir() {
	    return xDir;
    }

    /**
     * Gets the ball's y direction
     * @return yDir
     */
    public int getYDir() {
	    return yDir;
    }

    /**
     * Gets the ball start flag status
     * @return started
     */
    public boolean isStarted() {
        return started;
    }
}