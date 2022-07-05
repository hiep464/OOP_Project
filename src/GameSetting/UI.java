package GameSetting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Graphics.DrawObject;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    Graphics2D g2;
    BufferedImage heart_full, heart_haft, heart_blank;    
    public boolean messageOn = false;
    public String message = "";
    int messageCount;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    Color colorTitleBackGround;
    Color colorStateBackGround;
    Color colorBountStateBackGround;
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first screen, 1: the next screen
    
    
   
    public UI(GamePanel gp) {
        this.gp = gp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        colorTitleBackGround = new Color(70, 120, 80);
        colorStateBackGround = new Color(255, 0, 0, 200);
        colorBountStateBackGround = new Color(255, 0, 0);
        
        // CREATE HUB OBJECT
        DrawObject heartFull = new DrawObject(gp, "heart_full");
        heart_full = heartFull.getImage();
        DrawObject heartHaft = new DrawObject(gp, "heart_half");
        heart_haft = heartHaft.getImage();
        DrawObject heartBlank = new DrawObject(gp, "heart_blank");
        heart_blank = heartBlank.getImage();

        
    }
    
    public void showMessage(String text){
        
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        
        g2.setFont(arial_80B);
        g2.setColor(Color.white);
        
        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playeState){
            
            drawSubWindow(gp.tileSize/2 - 20, gp.tileSize/2 - 20,  gp.tileSize*5, gp.tileSize*4);
            drawPlayerLife();
            drawPlayerKunai();
            drawPlayerKey();
            
            if(gp.drawP.getPlayer().isKunaiAttacking() == true){
                drawTimeKunaiAttack();
            }
        }
        
        // PAUSTATE
        if(gp.gameState == gp.pauseState){
            
            drawSubWindow(gp.tileSize/2 - 20, gp.tileSize/2 - 20,  gp.tileSize*5, gp.tileSize*4);
            drawPlayerLife();
            drawPlayerKunai();
            drawPlayerKey();
            drawPauseScreen();
            if(gp.drawP.getPlayer().isKunaiAttacking() == true){
                drawTimeKunaiAttack();
            }
        }
        
        // DIALOGSTATE
        if(gp.gameState == gp.dialogueState){
            
            drawSubWindow(gp.tileSize/2 - 20, gp.tileSize/2 - 20,  gp.tileSize*5, gp.tileSize*4);
            drawPlayerLife();
            drawPlayerKunai();
            drawPlayerKey();
            drawDialogueScreen();
        }
    }
    
    public void drawPlayerLife(){
        
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        // DRAW MAXLIFE
        while(i < gp.drawP.getPlayer().getMaxLife()/2){
            
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        
        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        
        // DRWA CURRENT LIFE
        while(i < gp.drawP.getPlayer().getLife()){
            g2.drawImage(heart_haft, x, y, null);
            i++;
            if(i < gp.drawP.getPlayer().getLife()) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    
    public void drawPlayerKunai(){
        
        int x = gp.tileSize/2;
        int y = gp.tileSize*2;

        g2.drawImage(gp.dobj[6].getImage(), x+gp.tileSize/4+10, y+gp.tileSize/4+15, null);
        g2.setFont(g2.getFont().deriveFont(30f));
        g2.drawString(" X "+gp.drawP.getPlayer().getHasKunai(), x+gp.tileSize, y+gp.tileSize);
    }    
    public void drawPlayerKey(){
        
        int x = gp.tileSize/2;
        int y = gp.tileSize*1;
//        if(gp.dobj[7] == null)
        g2.drawImage(gp.dobj[7].getImage(), x+gp.tileSize/4+10, y+gp.tileSize/4+15, null);
        g2.setFont(g2.getFont().deriveFont(30f));
        g2.drawString(" X "+gp.drawP.getPlayer().getHasKey(), x+gp.tileSize, y+gp.tileSize);
    }
    
    public void drawTimeKunaiAttack(){
        
        int x = gp.drawP.getScreenX();
        int y = gp.drawP.getScreenY();
        g2.setColor(Color.black);
        g2.drawRect(x, y-gp.tileSize/2, gp.tileSize, 2);
        g2.setColor(Color.white);
        g2.drawRect(x, y-gp.tileSize/2, gp.tileSize*gp.drawP.getKunaiAttackCounter()/120, 2);
    }

    public void drawTitleScreen(){
        
        if(titleScreenState == 0){
            
            g2.setColor(colorTitleBackGround);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 75F));
            String text = "Blue Boy Adventure";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            // SHADOW
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);

            // MAIN COLOR 
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // BLIE BOY IMAGE
            x = gp.screenWidth/2 - gp.tileSize;
            y += gp.tileSize*2;
            g2.drawImage(gp.drawP.getDown1(), x, y, gp.tileSize*2, gp.tileSize*2 ,null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">",  x-gp.tileSize, y);
            }

            text = "QUIT GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1){
            
            // CLASS SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = "Select you class!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);
            
            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Thief";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        
    }
    
    public void drawPauseScreen(){
        
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    
    private void drawDialogueScreen() {
        
        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width  = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);
        
        g2.setFont(arial_40);
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    
    public void drawSubWindow(int x, int y, int width, int height){
        
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    
    public int getXforCenteredText(String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    
}
