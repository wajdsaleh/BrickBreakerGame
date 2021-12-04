package brick.breaker.game.with.touchpad;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Sounds {
    
    private void  background() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
    
    File file1 = new File("BSC_State_of_Mind_Squadda_B.wav");  
     AudioInputStream audio1 = AudioSystem.getAudioInputStream(file1);
     Clip clip1 = AudioSystem.getClip();
     clip1.open(audio1);
     clip1.start();  
}

    public void BackGround(){
    try {
        background();
    } catch (IOException ex) {
        Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
    } catch (LineUnavailableException ex) {
        Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedAudioFileException ex) {
        Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
    }
}}