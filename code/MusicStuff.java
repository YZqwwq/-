
import javax.sound.sampled.*;
import java.io.*;
public class MusicStuff {
    void playMusic(String musicLocation)
    {
        try
        {
            File musicPath = new File(musicLocation);

            if(musicPath.exists())
            {
                System.out.println("play");
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else
            {
                System.out.println("can't find file");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
