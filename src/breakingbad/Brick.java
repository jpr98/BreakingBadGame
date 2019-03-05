/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    @Override
    public void tick() {
        this.animationBroke.tick();
    }

    @Override
    public void render(Graphics g) {
        if(lives == 0 ){
            Assets.explosion.play();
        }
        if(lives <= 0 && lives > -45){
            g.drawImage(animationBroke.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            lives--;
        }
        if(lives == 1){
            g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);}
        if(lives == 2){
            g.drawImage(Assets.brick2, getX(), getY(), getWidth(), getHeight(), null);}
        if(lives == 3){
            g.drawImage(Assets.brick3, getX(), getY(), getWidth(), getHeight(), null);}
    }

    public void bottom(Ball ball) {
        // if ((ball.getX() >= getX()) && (ball.getX() <= getX() + getWidth() + 1) && (ball.getY() == getY() + getHeight())) {
        //     lives--;
        //     ball.setYDir(ball.getYDir() * -1);
        // }
        //if (ball.getY() < getY() + getHeight() && ){

        //}
        if (ball.getY() == getY() + getHeight()){
            ball.setYDir(ball.getYDir() * -1);
        }
    }
    
    public void top(Ball ball){
        // if ((ball.getX() >= getX()) && (ball.getX() <= getX() + getWidth() + 1) && (ball.getY() == getX())) {
        //     lives --;
        //     ball.setYDir(ball.getYDir() * -1);
        // }
        if (ball.getY() + ball.getHeight() == getY()) {
            lives--;
            ball.setYDir(ball.getYDir() * -1);
        }
    }
    
    public void left(Ball ball) {
        if ((ball.getY() >= getY()) && (ball.getY() <= getY() + getHeight()) && (ball.getX() == getX())) {
            lives--;
            ball.setXDir(ball.getXDir() * -1);
        }
    }
    
    public void right(Ball ball) {
        if ((ball.getY() >= getY()) && (ball.getY() <= getY() + getHeight()) && (ball.getX() == getX() + getWidth())) {
            lives--;
            ball.setXDir(ball.getXDir() * -1);
        }
    }

    public Rectangle getPerimetro(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    //Set methods
    public void setLives(int lives) {
        this.lives = lives;
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
    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}
