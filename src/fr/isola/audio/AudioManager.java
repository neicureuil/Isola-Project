package fr.isola.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioManager {

    public static AudioManager INSTANCE = new AudioManager();
    public static float VOLUME = 2;

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
            SetBackgroundMusicVolume();
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

    public void SetBackgroundMusicVolume() {
        FloatControl volCtrl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
        float newGain = volCtrl.getMinimum() + VOLUME * (volCtrl.getMaximum() - volCtrl.getMinimum()) / (VOLUME+1);
        volCtrl.setValue(newGain);
    }

}
