/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;
import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.*;

public class Display {
    private JFrame jframe;
    private Canvas canvas;

    private String title;
    private int width;
    private int height;

    /**
     * Constructor
     * @param title
     * @param width
     * @param height
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    /**
     * Create game view
     */
    public void createDisplay() {
        // create the app window
        jframe = new JFrame(title);

        // set the size of the window
        jframe.setSize(width, height);

        // setting not resizable, visible and possible to close
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        // creating the canvas to paint and setting size
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);

        // adding the canvas to the app window and packing to get the right dimensions
        jframe.add(canvas);
        jframe.pack();
    }

    /**
     * Return game view
     * @return canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Return game window
     * @return jFrame
     */
    public JFrame getJframe() {
        return jframe;
    }
}