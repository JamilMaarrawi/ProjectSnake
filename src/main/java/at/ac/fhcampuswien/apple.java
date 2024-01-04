package at.ac.fhcampuswien;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class apple {
    GamePanel gp;
    public BufferedImage i = null;
    public int x, y;
    int count = 0;
    public apple(GamePanel gp){
        this.gp = gp;
        getImage();
        this.x = getX() * gp.TileSize;
        this.y = getY() * gp.TileSize;
    }
    public void getImage(){
        int x = 1 + (int)(Math.random() * ((3 - 1) + 1));
        try{
            if(x==1)i = ImageIO.read(getClass().getResourceAsStream("/Other/Apple1.png"));
            if(x==2)i = ImageIO.read(getClass().getResourceAsStream("/Other/Apple2.png"));
            if(x==3)i = ImageIO.read(getClass().getResourceAsStream("/Other/Apple3.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int getX(){
        int x = 1 + (int)(Math.random() * ((21 - 1) + 1));
        return x;
    }
    public int getY(){
        int y = 5 + (int)(Math.random() * ((23 - 5) + 1));
        return y;
    }
    public void draw(Graphics2D g2){
        g2.drawImage(i,x,y,gp.TileSize,gp.TileSize,null);
    }
}
