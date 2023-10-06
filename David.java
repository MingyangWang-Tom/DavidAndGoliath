import javax.swing.ImageIcon;
import java.lang.Boolean;

public class David extends Sprite {
    
    private int numStones;

    public David(){
        super();
        numStones = 0;
        image = new ImageIcon("david.png");
    }
    
    public void pickUpStone(){
        numStones += 1;
    }

    public boolean isArmed(){
        if (numStones == 5){
            return true;
        }else{
            return false;
        }
    }

    public void reset(){
        numStones = 0;
    }
}
