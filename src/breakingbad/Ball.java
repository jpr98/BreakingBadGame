package breakingbad;

import java.awt.*;
import java.awt.Rectangle;

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
        setXDir(2);
        setYDir(-2);
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

    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean intersecta(Object obj){
        return obj instanceof Paddle && getPerimetro().intersects(((Paddle)obj).getPerimetro());
    }
    
    public boolean intersectaBrick(Object obj){
        if (((Brick)obj).getLives() < -50) {
            return false;
        }
        return obj instanceof Brick && getPerimetro().intersects(((Brick)obj).getPerimetro());
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