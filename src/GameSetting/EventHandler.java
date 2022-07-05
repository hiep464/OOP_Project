/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameSetting;


/**
 *
 * @author HOANG XUAN BACH
 */
public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][];
    
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
         
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            
            eventRect[col][row] = new EventRect(23, 23, 2, 2);
            eventRect[col][row].eventRectDeufaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDeufaultY = eventRect[col][row].y;
            col++;
            if(col == gp.maxWorldCol){
                row++;
                col = 0;
            }
        }   
    }
    
    public void checkEvent(){
        
        // Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.drawP.getPlayer().getWorldX() - previousEventX);
        int yDistance = Math.abs(gp.drawP.getPlayer().getWorldY() - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        
        if(canTouchEvent == true){
            if(hit(27, 16, "right") == true){ damgePit(27, 16, gp.dialogueState); }
            if(hit(23, 19, "any") == true){ damgePit(23, 19, gp.dialogueState); }
            if(hit(23, 12, "up") == true) { healingPool(23, 12, gp.dialogueState); }
    
        }
    }
    
    public boolean hit(int eventCol, int eventRow, String reqDirection){
        
        boolean hit = false;
        
        gp.drawP.getSolidArea().x = gp.drawP.getPlayer().getWorldX() + gp.drawP.getSolidArea().x;
        gp.drawP.getSolidArea().y = gp.drawP.getPlayer().getWorldY() + gp.drawP.getSolidArea().y;
        eventRect[eventCol][eventRow].x = eventCol*gp.tileSize + eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y = eventRow*gp.tileSize + eventRect[eventCol][eventRow].y;
        
        if(gp.drawP.getSolidArea().intersects(eventRect[eventCol][eventRow]) && eventRect[eventCol][eventRow].eventDone == false){
            if(gp.drawP.getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                
                hit = true;
                previousEventX = gp.drawP.getPlayer().getWorldX();
                previousEventY = gp.drawP.getPlayer().getWorldY();
                
            }
        }
        
        gp.drawP.getSolidArea().x = gp.drawP.getSolidAreaDefaultX();
        gp.drawP.getSolidArea().y = gp.drawP.getSolidAreaDefaultY();
        eventRect[eventCol][eventRow].x = eventRect[eventCol][eventRow].eventRectDeufaultX;
        eventRect[eventCol][eventRow].y = eventRect[eventCol][eventRow].eventRectDeufaultY;
        
        return hit;
    }

    public void teleport(int col, int row, int gameState){
        
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.drawP.getPlayer().setWorldX(gp.tileSize * 37);
        gp.drawP.getPlayer().setWorldX(gp.tileSize * 10);
                
    }
    
    public void damgePit(int col, int row, int gameState) {
        
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall in to a pit";
        gp.drawP.getPlayer().setLife(gp.drawP.getPlayer().getLife()-1);
        canTouchEvent = false;
    }
    
    public void healingPool(int col, int row, int gameState){
        
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water.\nYour life has been recovered.";
            gp.drawP.getPlayer().setLife(gp.drawP.getPlayer().getMaxLife());
            
        }
    }
}
