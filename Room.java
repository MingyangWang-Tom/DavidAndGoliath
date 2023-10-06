import java.awt.*;

public class Room implements Drawable{
    // holds the xyposition of the topleft corner of the room
    private Point pos;
    //a reference to the Room to the east of this room    private Room exitEast;
    private Room exitEast;
    private Room exitWest;
    private Room exitNorth;
    private Room exitSouth;
    public static final int SIZE = 50;
    
    //initialized pos to x, y also set all the exit variables to null
    public Room(int x, int y){
        pos = new Point(x, y);
        exitEast = null;
        exitWest = null;
        exitNorth = null;
        exitSouth = null;
    }

    @Override
    public void draw(Graphics g){
        //top(north) line
        if (exitNorth != null ){
            g.drawLine(pos.x, pos.y, pos.x+20, pos.y);
            g.drawLine(pos.x+30, pos.y, pos.x+SIZE, pos.y);
        }else{
        g.drawLine(pos.x, pos.y, pos.x+SIZE, pos.y);
        } 

        //right (east) line
        if (exitEast != null){
            g.drawLine(pos.x+SIZE, pos.y, pos.x+SIZE, pos.y+20);
            g.drawLine(pos.x+SIZE, pos.y+30, pos.x+SIZE, pos.y+SIZE);
            //pathway
            g.drawLine(pos.x+SIZE, pos.y+20, pos.x+60, pos.y+20);
            g.drawLine(pos.x+SIZE, pos.y+30, pos.x+60, pos.y+30);
        }else{ 
            g.drawLine(pos.x+SIZE, pos.y, pos.x+SIZE, pos.y+SIZE); 
        }

        //bottom (south) line
        if (exitSouth != null ){
            g.drawLine(pos.x+SIZE, pos.y+SIZE, pos.x+30, pos.y+SIZE);
            g.drawLine(pos.x+20, pos.y+SIZE, pos.x, pos.y+SIZE);
            //pathway
            g.drawLine(pos.x+30, pos.y+SIZE, pos.x+30, pos.y+60);
            g.drawLine(pos.x+20, pos.y+SIZE, pos.x+20, pos.y+60);
        }else{
            g.drawLine(pos.x+SIZE, pos.y+SIZE, pos.x, pos.y+SIZE); 
        }

        //left (west) line
        if (exitWest != null){
            g.drawLine(pos.x, pos.y+SIZE, pos.x, pos.y+30);
            g.drawLine(pos.x, pos.y+20, pos.x, pos.y);
        }else{
        g.drawLine(pos.x, pos.y+SIZE, pos.x, pos.y); 
        }
    }

    public void setEastExit(Room r){
        exitEast = r;
        r.exitWest = this;
        
    }

    public void setWestExit(Room r){
        exitWest = r;
        r.exitEast = this;
    }

    public void setNorthExit(Room r){
        exitNorth = r;
        r.exitSouth = this;
    }

    public void setSouthExit(Room r){
        exitSouth = r;
        r.exitNorth = this;
    }
    
    public Point getPosition(){
        return pos;
    }

    public boolean hasNorthExit(){
        if (exitNorth == null ){
            return false;
        }else{
            return true;
        }

    }

    public boolean hasSouthExit(){
        if (exitSouth == null ){
            return false;
        }else{
            return true;
        }
    }

    public boolean hasEastExit(){
        if (exitEast == null ){
            return false;
        }else{
            return true;
        }
    }

    public boolean hasWestExit(){
        if (exitWest == null ){
            return false;
        }else{
            return true;
        }
    }

    public Room getNorthExit(){
        return exitNorth;
    }

    public Room getSouthExit(){
        return exitSouth;
    }

    public Room getEastExit(){
        return exitEast;
    }

    public Room getWestExit(){
        return exitWest;
    }

}