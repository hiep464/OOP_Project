/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameSetting;

import Graphics.DrawEntity;
import Graphics.DrawOriginObject;


/**
 *
 * @author HOANG XUAN BACH
 */
public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    
    public void checkTile(DrawOriginObject DoObj){
        
        int entityLeftWorldX = DoObj.getoObject().getWorldX() + DoObj.getSolidArea().x;
        int entityRightWorldX = DoObj.getoObject().getWorldX() + DoObj.getSolidArea().x + DoObj.getSolidArea().width;
        int entityTopWorldY = DoObj.getoObject().getWorldY() + DoObj.getSolidArea().y;
        int entityBottomWorldY = DoObj.getoObject().getWorldY() + DoObj.getSolidArea().y + DoObj.getSolidArea().height;
    
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(DoObj.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - DoObj.getoObject().getSpeed())/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.dtile[tileNum1].isCollision() == true || gp.tileM.dtile[tileNum2].isCollision() == true){
                	DoObj.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + DoObj.getoObject().getSpeed())/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.dtile[tileNum1].isCollision() == true || gp.tileM.dtile[tileNum2].isCollision() == true){
                	DoObj.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - DoObj.getoObject().getSpeed())/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.dtile[tileNum1].isCollision() == true || gp.tileM.dtile[tileNum2].isCollision() == true){
                	DoObj.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + DoObj.getoObject().getSpeed())/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.dtile[tileNum1].isCollision() == true || gp.tileM.dtile[tileNum2].isCollision() == true){
                	DoObj.setCollisionOn(true);
                }
                break;
        }
    }
    
    public int checkObject(DrawOriginObject DoObj, boolean player){
        
        int index = 999;
        
        for(int i = 0; i < gp.dobj.length; i++){
            
            if(gp.dobj[i] != null){
                
                // Get entity's solid area position
            	DoObj.getSolidArea().x = DoObj.getoObject().getWorldX() + DoObj.getSolidArea().x;
            	DoObj.getSolidArea().y = DoObj.getoObject().getWorldY() + DoObj.getSolidArea().y;
                
                // Get the object's solid area position
                gp.dobj[i].getSolidArea().x = gp.dobj[i].sObject.getWorldX() + gp.dobj[i].getSolidArea().x;
                gp.dobj[i].getSolidArea().y = gp.dobj[i].sObject.getWorldY() + gp.dobj[i].getSolidArea().y;
                
                switch(DoObj.getDirection()) {
                case "up":  DoObj.getSolidArea().y -= DoObj.getoObject().getSpeed(); break;
                case "down": DoObj.getSolidArea().y += DoObj.getoObject().getSpeed(); break;
                case "left": DoObj.getSolidArea().x -= DoObj.getoObject().getSpeed(); break;
                case "right": DoObj.getSolidArea().x += DoObj.getoObject().getSpeed(); break;
                }
                
                if(DoObj.getSolidArea().intersects(gp.dobj[i].getSolidArea())){
                    if(gp.dobj[i].isCollision() == true){
                    	DoObj.setCollisionOn(true);
                    }
                    if(player == true){
                        index = i;
                    }
                }
                    
                
                DoObj.getSolidArea().x = DoObj.getSolidAreaDefaultX();
                DoObj.getSolidArea().y = DoObj.getSolidAreaDefaultY();
                gp.dobj[i].getSolidArea().x = gp.dobj[i].getSolidAreaDefaultX();
                gp.dobj[i].getSolidArea().y = gp.dobj[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }
    
    // NPC OR MONSTER
    public int checkEntity(DrawOriginObject DoObj, DrawEntity[] target){
        
        int index = 999;
        
        for(int i = 0; i < target.length; i++){
            
            if(target[i] != null){
                
                // Get entity's solid area position
            	DoObj.getSolidArea().x = DoObj.getoObject().getWorldX() + DoObj.getSolidArea().x;
            	DoObj.getSolidArea().y = DoObj.getoObject().getWorldY() + DoObj.getSolidArea().y;
                
                // Get the object's solid area position
                target[i].getSolidArea().x = target[i].entity.getWorldX() + target[i].getSolidArea().x;
                target[i].getSolidArea().y = target[i].entity.getWorldY() + target[i].getSolidArea().y;
                
                switch(DoObj.getDirection()) {
                case "up": DoObj.getSolidArea().y -= DoObj.getoObject().getSpeed(); break;
                case "down": DoObj.getSolidArea().y += DoObj.getoObject().getSpeed(); break;
                case "left": DoObj.getSolidArea().x -= DoObj.getoObject().getSpeed(); break;
                case "right": DoObj.getSolidArea().x += DoObj.getoObject().getSpeed(); break;
                }
                
                if(DoObj.getSolidArea().intersects(target[i].getSolidArea())){
                    if(target[i].entity != DoObj.getoObject()){
                    	DoObj.setCollisionOn(true);
                        index = i;
                    }
                }

                DoObj.getSolidArea().x = DoObj.getSolidAreaDefaultX();
                DoObj.getSolidArea().y = DoObj.getSolidAreaDefaultY();
                target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
                target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
            }
        }
        return index;
    } 
    
    public boolean checkPlayer(DrawOriginObject doOBJ){
        
        boolean contactPlayer = false;
        // Get entity's solid area position
        doOBJ.getSolidArea().x = doOBJ.getoObject().getWorldX() + doOBJ.getSolidArea().x;
        doOBJ.getSolidArea().y = doOBJ.getoObject().getWorldY() + doOBJ.getSolidArea().y;

        // Get the object's solid area position
        gp.drawP.getSolidArea().x = gp.drawP.getPlayer().getWorldX() + gp.drawP.getSolidArea().x;
        gp.drawP.getSolidArea().y = gp.drawP.getPlayer().getWorldY() + gp.drawP.getSolidArea().y;

        switch(doOBJ.getDirection()) {
        case "up": doOBJ.getSolidArea().y -= doOBJ.getoObject().getSpeed(); break;
        case "down": doOBJ.getSolidArea().y += doOBJ.getoObject().getSpeed(); break;
        case "left": doOBJ.getSolidArea().x -= doOBJ.getoObject().getSpeed(); break;
        case "right": doOBJ.getSolidArea().x += doOBJ.getoObject().getSpeed(); break;
        }
        
        if(doOBJ.getSolidArea().intersects(gp.drawP.getSolidArea())){
        	doOBJ.setCollisionOn(true);
            contactPlayer = true;
        }   
        doOBJ.getSolidArea().x = doOBJ.getSolidAreaDefaultX();
        doOBJ.getSolidArea().y = doOBJ.getSolidAreaDefaultY();
        gp.drawP.getSolidArea().x = gp.drawP.getSolidAreaDefaultX();
        gp.drawP.getSolidArea().y = gp.drawP.getSolidAreaDefaultY();
        
        return contactPlayer;
    }
}
