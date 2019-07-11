/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Duck;

/**
 *
 * @author Saurabh
 */
import java.awt.image.BufferedImage;
public class Spritesheet {
    private BufferedImage image;
    public Spritesheet(BufferedImage ss)
    {
        this.image=ss;
    }
    public  BufferedImage grabImage (int col,int row,int width,int height)
    {
        BufferedImage img=image.getSubimage((col*32)-32,(row*32)-32,width,height);
        return img;
    }
}
