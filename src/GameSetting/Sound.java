/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameSetting;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author HOANG XUAN BACH
 */
public class Sound {
    
    Clip clip;
    String soundPath[] = new String[30];
    
    public Sound() {
        
        soundPath[0] = "data/Sound/BlueBoyAdventure.wav";
        soundPath[1] = "data/Sound/coin.wav";
        soundPath[2] = "data/Sound/powerup.wav";
        soundPath[3] = "data/Sound/unlock.wav";
        soundPath[4] = "data/Sound/fanfare.wav";
        soundPath[5] = "data/Sound/hitmonster.wav";
        soundPath[6] = "data/Sound/receivedamage.wav";
        soundPath[7] = "data/Sound/cuttree.wav";
    }
    
    public void setFile(int i){
        
        try{
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundPath[i]));
            clip.open(ais);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        
    public void play(){
        
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    
}
