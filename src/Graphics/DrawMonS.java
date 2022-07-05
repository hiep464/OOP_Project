package Graphics;

import java.util.Random;

import Entity.MON_GreenSlime;
import GameSetting.GamePanel;
import GameSetting.UtilityTool;

public class DrawMonS extends DrawEntity{
	public MON_GreenSlime mons;
	public DrawMonS(GamePanel gp) {

        super(gp, "Green Slime", 8, 1, 2);
        mons = new MON_GreenSlime();
        this.entity = mons;
        this.oObject = mons;
        getSolidArea().x = 3;
        getSolidArea().y = 18;
        getSolidArea().width = 42;
        getSolidArea().height = 30;
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
        
        getEntityImage();
    }
	
	public void getEntityImage(){
        
        UtilityTool uTool = new UtilityTool();
        
        setUp1(uTool.setup("data/Monster/greenslime_down_1.png", gp.tileSize, gp.tileSize));
        setUp2(uTool.setup("data/Monster/greenslime_down_2.png", gp.tileSize, gp.tileSize));
        setDown1(uTool.setup("data/Monster/greenslime_down_1.png", gp.tileSize, gp.tileSize));
        setDown2(uTool.setup("data/Monster/greenslime_down_2.png", gp.tileSize, gp.tileSize));
        setLeft1(uTool.setup("data/Monster/greenslime_down_1.png", gp.tileSize, gp.tileSize));
        setLeft2(uTool.setup("data/Monster/greenslime_down_2.png", gp.tileSize, gp.tileSize));
        setRight1(uTool.setup("data/Monster/greenslime_down_1.png", gp.tileSize, gp.tileSize));
        setRight2(uTool.setup("data/Monster/greenslime_down_2.png", gp.tileSize, gp.tileSize));
        
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
