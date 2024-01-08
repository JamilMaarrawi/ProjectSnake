package Snake;

import at.ac.fhcampuswien.GamePanel;
import at.ac.fhcampuswien.KeyBoard;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class Snake {
    public Snake(GamePanel gp, KeyBoard k) {
        this.gp = gp;
        this.k = k;
    }

    GamePanel gp;
    KeyBoard k;

    public int x,y;
    public String Direction = "LEFT";
    public boolean collisionOn = false;

    public abstract void draw(Graphics2D g2);

    public abstract void Restart();

}
