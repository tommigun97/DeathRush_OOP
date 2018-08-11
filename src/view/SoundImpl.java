package view;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import view.Sound.song;



public class SoundImpl implements Sound{
	
	private Clip songToPlay;
	private boolean isOn;
	private boolean musicOn;
	
	
	public SoundImpl() {
	}
	
	
	private void setSong(String pathToSong) {
		AudioInputStream song;
		try {
			song = AudioSystem.getAudioInputStream(this.getClass().getResource(pathToSong));
			try {
				this.songToPlay = AudioSystem.getClip();
				this.songToPlay.open(song);
			}catch(LineUnavailableException e) {
				e.printStackTrace();
			}
		}catch(UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void musicPlay(String pathToSong) {
		if(this.isOn) {
			this.musicStop();
		}
		this.setSong(pathToSong);
		this.isOn = true;
		this.musicOn = true;
		this.songToPlay.start();
		this.songToPlay.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void musicStop() {
		if(this.isOn) {
			this.songToPlay.stop();
			this.isOn = false;
			this.musicOn = false;	
		}
	}
}
