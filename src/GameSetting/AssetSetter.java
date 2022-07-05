/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameSetting;

import Graphics.DrawMonS;
import Graphics.DrawNpc;
import Graphics.DrawObject;
import Object.OBJ_Boots;
import Object.OBJ_Door;
import Object.OBJ_Key;
import Object.OBJ_Kunai;
/**
 *
 * @author HOANG XUAN BACH
 */
public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject(){
    	
    	gp.dobj[0] = new DrawObject(gp, "door");
    	gp.dobj[0].setCollision(true);
    	gp.dobj[0].sObject = new OBJ_Door();
        gp.dobj[0].sObject.setWorldX(23 * gp.tileSize);
        gp.dobj[0].sObject.setWorldY(19 * gp.tileSize);
        
        gp.dobj[1] = new DrawObject(gp, "door");
        gp.dobj[1].setCollision(true);
        gp.dobj[1].sObject = new OBJ_Door();
        gp.dobj[1].sObject.setWorldX(23 * gp.tileSize);
        gp.dobj[1].sObject.setWorldY(24 * gp.tileSize);
        
        gp.dobj[2] = new DrawObject(gp, "key");
        gp.dobj[2].sObject = new OBJ_Key();
        gp.dobj[2].sObject.setWorldX(23 * gp.tileSize);
        gp.dobj[2].sObject.setWorldY(20 * gp.tileSize);
        
        gp.dobj[3] = new DrawObject(gp, "boots");
        gp.dobj[3].sObject = new OBJ_Boots();
        gp.dobj[3].sObject.setWorldX(23 * gp.tileSize);
        gp.dobj[3].sObject.setWorldY(21 * gp.tileSize);
        
        gp.dobj[4] = new DrawObject(gp, "key");
        gp.dobj[4].sObject = new OBJ_Key();
        gp.dobj[4].sObject.setWorldX(23 * gp.tileSize);
        gp.dobj[4].sObject.setWorldY(22 * gp.tileSize);
        
        gp.dobj[5] = new DrawObject(gp, "kunai");
        gp.dobj[5].sObject = new OBJ_Kunai();
        gp.dobj[5].sObject.setWorldX(23 * gp.tileSize);
        gp.dobj[5].sObject.setWorldY(23 * gp.tileSize);
        
        gp.dobj[6] = new DrawObject(gp, "kunai");
        gp.dobj[6].sObject = new OBJ_Kunai();
//        System.out.println(gp.dobj[6].getImage());
        gp.dobj[6].setImage(gp.uTool.scaleImage(gp.dobj[6].getImage(), gp.tileSize/2, gp.tileSize/2));
        
        gp.dobj[7] = new DrawObject(gp, "key");
//        System.out.println(gp.dobj[7]);
        gp.dobj[7].sObject = new OBJ_Key();
        gp.dobj[7].setImage(gp.uTool.scaleImage(gp.dobj[7].getImage(), gp.tileSize/2, gp.tileSize/2));
//        System.out.println(gp.dobj[7].getImage());
        }
    
    public void setNPC(){
        
    	gp.drawN[0] = new DrawNpc(gp);
//    	System.out.println(gp.drawN[0].getDirection());
//        gp.drawN[0].entity = new NPC_OldMan();
        gp.drawN[0].entity.setWorldX(21 * gp.tileSize);
        gp.drawN[0].entity.setWorldY(21 * gp.tileSize);
        
        gp.drawN[1] = new DrawNpc(gp);
//        gp.drawN[1].entity = new NPC_OldMan();
        gp.drawN[1].entity.setWorldX(22 * gp.tileSize);
        gp.drawN[1].entity.setWorldY(21 * gp.tileSize);
        
        gp.drawN[2] = new DrawNpc(gp);
//        gp.drawN[2].entity = new NPC_OldMan();
        gp.drawN[2].entity.setWorldX(20 * gp.tileSize);
        gp.drawN[2].entity.setWorldY(21 * gp.tileSize);
    }
    
    public void setMonster(){
        
    	gp.drawM[0] = new DrawMonS(gp);
//        gp.drawM[0].entity = new MON_GreenSlime();
        gp.drawM[0].entity.setWorldX(23 * gp.tileSize);
        gp.drawM[0].entity.setWorldY(36 * gp.tileSize);
        
        gp.drawM[1] = new DrawMonS(gp);
//        gp.drawM[1].entity = new MON_GreenSlime();
        gp.drawM[1].entity.setWorldX(23 * gp.tileSize);
        gp.drawM[1].entity.setWorldY(37 * gp.tileSize);
        
        gp.drawM[2] = new DrawMonS(gp);
//        gp.drawM[2].entity = new MON_GreenSlime();
        gp.drawM[2].entity.setWorldX(23 * gp.tileSize);
        gp.drawM[2].entity.setWorldY(35 * gp.tileSize);
    }
}
