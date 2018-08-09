package view;

import java.io.File;

import javax.sound.sampled.AudioInputStream;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.*;

public class SoundImpl implements Sound{

	

	private String currentFileSoundPath;
	private Media media;
	private MediaPlayer player;
	private boolean go;
	
	public SoundImpl(String path) {
		this.changeSong(path);
	}
	
	public void setCurrentFileSoundPath(String currentFileSoundPath) {
		this.currentFileSoundPath = currentFileSoundPath;
	}

	public void changeSong(String path) {
		this.setGo(false);
		this.currentFileSoundPath = path;
		ClassLoader cl = getClass().getClassLoader();
		media = new Media(cl.getResource(this.currentFileSoundPath).toExternalForm());
		this.player = new MediaPlayer(this.media);
	}
	
	private void setGo(boolean x) {
		this.go = x;
	}
	
	public void play() {
		
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		this.playSound();
		
	}

	private void playSound() {
		
		this.player.play();
		System.out.println("play");
	}
}
