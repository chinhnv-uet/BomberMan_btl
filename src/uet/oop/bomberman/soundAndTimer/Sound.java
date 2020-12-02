package uet.oop.bomberman.soundAndTimer;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;

public class Sound {
	
	public static String soundGame = "res\\sounds\\inLevel.mp3";
	public static String soundTransferLevel = "res\\sounds\\levelComplete.mp3" ;
	public static String soundEatingItem = "res\\sounds\\eatingItem.wav";
	public static String soundMenu = "res\\sounds\\Title.mp3";
	public static String soundExplosion = "res\\sounds\\explosion.mp3";
	public static String soundDead = "res\\sounds\\LifeLost.mp3";
	public static String soundLoseGame = "res\\sounds\\gameOver.mp3";
	public static String soundWinGame = "res\\sounds\\Victory.mp3";
	public static String soundMoving = "res\\sounds\\moving.wav";
	public static String soundPlaceBomb = "res\\sounds\\placeBomb.wav";
	
	private MediaPlayer mediaPlayer;
	private String path;
	public Sound() {
		
	}
	
	@SuppressWarnings("deprecation")
	public Sound(String path) {
		
		this.setPath(path);
		try {
			mediaPlayer = new MediaPlayer(new Media(new File(path).toURL().toString()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
			
		
	}
	
	@SuppressWarnings("deprecation")
	public void setSound()  {
		if (BombermanGame.mute) return;
		
		if (path.equals(soundGame) || path.equals(soundMenu)) {
				mediaPlayer.setOnEndOfMedia(new Runnable() {
					@Override
					public void run() {
						mediaPlayer.seek(new Duration(0));
						
					}
				});
		}
		if (path.equals(soundDead)) {
			mediaPlayer.setStopTime(new Duration(1000*3));
			try {
				mediaPlayer = new MediaPlayer(new Media(new File(path).toURL().toString()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} 
		if (path.equals(soundEatingItem)) {
			mediaPlayer.setStopTime(new Duration(1000*2));
			try {
				mediaPlayer = new MediaPlayer(new Media(new File(path).toURL().toString()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
        
        mediaPlayer.play();
        
    }
    
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

}
