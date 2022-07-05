package Main;

import javax.swing.JFrame;
import GameSetting.GamePanel;

public class DemoOOPGame {

    public static void main(String[] args) {
        // TODO code application logic here
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D advanture");
//        window.setLocationRelativeTo(null);
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();
        
        window.setLocale(null);
        window.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
        
    }
    
}
