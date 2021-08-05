import java.util.*;
import java.io.*;

/**
 * Created on:  Feb 23, 2021
 * Questions:
 */

public class AdapterPattern {

    public static void main(String[] args) {

    }
}

/**
 * We have a MediaPlayer interface and a concrete class AudioPlayer implementing the MediaPlayer interface. AudioPlayer can play mp3 format audio files by default.
 * <p>
 * We are having another interface AdvancedMediaPlayer and concrete classes implementing the AdvancedMediaPlayer interface. These classes can play vlc and mp4 format files.
 * <p>
 * We want to make AudioPlayer to play other formats as well. To attain this, we have created an adapter class MediaAdapter which implements the MediaPlayer interface and uses AdvancedMediaPlayer objects to play the required format.
 * <p>
 * AudioPlayer uses the adapter class MediaAdapter passing it the desired audio type without knowing the actual class which can play the desired format. AdapterPatternDemo, our demo class will use AudioPlayer class to play various formats.
 */
interface MediaPlayer {
    void play(String fineType, String fileName);
}

interface AdvancedMediaPlayer {
    void playMP3(String fineType, String fileName);

    void playFLV(String fineType, String fileName);
}

class MP3Player implements MediaPlayer {

    @Override
    public void play(String fineType, String fileName) {
        System.out.println("Playing MP3 File. File Name: " + fileName);
    }
}

class FLVPlayer implements MediaPlayer {

    @Override
    public void play(String fineType, String fileName) {
        System.out.println("Playing FLV File. File Name : " + fileName);
    }
}

class AdvanceMediaPlayerAdaptor implements MediaPlayer {

    MediaPlayer mediaPlayer;

    public AdvanceMediaPlayerAdaptor(String fileType) {
        if (fileType.equals("MP3")) {
            mediaPlayer = new MP3Player();
        } else {
            mediaPlayer = new FLVPlayer();
        }
    }

    @Override
    public void play(String fineType, String fileName) {
        mediaPlayer.play(fineType, fileName);
    }
}