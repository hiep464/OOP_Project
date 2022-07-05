/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Object.OriginObject;

/**
 *
 * @author HOANG XUAN BACH
 */
public class Entity extends OriginObject{
    
    
    // CHARATER STATUS
    private int maxLife;
    private int life;

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    public Entity(String name, int maxLife, int speed,int type) {
        this.maxLife = maxLife;
        this.life = this.maxLife;
        setSpeed(speed);
        
        setName(name);
        setType(type);

    }
    
    
    
    
    public void damageReaction(){}
    
    
    
}
