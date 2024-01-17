package Snake;

import at.ac.fhcampuswien.GamePanel;
import at.ac.fhcampuswien.KeyBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tail extends Snake{

    public BufferedImage TailUp, TailDown, TailLeft, TailRight;

    //Constructor
    public Tail(GamePanel gp, KeyBoard k) {
        super(gp,k);
        x = 12 * gp.TileSize;
        y = 12 * gp.TileSize;
        getImage();
    }
    //Gets Image for various directions
    public void getImage() {
        try {
            TailUp = ImageIO.read(getClass().getResourceAsStream("/Snake/TailUp.png"));
            TailDown = ImageIO.read(getClass().getResourceAsStream("/Snake/TailDown.png"));
            TailLeft = ImageIO.read(getClass().getResourceAsStream("/Snake/TailLeft.png"));
            TailRight = ImageIO.read(getClass().getResourceAsStream("/Snake/TailRight.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //Draws Image according to direction
    @Override
    public void draw(Graphics2D g2) {
        BufferedImage i = null;
        switch (Direction){
            case "UP", "LEFTUP", "RIGHTUP" -> i = TailUp;
            case "DOWN", "LEFTDOWN", "RIGHTDOWN" -> i = TailDown;
            case "LEFT", "UPLEFT", "DOWNLEFT" -> i = TailLeft;
            case "RIGHT", "UPRIGHT", "DOWNRIGHT" -> i = TailRight;
        }
        g2.drawImage(i,x,y,gp.TileSize,gp.TileSize,null);
    }
    //When game is restarted, Tail gets to original position and direction
    @Override
    public void Restart() {
        x=12*gp.TileSize;
        y=12*gp.TileSize;
        Direction="LEFT";
        collisionOn=false;
        gp.w.add(this);
    }
}
