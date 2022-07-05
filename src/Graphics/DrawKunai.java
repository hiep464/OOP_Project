package Graphics;

import GameSetting.GamePanel;
import Object.OBJ_Kunai;

public class DrawKunai extends DrawObject{
	public DrawKunai(GamePanel gp, String name) {
		super(gp, name);
		// TODO Auto-generated constructor stub
		distance = gp.tileSize*5;
	}

	public OBJ_Kunai kunai;
	private int distance;
    private int startAttackX;
    private int startAttackY;

    public int getStartAttackX() {
        return startAttackX;
    }

    public void setStartAttackX(int startAttackX) {
        this.startAttackX = startAttackX;
    }

    public int getStartAttackY() {
        return startAttackY;
    }

    public void setStartAttackY(int startAttackY) {
        this.startAttackY = startAttackY;
    }
    
    public void update(){
        
        int distanceX = Math.abs(startAttackX - kunai.getWorldX());
        int distanceY = Math.abs(startAttackY - kunai.getWorldY());
        if(Math.max(distanceX, distanceY) < distance){
            
            setCollisionOn(false);
            
            getGp().cChecker.checkTile(this);
            getGp().cChecker.checkEntity(this, getGp().drawN);
            
            int indexMonster =  getGp().cChecker.checkEntity(this, getGp().drawM);
            inflictDamage(indexMonster);
                        
            getGp().cChecker.checkObject(this, false);
            
            System.out.println(isCollisionOn());
            
            if(isCollisionOn() == false){
                switch(getDirection()){
                case "up": kunai.setWorldY(kunai.getWorldY()-kunai.getSpeed()); break;
                case "down": kunai.setWorldY(kunai.getWorldY()+kunai.getSpeed()); break;
                case "left": kunai.setWorldX(kunai.getWorldX()-kunai.getSpeed()); break;
                case "right": kunai.setWorldX(kunai.getWorldX()+kunai.getSpeed()); break;
                }
            }else setDisappearing(true);
        }
        else {
            setDisappearing(true);
        }
    }
    
    public void inflictDamage(int i){
        
        if(i != 999){
            if(getGp().drawM[i].isInvincible() == false){
            
                getGp().playSE(6);
                getGp().drawM[i].entity.setLife(getGp().drawM[i].entity.getLife()-1);
                getGp().drawM[i].entity.damageReaction();
                getGp().drawM[i].setInvincible(true);

                if(getGp().drawM[i].entity.getLife() <= 0){

                    getGp().drawM[i].setDying(true);
                }
            }
        }
    }
}

