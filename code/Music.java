
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import javax.sound.sampled.*;
import java.io.*;

public class Music {
    public static void music() {
        String filepath = "file/music/bg1.wav";
        MusicStuff musicObject = new MusicStuff();
        musicObject.playMusic(filepath);
    }

/*    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("file/music/bg1.mp3");

        InputStream is = new FileInputStream(file);

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);//音频输入

        AudioFormat audioFormat = audioInputStream.getFormat();//MP3格式

        AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                audioFormat.getSampleRate(), 16, audioFormat.getChannels(),
                audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
        AudioInputStream decodeAudioStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodeFormat);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);//创建源数据行
        line.open(decodeFormat);

        line.start();

        byte[] AUDIO_BUFER = new byte[100];
        int readLenth = 0;
        while (true) {
            readLenth = decodeAudioStream.read(AUDIO_BUFER, 0, AUDIO_BUFER.length);
            if (readLenth < 0){
                break;
            }
            line.write(AUDIO_BUFER,0,readLenth);
        }

        line.drain();//清空缓冲区
        line.stop();
        line.close();

    }*/
/*     String path ="file/music/bg1.mp3";

    public  void getMusic()throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        File file = new File(path);

        InputStream is = new FileInputStream(file);

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);//音频输入

        AudioFormat audioFormat = audioInputStream.getFormat();//MP3格式

        AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                audioFormat.getSampleRate(), 16, audioFormat.getChannels(),
                audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
        AudioInputStream decodeAudioStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodeFormat);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);//创建源数据行
        line.open(decodeFormat);

        line.start();

        byte[] AUDIO_BUFER = new byte[100];
        int readLenth = 0;
        while (true) {
            readLenth = decodeAudioStream.read(AUDIO_BUFER, 0, AUDIO_BUFER.length);
            if (readLenth < 0){
                break;
            }
            line.write(AUDIO_BUFER,0,readLenth);
        }

        line.drain();//清空缓冲区
        line.stop();
        line.close();

    }

    public static void getMp3Duration(String filePath) {
        try {
            File mp3File = new File(filePath);
            MP3File f = (MP3File) AudioFileIO.read(mp3File);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            System.out.println("时长:" + Float.parseFloat(audioHeader.getTrackLength() + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

//    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//     getMusic();
//    }
}
