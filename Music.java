import java.io.File; 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Music {
    Clip clip;
    public Music(){

    }
    
    public void playMeida(){
        try {
            String soundName = "backgroundMusic.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start(); 
            } catch (Exception e) {
                System.out.println("file not found");
               
            }
     }

    public void reset(){
        clip.stop();
        
        try {
            String soundName = "backgroundMusic.wav";    
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start(); //start to play the clip
            } catch (Exception e) {
                System.out.println("file not found");
                //Do stuff in case of an exception, for example, file not found
            }
     }
}
