package view;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class implements the Sound.
 *
 */
public class SoundImpl implements Sound {

    private Clip songToPlay;
    private boolean isOn;

    private void setSong(final String pathToSong) {
        AudioInputStream song;
        try {
            song = AudioSystem.getAudioInputStream(this.getClass().getResource(pathToSong));
            try {
                this.songToPlay = AudioSystem.getClip();
                this.songToPlay.open(song);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @Override
    public final void musicPlay(final String pathToSong) {
        if (this.isOn) {
            this.musicStop();
        }
        this.setSong(pathToSong);
        this.isOn = true;
        this.songToPlay.start();
        this.songToPlay.loop(Clip.LOOP_CONTINUOUSLY);
    }
    @Override
    public final void musicStop() {
        if (this.isOn) {
            this.songToPlay.stop();
            this.isOn = false;
        }
    }
}
