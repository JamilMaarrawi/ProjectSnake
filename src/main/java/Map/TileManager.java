package Map;

import at.ac.fhcampuswien.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] map;
    public int Map=1;
    public boolean GotMap2,GotMap3 = false;

    //Constructor for class "TileManager"
    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        map = new int[gp.maxScreenRow][gp.maxScreenCol];
        for (int row = 3; row < gp.maxScreenRow; row++){
            for (int col = 0; col < gp.maxScreenCol; col++) {
                if (row==3 || row==gp.maxScreenRow-1){
                    if (col%2==0) map[row][col] = 3;
                    else map[row][col] = 4;
                } else if (col==gp.maxScreenCol-1 || col==0 ){
                    if (row%2==0) map[row][col] = 3;
                    else map[row][col] = 4;
                }
            }
        }
    }
    //Gets image for Tiles
    public void getTileImage() {
        try {
            tile[0]=new Tile();
            tile[0].i = null;

            tile[1]=new Tile();
            tile[1].i = ImageIO.read(getClass().getResourceAsStream("/Other/Tile1.png"));

            tile[2]=new Tile();
            tile[2].i = ImageIO.read(getClass().getResourceAsStream("/Other/Tile2.png"));

            tile[3]=new Tile();
            tile[3].i = ImageIO.read(getClass().getResourceAsStream("/Other/Brick1.png"));
            tile[3].collision = true;

            tile[4]=new Tile();
            tile[4].i = ImageIO.read(getClass().getResourceAsStream("/Other/Brick2.png"));
            tile[4].collision = true;



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Draws the Map on the Game-Panel
    public void draw(Graphics2D g2) {
        Background(g2);
        if(Map==1)Map1(g2);
        else if (Map==2)Map2(g2);
        else if (Map==3)Map3(g2);
    }
    //Creates Background for Game-Panel
    public void Background(Graphics2D g2) {
        for (int row = 3; row < gp.maxScreenRow; row++) {
            if (row % 2 == 0) {
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    if (col % 2 == 0) {
                        g2.drawImage(tile[1].i, col*gp.TileSize, row* gp.TileSize, gp.TileSize, gp.TileSize, null);
                    } else {
                        g2.drawImage(tile[2].i, col*gp.TileSize, row* gp.TileSize, gp.TileSize, gp.TileSize, null);
                    }
                }
            } else {
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    if (col % 2 != 0) {
                        g2.drawImage(tile[1].i, col*gp.TileSize, row* gp.TileSize, gp.TileSize, gp.TileSize, null);
                    } else {
                        g2.drawImage(tile[2].i, col*gp.TileSize, row* gp.TileSize, gp.TileSize, gp.TileSize, null);
                    }
                }
            }
        }
    }
    //Creates Map 1
    public void Map1(Graphics2D g2){
        map = new int[gp.maxScreenRow][gp.maxScreenCol];
        for (int row = 3; row < gp.maxScreenRow; row++){
            for (int col = 0; col < gp.maxScreenCol; col++) {
                if (row==3 || row==gp.maxScreenRow-1){
                    if (col%2==0) map[row][col] = 3;
                    else map[row][col] = 4;
                } else if (col==gp.maxScreenCol-1 || col==0 ){
                    if (row%2==0) map[row][col] = 3;
                    else map[row][col] = 4;
                }
            }
        }
        for (int row = 0; row < gp.maxScreenRow; row++){
            for (int col = 0; col < gp.maxScreenCol; col++) {
                g2.drawImage(tile[map[row][col]].i, col*gp.TileSize, row* gp.TileSize, gp.TileSize, gp.TileSize, null);
            }
        }
    }
    //Creates Map 2
    public void Map2(Graphics2D g2){
        map = new int[gp.maxScreenRow][gp.maxScreenCol];
        map[ 3] = new int[]{3,3,4,4,4,3,3,3,3,4,3,3,3,3,4,4,4,3,3,3,4,4};
        map[ 4] = new int[]{3,0,0,0,3,0,0,0,0,0,3,3,3,0,0,0,0,0,0,0,0,3};
        map[ 5] = new int[]{3,0,3,0,3,0,3,0,0,0,3,3,4,0,3,0,0,0,0,0,0,3};
        map[ 6] = new int[]{3,0,3,0,0,0,3,0,0,0,3,0,0,0,3,0,3,3,3,4,4,4};
        map[ 7] = new int[]{3,0,4,0,0,0,3,0,0,0,4,0,0,3,3,0,0,0,0,0,0,4};
        map[ 8] = new int[]{3,0,4,0,0,0,3,0,0,0,4,3,0,0,3,3,3,3,4,4,0,3};
        map[ 9] = new int[]{3,0,3,3,3,4,4,0,0,0,4,4,3,0,3,0,0,0,0,0,0,3};
        map[10] = new int[]{3,0,3,0,0,0,0,0,0,0,4,3,3,0,3,0,4,4,4,3,3,3};
        map[11] = new int[]{3,0,3,0,3,3,4,4,4,3,3,3,3,0,3,0,0,0,0,0,0,3};
        map[12] = new int[]{4,0,3,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,0,3};
        map[13] = new int[]{4,0,3,3,3,3,4,4,4,3,3,3,3,4,3,0,0,0,0,3,0,4};
        map[14] = new int[]{4,0,3,3,3,3,3,0,0,0,0,0,4,4,3,0,0,0,0,0,0,4};
        map[15] = new int[]{3,0,4,4,3,3,3,0,3,3,3,0,0,4,3,0,0,0,0,0,0,4};
        map[16] = new int[]{3,0,4,0,0,0,3,0,0,3,3,3,0,0,3,3,3,3,3,3,0,4};
        map[17] = new int[]{3,0,3,0,0,0,3,3,0,0,4,4,3,0,3,3,3,3,3,3,0,3};
        map[18] = new int[]{3,0,3,0,0,0,3,3,3,0,4,4,3,0,0,4,4,3,3,0,0,3};
        map[19] = new int[]{3,0,3,0,0,0,3,3,0,0,4,3,3,3,0,4,4,3,0,0,3,3};
        map[20] = new int[]{3,0,3,3,0,0,3,0,0,4,4,3,3,3,0,3,3,0,0,3,4,4};
        map[21] = new int[]{4,0,0,0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,3,3,4,4};
        map[22] = new int[]{4,4,3,3,3,4,4,3,3,3,3,4,4,3,3,4,4,4,3,3,3,3};
        for (int row = 0; row < gp.maxScreenRow; row++){
            for (int col = 0; col < gp.maxScreenCol; col++) {
                g2.drawImage(tile[map[row][col]].i, col*gp.TileSize, row* gp.TileSize, gp.TileSize, gp.TileSize, null);
            }
        }
    }
    //Creates Map 3
    public void Map3(Graphics2D g2){
        map = new int[gp.maxScreenRow][gp.maxScreenCol];
        map[ 3] = new int[]{3,4,4,4,3,3,3,3,3,3,3,3,3,4,4,3,3,3,3,3,3,3};
        map[ 4] = new int[]{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4};
        map[ 5] = new int[]{3,0,4,4,3,3,3,3,3,0,0,3,3,3,3,3,3,3,3,3,0,4};
        map[ 6] = new int[]{3,0,4,0,0,0,0,0,0,0,0,3,3,4,0,0,0,0,0,3,0,4};
        map[ 7] = new int[]{3,0,3,0,0,0,0,0,0,0,0,3,4,4,0,3,3,3,0,3,0,3};
        map[ 8] = new int[]{3,0,3,0,0,0,0,0,0,0,0,3,4,4,0,3,4,4,0,3,0,3};
        map[ 9] = new int[]{3,0,3,3,3,3,3,3,3,3,3,3,3,0,0,3,4,4,0,3,0,3};
        map[10] = new int[]{3,0,3,4,4,3,0,0,0,0,0,0,0,0,3,3,3,3,0,3,0,3};
        map[11] = new int[]{3,0,3,4,4,0,0,3,3,3,3,3,3,3,3,3,3,3,0,3,0,3};
        map[12] = new int[]{3,0,3,3,0,0,3,0,0,0,0,0,0,3,3,3,3,3,0,3,0,3};
        map[13] = new int[]{3,0,3,0,0,3,0,0,3,0,0,0,0,3,0,0,0,0,0,3,0,3};
        map[14] = new int[]{4,0,0,0,4,0,0,3,3,0,3,3,3,3,0,3,3,3,3,3,0,3};
        map[15] = new int[]{4,0,0,4,0,0,3,3,0,0,3,0,0,3,0,0,3,3,3,3,0,3};
        map[16] = new int[]{4,3,3,0,0,3,3,0,0,3,3,0,0,3,3,0,0,3,4,4,0,3};
        map[17] = new int[]{3,3,0,0,3,3,0,0,3,4,3,0,0,3,3,3,0,0,3,4,0,3};
        map[18] = new int[]{3,0,0,3,3,0,0,3,4,4,3,0,0,3,3,3,3,0,0,4,0,3};
        map[19] = new int[]{3,0,3,3,0,0,3,3,4,3,3,0,0,3,3,3,3,3,0,0,0,3};
        map[20] = new int[]{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3};
        map[21] = new int[]{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4};
        map[22] = new int[]{3,3,3,3,3,4,4,4,4,4,3,3,3,3,3,3,3,3,3,3,4,4};
        for (int row = 0; row < gp.maxScreenRow; row++){
            for (int col = 0; col < gp.maxScreenCol; col++) {
                g2.drawImage(tile[map[row][col]].i, col*gp.TileSize, row* gp.TileSize, gp.TileSize, gp.TileSize, null);
            }
        }
    }
}
