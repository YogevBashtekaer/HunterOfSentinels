package org.example;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Music {
    protected Clip clip;
    private AudioInputStream sound;

    public Music(String path) {
        try {
            InputStream input = getClass().getResourceAsStream(path);
            if (input == null) {
                throw new IOException("File not found: " + path);
            }
            this.sound = AudioSystem.getAudioInputStream(new BufferedInputStream(input));
            clip = AudioSystem.getClip();
            clip.open(this.sound);
        } catch (Exception e) {
            System.out.println("Problem with sound: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public  void play() {
        clip.start();
    }
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    public  void loadMusic(String path) {
        try {
            if (clip != null && clip.isRunning()) {
                stop();
            }
            File file = new File(path);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            System.out.println("Problem with loading sound: " + e.getMessage());
        }
    }
    public  void changeMusic(String newPath) {
        loadMusic(newPath);
        play();
    }

    public void loop() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }


    public static void main(String[] args) {
        //Music music = new Music();
        //music.play();

    }
}
