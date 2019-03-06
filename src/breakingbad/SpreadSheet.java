/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbad;

import java.awt.image.BufferedImage;

/**
 *
 * @author juanpabloramos
 */
public class SpreadSheet {
    private BufferedImage sheet; // to store the spritesheet

    /**
     * Create new spritesheet
     * @return sheet the <code>image</code> with the sprites
     */
    public SpreadSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    /**
     * Srop a sprite from the spritesheet
     * @param x an <code>int</code> value with the x coordinate
     * @param y an <code>int</code> value with the y coordinate
     * @param width an <code>int</code> valur with the width of the sprite
     * @param height an <code>int</code> value iwth the height of the sprite
     * @return
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }

}
