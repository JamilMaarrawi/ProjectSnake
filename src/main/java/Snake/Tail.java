package Snake;

import at.ac.fhcampuswien.GamePanel;
import at.ac.fhcampuswien.KeyBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tail extends Snake{

    public BufferedImage TailUp, TailDown, TailLeft, TailRight;

    public Tail(GamePanel gp, KeyBoard k) {
        super(gp,k);
        x = (gp.screenWidth/2) + (gp.TileSize);
        y = (gp.screenHeight/2) - (2*gp.TileSize);
        getImage();
    }

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
}
