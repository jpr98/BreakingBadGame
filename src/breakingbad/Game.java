/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Game implements Runnable {
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private KeyManager keyManager;
    private Paddle paddle;
    private Ball ball;
    //  Linked list, numero de bricks, posicion x y y
    private LinkedList<Brick> bricks;
    private int numBricks;
    private int posX;
    private int posY;
    private int frames;
    private int score;
    private int lives;
    private boolean paused;

    /**
     * Constructor
     * @param title
     * @param width
     * @param height
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.running = false;
        numBricks = 104;
        keyManager = new KeyManager();
        bricks = new LinkedList<>();
        posY = 10;
        posX = 20;
        frames = 0;
        score = 0;
        lives = (int)(Math.random() * 3 + 3);
        paused = false;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public int getLives(){
        return lives;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Returns object for key listeners
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Initializer, create game figures and display
     */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        paddle = new Paddle((getWidth()/2)-50, getHeight() - 80, 35, 100, this);
        ball = new Ball((getWidth()/2)-12, getHeight() - 106, 25, 25, this);
        display.getJframe().addKeyListener(keyManager);
    }

    /**
     * Start game
     */
    @Override
    public void run() {
        init();
        int fps = 60;
        double timeTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timeTick;
            lastTime = now;
            if (delta > 0) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    /**
     * Make changes to objects each frame
     */
    private void tick() {
        frames++;
        keyManager.tick();
        if (keyManager.p) {
            paused = !paused;
        }
        if (!paused) {
            paddle.tick();
            ball.tick();
            //  Rebote con el paddle
            if(ball.intersecta(paddle) && frames > 15) {
                // ball hits left side of paddle
                int randX = (int) (Math.random() * 3 + 3);
                if (ball.getX() >= paddle.getX() + paddle.getWidth() / 2) {
                    ball.setXDir(randX * 1);
                    ball.setYDir(ball.getYDir() * -1);
                } 
                // ball hits right side of paddle
                else {
                    ball.setXDir(randX * -1);
                    ball.setYDir(ball.getYDir() * -1);
                }
                Assets.blip.play();
                frames = 0;
            }
            for(int i = 0; i < bricks.size(); i++){
                //  Tick para la animacion
                Brick brick = bricks.get(i);
                brick.tick();
                //  Rebote con bricks
                if (ball.intersectaBrick(brick)) {
                    brick.setLives(brick.getLives()-1);
                    ball.setXDir(ball.getXDir() * -1);
                    ball.setYDir(ball.getYDir() * -1);
                    /*brick.bottom(ball);
                    brick.top(ball);
                    brick.left(ball);
                    brick.right(ball);*/
                }
                if(brick.getLives() == -45) {
                    bricks.remove(i);
                    score += 25;
                }
            }
            if(numBricks > 0){
                if(posX < getWidth()-50)
                {
                    int livesbrick = (int) (Math.random() * 3 + 1);
                    bricks.add(new Brick(posX, posY, 50, 30, livesbrick));
                    posX += 50;
                    numBricks--;
                }
                else{
                    posY += 35;
                    posX = 20;
                }
            }
        }
    }

    /**
     * Draw images each frame
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.clearRect(0,0, width,height);
            if(lives == 0) {
                g.drawImage(Assets.GameOver, 0, 0, width, height, null);
                bricks.clear();
                g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                g.setColor(Color.white);
                g.drawString("Score: " + score, getWidth()/2-60, getHeight()-100);
                bs.show();
                g.dispose();
            }
            else{
                g = bs.getDrawGraphics();
                g.clearRect(0,0, width,height);
                g.drawImage(Assets.background, 0, 0, width, height, null);
                //  Render de los bricks 
                for(int i = 0; i < bricks.size(); i++) {   
                    bricks.get(i).render(g);
                        
                }
                //  Render de las vidas y el score
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.setColor(Color.white);
                g.drawString("Score: " + score + " Lives: " + lives, getWidth()-200, getHeight()-30);
            
                paddle.render(g);
                ball.render(g);
                bs.show();
                g.dispose();
            }
        }
    }

    /**
     * Create new thread for game to run on
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Stop game
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}