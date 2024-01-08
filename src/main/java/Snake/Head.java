package Snake;

import at.ac.fhcampuswien.GamePanel;
import at.ac.fhcampuswien.KeyBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Head extends Snake {

    public BufferedImage HeadUp, HeadDown, HeadLeft, HeadRight;
    public Head(GamePanel gp, KeyBoard k) {
        super(gp,k);
        x = 10 * gp.TileSize;
        y = 12 * gp.TileSize;
        collisionOn = false;
        getImage();
    }

    public void getImage() {
        try {
            HeadUp = ImageIO.read(getClass().getResourceAsStream("/Snake/HeadUp.png"));
            HeadDown = ImageIO.read(getClass().getResourceAsStream("/Snake/HeadDown.png"));
            HeadLeft = ImageIO.read(getClass().getResourceAsStream("/Snake/HeadLeft.png"));
            HeadRight = ImageIO.read(getClass().getResourceAsStream("/Snake/HeadRight.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage i = null;
        switch (k.Direction){
            case "UP" -> i = HeadUp;
            case "DOWN" -> i = HeadDown;
            case "LEFT" -> i = HeadLeft;
            case "RIGHT" -> i = HeadRight;
        }
        g2.drawImage(i,x,y,gp.TileSize,gp.TileSize,null);
    }
    @Override
    public void Restart() {
        x=10*gp.TileSize;
        y=12*gp.TileSize;
        Direction="LEFT";
        gp.k.Direction="LEFT";
        collisionOn=false;
        gp.w.add(this);
    }
}
