
package Entity;

import Graphics.DrawMonS;

public class MON_GreenSlime extends Entity{
    
    public MON_GreenSlime() {

        super("Green Slime", 8, 1, 2);

    }
    
    
    

    public void damageReaction(DrawMonS dMon){
        
    	dMon.setActionLockCounter(0);
    	dMon.setDirection(dMon.gp.drawP.getDirection());
    }
}
