package at.ac.fhcampuswien;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

    public String Direction = "LEFT";
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (Direction != "DOWN") Direction = "UP";
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (Direction != "UP") Direction = "DOWN";
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (Direction != "RIGHT") Direction = "LEFT";
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (Direction != "LEFT") Direction = "RIGHT";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public String getDirection() {
        return Direction;
    }
}
