package at.ac.fhcampuswien;

import Snake.Snake;

public class CollisionDetector {

    GamePanel gp;
    public CollisionDetector(GamePanel gp){
        this.gp = gp;
    }

    public void CheckTile(Snake s){

        if(gp.TM.tile[ gp.TM.map[s.y/gp.TileSize][s.x/gp.TileSize]].collision) s.collisionOn = true;

    }
}
