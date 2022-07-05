package Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameSetting.GamePanel;
import GameSetting.UtilityTool;
import Object.StaticObject;

public class DrawStaticObject extends DrawOriginObject{
	public UtilityTool uTool;
	public StaticObject sObject;
	
	public DrawStaticObject(GamePanel gp, String name, int worldX, int worldY, boolean colision, int type) {
        setGp(gp);
        this.oObject = this.sObject;
        setCollision(colision);
        sObject.setName(name);
        sObject.setWorldX(worldX);
        sObject.setWorldX(worldY);
        sObject.setType(type);
        setSolidArea(new Rectangle(0, 0, gp.tileSize, gp.tileSize));
        
        this.uTool = new UtilityTool();
    }
	
	public DrawStaticObject(GamePanel gp, String name,boolean colision,int type) {
        setGp(gp);
        setCollision(colision);

        sObject = new StaticObject(name,colision,type);
        setSolidArea(new Rectangle(0, 0, gp.tileSize, gp.tileSize));
        
        this.uTool = new UtilityTool();
    }
	
	public void draw(Graphics2D g2){

        BufferedImage image = getImage();
        
        int screenX =  sObject.getWorldX() - gp.drawP.getPlayer().getWorldX()  + gp.drawP.getScreenX();
        int screenY =  sObject.getWorldY() - gp.drawP.getPlayer().getWorldY()  + gp.drawP.getScreenY();

        if(sObject.getWorldX() + gp.tileSize> gp.drawP.getPlayer().getWorldX() - gp.drawP.getScreenX() &&
        		sObject.getWorldX() - gp.tileSize< gp.drawP.getPlayer().getWorldX() + gp.drawP.getScreenX() &&
        		sObject.getWorldY() + gp.tileSize> gp.drawP.getPlayer().getWorldY() - gp.drawP.getScreenY() &&
        		sObject.getWorldY() - gp.tileSize< gp.drawP.getPlayer().getWorldY() + gp.drawP.getScreenY()){
            
            
            if(isDisappearing() == true){
                
                disappearAnimation(g2);
            }
            
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            changeAlpha(g2, 1f);
            
            if(gp.keyH.drawSolidArea == true){
                g2.setColor(Color.red);
                g2.drawRect(screenX + getSolidArea().x, screenY + getSolidArea().y, getSolidArea().width, getSolidArea().height);
            }
        }
    }
}
