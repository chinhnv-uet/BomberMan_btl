package uet.oop.bomberman.soundAndTimer;

import java.util.Timer;
import java.util.TimerTask;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.frameGame.Game;

public class Timers {
	private boolean play = false;
	Timer timers;
    private int interval;
	public static int delay = 1000;

    public static int period = 1000;
    private int check; //kiem tra xem dem nguoc thoi gian cho doi tuong nao
    public void setTime() {
        timers = new Timer();
        setPlay(true);
        timers.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                setInterval();
            }

        }, delay, period);
        
    }
    public final int setInterval() {
        if (interval <= 1) {
            timers.cancel();
            if (check == BombermanGame.timeLiving) Game.bomberman.setAlive(false);
            if (check == 50) Bomber.canPassBom = false;
            if (check == 60) Bomber.canPassFlame = false;
            setPlay(false);
            
        }
        return --interval;
    }
	public boolean isPlay() {
		return play;
	}
	public void setPlay(boolean play) {
		this.play = play;
	}


    public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
		check = interval;
	}
}
