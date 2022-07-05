/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;


/**
 *
 * @author HOANG XUAN BACH
 */
public class StaticObject extends OriginObject{
    
    public StaticObject(String name, int worldX, int worldY, boolean colision, int type) {
//        setCollision(colision);
        setName(name);
        setWorldX(worldX);
        setWorldX(worldY);
        setType(type);
//        setSolidArea(new Rectangle(0, 0, gp.tileSize, gp.tileSize));
       
    }
    
    public StaticObject(String name,boolean colision,int type) {
//        setCollision(colision);
        setName(name);
        setType(type);
//        setSolidArea(new Rectangle(0, 0, gp.tileSize, gp.tileSize));
        
    }
        
}
