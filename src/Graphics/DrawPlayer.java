package Graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Player;
import GameSetting.GamePanel;
import GameSetting.KeyHandler;
import GameSetting.UtilityTool;

public class DrawPlayer extends DrawEntity{
	Player player;
	public DrawPlayer(GamePanel gp, KeyHandler keyH) {
        
        super(gp, "Player", 6, 4, 0);
        
//        player.setKeyH(keyH);
        this.player = new Player(keyH);
        this.entity = this.player;
        this.oObject = this.player;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        
        getSolidArea().x = 8;
        getSolidArea().y = 16;
        getSolidArea().width = getGp().tileSize - 16;
        getSolidArea().height = getGp().tileSize - 16;
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
        
        getAttackArea().width = 36;
        getAttackArea().height = 36;
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        
        this.playerKunai = new DrawKunai[player.getHasKunai()];
    }
	
	public void setDefaultValues(){

		player.setWorldX(getGp().tileSize*23);
		player.setWorldY(getGp().tileSize*21);
       
		player.setLife(player.getMaxLife());
    }
	
	public DrawKunai[] playerKunai;
	private final int screenX;
    private final int screenY;
	private int standCounter = 0;
    private int kunaiAttackCounter = 0;
    
	public DrawKunai[] getPlayerKunai() {
		return playerKunai;
	}

	public void setPlayerKunai(DrawKunai[] playerKunai) {
		this.playerKunai = playerKunai;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getStandCounter() {
		return standCounter;
	}

	public void setStandCounter(int standCounter) {
		this.standCounter = standCounter;
	}

	public int getKunaiAttackCounter() {
		return kunaiAttackCounter;
	}

	public void setKunaiAttackCounter(int kunaiAttackCounter) {
		this.kunaiAttackCounter = kunaiAttackCounter;
	}
	
	
	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public void getPlayerImage(){
	       
        UtilityTool uTool = new UtilityTool();
        
        setUp1(uTool.setup("data/Player/boy_up_1.png", gp.tileSize, gp.tileSize));
        setUp2(uTool.setup("data/Player/boy_up_2.png", gp.tileSize, gp.tileSize));
        setDown1(uTool.setup("data/Player/boy_down_1.png", gp.tileSize, gp.tileSize));
        setDown2(uTool.setup("data/Player/boy_down_2.png", gp.tileSize, gp.tileSize));
        setLeft1(uTool.setup("data/Player/boy_left_1.png", gp.tileSize, gp.tileSize));
        setLeft2(uTool.setup("data/Player/boy_left_2.png", gp.tileSize, gp.tileSize));
        setRight1(uTool.setup("data/Player/boy_right_1.png", gp.tileSize, gp.tileSize));
        setRight2(uTool.setup("data/Player/boy_right_2.png", gp.tileSize, gp.tileSize));
    }
    
    public void getPlayerAttackImage(){
        
        UtilityTool uTool = new UtilityTool();
        
        setAttackUp1(uTool.setup("data/Player/Attacking sprites/boy_attack_up_1.png", gp.tileSize, gp.tileSize*2));
        setAttackUp2(uTool.setup("data/Player/Attacking sprites/boy_attack_up_2.png", gp.tileSize, gp.tileSize*2));
        setAttackDown1(uTool.setup("data/Player/Attacking sprites/boy_attack_down_1.png", gp.tileSize, gp.tileSize*2));
        setAttackDown2(uTool.setup("data/Player/Attacking sprites/boy_attack_down_2.png", gp.tileSize, gp.tileSize*2));
        setAttackLeft1(uTool.setup("data/Player/Attacking sprites/boy_attack_left_1.png", gp.tileSize*2, gp.tileSize));
        setAttackLeft2(uTool.setup("data/Player/Attacking sprites/boy_attack_left_2.png", gp.tileSize*2, gp.tileSize));
        setAttackRight1(uTool.setup("data/Player/Attacking sprites/boy_attack_right_1.png", gp.tileSize*2, gp.tileSize));
        setAttackRight2(uTool.setup("data/Player/Attacking sprites/boy_attack_right_2.png", gp.tileSize*2, gp.tileSize));
        
    }
    public void update(){

        // KUNAI ATTACK
        if(player.getKeyH().kPressed == true){
            System.out.println("Kuai Attacking = true");
            player.setKunaiAttacking(true);
        }

        if(player.isKunaiAttacking() == true){
        	player.kunaiAttack(this);
        }
        if(player.isAttacking() == true){
        	player.attacking(this);
        }
        else if(player.getKeyH().upPressed == true || player.getKeyH().downPressed == true ||
        		player.getKeyH().leftPressed == true || player.getKeyH().rightPressed == true || 
        				player.getKeyH().enterPressed == true){

            if(player.getKeyH().upPressed == true){ setDirection("up");
            }else if(player.getKeyH().downPressed == true){ setDirection("down");
            }else if(player.getKeyH().leftPressed == true){ setDirection("left");
            }else if(player.getKeyH().rightPressed == true){ setDirection("right"); }

            //moving = true;

            // CHECK TILE COLLISION
            setCollisionOn(false);
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            player.pickUpObject(objIndex, this);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.drawN);
            player.interactNPC(npcIndex, this);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.drawM);
            player.contactMonster(monsterIndex, this);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            
            if(isCollisionOn() == false && player.getKeyH().enterPressed == false){
                switch(getDirection()){
                    case "up": player.setWorldY(player.getWorldY()-player.getSpeed()); break;
                    case "down": player.setWorldY(player.getWorldY()+player.getSpeed()); break;
                    case "left": player.setWorldX(player.getWorldX()-player.getSpeed()); break;
                    case "right": player.setWorldX(player.getWorldX()+player.getSpeed()); break; 
                }
            }
                        
            setSpriteCounter(getSpriteCounter()+1);
                if(getSpriteCounter() > 12){
                    if(getSpriteNum() == 1){
                        setSpriteNum(2);
                    }
                    else if(getSpriteNum() ==2){
                        setSpriteNum(1);
                    }
                    setSpriteCounter(0);
                }
        }
        else {
        	setStandCounter(getStandCounter()+1);

            if(getStandCounter() == 20){
                setSpriteNum(1);
                setStandCounter(0);

            }
        }
        
        // This need to be outside of key if statement!
        if(isInvincible() == true){
            setInvincibleCounter(getInvincibleCounter()+1);
            if(getInvincibleCounter() > 60){
                setInvincible(false);
                setInvincibleCounter(0);
            }
        }   
    }
public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        int attackWorldX = screenX;
        int attackWorldY = screenY;
        
        switch (getDirection()) {
        case "up":
            if(player.isAttacking() == false){
                if(getSpriteNum() == 1){ image = getUp1(); }
                if(getSpriteNum() == 2){ image = getUp2(); }
            }
            if(player.isAttacking() == true){
                tempScreenY -= gp.tileSize;
                if(getSpriteNum() == 1){ image = getAttackUp1(); }
                if(getSpriteNum() == 2){ image = getAttackUp2(); }
            }
            attackWorldY = attackWorldY - getAttackArea().height -4;
            break;
        case "down":
            if(player.isAttacking() == false){
                if(getSpriteNum() == 1){ image = getDown1(); }
                if(getSpriteNum() == 2){ image = getDown2(); } 
            }
            if(player.isAttacking() == true){
                if(getSpriteNum() == 1){ image = getAttackDown1(); }
                if(getSpriteNum() == 2){ image = getAttackDown2(); }
            }
            attackWorldY += getSolidArea().height;
            break;
        case "left":
            if(player.isAttacking() == false){
                if(getSpriteNum() == 1){ image = getLeft1(); }
                if(getSpriteNum() == 2){ image = getLeft2(); }
            }
            if(player.isAttacking() == true){
                tempScreenX -= gp.tileSize;
                if(getSpriteNum() == 1){ image = getAttackLeft1(); }
                if(getSpriteNum() == 2){ image = getAttackLeft2(); }
            }
            attackWorldX = attackWorldX - getAttackArea().width - 4;
            break;
        case "right":
            if(player.isAttacking() == false){
                if(getSpriteNum() == 1){ image = getRight1(); }
                if(getSpriteNum() == 2){ image = getRight2(); }
            }
            if(player.isAttacking() == true){
                if(getSpriteNum() == 1){ image = getAttackRight1(); }
                if(getSpriteNum() == 2){ image = getAttackRight2(); }
            }
            attackWorldX += getSolidArea().width;
            break;
        }
        
        if(isInvincible() == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
        if(gp.keyH.drawSolidArea == true){
//            g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
            g2.setColor(Color.red);
            g2.drawRect(screenX + getSolidArea().x, screenY + getSolidArea().y, getSolidArea().width, getSolidArea().height);
            g2.drawRect(attackWorldX + getSolidArea().x, attackWorldY + getSolidArea().y, getAttackArea().width+4, getAttackArea().height+4);
        }
    }
}
