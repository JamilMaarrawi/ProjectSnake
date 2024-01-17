package at.ac.fhcampuswien;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[5];
    //Constructor for class "Sound"
    public Sound(){
        soundURL[0] = getClass().getResource("/Sounds/Song.wav");
        soundURL[1] = getClass().getResource("/Sounds/Eat.wav");
        soundURL[2] = getClass().getResource("/Sounds/Over.wav");
    }
    //Gets the Sound-File according to the integer given
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //Starts the Music/Sound-Effects
    public void play(){
        clip.start();
    }
    //Creates a loop to play Music over and over again
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    //Stops the Music/Sound-Effect
    public void stop(){
        clip.stop();
    }
}
