import javax.swing.ImageIcon;
import java.awt.*;
import java.util.*;


public abstract class Sprite implements Drawable{
    protected Room currentRoom; //room of the sprite will be drawn
    protected ImageIcon image; //the sprite picture

    public Sprite(){
        currentRoom = null;
        image = null;
    }

    public void setCurrentRoom(Room r){
        currentRoom = r;

    }

    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    @Override
    public void draw(Graphics g){
        if (currentRoom != null){
            Point pos1 = currentRoom.getPosition();
            image.paintIcon(null, g, pos1.x+7, pos1.y+7);
        }  
    }
    //check whether currentRoom has an exit to the different direction
    public void moveNorth(){
        if (currentRoom.hasNorthExit() == true){
            currentRoom = currentRoom.getNorthExit();
        }
    }

    public void moveSouth(){
        if (currentRoom.hasSouthExit() == true){
            currentRoom = currentRoom.getSouthExit();
        }
    }

    public void moveEast(){
        if (currentRoom.hasEastExit() == true){
            currentRoom = currentRoom.getEastExit();
        }
    }

    public void moveWest(){
        if (currentRoom.hasWestExit() == true){
            currentRoom = currentRoom.getWestExit();
        }
    }

    //extra credits
    public boolean equals(Sprite r){
        boolean result;
        if (this.getCurrentRoom() == r.getCurrentRoom()){
            result = true;
        } else{
            result = false;
        }
        return result;
    }

}

