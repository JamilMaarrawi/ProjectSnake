package at.ac.fhcampuswien;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

    GamePanel gp;
    public String Direction = "LEFT";
    public String Redo = null;
    public KeyBoard(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (Direction != "DOWN"){
                if (gp.w.get(1).x!=gp.h.x)Direction = "UP";
                else Redo="UP";
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (Direction != "UP"){
                if (gp.w.get(1).x!=gp.h.x)Direction = "DOWN";
                else Redo="DOWN";
            }
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (Direction != "RIGHT"){
                if (gp.w.get(1).y!=gp.h.y)Direction = "LEFT";
                else Redo="LEFT";
            }
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (Direction != "LEFT"){
                if (gp.w.get(1).y!=gp.h.y)Direction = "RIGHT";
                else Redo="RIGHT";
            }
        }
        if(code == KeyEvent.VK_P) {
            if (gp.GameState==1)gp.GameState=3;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
