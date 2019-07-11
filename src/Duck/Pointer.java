/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Duck;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 *
 * @author Saurabh
 */
public class Pointer 
{  private double x,y;
DuckHunt game;
Pointer(DuckHunt game)
{
    this.game=game;
}
     public void setX(int x)
{
    this.x=x;
            
}
public void setY(int y)
{
    this.y=y;
}
public double getX(){
 
    return x;
   
}
public double getY(){
    return y;}
ImageIcon ii=new ImageIcon("src/arrow1.png");
    public Image img=ii.getImage();

    
   public void render(Graphics g)
   {
       g.drawImage(img, (int)this.getX(),(int)this.getY(),null);
   }
}
