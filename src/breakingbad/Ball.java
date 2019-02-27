package breakingbad;

import java.awt.*;

class Ball extends Item {
    
    private boolean onScreen;
    private int width;
    private int height;
    private int xDir;
    private int yDir;

    public Ball(int x, int y, int width, int height) {
        super(x,y);
        setXDir(1);
        setYDir(-1);
        setHeight(height);
        setWidth(width);
        setOnScreen(true);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setXDir(int xDir) {
	this.xDir = xDir;
    }

    public void setYDir(int yDir) {
	this.yDir = yDir;
    }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getXDir() {
	return xDir;
    }

    public int getYDir() {
	return yDir;
    }

    public boolean isOnScreen() {
	return onScreen;
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {

    }

}