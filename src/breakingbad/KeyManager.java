/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class KeyManager implements KeyListener {
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean p;

    private boolean keys[];

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_P)
            keys[e.getKeyCode()] = true;
            
        if (keys[KeyEvent.VK_P]) {
            keys[e.getKeyCode()] = false;
        } else {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_P) {
            keys[e.getKeyCode()] = false;
        }
    }

    public void tick() {
        // setting values of pressed keys to directions
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        up = keys[KeyEvent.VK_UP];
        p = keys[KeyEvent.VK_P];
    }
}