package Graphics;

import GameSetting.GamePanel;
import Object.Tile;

public class DrawTile extends DrawStaticObject{
	Tile tile;
	public DrawTile(GamePanel gp, String name, boolean collision) {
        
        super(gp, name, collision, 1);
    }
}
