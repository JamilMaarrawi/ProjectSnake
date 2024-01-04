package Snake;

import at.ac.fhcampuswien.GamePanel;
import at.ac.fhcampuswien.KeyBoard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Body extends Snake{

    public BufferedImage BodyUp, BodyDown, BodyLeft, BodyRight;
    public BufferedImage DownLeft_RightUp, DownRight_LeftUp, UpLeft_RightDown, UpRight_LeftDown;

    public Body(GamePanel gp, KeyBoard k) {
        super(gp,k);
        x = (gp.screenWidth/2);
        y = (gp.screenHeight/2) - (2*gp.TileSize);
        getImage();
    }

    public void getImage() {
        try {
            BodyUp = ImageIO.read(getClass().getResourceAsStream("/Snake/BodyUp.png"));
            BodyDown = ImageIO.read(getClass().getResourceAsStream("/Snake/BodyDown.png"));
            BodyLeft = ImageIO.read(getClass().getResourceAsStream("/Snake/BodyLeft.png"));
            BodyRight = ImageIO.read(getClass().getResourceAsStream("/Snake/BodyRight.png"));
            DownLeft_RightUp = ImageIO.read(getClass().getResourceAsStream("/Snake/DownLeft_RightUp.png"));
            DownRight_LeftUp = ImageIO.read(getClass().getResourceAsStream("/Snake/DownRight_LeftUp.png"));
            UpLeft_RightDown = ImageIO.read(getClass().getResourceAsStream("/Snake/UpLeft_RightDown.png"));
            UpRight_LeftDown = ImageIO.read(getClass().getResourceAsStream("/Snake/UpRight_LeftDown.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage i = null;
        switch (Direction) {
            case "UP" -> i = BodyUp;
            case "DOWN" -> i = BodyDown;
            case "LEFT" -> i = BodyLeft;
            case "RIGHT" -> i = BodyRight;
            case "UPLEFT", "RIGHTDOWN" -> i = UpLeft_RightDown;
            case "UPRIGHT", "LEFTDOWN" -> i = UpRight_LeftDown;
            case "DOWNLEFT", "RIGHTUP" -> i = DownLeft_RightUp;
            case "DOWNRIGHT", "LEFTUP" -> i = DownRight_LeftUp;
        }
        g2.drawImage(i,x,y,gp.TileSize,gp.TileSize,null);
    }
}
