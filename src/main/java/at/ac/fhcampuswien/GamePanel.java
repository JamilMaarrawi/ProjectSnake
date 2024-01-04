package at.ac.fhcampuswien;

import Snake.*;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable{

    public final int TileSize = 32;

    public final int maxScreenCol = 22;
    public final int maxScreenRow = 28;
    public final int screenWidth = TileSize * maxScreenCol;
    public final int screenHeight = TileSize * maxScreenRow;
    public int Score = 0;
    public int Coins = 0;
    public int appleCount = 0;

    int FPS = 5;
    KeyBoard k = new KeyBoard();
    Thread gameThread;
    Head h = new Head(this,k);
    Body b = new Body(this,k,screenWidth/2,screenHeight/2 - 2 * TileSize);
    Tail t = new Tail(this,k);
    List<Snake> w = new ArrayList<>();
    List<apple> a = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.getHSBColor(43,9,90));
        this.setDoubleBuffered(true);
        this.addKeyListener(k);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double Interval = 1000000000/FPS;
        double delta = 0;
        long before = System.nanoTime();
        long now;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            now = System.nanoTime();
            delta = delta + (now - before)/Interval;
            timer = timer + (now - before);
            before = now;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if(a.size() < 3){
            a.add(new apple(this));
        }
        for (int i = 0; i < a.size();i++){
            if(a.get(i).x == h.x && a.get(i).y == h.y){
                a.remove(a.get(i));
                w.add(w.size()-2, new Body(this,k,w.get(w.size()-2).x,w.get(w.size()-2).y));
            }
        }
        int[] ox = new int[w.size()];
        int[] oy = new int[w.size()];

        for (int i = 0; i<w.size(); i++) {
            ox[i] = w.get(i).x;
            oy[i] = w.get(i).y;
        }

        switch (k.Direction) {
            case "UP" -> {
                if (!w.get(0).collisionOn) h.y = h.y - TileSize;
                h.Direction = "UP";
            }
            case "DOWN" -> {
                if (!w.get(0).collisionOn) h.y = h.y + TileSize;
                h.Direction = "DOWN";
            }
            case "LEFT" -> {
                if (!w.get(0).collisionOn) h.x = h.x - TileSize;
                h.Direction = "LEFT";
            }
            case "RIGHT" -> {
                if (!w.get(0).collisionOn) h.x = h.x + TileSize;
                h.Direction = "RIGHT";
            }
        }
        for (int i = 1; i < w.size(); i++) {
            w.get(i).x = ox[i - 1];
            w.get(i).y = oy[i - 1];
        }

        for (int i=1; i<w.size(); i++){
            switch (w.get(i).Direction){
                case ("UP"), ("RIGHTUP"), ("LEFTUP"):
                    if (w.get(i).y != w.get(i-1).y + TileSize){
                        if(w.get(i).x == w.get(i-1).x + TileSize){
                            w.get(i).Direction = "UPLEFT";
                        }
                        else if (w.get(i).x == w.get(i-1).x - TileSize) {
                            w.get(i).Direction = "UPRIGHT";
                        }
                    } else {w.get(i).Direction = "UP";}
                    break;
                case ("DOWN"), ("RIGHTDOWN"), ("LEFTDOWN"):
                    if (w.get(i).y != w.get(i-1).y - TileSize){
                        if(w.get(i).x == w.get(i-1).x + TileSize){
                            w.get(i).Direction = "DOWNLEFT";
                        }
                        else if (w.get(i).x == w.get(i-1).x - TileSize) {
                            w.get(i).Direction = "DOWNRIGHT";
                        }
                    } else {w.get(i).Direction = "DOWN";}
                    break;
                case ("LEFT"), ("UPLEFT"), ("DOWNLEFT") :
                    if (w.get(i).x != w.get(i-1).x + TileSize){
                        if(w.get(i).y == w.get(i-1).y + TileSize){
                            w.get(i).Direction = "LEFTUP";
                        }
                        else if (w.get(i).y == w.get(i-1).y - TileSize) {
                            w.get(i).Direction = "LEFTDOWN";
                        }
                    } else {w.get(i).Direction = "LEFT";}
                    break;
                case ("RIGHT"), ("UPRIGHT"), ("DOWNRIGHT") :
                    if (w.get(i).x != w.get(i-1).x - TileSize){
                        if(w.get(i).y == w.get(i-1).y + TileSize){
                            w.get(i).Direction = "RIGHTUP";
                        }
                        else if (w.get(i).y == w.get(i-1).y - TileSize) {
                            w.get(i).Direction = "RIGHTDOWN";
                        }
                    } else {w.get(i).Direction = "RIGHT";}
                    break;
            }
        }
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        draw(g2);
        for (Snake s : w){
            s.draw(g2);
        }
        for (apple p : a){
            p.draw(g2);
        }
        g2.dispose();
    }
    public void draw(Graphics2D g2){
        BufferedImage i=null;
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 0, 0, 6*TileSize, 3*TileSize, null);
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/01.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 17*TileSize, 10, 80, 40, null);
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/02.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 17*TileSize, TileSize+15, 80, 40, null);
    }

}
