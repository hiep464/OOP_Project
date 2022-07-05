/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import GameSetting.GamePanel;
import GameSetting.UtilityTool;
import Graphics.DrawTile;

import java.awt.image.BufferedImage;

/**
 *
 * @author HOANG XUAN BACH
 */
public class TileManager {
    
    UtilityTool uTool =  new UtilityTool();
    GamePanel gp;
    public DrawTile[] dtile;
    public int mapTileNum[][];
    

    public TileManager(GamePanel gp) {
        
        this.gp = gp;
        dtile = new DrawTile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap("data/Map/worldV2.txt"); 
    }
    
    public void getTileImage(){
        
        //  PLACE HOLDER
        setupTileObject(0, "grass00", false);
        setupTileObject(1, "grass00", false);
        setupTileObject(2, "grass00", false);
        setupTileObject(3, "grass00", false);
        setupTileObject(4, "grass00", false);
        setupTileObject(5, "grass00", false);
        setupTileObject(6, "grass00", false);
        setupTileObject(7, "grass00", false);
        setupTileObject(8, "grass00", false);
        setupTileObject(9, "grass00", false);
        // PLACE HOLDER
        
        setupTileObject(10, "grass00", false);
        setupTileObject(11, "grass01", false);
        setupTileObject(12, "water00", true);
        setupTileObject(13, "water01", true);
        setupTileObject(14, "water02", true);
        setupTileObject(15, "water03", true);
        setupTileObject(16, "water04", true);
        setupTileObject(17, "water05", true);
        setupTileObject(18, "water06", true);
        setupTileObject(19, "water07", true);
        setupTileObject(20, "water08", true);
        setupTileObject(21, "water09", true);
        setupTileObject(22, "water10", true);
        setupTileObject(23, "water11", true);
        setupTileObject(24, "water12", true);
        setupTileObject(25, "water13", true);
        setupTileObject(26, "road00", false);
        setupTileObject(27, "road01", false);
        setupTileObject(28, "road02", false);
        setupTileObject(29, "road03", false);
        setupTileObject(30, "road04", false);
        setupTileObject(31, "road05", false);
        setupTileObject(32, "road06", false);
        setupTileObject(33, "road07", false);
        setupTileObject(34, "road08", false);
        setupTileObject(35, "road09", false);
        setupTileObject(36, "road10", false);
        setupTileObject(37, "road11", false);
        setupTileObject(38, "road12", false);
        setupTileObject(39, "earth", false);
        setupTileObject(40, "wall", true);
        setupTileObject(41, "tree", true);
        
        
    }
    
    public void setupTileObject(int index, String imageName, boolean collision){
        
        dtile[index] = new DrawTile(gp, imageName, collision);
        BufferedImage image = uTool.setup("data/TilesN/"+imageName+".png", gp.tileSize, gp.tileSize);
        dtile[index].setImage(image);
    }
    
    public void loadMap(String filePath){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                
                String line = br.readLine();
                
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2){
        
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX =  worldX - gp.drawP.getPlayer().getWorldX()  + gp.drawP.getScreenX();
            int screenY =  worldY - gp.drawP.getPlayer().getWorldY()  + gp.drawP.getScreenY();

            if(worldX + gp.tileSize> gp.drawP.getPlayer().getWorldX() - gp.drawP.getScreenX() &&
               worldX - gp.tileSize< gp.drawP.getPlayer().getWorldX() + gp.drawP.getScreenX() &&
               worldY + gp.tileSize> gp.drawP.getPlayer().getWorldY() - gp.drawP.getScreenY() &&
               worldY - gp.tileSize< gp.drawP.getPlayer().getWorldY() + gp.drawP.getScreenY()){
               g2.drawImage(dtile[tileNum].getImage(), screenX, screenY, null);
            }

            worldCol++;
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
        
}
