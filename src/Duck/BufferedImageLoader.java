/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package duck_hunt;

/**
 *
 * @author psaur
 */
package Duck;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class BufferedImageLoader {
    private BufferedImage image;
    public BufferedImage loadImage(String path) throws IOException
    {
        image=  ImageIO.read(new File(path));
        return image;
    }
    
}
