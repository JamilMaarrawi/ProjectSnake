package at.ac.fhcampuswien;

import Map.TileManager;
import Snake.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable{

    public final int TileSize = 32;

    public final int maxScreenCol = 22;
    public final int maxScreenRow = 23;
    public final int screenWidth = TileSize * maxScreenCol;
    public final int screenHeight = TileSize * maxScreenRow;
    public int Score = 0;
    public int Coins = 306;

    public int FPS1 = 3;
    public int FPS2 = 5;
    public int FPS3 = 7;
    public KeyBoard k = new KeyBoard(this);
    Thread gameThread;
    Head h = new Head(this,k);
    Body b = new Body(this,k,11*TileSize,12*TileSize);
    Tail t = new Tail(this,k);
    public List<Snake> w = new ArrayList<>();
    List<Apple> a = new ArrayList<>();
    TileManager TM = new TileManager(this);
    CollisionDetector CD = new CollisionDetector(this);
    Sound Music = new Sound();
    Sound SE = new Sound();
    Text text = new Text(this);
    public int GameState;
    Button m1,m2,m3,easy,medium,hard,resume,restart,GoHome;
    public Graphics2D g2;
    public String Difficulty="medium";
    boolean Ate;
    BufferedImage Frame=null;
    BufferedImage Frame1=null;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.getHSBColor(43,9,90));
        this.setDoubleBuffered(true);
        this.addKeyListener(k);
        this.setFocusable(true);
        w.add(h);
        w.add(b);
        w.add(t);
        playMusic(0);
        GameState = 0;
        m1 = new Button(100,425,3*TileSize+10,3*TileSize,this);
        m2 = new Button(screenWidth/2 - 48,425,3*TileSize+10,3*TileSize,this);
        m2.setToolTipText("Price: 100c");
        m3 = new Button(screenWidth-3*TileSize-100,425,3*TileSize+10,3*TileSize,this);
        m3.setToolTipText("Price: 200c");
        restart = new Button(100,500,6*TileSize,3*TileSize,this);
        GoHome = new Button(screenWidth-6*TileSize-100,500,6*TileSize,3*TileSize,this);
        resume = new Button(100,500,6*TileSize,3*TileSize,this);
        easy = new Button(screenWidth/2-96,350,6*TileSize,2*TileSize,this);
        medium = new Button(screenWidth/2-96,450,6*TileSize,2*TileSize,this);
        hard = new Button(screenWidth/2-96,550,6*TileSize,2*TileSize,this);
        try {Frame=ImageIO.read(getClass().getResourceAsStream("/Other/0Frame.png"));} catch (IOException e) {e.printStackTrace();}
        try {Frame1=ImageIO.read(getClass().getResourceAsStream("/Other/0Frame1.png"));} catch (IOException e) {e.printStackTrace();}
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double Interval1 = 1000000000/FPS1;
        double Interval2 = 1000000000/FPS2;
        double Interval3 = 1000000000/FPS3;
        double delta = 0;
        long before = System.nanoTime();
        long now;

        while (gameThread != null) {
            now = System.nanoTime();
            if(Difficulty.equals("easy"))delta = delta + (now - before)/Interval1;
            if(Difficulty.equals("medium"))delta = delta + (now - before)/Interval2;
            if(Difficulty.equals("hard"))delta = delta + (now - before)/Interval3;
            before = now;

            if(delta >= 1) {
                if(GameState == 1) update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if(a.size() < 3){
            a.add(new Apple(this));
        }
        for (int i = 0; i < a.size();i++){
            if(a.get(i).x == h.x && a.get(i).y == h.y) {
                a.remove(a.get(i));
                w.add(1,new Body(this,k,h.x,h.y));
                w.get(1).Direction=h.Direction;
                Ate=true;
                playSE(1);
                Coins++;
                Score++;
            }
        }
        int[] ox = new int[w.size()];
        int[] oy = new int[w.size()];

        for (int i = 0; i<w.size(); i++) {
            ox[i] = w.get(i).x;
            oy[i] = w.get(i).y;
        }

        CD.CheckTile(w.get(0));
        switch (k.Direction) {
            case "UP" -> {
                h.y = h.y - TileSize;
                h.Direction = "UP";
            }
            case "DOWN" -> {
                h.y = h.y + TileSize;
                h.Direction = "DOWN";
            }
            case "LEFT" -> {
                h.x = h.x - TileSize;
                h.Direction = "LEFT";
            }
            case "RIGHT" -> {
                h.x = h.x + TileSize;
                h.Direction = "RIGHT";
            }
        }
        if(!Ate) {
            for (int i = 1; i < w.size(); i++) {
                w.get(i).x = ox[i - 1];
                w.get(i).y = oy[i - 1];
            }
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
            if (Ate){
                Ate=false;
                return;
            }
        }
        for (int i=1;i<w.size();i++) {
            if (w.get(i).x == h.x && w.get(i).y == h.y) {
                h.collisionOn = true;
                break;
            }
        }
        if(k.Redo!=null){
            k.Direction = k.Redo;
            k.Redo = null;
        }
        if(h.collisionOn){
            Score=0;
            GameState=2;
            playSE(2);
        }
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g2 = (Graphics2D) g;
        if (GameState==0){
            draw0(g2);
            text.draw(g2);
            g2.dispose();
        } else if (GameState==1){
            draw1(g2);
            text.draw(g2);
            TM.draw(g2);
            //g2.drawImage(Frame, 0, 0, screenWidth, screenHeight, null);
            for (Snake s : w) {
                s.draw(g2);
            }
            for (Apple p : a) {
                p.draw(g2);
            }
            g2.dispose();
        } else if (GameState==2){
            draw2(g2);
            g2.dispose();
        } else if (GameState==3){
            draw3(g2);
            g2.dispose();
        } else if (GameState==4){
            draw4(g2);
            g2.dispose();
        }
    }
    public void draw1(Graphics2D g2){
        BufferedImage i=null;
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0Title.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 0, 0, 6*TileSize, 3*TileSize, null);
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0Coins.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 17*TileSize, 10, 80, 40, null);
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0Score.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 17*TileSize, TileSize+15, 80, 40, null);
        //g2.drawImage(Frame, 0, 0, screenWidth, screenHeight, null);
        //g2.drawImage(Frame1, 0, 48, screenWidth, 48, null);
        remove(m1);
        remove(m2);
        remove(m3);
        remove(easy);
        remove(medium);
        remove(hard);
        remove(restart);
        remove(resume);
        remove(GoHome);
    }
    public void draw0(Graphics2D g2){
        BufferedImage i=null;
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0Title.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 130, 120, 14*TileSize, 7*TileSize, null);
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0Coins.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 17*TileSize, 10, 80, 40, null);
        g2.drawImage(Frame, 0, 0, screenWidth, screenHeight, null);
        m1.setIcon(new  ImageIcon(getClass().getResource("/Other/map1.png")));
        m1.drawButton();
        if(TM.GotMap2)m2.setIcon(new  ImageIcon(getClass().getResource("/Other/map2.png")));
        else m2.setIcon(new  ImageIcon(getClass().getResource("/Other/Lock.png")));
        m2.drawButton();
        if(TM.GotMap3)m3.setIcon(new  ImageIcon(getClass().getResource("/Other/map3.png")));
        else m3.setIcon(new  ImageIcon(getClass().getResource("/Other/Lock.png")));
        m3.drawButton();
        remove(easy);
        remove(medium);
        remove(hard);
        remove(restart);
        remove(resume);
        remove(GoHome);
    }
    public void draw2(Graphics2D g2){
        BufferedImage i=null;
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0GameOver.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 60, 120, 18*TileSize, 9*TileSize, null);
        g2.drawImage(Frame, 0, 0, screenWidth, screenHeight, null);
        restart.setBackground(Color.getHSBColor(43,9,90));
        restart.setForeground(Color.WHITE);
        restart.setIcon(new ImageIcon(getClass().getResource("/Other/RecGreen.png")));
        restart.setBorder(null);
        restart.setFont(text.F);
        restart.setText("Restart");
        restart.setIconTextGap(-150);
        restart.drawButton();
        GoHome.setBackground(Color.getHSBColor(43,9,90));
        GoHome.setForeground(Color.WHITE);
        GoHome.setIcon(new ImageIcon(getClass().getResource("/Other/RecRed.png")));
        GoHome.setBorder(null);
        GoHome.setFont(text.F);
        GoHome.setText("Home");
        GoHome.setIconTextGap(-140);
        GoHome.drawButton();
    }
    public void draw3(Graphics2D g2){
        BufferedImage i=null;
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0Pause.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 60, 120, 18*TileSize, 9*TileSize, null);
        g2.drawImage(Frame, 0, 0, screenWidth, screenHeight, null);
        resume.setBackground(Color.getHSBColor(43,9,90));
        resume.setForeground(Color.WHITE);
        resume.setIcon(new ImageIcon(getClass().getResource("/Other/RecGreen.png")));
        resume.setBorder(null);
        resume.setFont(text.F);
        resume.setText("Resume");
        resume.setIconTextGap(-150);
        resume.drawButton();
        GoHome.setBackground(Color.getHSBColor(43,9,90));
        GoHome.setForeground(Color.WHITE);
        GoHome.setIcon(new ImageIcon(getClass().getResource("/Other/RecRed.png")));
        GoHome.setBorder(null);
        GoHome.setFont(text.F);
        GoHome.setText("Home");
        GoHome.setIconTextGap(-140);
        GoHome.drawButton();
    }
    public void draw4(Graphics2D g2){
        BufferedImage i=null;
        try {i=ImageIO.read(getClass().getResourceAsStream("/Other/0Difficulty.png"));} catch (IOException e) {e.printStackTrace();}
        g2.drawImage(i, 70, 0, 18*TileSize, 13*TileSize, null);
        g2.drawImage(Frame, 0, 0, screenWidth, screenHeight, null);
        easy.setBackground(Color.getHSBColor(43,9,90));
        easy.setForeground(Color.WHITE);
        easy.setIcon(new ImageIcon(getClass().getResource("/Other/Rec1.png")));
        easy.setBorder(null);
        easy.setFont(text.F);
        easy.setText("Easy");
        easy.setIconTextGap(-130);
        easy.drawButton();
        medium.setBackground(Color.getHSBColor(43,9,90));
        medium.setForeground(Color.WHITE);
        medium.setIcon(new ImageIcon(getClass().getResource("/Other/Rec2.png")));
        medium.setBorder(null);
        medium.setFont(text.F);
        medium.setText("Medium");
        medium.setIconTextGap(-150);
        medium.drawButton();
        hard.setBackground(Color.getHSBColor(43,9,90));
        hard.setForeground(Color.WHITE);
        hard.setIcon(new ImageIcon(getClass().getResource("/Other/Rec3.png")));
        hard.setBorder(null);
        hard.setFont(text.F);
        hard.setText("Hard");
        hard.setIconTextGap(-130);
        hard.drawButton();
        remove(m1);
        remove(m2);
        remove(m3);
        remove(restart);
        remove(resume);
        remove(GoHome);
    }
    public void playMusic(int i){
        Music.setFile(i);
        Music.play();
        Music.loop();
    }
    public void playSE(int i){
        SE.setFile(i);
        SE.play();
    }
}
