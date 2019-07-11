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

/**
 *
 * @author Saurabh
 */
public class Result {

    private DuckHunt game;
    //  Object exits;

    Result(DuckHunt game) {
        this.game = game;
    }

    public Rectangle exitsButton = new Rectangle(DuckHunt.WIDTH / 2 + 100, 450, 250, 50);
    public Rectangle restartButton = new Rectangle(DuckHunt.WIDTH / 2 + 100, 510, 250, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("ariel", Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.white);
        g2d.draw(exitsButton);
        g2d.draw(restartButton);
        String score = "YOUR SCORE  " + game.score;
        String ducks_hunted = "DUCK(s) HUNTED  " + game.score / 20;
        g.drawString(score, 500, 300);
        g.drawString(ducks_hunted, 500, 360);
        String s1 = "EXIT";
        String s3 = "RESTART";
        g.drawString(s1, exitsButton.x, exitsButton.y + 50);
        g.drawString(s3, restartButton.x, restartButton.y + 50);
        
    }

}
