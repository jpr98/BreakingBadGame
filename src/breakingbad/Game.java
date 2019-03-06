/*
 * Game class creates the objects, manages the interactions between them,
 * and pauses, restarts and end the game
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
        keyManager = new KeyManager();
    }

    /**
     * Sets the game lives
     * @param lives
     */
    public void setLives(int lives){
        this.lives = lives;
    }

    /**
     * Returns the game lives
     * @return lives
     */
    public int getLives(){
        return lives;
    }

    /**
     * Return game width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return game height
     * @return height
     */
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

    /**
     * Return object for paddle
     * @return paddle
     */
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
        createBricks();
        frames = 0;
        score = 0;
        lives = (int)(Math.random() * 3 + 3);
        paused = false;
        display.getJframe().addKeyListener(keyManager);
    }

    /**
     * Restarts the game and sets the variables to initial values
     */
    private void restart() {
        Assets.init();
        paddle = new Paddle((getWidth()/2)-50, getHeight() - 80, 35, 100, this);
        ball = new Ball((getWidth()/2)-12, getHeight() - 106, 25, 25, this);
        createBricks();
        frames = 0;
        score = 0;
        lives = (int)(Math.random() * 3 + 3);
        paused = false;
    }

    /**
     * Creates the bricks with random number of lives
     */
    private void createBricks() {
        bricks = new LinkedList<>();
        posY = 10;
        posX = 20;
        numBricks = 104;
        while (numBricks > 0){
            if (posX < getWidth()-50) {
                int livesbrick = (int) (Math.random() * 3 + 1);
                bricks.add(new Brick(posX, posY, 50, 30, livesbrick));
                posX += 50;
                numBricks--;
            } else {
                posY += 35;
                posX = 20;
            }
        }
    }
    /**
     * Starts the game
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
     * Makes changes to objects each frame
     */
    private void tick() {
        frames++;
        keyManager.tick();
        // Pause and restart listeners
        if (keyManager.p) {
            paused = true;
        } else {
            paused = false;
        }
        if (keyManager.r) {
            restart();
        }
        // Logic to the game's objects
        if (!paused) {
            paddle.tick();
            ball.tick();
            //  Paddle interaction
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
            // Brick interaction
            for(int i = 0; i < bricks.size(); i++){
                //  Tick para la animacion
                Brick brick = bricks.get(i);
                brick.tick();
                // Brick collisions
                if (ball.intersectaBrick(brick)) {
                    brick.setLives(brick.getLives()-1);
                    ball.setXDir(ball.getXDir() * -1);
                    ball.setYDir(ball.getYDir() * -1);
                }
                if (brick.getLives() == -45) {
                   bricks.remove(i);
                    score += 25;
                }
            }
        }
    }

    /**
     * Draw images for each frame
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.clearRect(0,0, width,height);
            // Losing display
            if(lives == 0 && bricks.size() > 0) {
                g.drawImage(Assets.GameOver, 0, 0, width, height, null);
                bricks.clear();
                g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                g.setColor(Color.white);
                g.drawString("Score: " + score, getWidth()/2-60, getHeight()-100);
                bs.show();
                g.dispose();
            }
            // Playing display
            else if (bricks.size() > 0){
                g = bs.getDrawGraphics();
                g.clearRect(0,0, width,height);
                g.drawImage(Assets.background, 0, 0, width, height, null);

                //  Rendering lives and score
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g.setColor(Color.white);
                g.drawString("Score: " + score + " Lives: " + lives, getWidth()-200, getHeight()-30);
            
                // Render of objects
                for(int i = 0; i < bricks.size(); i++) {   
                    bricks.get(i).render(g);
                }
                paddle.render(g);
                ball.render(g);
                bs.show();
                g.dispose();
            }
            // Winning display
            else {
                g.drawImage(Assets.youWin, 0, 0, width, height, null);
                bricks.clear();
                g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                g.setColor(Color.white);
                g.drawString("Score: " + score, getWidth()/2-60, getHeight()-100);
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