package fr.isola.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Singleton permetant la gestion de la musique dans l'application.
 */
public class AudioManager {


    /**
     * Instance du singleton de la classe.
     */
    public static AudioManager INSTANCE = new AudioManager();
    /**
     * Float static correspondant au volume de l'audio.
     */
    public static float VOLUME = 2;
    /**
     * Clip par lequel est jouer la musique.
     */
    private  Clip musicClip;

    /**
     * Initialise la classe.
     */
    public AudioManager() {
        try {
            musicClip = AudioSystem.getClip();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Joue une musique d'arrière plan.
     * @param name Nom du fichier de la musique (doit être un .waw et être dans /resources/audio).
     */
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

    /**
     * Joue une musique d'arrière de victoire.
     * @param name Nom du fichier de la musique (doit être un .waw et être dans /resources/victory).
     */
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

    /**
     * Stop toutes les musiques.
     */
    public void StopBackgroundAudio() {
        try {
            musicClip.stop();
            musicClip.close();;
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Change le volume de la musique en cours.
     * Le volume choisit est celui de la variable VOLUME.
     */
    public void SetBackgroundMusicVolume() {
        FloatControl volCtrl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
        float newGain = volCtrl.getMinimum() + VOLUME * (volCtrl.getMaximum() - volCtrl.getMinimum()) / (VOLUME+1);
        volCtrl.setValue(newGain);
    }


    /**
     * Augmentre le volume de 1.
     */
    public void IncreaseVolume() {
        AudioManager.VOLUME++;
        if(AudioManager.VOLUME > 100) AudioManager.VOLUME = 100;
        SetBackgroundMusicVolume();
    }

    /**
     * Diminue le volume de 1.
     */
    public void DecreaseVolume() {
        AudioManager.VOLUME--;
        if(AudioManager.VOLUME < 0) AudioManager.VOLUME = 0;
        SetBackgroundMusicVolume();
    }

}
