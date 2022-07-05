/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

/**
 *
 * @author HOANG XUAN BACH
 */
public class OriginObject {
    
    // STATUS
    private String name;
//    private String direction = null;
    private int speed = 0;
    private int worldX, worldY;
    private int type; // 0:Player, 1:StaticObject, 2:Monster, 3:NPC
    
    

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
   
}
