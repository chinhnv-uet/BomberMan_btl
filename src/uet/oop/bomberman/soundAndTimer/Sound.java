package uet.oop.bomberman.soundAndTimer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import uet.oop.bomberman.BombermanGame;

public class Sound {
	
	public static String soundGame = "res\\sounds\\inLevel.wav";
	public static String soundTransferLevel = "res\\sounds\\levelComplete.wav" ;
	public static String soundEatingItem = "res\\sounds\\eatingItem.wav";
	public static String soundMenu = "res\\sounds\\Title.wav";
	public static String soundExplosion = "res\\sounds\\explosion.wav";
	public static String soundDead = "res\\sounds\\LifeLost.wav";
	public static String soundLoseGame = "res\\sounds\\gameOver.wav";
	public static String soundWinGame = "res\\sounds\\Victory.wav";
	public static String soundMoving = "res\\sounds\\moving.wav";
	public static String soundPlaceBomb = "res\\sounds\\placeBomb.wav";
	
	long currentFrame;
	Clip clip;
	private boolean running = false;
	private String path;
	private String status = "";

	public Sound(String path){
		this.path = path;
		try {
			File file = new File(path);
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);; 
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void play() {
		if (running || BombermanGame.mute) return;
		clip.setFramePosition(0);
		if (path.equals(soundMenu) || path.equals(soundGame)) clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		running = true;
		status = "play";
	}
	public void stop() {
		clip.stop();
		running = false;
		status = "stop";
	}
	public void pause() {
		clip.stop();
		running = false;
		currentFrame = clip.getMicrosecondPosition();
		status = "pause";
	}

	public void resume() {
		if (status.equals("pause")) {
			if (running || BombermanGame.mute) return;
			clip.close();
			resetAudioStream();
			if (path.equals(soundGame)) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			clip.setMicrosecondPosition(currentFrame);
			clip.start();
			running = true;
			status = "play";
		}
	}

	private void resetAudioStream() {
		try {
			File file = new File(path);
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);;
			clip = AudioSystem.getClip();
			clip.open(ais);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getStatus() {
		return status;
	}
}
