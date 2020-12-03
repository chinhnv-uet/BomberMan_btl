package uet.oop.bomberman.soundAndTimer;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	
	Long currentFrame;
	Clip clip;

	String status;

	AudioInputStream audioInputStream;
	private String filePath;
	
	public Sound(String path)  {
		this.filePath = path;
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void play() {
		clip.start();
	}
	public void stop() {
		clip.stop();
		clip.close();
		try {
			resetAudioStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		clip.open(audioInputStream);
	}
    
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String path) {
		this.filePath = path;
	}


}
