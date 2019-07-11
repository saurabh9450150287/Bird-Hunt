/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Duck;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/*
@author Saurabh
*/
public class GameMenu {
private DuckHunt game;    
    GameMenu(DuckHunt d)
    {
        this.game=d;
    }
  

    public Rectangle play1Button=new Rectangle(DuckHunt.WIDTH/2-90,250,360,50);
    public Rectangle play2Button=new Rectangle(DuckHunt.WIDTH/2-90,350,360,50);
    public Rectangle exitButton=new Rectangle(DuckHunt.WIDTH/2-90,450,150,50);
    public void render(Graphics g)
      {
        Graphics2D g2d=(Graphics2D) g;
        Font font =new Font("ariel",Font.BOLD,50);
        Font font2 =new Font("ariel",Font.ITALIC,100);
        g.setFont(font);
        g.setColor(Color.white);
        g2d.draw(play1Button);  
        g2d.draw(play2Button);  
        g2d.draw(exitButton);
        String s1="1-DUCK MODE";
        String s3="2-DUCK MODE";String s2="EXIT";         
        String s4="PROFESSOR  HUNT";
        g.drawString(s1,play1Button.x,play1Button.y+50);
        g.drawString(s3,play2Button.x,play2Button.y+50);
        g.drawString(s2,exitButton.x,exitButton.y+50);
        g.setFont(font2);
        g.setColor(Color.ORANGE);
        g.drawString(s4, 150, 100);  
    }
}
