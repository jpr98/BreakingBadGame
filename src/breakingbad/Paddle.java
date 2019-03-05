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

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getXSpeed() {
        return xSpeed;
    }
    
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

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.paddle, getX(), getY(), getWidth(), getHeight(), null);
    }

    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
}