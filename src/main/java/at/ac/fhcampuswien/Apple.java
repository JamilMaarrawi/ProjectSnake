package at.ac.fhcampuswien;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import Snake.*;

public class Apple {
    GamePanel gp;
    public BufferedImage i = null;
    public int x, y;

    //Constructor for class "Apple"
    public Apple(GamePanel gp){
        this.gp = gp;
        getImage();
        while (true) {
            this.x = getX() * gp.TileSize;
            this.y = getY() * gp.TileSize;
            if(CheckIfValid(x/gp.TileSize,y/gp.TileSize)) break;
        }
    }
    //Chooses randomly between Red, Green and Yellow apples and gets the Image
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
    //Gets a random X Coordinate
    public int getX(){
        int x = 1 + (int)(Math.random() * ((20 - 1) + 1));
        return x;
    }
    //Gets a random Y Coordinate
    public int getY(){
        int y = 5 + (int)(Math.random() * ((21 - 5) + 1));
        return y;
    }
    //Draws the Apple
    public void draw(Graphics2D g2){
        g2.drawImage(i,x,y,gp.TileSize,gp.TileSize,null);
    }
    //Checks if the X and Y coordinates are valid
    public boolean CheckIfValid(int x, int y){
        boolean Valid=true;
        if(gp.TM.map[y][x]!=0) Valid=false;
        for (Snake s : gp.w){
            if (s.x / gp.TileSize == x && s.y / gp.TileSize == y) {
                Valid = false;
                break;
            }
        }
        return Valid;
    }
}
