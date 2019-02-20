/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    public boolean upleft;
    public boolean upright;
    public boolean downleft;
    public boolean downright;

    private boolean keys[];

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void tick() {
        // setting values of pressed keys to directions
        upleft = keys[KeyEvent.VK_Q];
        upright = keys[KeyEvent.VK_E];
        downleft = keys[KeyEvent.VK_A];
        downright = keys[KeyEvent.VK_D];
    }
}