import javax.swing.ImageIcon;
import java.util.*;
import java.lang.Math;
import java.awt.*;

public class Goliath extends Sprite {

    public Goliath(){
        super();
        image = new ImageIcon("goliath.png");
    }

    //extra credits
    public void moveRandomly(){
        ArrayList<Integer> exits = new ArrayList<>(); //1,2,3,4 represents north south east and west

        //check current room has what exits
        if (currentRoom.hasNorthExit()){
            exits.add(1);
        }
        if (currentRoom.hasSouthExit()){
            exits.add(2);
        }
        if (currentRoom.hasEastExit()){
            exits.add(3);
        }
        if (currentRoom.hasWestExit()){
            exits.add(4);
        }

         //randomly pick one int from exits
        int randomExit = (int) (Math.random() * exits.size());
        var exit = exits.get(randomExit);
        if (exit == 1){
            currentRoom = currentRoom.getNorthExit();
        } else if (exit == 2){
            currentRoom = currentRoom.getSouthExit();
        }else if (exit == 3){
            currentRoom = currentRoom.getEastExit();
        }else{
            currentRoom = currentRoom.getWestExit();
        }
    }
    //goliath move toward david
    public void moveAI(David d){
        Point posG = currentRoom.getPosition();
        int xG = posG.x, yG = posG.y;
        Room davidRoom = d.getCurrentRoom();
        Point posD = davidRoom.getPosition();
        int xD = posD.x, yD = posD.y;
        
        
        
        if (xG - xD > 0 && yG - yD > 0 && currentRoom.hasNorthExit()){
            currentRoom = currentRoom.getNorthExit();
        }else if (xG - xD > 0 && yG - yD > 0 && currentRoom.hasEastExit()){
            currentRoom = currentRoom.getEastExit();
        }
        else if(xG - xD < 0 && yG - yD < 0 && currentRoom.hasSouthExit()){
            currentRoom = currentRoom.getSouthExit();
        }
        else if (xG -xD < 0 && currentRoom.hasEastExit()){
            currentRoom = currentRoom.getEastExit();
        }else if(yG - yD < 0 && currentRoom.hasSouthExit()){
            currentRoom = currentRoom.getSouthExit();
        }else if (xG - xD > 0 && currentRoom.hasWestExit()){
            currentRoom = currentRoom.getWestExit();
        }else if (xG - xD > 0 && yG - yD < 0 && currentRoom.hasWestExit()){
            currentRoom = currentRoom.getWestExit();
        }else if (yG - yD > 0 && currentRoom.hasNorthExit()){
            currentRoom = currentRoom.getNorthExit();
        }
        
        else{
            this.moveRandomly();
        }

        
    }
}
