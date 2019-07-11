package Duck;
import Database.Connect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
/**
 *
 * @author Saurabh
 */
class Game_Setup implements MouseListener {
    private Image ScaledImage(Image img,int w,int h)
    {
        BufferedImage resizedImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2=resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img,0,0,w,h,null);
        g2.dispose();
        return resizedImage;
    }

    Random rand=new Random();
    private Image image1,image2,image3;
  private int xl =1000,xr=1000;
    private double yl=300.0,yr=300.0;
    private int w;
    
    private int h;
     private DuckHunt game=new DuckHunt();
     private int HighScore,HighScore2;//=game.HighScore;
     private String HIGHSCORE,HIGHSCORE2;//=Integer.toString(HighScore);
     private String Date,Date2;
    // Connect c=new Connect();
   public Game_Setup(){}
    public  Game_Setup(DuckHunt ob) 
    {
        game=ob;
        HighScore=game.HighScore;
        HIGHSCORE=Integer.toString(HighScore);
        HighScore2=game.HighScore2;
        HIGHSCORE2=Integer.toString(HighScore2);
        Date=game.date;
        Date2=game.date2;
    }
    
  
    void render(Graphics g) throws ClassNotFoundException, SQLException
    { HIGHSCORE=Integer.toString(game.HighScore);
    Date=game.date;
    HIGHSCORE2=Integer.toString(game.HighScore2);
    Date2=game.date2;
          ImageIcon ii1,ii2,ii3;
    if(game.level==1||game.level==2)
    {
       ii1 = new ImageIcon("src\\birdR.gif");
       image1 = ii1.getImage();
        ii2 = new ImageIcon("src\\bird2L.gif");
       image2=ii2.getImage();
       ii3 = new ImageIcon("src/butterfly.gif");
       image3 = ii3.getImage();
    }
    
     else    if(game.level==3||game.level<=4)
        {
       ii1 = new ImageIcon("C:\\Users\\hspan\\OneDrive\\Documents\\NetBeansProjects\\PROFESSOR_HUNT\\src\\200R.gif");
       image1 = ii1.getImage();
        ii2 = new ImageIcon("C:\\Users\\hspan\\OneDrive\\Documents\\NetBeansProjects\\PROFESSOR_HUNT\\src\\200L.gif");
       image2=ii2.getImage();
    }
     
      else  if(game.level>=5)
        {
       ii1 = new ImageIcon("src/200R.gif");
       image1 = ii1.getImage();
        ii2 = new ImageIcon("src/200L.gif");
       image2=ii2.getImage();
        }
        Font font=new Font("ariel",Font.BOLD,20);
        g.setColor(Color.BLUE);
        g.drawRect(900,590,200,20);
        g.drawRect(900,630,200,20);
        g.setColor(Color.ORANGE);
        
        g.fillRect(900,590, 200, 20);
        g.fillRect(900, 550, 200, 20);
        g.fillRect(900, 630, 200, 20);
        g.setColor(Color.RED);
       // System.out.println(game.health);
        g.fillRect(900,590,200-game.health,20);
        g.fillRect(900,630,game.bullets_misused*20,20);
        g.setColor(Color.BLUE);
        if(game.score<=1200)
        g.fillRect(900,550,game.progress,20);
        else
        {g.setColor(Color.RED);
            g.drawString("final level",910,50);}
       
        g.drawRect(900,550,200,20);
        g.setFont(font);
        g.setColor(Color.RED);
       
        String s="SCORE  "+Integer.toString(game.score);
        g.drawRect(1100,30,150,21);
        
        g.setColor(Color.red);
        g.setColor(Color.WHITE);
        g.fillRect(700,660, 550, 21);
        g.setColor(Color.red);
        if(game.M==game.M.GAME1)
        g.drawString("  HighScore::"+HIGHSCORE+"        Created On::"+Date,710,675);
        if(game.M==game.M.GAME2)
        g.drawString("  HighScore::"+HIGHSCORE2+"        Created On::"+Date2,710,675);
        g.drawRect(1100,50,150,21);
        String s1="LEVEL  "+Integer.toString(game.level);
        String s3="FINAl LEVEL";
        String s4="Progress";
        String s5="Birds Missed";
        g.drawString(s,1100,50);
        g.drawString(s1,1100,70);
         if(game.level==7)
         g.drawString(s3,900,570);
         g.drawString(s4,800,570);
         g.drawString(s5,770,615);
         g.drawString("Misfires",810,650);
        if(xl>game.Width())
            {
                xl=0;game.health-=20;game.y=false;}
        else if(xr<0)
            {xr=1000;game.health-=20;game.y=false;}
       else if(yr<-200){xr=1000;yr=300;game.health-=20;game.y=false;}
       else if (yl<-200){xl=0;yl=300;game.health-=20;game.y=false;}
        if(game.M==game.M.GAME1)
        {
            g.drawImage(image3, 300, 100, null);
        if(game.x)
        { 
        g.drawImage(image1, (int)xl, (int)yl, null);
        g.dispose();
        }
        else  
        { g.drawImage(image2, (int)xr, (int)yr, null);}
        g.dispose();
        }
        else if(game.M==game.M.GAME2)
        {
             
        
             g.drawImage(image1, (int)xl, (int)yl, null);
       
             g.drawImage(image2, (int)xr, (int)yr, null);
        }
        
        
        }
   
    public Rectangle getBounds(boolean b)
    {if(b==true)
        return new Rectangle((int)xr,(int)yr,200,200);
    return new Rectangle((int)xl,(int)yl,200,200);
    }
    public void tick1()
    {
        if(game.x==true)
    {
        if(game.score>=1200){xl+=12;yl-=3;yr-=3;game.level=7;}
        else if(game.score>=1000){xl+=9;yl-=3;yr-=3;game.level=6;}
     else   if(game.score>=800){xl+=7;yl-=2.3;yr-=2.3;game.level=5;}
        else if(game.score>=600){xl+=5;yl-=1.7;yr-=1.7;game.level=4;}
        else if(game.score>=400){xl+=3;yl-=1;yr-=1;game.level=3;}
        else if(game.score>=200){xl+=2;yl-=.5;yr-=.5;game.level=2;}
        else {xl+=1;yl-=.3;yr-=.3;game.level=1;}
    }
        
        else
    {if(game.score>=1200){xr-=12;yl-=3;yr-=3;game.level=7;}
        else if(game.score>=1000){xr-=9;yl-=3;yr-=3;game.level=6;}
     else   if(game.score>=800){xr-=7;yl-=2.3;yr-=2.3;game.level=5;}
        else if(game.score>=600){xr-=5;yl-=1.7;yr-=1.7;game.level=4;}
        else if(game.score>=400){xr-=3;yl-=1;yr-=1;game.level=3;}
        else if(game.score>=200){xr-=2;yl-=.5;yr-=.5;game.level=2;}
        else {xr-=1;yl-=.3;yr-=.3;game.level=1;}
    }
    }
    
   public void tick2()
    {
    
        if(game.score>=1200){xl+=12;xr-=12;yr-=3;yl-=3;game.level=7;}
        else if(game.score>=1000){xl+=9;xr-=9;yl-=3;yr-=3;game.level=6;}
        else if(game.score>=800){xl+=7;xr-=7;yl-=2.3;yr-=2.3;game.level=5;}
        else if(game.score>=600){xl+=5;xr-=5;yl-=1.7;yr-=1.7;game.level=4;}
        else if(game.score>=400){xl+=3;xr-=3;yl-=1;yr-=1;game.level=3;}
        else if(game.score>=200){xl+=2;xr-=2;yl-=.5;yr-=.5;game.level=2;}
        else {xl+=1;xr-=1;yl-=.3;yr-=.3;game.level=1;}

   
    }
    
public void setX(double x,boolean b)
{if(b==true)
            this.xl=(int)x;
            else this.xr=(int)x;
}
public void setY(double y,boolean b)
{if(b)
    this.yl=y;
else
this.yr=y;
}
public double getX(boolean b)
{
   if(b==true)
    return xl;
   else return xr;
}
public double getY(boolean b)
{
    if(b)
    return yl;
    return yr;
}

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
