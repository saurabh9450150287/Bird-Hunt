/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Duck;

import com.sun.prism.paint.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Saurabh
 */
public class MouseInput implements MouseListener, MouseMotionListener {

    Random rand = new Random();
    Player p;
    Pointer ptr;
    DuckHunt game;
    Graphics g;
   
    public void playSound(String s) 
{
  try
  {
    
    InputStream inputStream = getClass().getResourceAsStream(s);
    if(inputStream==null)System.out.println("ITS NULL");
    AudioStream audioStream;
      audioStream = new AudioStream(inputStream);
    AudioPlayer.player.start(audioStream);
  }
  catch (Exception e)
  {System.out.println("EXCEPTION CAUGHT"+e);
  }
}
    int a,b;
    public MouseInput(Player p, Pointer ptr, DuckHunt game,Graphics g) 
    {   this.g=g;
        this.p = p;
        this.ptr = ptr;
        this.game = game;
    }

    Rectangle getBounds(int x, int y) 
    {
        return new Rectangle(x, y, 32, 32);
    }
   
   
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) 
    {
        if (game.M == game.M.GAME1) 
        {int x=game.score;
            if (!game.getbird().getBounds(false).intersects(getBounds(a=e.getX(), b=e.getY()))&&!game.getbird().getBounds(true).intersects(getBounds(a=e.getX(), b=e.getY())))
                game.bullets_misused++;
                 this.playSound("/mg3.wav");
            if (game.getbird().getBounds(false).intersects(getBounds(a=e.getX(), b=e.getY()))||game.getbird().getBounds(true).intersects(getBounds(e.getX(), e.getY()))) 
            {  
                game.y = true;
                this.playSound("src/ScreamingHawk.mp3");
                game.score += 20;
                game.progress += 20;
                System.out.println("Score="+game.score+"x="+x);
                if (game.score==200||game.score == 400 || game.score == 600 || game.score == 800 || game.score == 1000) 
                {
                    game.health = 200;
                    game.progress = 0;
                    game.bullets_misused=0;
                }
                game.x = rand.nextBoolean();
//System.out.println(game.x);
//game.x=rand.nextBoolean();
                game.getbird().setX(0, true);
                game.getbird().setX(1000, false);
                game.getbird().setY((double) (ThreadLocalRandom.current().nextInt(200, 500)), true);
                game.getbird().setY((double) (ThreadLocalRandom.current().nextInt(200, 500)), false);//bd.getx=117.19999999999732bd.getY=35.83999999999946e.getx=208e.getY=148
            }
            
        } 
        else if (game.M == game.M.GAME2) {
            if (!game.getbird().getBounds(false).intersects(getBounds(a=e.getX(), b=e.getY()))&&!game.getbird().getBounds(true).intersects(getBounds(a=e.getX(), b=e.getY())))
                game.bullets_misused++;
            this.playSound("/mg3.wav");
            
            if (game.getbird().getBounds(false).intersects(getBounds(e.getX(), e.getY()))) 
            {
                game.getbird().setX(0, true);
                game.getbird().setY((double) (ThreadLocalRandom.current().nextInt(100, 300)), true);
                game.score += 20;
                game.progress += 20;
            }
            if (game.score == 200 || game.score == 400 || game.score == 600 || game.score == 800 || game.score == 1000) 
            {
                game.health = 200;
                game.progress = 0;
                game.bullets_misused=0;
            }
           if (game.getbird().getBounds(true).intersects(getBounds(e.getX(), e.getY()))) 
            {
                game.getbird().setX(1000, false);
                game.getbird().setY((double) (ThreadLocalRandom.current().nextInt(100, 300)), false);
                game.score += 20;
                game.progress += 20;
            }
           if (game.score == 200 || game.score == 400 || game.score == 600 || game.score == 800 || game.score == 1000) 
            {
                game.health = 200;
                game.progress = 0;
                game.bullets_misused=0;
            }
       

        } 
        else if (game.M == game.M.RESULT) 
        {
            if (game.resu.exitsButton.intersects(getBounds(e.getX(), e.getY()))) 
            {
                System.exit(1);
            } else if (game.resu.restartButton.intersects(getBounds(e.getX(), e.getY()))) {
                game.M = game.M.MENU;
                game.level = 1;
                game.health = 200;
                game.progress = 0;
                game.score = 0;
            }
        } 
        else {
             game.health = 200;
                game.progress = 0;
                game.bullets_misused=0;
            //  System.out.println("press x="+e.getX()+"press y="+e.getY());
            if (game.g_menu.play1Button.intersects(getBounds(e.getX(), e.getY()))) {
                game.M = game.M.GAME1;
            }
            if (game.g_menu.play2Button.intersects(getBounds(e.getX(), e.getY()))) {
                game.M = game.M.GAME2;
            }
            if (game.g_menu.exitButton.intersects(getBounds(e.getX(), e.getY()))) {
                System.exit(1);
            }
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        //if(DUCK_huNT.M=DUCK_huNT.MODE.GAME)
        double x = e.getX();
        double y = e.getY();

        // Player p=game.getPlayer();
        //if(x<game.Width()+100){}
        if (game.M == game.M.GAME1 || game.M == game.M.GAME2) {
            p.setX(x - 50);
            p.setY(y - 50);
        } else 
        {
            ptr.setX(e.getX());
            ptr.setY(e.getY());
            //if(game.g_menu.playButton.intersects(getBounds(e.getX(),e.getY())))
            //  game.g_menu.playButton.setColor(Color.BLUE);
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
