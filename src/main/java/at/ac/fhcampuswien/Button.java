package at.ac.fhcampuswien;

import Snake.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {
    GamePanel gp;
    int x,y,width,height;
    //Constructor for class "Button"
    public Button(int x, int y, int width, int height,GamePanel gp){
        super();
        setVisible(false);
        setEnabled(false);
        setBackground(Color.GRAY);
        setDoubleBuffered(true);
        this.x=x;this.y=y;this.width=width;this.height=height;
        setBounds(this.x,this.y,this.width,this.height);
        this.gp=gp;
        addMouseListener(this);
        setFocusable(false);
    }
    //Draws the Button
    public void drawButton(){
        setVisible(true);
        setEnabled(true);
        setBounds(this.x,this.y,this.width,this.height);
        repaint();
        gp.add(this);
    }
    //Performs the Action when Button is clicked
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == gp.m1) {
            gp.TM.Map = 1;
            gp.GameState = 4;
        } else if (e.getSource() == gp.m2) {
            if(gp.TM.GotMap2) {
                gp.TM.Map = 2;
                gp.GameState = 4;
            } else {
                if(gp.Coins>=100){
                    gp.Coins-=100;
                    gp.TM.GotMap2 = true;
                }
            }
        } else if (e.getSource() == gp.m3) {
            if(gp.TM.GotMap3) {
                gp.TM.Map = 3;
                gp.GameState = 4;
            } else {
                if(gp.Coins>=200){
                    gp.Coins-=200;
                    gp.TM.GotMap3 = true;
                }
            }
        } else if (e.getSource() == gp.restart) {
            gp.a.removeAll(gp.a);
            gp.w.removeAll(gp.w);
            gp.h.Restart();
            gp.b.Restart();
            gp.t.Restart();
            gp.GameState = 1;
        } else if (e.getSource() == gp.GoHome) {
            gp.Score=0;
            gp.a.removeAll(gp.a);
            gp.w.removeAll(gp.w);
            gp.h.Restart();
            gp.b.Restart();
            gp.t.Restart();
            gp.GameState = 0;
        } else if (e.getSource() == gp.resume) {
            gp.GameState=1;
        } else if (e.getSource() == gp.easy) {
            gp.Difficulty="easy";
            gp.GameState=1;
        } else if (e.getSource() == gp.medium) {
            gp.Difficulty="medium";
            gp.GameState=1;
        } else if (e.getSource() == gp.hard) {
            gp.Difficulty="hard";
            gp.GameState=1;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
