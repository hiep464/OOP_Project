/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameSetting;

import java.util.Comparator;

import Graphics.DrawOriginObject;
/**
 *
 * @author HOANG XUAN BACH
 */
public class EntityComparator implements Comparator<DrawOriginObject> {

    @Override
    public int compare(DrawOriginObject sv1, DrawOriginObject sv2) {
                
        int result = Integer.compare(sv1.getoObject().getWorldY(), sv2.getoObject().getWorldY());
        return result;
    }
    
}
