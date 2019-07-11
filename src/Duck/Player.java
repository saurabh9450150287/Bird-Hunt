/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Duck;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
/**
 *
 * @author psaur
 */
public class Player{
   //  private boolean pause=true;
    private double x,y;
    public BufferedImage player;
    Player(DuckHunt game,double x,double y){
        this.x=x;
        this.y=y;
        Spritesheet ss=new Spritesheet(game.getSpritesheet());
        player=ss.grabImage(1,1,100,100);
    }
    
    public void tick()
    {
        
    }
    public void render(Graphics g)
    {
        g.drawImage(player,(int)x,(int)y,null);
    }
    public Rectangle getBounds()
    {
    return new Rectangle((int)x,(int)y,100,100);
    }
    public int getX()
            {
                return (int)x;   
            }
    public int getY()
            {
                return (int)y;
            }
    public void setX(double x)
    { // if(pause==false)
        this.x=x;
    }
    public void setY(double y)
    { //if(pause==false)
        this.y=y;
    }

    
}
