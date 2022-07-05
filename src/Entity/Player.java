/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;


import GameSetting.KeyHandler;
import Graphics.DrawKunai;
import Graphics.DrawPlayer;

/**
 *
 * @author HOANG XUAN BACH
 */
public class Player extends Entity{
    
    private KeyHandler keyH;
    
    private int hasKunai = 10;
    private int hasKey = 0;
    public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	private boolean kunaiAttacking = false;
    private boolean attacking = false;


    public int getHasKey() {
        return hasKey;
    }

    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }

    public boolean isKunaiAttacking() {
        return kunaiAttacking;
    }

    public void setKunaiAttacking(boolean kunaiAttacking) {
        this.kunaiAttacking = kunaiAttacking;
    }
    
    public int getHasKunai() {
        return hasKunai;
    }

    public void setHasKunai(int hasKunai) {
        this.hasKunai = hasKunai;
    }
    
    public Player(KeyHandler keyH) {
        
        super("Player", 6, 4, 0);
        
        this.keyH = keyH;

    }
       
    
    
    public KeyHandler getKeyH() {
		return keyH;
	}

	public void setKeyH(KeyHandler keyH) {
		this.keyH = keyH;
	}

    
    
        
    public void kunaiAttack(DrawPlayer Dplayer) {
        
        if(Dplayer.getKunaiAttackCounter() == 0){

            if(hasKunai > 0){
            
                System.out.println("Kunai created!");
                int startAttackX = this.getWorldX() + Dplayer.getSolidArea().x;
                int startAttackY = this.getWorldY() + Dplayer.getSolidArea().y;

                Dplayer.playerKunai[hasKunai-1] = new DrawKunai(Dplayer.getGp(), "kunai");

                switch(Dplayer.getDirection()){
                    case "up": startAttackY -= Dplayer.playerKunai[hasKunai-1].getSolidArea().height; break;
                    case "down": startAttackY += Dplayer.getSolidArea().height; break;
                    case "left": startAttackX -= Dplayer.playerKunai[hasKunai-1].getSolidArea().height; break;
                    case "right": startAttackX += Dplayer.getSolidArea().height; break;
                }

                Dplayer.playerKunai[hasKunai-1].setDirection(Dplayer.getDirection());
                Dplayer.playerKunai[hasKunai-1].kunai.setWorldX(startAttackX);
                Dplayer.playerKunai[hasKunai-1].kunai.setWorldY(startAttackY);
                Dplayer.playerKunai[hasKunai-1].setStartAttackX(startAttackX);
                Dplayer.playerKunai[hasKunai-1].setStartAttackY(startAttackY);
                Dplayer.playerKunai[hasKunai-1].kunai.setSpeed(this.getSpeed()+3);
                hasKunai--;
            }
        }
        
        Dplayer.setKunaiAttackCounter(Dplayer.getKunaiAttackCounter() + 1);
        if(Dplayer.getKunaiAttackCounter() > 120){
        	Dplayer.setKunaiAttackCounter(0);
            kunaiAttacking = false;
        }
    }
    
    public void attacking(DrawPlayer Dplayer){
        
    	Dplayer.setSpriteCounter(Dplayer.getSpriteCounter()+1);
        
        if(Dplayer.getSpriteCounter() <= 5){
        	Dplayer.setSpriteNum(1);
        }
        if(Dplayer.getSpriteCounter() > 5 && Dplayer.getSpriteCounter() <= 25){
        	Dplayer.setSpriteNum(2);
            
            // Save the current worldX, worldY, solidArea
            int currentWorldX = getWorldX();
            int currentWorldY = getWorldY();
            int solidAreaWith = Dplayer.getSolidArea().width;
            int solidAreaHeight = Dplayer.getSolidArea().height;
            
            // Adjust player/s worldX/Y for the attackArea
            switch(Dplayer.getDirection()){
                case "up": setWorldY(getWorldY() - Dplayer.getAttackArea().height); break;
                case "down": setWorldY(getWorldY() + Dplayer.getSolidArea().height); break;
                case "left": setWorldX(getWorldX() - Dplayer.getAttackArea().width); break;
                case "right": setWorldX(getWorldX() + Dplayer.getSolidArea().width); break;
            }
            
            Dplayer.getSolidArea().width = Dplayer.getAttackArea().width;
            Dplayer.getSolidArea().height = Dplayer.getAttackArea().height;
            
            // Check monster collision with the updated worldX, worldY and solidArea
            int monterIndex = Dplayer.gp.cChecker.checkEntity(Dplayer, Dplayer.gp.drawM);
            damageMonster(monterIndex, Dplayer);
            
            // After checking collision, restore the original data
            setWorldX(currentWorldX);
            setWorldY(currentWorldY);
            Dplayer.getSolidArea().width = solidAreaWith;
            Dplayer.getSolidArea().height = solidAreaHeight;
            
        }
        if(Dplayer.getSpriteCounter() > 25){
        	Dplayer.setSpriteNum(1);
        	Dplayer.setSpriteCounter(0);
        	setAttacking(false);
        }
    }
    
    public void pickUpObject(int i, DrawPlayer Dplayer){
        
        if(i != 999){
            
            String objectName = Dplayer.gp.dobj[i].sObject.getName();
            
            switch(objectName){
                case "Key":
                	Dplayer.gp.playSE(1);
                    hasKey++;
                    Dplayer.gp.dobj[i] = null;
                    Dplayer.gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0){
                    	Dplayer.gp.dobj[i] = null;
                        hasKey--;
                        Dplayer.gp.playSE(3);
                        Dplayer.gp.ui.showMessage("You opened the door!");
                    }
                    else {
                    	Dplayer.gp.ui.showMessage("You need the key!");
                    }
                    break;
                case "Boots":
                	Dplayer.gp.ui.showMessage("Speed up!");
                	Dplayer.gp.playSE(2);
                    setSpeed(getSpeed()+2);
                    Dplayer.gp.dobj[i] = null;
                    break;
                case "Chest":
                	Dplayer.gp.ui.gameFinished = true;
                	Dplayer.gp.stopMusic();
                	Dplayer.gp.playSE(4);
                    break;
          }
        }    
    }
    
    public void interactNPC(int i, DrawPlayer Dplayer) {
        
        if(Dplayer.gp.keyH.enterPressed == true){
            if(i != 999){
            	Dplayer.gp.gameState = Dplayer.gp.dialogueState;
            	Dplayer.gp.drawN[i].speak();
            }
            else {
            	Dplayer.gp.playSE(7);
                setAttacking(true);
            }
        }
        
    }

    public void contactMonster(int i, DrawPlayer Dplayer) {
        
        if(i != 999){
            
            if(Dplayer.isInvincible() == false){
            	Dplayer.gp.playSE(6);
                setLife(getLife()-1);
                Dplayer.setInvincible(true);
            }

        }
    }
    
    public void damageMonster(int i, DrawPlayer Dplayer){
        
        if( i != 999){
            
            if(Dplayer.gp.drawM[i].isInvincible() == false){
                
            	Dplayer.gp.playSE(5);
            	Dplayer.gp.drawM[i].entity.setLife(Dplayer.gp.drawM[i].entity.getLife()-1);
//              System.out.println("Monster ["+i+"] attacked: "+getGp().monster[i].getLife());
            	Dplayer.gp.drawM[i].setInvincible(true);
            	Dplayer.gp.drawM[i].entity.damageReaction();
                
                if(Dplayer.gp.drawM[i].entity.getLife() <= 0){
                	Dplayer.gp.drawM[i].setDying(true);
                }
            }
        }
    }

}
