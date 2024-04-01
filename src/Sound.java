import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound extends Thread{
    AudioFileFormat aff;
    AudioInputStream ais;
    int n;
    static boolean attivo = false;
    private Clip clip;

    public Sound (File sf, int n) {
        aff=null;
        ais=null;
        try {
            aff=AudioSystem.getAudioFileFormat(sf);
            ais=AudioSystem.getAudioInputStream(sf);
        }
        catch (IOException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }

        this.n = n;
        this.start();
    }

    public void ferma() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        interrupt();
    }

    public void run(){
        try {
            AudioFormat af=aff.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class,ais.getFormat(),((int) ais.getFrameLength() *af.getFrameSize()));
            clip = (Clip) AudioSystem.getLine(info);

            clip.open(ais);

            clip.loop(n) ;
            clip.start();
        } catch (LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
    }
}