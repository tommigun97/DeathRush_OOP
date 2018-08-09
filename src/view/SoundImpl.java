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
	private ClassLoader cl;
	public SoundImpl(String path) {
		this.setCurrentFileSoundPath(path);
	}
	
	public void setCurrentFileSoundPath(String currentFileSoundPath) {
		this.currentFileSoundPath = currentFileSoundPath;
		cl = getClass().getClassLoader();
		media = new Media(cl.getResource(this.currentFileSoundPath).toExternalForm());
		this.player = new MediaPlayer(this.media);
	} 

	public void changeSong(String path) {
		this.player.stop();
		this.player.dispose();
		this.currentFileSoundPath = path;
		media = new Media(cl.getResource(this.currentFileSoundPath).toExternalForm());
		this.player = new MediaPlayer(this.media);
		this.playSound();		
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

	public void playSound() {
		this.player.play();
	}
}
