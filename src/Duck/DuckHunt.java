package Duck;
import Database.*;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.io.*;
import javafx.scene.input.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
/*
@author Saurabh
*/
   public class DuckHunt extends Canvas implements Runnable 
      {
    private int flag = 0;
    public static final int WIDTH = 1270;
    public static final int HEIGHT =660;
    public static final int SCALE=1;
    public final String TITLE="PROFESSOR HUNT";
    private boolean running = false;
    private Thread thread;
    //public Thread t1,t2,t3;
    private BufferedImage image = new BufferedImage(WIDTH *SCALE,HEIGHT*SCALE,BufferedImage.TYPE_INT_RGB);
    private Image back_ground,bg2,menu,result,bg3,bg4,bg1,bg5,bg6,bg7;
    private BufferedImage ss;
    private Player p;
    public Game_Setup bd;
    public int health=200;
    public GameMenu g_menu = new GameMenu(this);
    //public Pause pau=new Pause(this);
    private Pointer ptr;
    private MouseInput Mouse;
    public boolean x=false,y=true;
    public int score= 0;
    public short level = 1;
    public int progress = 0;
    public int HighScore,HighScore2;
    public String date,date2;
    public int bullets_misused=0;
    Graphics g;
    int t=0;
    public Result resu;
    //public Connect c=new Connect();
    public enum MODE 
    {
        MENU, GAME1, GAME2, RESULT
    };
    public MODE M = MODE.MENU;
    public void init() throws ClassNotFoundException, SQLException 
      {
        ptr = new Pointer(this);
        resu = new Result(this);
        BufferedImageLoader loader = new BufferedImageLoader();
        try 
        {
          ss = loader.loadImage("src/player.png");
        } 
        catch(IOException e) 
        {
            e.printStackTrace();
        }
        p = new Player(this,100,100);
        Mouse = new MouseInput(p,ptr,this,g);
        this.getHighScore1();
        this.getHighScore2();
        bd = new Game_Setup(this);
        this.addMouseMotionListener(Mouse);
        this.addMouseListener(Mouse);
        
    }
    private synchronized void start()
    {
        if(running) 
        {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    private synchronized void stop() 
    {
        if(!running) 
        {
            return;
        }
        running = false;
        try {
            thread.join();
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(1);
    }
    /**
     * @param args the command line arguments
     */
    int updates = 0;
    int frames = 0;
    @Override
    public void run() {
        try {
            init();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
        }
        long lastTime = System.nanoTime();
        final double amount_of_ticks = 60.0;
        double ns = 1000000000 / amount_of_ticks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) 
        {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 10) 
            {
                try {
                    try {
                        tick();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
                }
                updates++;
                delta--;
            }
            try {
                render();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() throws SQLException, ClassNotFoundException 
    {
        if (M == MODE.GAME1) 
        {
            p.tick();

            if (health != 0&&bullets_misused<=10) 
            {
                bd.tick1();
            } 
            
            else 
      {t=0;
                try 
        {System.out.println("Entered try");
         
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
         LocalDateTime now = LocalDateTime.now();   
         System.out.println("DONE");
         Connect c = new Connect();  
         ResultSet rs;
         if(score>=HighScore)
         {System.out.println("ITS OKAY1");
          c.putData((String)dtf.format(now),score);
         System.out.println("ITS OKAY");
         c.Close();
         this.getHighScore1();
         }
        } 
        catch (ClassNotFoundException ex) 
        {System.out.println("Entered Catch "+ex);
            Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
        }
               this.getHighScore1();
                M = MODE.RESULT;
            }
        } 
        else if (M == MODE.GAME2) 
          {
            p.tick();

            if (health != 0&&bullets_misused<=10) 
            {
                bd.tick2();
            } 
            else 
            { t=0;
                try 
        {System.out.println("Entered try");
         
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
         LocalDateTime now = LocalDateTime.now();  
         System.out.println("DONE");
         Connect c = new Connect();  
         ResultSet rs;
         if(score>=HighScore2)
         {
             c.putData2((String)dtf.format(now),score);
         c.Close();
         HighScore2=score;
         this.getHighScore2();
         }
         
        } 
        catch (ClassNotFoundException ex) 
        {System.out.println("Entered Catch "+ex);
            Logger.getLogger(DuckHunt.class.getName()).log(Level.SEVERE, null, ex);
        }
               this.getHighScore2();
                M = MODE.RESULT;
            }
        }

    }

    private void render() throws ClassNotFoundException, SQLException {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) 
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g;
        g = bs.getDrawGraphics();
        ImageIcon ii = new ImageIcon("src/acm.jpg");
        ImageIcon i2 = new ImageIcon("src/butterfly.gif");
     //   ImageIcon i2 = new ImageIcon("src/b3.gif");
        ImageIcon i3 = new ImageIcon("src/acm.jpg");
        ImageIcon i4 = new ImageIcon("src/acm.jpg");
        ImageIcon i5 = new ImageIcon("src/acm.jpg");
        ImageIcon i6 = new ImageIcon("src/back4.jpg");
        ImageIcon i7 = new ImageIcon("src/cb1.jpg");
        ImageIcon i8 = new ImageIcon("src/back3.jpg");
        ImageIcon i9 = new ImageIcon("src/cb.jpg");
        ImageIcon i10 = new ImageIcon("src/back5.jpg");
        back_ground = ii.getImage();
        bg2 = i2.getImage();
        menu = i3.getImage();
        result = i4.getImage();
        bg3=i5.getImage();
        bg4=i6.getImage();
        bg5=i7.getImage();
        bg6=i8.getImage();
        bg7=i9.getImage();
        bg1=i10.getImage();
        if (M == MODE.GAME1 || M == MODE.GAME2) 
        {
            if (level==1)
            {
                g.drawImage(back_ground, -50, 0, this);
                p.render(g);
                bd.render(g);
            }
            else if(level==2)
            {
                 g.drawImage(bg3, -50, 0, this);
                p.render(g);
                bd.render(g);
            }
            else if(level==3)
            {
                 g.drawImage(bg4, -50, 0, this);
                p.render(g);
                bd.render(g);
            }
             else if(level==4)
             {
                 g.drawImage(bg5, -50, 0, this);
                p.render(g);
                bd.render(g);
            }
            else if(level==5)
            {
                 g.drawImage(bg6, -50, 0, this);
                p.render(g);
                bd.render(g);
            }
            else if(level==6)
            {
                 g.drawImage(bg7, -50, 0, this);
                p.render(g);
                bd.render(g);
            }
            else if(level==6)
            {
                 g.drawImage(bg1, -50, 0, this);
                p.render(g);
                bd.render(g);
            }
            else if(level==7)
            {
                g.drawImage(bg1, -50, 0, this);
                p.render(g);
                bd.render(g);
            }
            
        } 
        else if (M == MODE.MENU) 
        { if(t==0)
            Mouse.playSound("/rename.wav");
        t=1;
            updates = 0;
            g.drawImage(menu, 0, 0, this);
            g_menu.render(g);
            ptr.render(g);
          //  pau.render(g);
        }
        else if (M == MODE.RESULT) 
        {
            g.drawImage(result, 0, 0, this);
            resu.render(g);
            ptr.render(g);
            //pau.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static Cursor customCursor() 
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("download(1).png");
          Point point = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(img, point, "Cursor");
        return cursor;
    }
    public static void main(String[] args) {  
        DuckHunt game = new DuckHunt();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        JFrame frame = new JFrame(game.TITLE);
        
        frame.setCursor(customCursor());
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
    }
    public BufferedImage getSpritesheet() 
    {
        return ss;
    }

    public Player getPlayer() 
    {
        return p;
    }

    public Game_Setup getbird() {
        return bd;
    }

    public double Width() 
    {
        return WIDTH * SCALE;
    }

    public double Height() 
    {
        return HEIGHT * SCALE;
    }
    public void getHighScore1() throws ClassNotFoundException, SQLException
    {
      Connect c=new Connect();
      ResultSet rs;
      rs=c.getHighScores();
      System.out.println("GET");
      date=rs.getString(1);
      System.out.println("GET2");
      HighScore= rs.getInt(2);
      System.out.println("Highscore"+HighScore);
      c.Close();
    }
    public void getHighScore2() throws ClassNotFoundException, SQLException
    {
      Connect c=new Connect();
      ResultSet rs;
      rs=c.getHighScores2();
      date2=rs.getString(1);
      HighScore2= rs.getInt(2);
      System.out.println("Highscore"+HighScore);
      c.Close();
    }
}