import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.lang.Boolean;
import java.io.File; 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;


public class Main extends JPanel implements KeyListener {
    private Room[] roomList;
    private David david;
    private ArrayList<Stone> stones;
    private Goliath goliath;
    private ArrayList<Drawable> items;
    private Stone stone1;
    private Stone stone2;
    private Stone stone3;
    private Stone stone4;
    private Stone stone5;
    private Music music;
    private javax.swing.Timer timer;
    private boolean gameover;
   
    
    public Main(){
        roomList = new Room[26];
        //set rooms position
        roomList[0] = new Room(10, 10);
        roomList[1] = new Room(70, 10);
        roomList[2] = new Room(130, 10);
        roomList[3] = new Room(190, 10);
        roomList[4] = new Room(250, 10);
        roomList[5] = new Room(10, 70);
        roomList[6] = new Room(70, 70);
        roomList[7] = new Room(130, 70);
        roomList[8] = new Room(190, 70);
        roomList[9] = new Room(250, 70);
        roomList[10] = new Room(70, 130);
        roomList[11] = new Room(130, 130);
        roomList[12] = new Room(190, 130);
        roomList[13] = new Room(130, 190);
        roomList[14] = new Room(190, 190);
        roomList[15] = new Room(250, 190);
        roomList[16] = new Room(10, 250);
        roomList[17] = new Room(70, 250);
        roomList[18] = new Room(130, 250);
        roomList[19] = new Room(190, 250);
        roomList[20] = new Room(250, 250);
        roomList[21] = new Room(310, 250);
        roomList[22] = new Room(130, 310);
        roomList[23] = new Room(190, 310);
        roomList[24] = new Room(250, 310);
        roomList[25] = new Room(310, 310);

        //rooms into the items arraylist
        items = new ArrayList<>();
        for (int i  = 0; i < 26; i++){
            items.add(roomList[i]);
        }

        //connect rooms
        roomList[0].setSouthExit(roomList[5]);
        roomList[1].setEastExit(roomList[2]);
        roomList[1].setSouthExit(roomList[6]);
        roomList[2].setEastExit(roomList[3]);
        roomList[3].setEastExit(roomList[4]);
        roomList[4].setSouthExit(roomList[9]);
        roomList[5].setEastExit(roomList[6]);
        roomList[6].setSouthExit(roomList[10]);
        roomList[7].setEastExit(roomList[8]);
        roomList[7].setSouthExit(roomList[11]);
        roomList[8].setEastExit(roomList[9]);
        roomList[8].setSouthExit(roomList[12]);
        roomList[10].setEastExit(roomList[11]);
        roomList[11].setEastExit(roomList[12]);
        roomList[11].setSouthExit(roomList[13]);
        roomList[12].setSouthExit(roomList[14]);
        roomList[13].setEastExit(roomList[14]);
        roomList[13].setSouthExit(roomList[18]);
        roomList[14].setEastExit(roomList[15]);
        roomList[15].setSouthExit(roomList[20]);
        roomList[16].setEastExit(roomList[17]);
        roomList[17].setEastExit(roomList[18]);
        roomList[18].setSouthExit(roomList[22]);
        roomList[19].setSouthExit(roomList[23]);
        roomList[20].setEastExit(roomList[21]);
        roomList[20].setSouthExit(roomList[24]);
        roomList[21].setSouthExit(roomList[25]);
        roomList[22].setEastExit(roomList[23]);
        roomList[23].setEastExit(roomList[24]);

        //set david room number
        david = new David();
        david.setCurrentRoom(roomList[16]);
        items.add(david);
        //set stone's room number
        stones = new ArrayList<>();
        stone1 = new Stone();
        stones.add(stone1);
        items.add(stone1);
        stone2 = new Stone();
        stones.add(stone2);
        items.add(stone2);
        stone3 = new Stone();
        stones.add(stone3);
        items.add(stone3);
        stone4 = new Stone();
        stones.add(stone4);
        items.add(stone4);
        stone5 = new Stone();
        stones.add(stone5);
        items.add(stone5);

        stone1.setCurrentRoom(roomList[0]);
        stone2.setCurrentRoom(roomList[8]);
        stone3.setCurrentRoom(roomList[13]);
        stone4.setCurrentRoom(roomList[15]);
        stone5.setCurrentRoom(roomList[25]);
        //set goliath room number
        goliath = new Goliath();
        goliath.setCurrentRoom(roomList[11]);
        items.add(goliath);

        //Extra Credits
        //Play background music
        music = new Music();
        music.playMeida();
        gameover = false;
        //goliath move every second
        timer = new javax.swing.Timer(1000, taskPerformer);
        timer.start();

        addKeyListener(this);
    }
    
   
    @Override
	public void paintComponent(Graphics g) {
        //background
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 370, 370);

        //draw sprites
        g.setColor(Color.RED);
        /*OLD CODES 
        for (int i = 1; i < 27; i++){
            roomList[i].draw(g);
        }
        //draw david, stones and goliath
        david.draw(g);

        for (int i = 0; i < 5; i++){
            Stone x = stones.get(i);
            x.draw(g);
            
        }
       
        goliath.draw(g);*/
        for (int i = 0; i < 33; i++){
            Drawable x = items.get(i);
            x.draw(g);
        }

        requestFocusInWindow();
    }
    //

    //extra credits
    ActionListener taskPerformer = new ActionListener(){
        public void actionPerformed(ActionEvent evt){
            if (gameover == false){
                goliath.moveAI(david); 
                //goliath.moveRandomly();
            
            if (david.equals(goliath)&& david.isArmed()==true){
                timer.stop();
                JOptionPane.showMessageDialog(null, "Congratulations! You win");
                
                resetRandomly();
            }else if(david.equals(goliath)&& david.isArmed()==false){
                timer.stop();
                JOptionPane.showMessageDialog(null, "Oops, Goliath caught you! Try again");
                
                resetRandomly();
            }
            repaint();
            }
        }
    };

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        
        int k = e.getKeyCode();
        //david movement
        if (k == KeyEvent.VK_UP){
            david.moveNorth();
        }
        if (k == KeyEvent.VK_DOWN){
            david.moveSouth();
        }
        if (k == KeyEvent.VK_LEFT){
            david.moveWest();
        }
        if (k == KeyEvent.VK_RIGHT){
            david.moveEast();
        } 
        //extra credits goliath movement
        if (k == KeyEvent.VK_W){
            goliath.moveNorth();
        }
        if (k == KeyEvent.VK_S){
            goliath.moveSouth();
        }
        if (k == KeyEvent.VK_A){
            goliath.moveWest();
        }
        if (k == KeyEvent.VK_D){
            goliath.moveEast();
        }
        //david pick up stones
        for (int i = 0; i < 5; i++){
            Stone x = stones.get(i);
            if (x.getCurrentRoom()== david.getCurrentRoom()){
                david.pickUpStone();
                x.setCurrentRoom(null);
            }
        }
        //check if david has enough stones
        if (david.equals(goliath) && david.isArmed()==true){
            gameover = true;
            JOptionPane.showMessageDialog(null, "Congratulations! You win");
            resetRandomly();
            //reset();
        }else if(david.equals(goliath)&& david.isArmed()==false){
            gameover = true;
            JOptionPane.showMessageDialog(null, "Oops, Goliath caught you! Try again");//这里别照抄 换成你自己的句子
            resetRandomly();
            //reset();
        }
        repaint();
    }
    //reset to sprites original places
    private void reset(){
        david.reset();
        david.setCurrentRoom(roomList[16]);

        stone1.setCurrentRoom(roomList[0]);
        stone2.setCurrentRoom(roomList[8]);
        stone3.setCurrentRoom(roomList[13]);
        stone4.setCurrentRoom(roomList[15]);
        stone5.setCurrentRoom(roomList[25]);
        
        goliath.setCurrentRoom(roomList[11]);
        music.reset();
        timer.start();
        gameover = false;
    }
    //extra credits set sprites to random places
    private void resetRandomly(){
        var randomRoomList = roomList;
        Collections.shuffle(Arrays.asList(randomRoomList));
        david.reset();
        david.setCurrentRoom(randomRoomList[0]);
        stone1.setCurrentRoom(randomRoomList[1]);
        stone2.setCurrentRoom(randomRoomList[2]);
        stone3.setCurrentRoom(randomRoomList[3]);
        stone4.setCurrentRoom(randomRoomList[4]);
        stone5.setCurrentRoom(randomRoomList[5]);
        goliath.setCurrentRoom(randomRoomList[6]);
        music.reset();
        timer.start();
        gameover = false;
    }
    public static void main(String[] args) {
		var window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(370, 400);
		window.setContentPane(new Main());
		window.setVisible(true);
    }
}
