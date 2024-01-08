package at.ac.fhcampuswien;

import java.awt.*;

public class Text {
    GamePanel gp;
    Font arial40;
    Font F;
    public Text(GamePanel gp){
        this.gp = gp;
        arial40 = new Font("Arial",Font.BOLD,35);
        F = new Font("SansSerif",Font.BOLD,30);
    }
    public void draw(Graphics2D g2){
        g2.setFont(arial40);
        g2.setColor(new Color(70,140,70));
        g2.drawString(String.valueOf(gp.Coins),20*gp.TileSize -10, 40);
        if(gp.GameState == 1)g2.drawString(String.valueOf(gp.Score),20*gp.TileSize -10, 80);
        if(gp.GameState == 0)g2.drawString("Level 1",90, 560);
        if(gp.GameState == 0)g2.drawString("Level 2",gp.screenWidth/2-60, 560);
        if(gp.GameState == 0)g2.drawString("Level 3",gp.screenWidth-210, 560);
    }
}
