/*
 * Item class is an abstract class to which other objects will conform
 */
package breakingbad;
import java.awt.Graphics;

public abstract class Item {
    protected int x;
    protected int y;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns x position of Item
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns y position of Item
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets x position of item
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets y position of Item
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

}