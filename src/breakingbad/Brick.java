/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

import java.awt.Graphics;

/**
 *
 * @author juanpabloramos
 */
public class Brick extends Item {

    private int lives, hits, width, height;
    private boolean destroyed;

    public Brick(int x, int y, int width, int height, int lives) {
        super(x, y);
        setHits(0);
        setDestroyed(false);
        this.width = width;
        this.height = height;
        this.lives = lives;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        if(lives == 1){
            g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);}
        if(lives == 2){
            g.drawImage(Assets.brick2, getX(), getY(), getWidth(), getHeight(), null);}
        if(lives == 3){
            g.drawImage(Assets.brick3, getX(), getY(), getWidth(), getHeight(), null);}
    }

    public void addHit() {
        hits++;
        if (hits == lives) {
            setDestroyed(true);
        }
    }

    public boolean hitBottom(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width + 1) && (ballY == y + height) && (destroyed == false)) {
            addHit();
            return true;
        }
        return false;
    }

    public boolean hitTop(int ballX, int ballY) {
        if ((ballX >= x) && (ballX <= x + width + 1) && (ballY == y) && (destroyed == false)) {
            addHit();
            return true;
        }
        return false;
    }

    public boolean hitLeft(int ballX, int ballY) {
        if ((ballY >= y) && (ballY <= y + height) && (ballX == x) && (destroyed == false)) {
            addHit();
            return true;
        }
        return false;
    }

    public boolean hitRight(int ballX, int ballY) {
        if ((ballY >= y) && (ballY <= y + height) && (ballX == x + width) && (destroyed == false)) {
            addHit();
            return true;
        }
        return false;
    }

    //Set methods
    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    //Get methods
    public int getLives() {
        return lives;
    }

    public int getHits() {
        return hits;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
}
