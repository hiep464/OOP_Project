/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameSetting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author HOANG XUAN BACH
 */
public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, kPressed;
    // DEBUG
    boolean checkDrawTime = false;
    public boolean drawSolidArea = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        // TITLE STATE
        if(gp.gameState == gp.titleState){
            
            if(gp.ui.titleScreenState == 0){
                switch (code) {
                case KeyEvent.VK_W:
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if(gp.ui.commandNum == 0){ gp.ui.titleScreenState = 1; }
                    if(gp.ui.commandNum == 1){ /* add later*/ }
                    if(gp.ui.commandNum == 2){ System.exit(0); }
                }
            }
            else if(gp.ui.titleScreenState == 1){
                
                switch (code) {
                case KeyEvent.VK_W:
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){ gp.ui.commandNum = 3; }
                    break;
                case KeyEvent.VK_S:
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3){ gp.ui.commandNum = 0; }
                    break;
                case KeyEvent.VK_ENTER:
                    if(gp.ui.commandNum == 0){
                        gp.gameState = gp.playeState;
                        gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1){  /* add later*/ }
                    if(gp.ui.commandNum == 2){ /* add later*/ }
                    if(gp.ui.commandNum == 3){
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }
        
        // PLAY STATE
        else if(gp.gameState == gp.playeState){
            switch (code) {
            case KeyEvent.VK_W: upPressed = true;  break;
            case KeyEvent.VK_S: downPressed = true; break;
            case KeyEvent.VK_A: leftPressed = true; break;
            case KeyEvent.VK_D: rightPressed = true; break;
            case KeyEvent.VK_K: 
                
                kPressed = true;
                System.out.println("k enter press!");
                break;
            case KeyEvent.VK_T:

                // DEBUG
                if(checkDrawTime == false){ checkDrawTime = true; }
                else if(checkDrawTime == true){ checkDrawTime = false; }
                break;
            case KeyEvent.VK_R:
                if(drawSolidArea == false){ drawSolidArea = true; }
                else if(drawSolidArea == true){ drawSolidArea = false; }
                break;
            case KeyEvent.VK_P: gp.gameState = gp.pauseState; break;
            case KeyEvent.VK_ENTER: enterPressed = true; break;
            default: break;
            } 
        }
        
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){ gp.gameState = gp.playeState; }
        }
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playeState;
                gp.keyH.enterPressed = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        switch (code) {
            case KeyEvent.VK_W: upPressed = false; break;
            case KeyEvent.VK_S: downPressed = false; break;
            case KeyEvent.VK_A: leftPressed = false; break;
            case KeyEvent.VK_D: rightPressed = false; break;
            case KeyEvent.VK_ENTER: enterPressed = false; break;
            case KeyEvent.VK_K: 
                kPressed = false;
                System.out.println("k enter release!");
                break;
            default: break;
        }
    }
    
}
