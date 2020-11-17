package fr.isola.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioManager {

    public static AudioManager INSTANCE = new AudioManager();

    private  Clip musicClip;

    public AudioManager() {
        try {
            musicClip = AudioSystem.getClip();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void PlayerBackgroundMusic(String name) {
        try {
            StopBackgroundAudio();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resources/audio/" + name + ".wav"));
            musicClip.open(inputStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void PlayVictorySound(String name) {
        try {
            StopBackgroundAudio();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resources/audio/victory/" + name + ".wav"));
            musicClip.open(inputStream);
            musicClip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void StopBackgroundAudio() {
        try {
            musicClip.stop();
            musicClip.close();;
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public float GetBackgroundMusicVolume() {
        FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
        return gainControl.getValue();
    }

    public void SetBackgroundMusicVolume(float v) {
        FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(v);
    }

}
