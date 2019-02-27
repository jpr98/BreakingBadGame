package breakingbad;

import java.awt.*;

public class Paddle extends Item {
    private int height;
    private int width;
    private int xSpeed;

    public Paddle(int x, int y, int height, int width) {
        super(x, y);
        
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
        
    }

    @Override
    public void render(Graphics g) {
        
    }

    
}