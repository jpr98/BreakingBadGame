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
    //  Linked list, numero de bricks, posicion x y y
    private LinkedList<Brick> bricks;
    private int numBricks;
    private int posX;
    private int posY;
    private int lives = 4; 

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
    }

    /**
     * Returns game width
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns game height
     * @return
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
     * Initializer, create game figures and display
     */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        paddle = new Paddle((getWidth()/2)-50, getHeight() - 80, 35, 100, this);
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
        keyManager.tick();
        paddle.tick();
        if(numBricks > 0){
            if(posX < getWidth()-50)
            {
                lives = (int) (Math.random() * 3 + 1);
                System.out.println(lives);
                bricks.add(new Brick(posX, posY, 50, 30, lives));
                posX += 50;
                numBricks--;
            }
            else{
                posY += 35;
                posX = 20;
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
            g.drawImage(Assets.background, 0, 0, width, height, null);
            //  Render de los bricks 
            for(int i = 0; i < bricks.size(); i++)
                {
                    bricks.get(i).render(g);
                }
            paddle.render(g);
            bs.show();
            g.dispose();
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