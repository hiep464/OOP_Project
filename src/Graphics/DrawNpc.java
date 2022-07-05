package Graphics;

import java.util.Random;

import Entity.NPC_OldMan;
import GameSetting.GamePanel;
import GameSetting.UtilityTool;

public class DrawNpc extends DrawEntity{
	public DrawNpc(GamePanel gp) {
        
        super(gp, "Oldman", 0, 1, 3);
        
        npc = new NPC_OldMan();
        this.oObject = npc;
        this.entity = npc;
        getEntityImage();
        setDialogue();
    }
	
	public NPC_OldMan npc;
	
	public void getEntityImage(){
        
        UtilityTool uTool = new UtilityTool();
        
        setUp1(uTool.setup("data/NPC/oldman_up_1.png", gp.tileSize, gp.tileSize));
        setUp2(uTool.setup("data/NPC/oldman_up_2.png", gp.tileSize, gp.tileSize));
        setDown1(uTool.setup("data/NPC/oldman_down_1.png", gp.tileSize, gp.tileSize));
        setDown2(uTool.setup("data/NPC/oldman_down_2.png", gp.tileSize, gp.tileSize));
        setLeft1(uTool.setup("data/NPC/oldman_left_1.png", gp.tileSize, gp.tileSize));
        setLeft2(uTool.setup("data/NPC/oldman_left_2.png", gp.tileSize, gp.tileSize));
        setRight1(uTool.setup("data/NPC/oldman_right_1.png", gp.tileSize, gp.tileSize));
        setRight2(uTool.setup("data/NPC/oldman_right_2.png", gp.tileSize, gp.tileSize));
    }
    
    public void setDialogue(){
        
        getDialogues()[0] = "Hello, Bach.";
        getDialogues()[1] = "I used to a happy.";
        getDialogues()[2] ="But now monster every\nwhere.";
        getDialogues()[3] = "I am so scare.";
        
    }
    public void speak(){
        
        super.speak();
    }
    public void setAction(){
        
    	setActionLockCounter(getActionLockCounter()+1);
        
        if(getActionLockCounter() == 120){
            
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){ setDirection("up"); }
            else if(i > 25 && i <= 50){ setDirection("down"); }
            else if(i > 50 && i <= 75){ setDirection("left"); }
            else if(i > 75 && i <= 100){ setDirection("right"); }
            
            setActionLockCounter(0);
        }
    }
}
