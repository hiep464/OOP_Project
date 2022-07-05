package Graphics;

import java.awt.image.BufferedImage;

import GameSetting.GamePanel;

public class DrawObject extends DrawStaticObject{
	public DrawObject(GamePanel gp, String name){
		super(gp, name, false, 1);
		this.oObject = this.sObject;
	    BufferedImage image = uTool.setup("data/Object/"+name+".png", gp.tileSize, gp.tileSize);
	    setImage(image);
	    
	}
}
