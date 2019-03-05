package breakingbad;

import java.awt.*;

class Ball extends Item {
    
    private boolean onScreen;
    private int width;
    private int height;
    private int xDir;
    private int yDir;
    private Game game;
    private boolean started;

    public Ball(int x, int y, int width, int height, Game game) {
        super(x,y);
        this.game = game;
        setXDir(1);
        setYDir(-1);
        setHeight(height);
        setWidth(width);
        setOnScreen(true);
        setStarted(false);
        
    }

    /** Logic methods */
    @Override
    public void tick() {
        if (game.getKeyManager().up) {
            setStarted(true);
        }
        if (isStarted()) {
            setY(getY()-2);
            setX(getX()-2);
        } else {
            setX(game.getPaddle().getX()+(game.getPaddle().getWidth()/2)-10);
        }
        /** Checking for collisions with walls */
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }

    /** Setter methods */
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

    public void setStarted(boolean started) {
        this.started = started;
    }

    /** Getter methods */
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

    public boolean isStarted() {
        return started;
    }
}