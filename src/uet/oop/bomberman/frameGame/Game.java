package uet.oop.bomberman.frameGame;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.stillsobject.*;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
    private List<Grass> grassList;
    private List<Entity> entityList; // list to check collision
    private List<Bomb> bombs;
    private List<Enemy> enemyList;


    public static String[] paths = {"res\\levels\\Level1.txt", "res\\levels\\Level2.txt"};
    public int WIDTH, HEIGHT;

    public static Bomber bomberman = new Bomber(1, 1, new Keyboard());
    public Bomber bomberInPreLevel = new Bomber(1, 1, new Keyboard());
    public Level level = new Level();
    private int currentLevel = 1;
    private boolean gameOver = false;

    public Game() {
    }

    public void createMap() {

        level.createMapLevel(paths[currentLevel - 1]);
        WIDTH = level.getW();
        HEIGHT = level.getH();

        this.setGrassList(level.getGrassList());

        //phuc hoi cac thuoc tinh bomber cua level truoc va set vi tri moi
        Bomber tmp = level.getBomber();
        bomberman.restoreBomber(bomberInPreLevel);
        bomberman.setX(tmp.getX());
        bomberman.setY(tmp.getY());

        entityList = level.getCollidableEntities();
        enemyList = level.getEnemyList();
        setTime();
    }

    public void update() {
        bomberman.update();
    	for (Entity e : enemyList) {
            if (e.getImg() == null) {
                enemyList.remove(e);
                break;
            } else {
                e.update();
            }
        }
        


        for (Entity e : entityList) {

            if (e.getImg() == null) { // if img == null, thi xoa entity do
                if (e instanceof Brick) {
                    if (((Brick) e).isBrickHasPortal()) {
                        this.addEntity(new Portal(e.getXUnit(), e.getYUnit()));
                    }
                    if (((Brick) e).isBrickHasItem()) {
                        this.addEntity(((Brick) e).getItem());
                    }
                }
                entityList.remove(e);

                break;
            } else {
                e.update();
            }
        }

        if (enemyList.size() == 0) bomberman.setKillAllEnemies(true);
        if (bomberman.isCollideWithAPortal() ) {
        	currentLevel++;
        	if (currentLevel > paths.length) {
        		System.out.println("You win");
        		gameOver = true;
        		bomberman.setAlive(false);
        		return;
        	}
        	this.createMap();
        } 
        if (bomberman.isAlive() == false) {
        	BombermanGame.lives = BombermanGame.lives - 1;

        if (enemyList.size() == 0) {
            bomberman.setKillAllEnemies((true));
        }
        if (bomberman.isCollideWithAPortal()) {
            bomberInPreLevel.restoreBomber(bomberman);
            currentLevel++;
            this.createMap();
        }
        if (bomberman.isAlive() == false) gameOver = true;


        	System.out.println("Lives: " + BombermanGame.lives);
        	System.out.println("Scores:" + BombermanGame.scores);
            if (BombermanGame.lives == 0) {
            	gameOver = true;
            } else {
            	this.createMap();
            }
        	
        }
    }
    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grassList.forEach(g -> g.render(gc));


        entityList.forEach(e -> e.render(gc));
        enemyList.forEach(e -> {
        	e.render(gc);
        	e.setBomber(bomberman);
        	if (e instanceof Oneal) {
        		((Oneal) e).updateBomberForAI();
        	}

        entityList.forEach(ett -> {
            ett.render(gc);
            if (ett instanceof Enemy) {
                ((Enemy) ett).setBomber(bomberman);
            }

        });
        bomberman.bombRender(gc);
        bomberman.render(gc);
        });
    }

    public Entity getEntityOnCoodinate(int x, int y) {
        for (Entity e : entityList) {
            if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }
        for (Enemy e : enemyList) {
            if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }
        bombs = bomberman.getBombList();
        for (Bomb b : bombs) {
            if (b.getXUnit() == x && b.getYUnit() == y) {
                return b;
            }
        }
        return null;
    }

    // set timer for one life
    static Timer timer;
    static int interval;
    public void setTime() {
    	int delay = 1000;
    	int period = 1000;
    	timer = new Timer();
    	interval = BombermanGame.timeLiving;
    	timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (bomberman.isAlive()) System.out.println(setInterval());
			}
    		
    	}, delay, period);
    	
    	
    }
    public static final int setInterval() {
    	if (interval <= 1) {
    		timer.cancel();
    		bomberman.setAlive(false);
    	} 
    	return --interval;
    	
    }
    
    
    
    public void setGrassList(List<Grass> grassList) {
        this.grassList = grassList;
    }

    public void addEntity(Entity e) {
        entityList.add(e);
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int wIDTH) {
        WIDTH = wIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int hEIGHT) {
        HEIGHT = hEIGHT;
    }

    public boolean isGameOver() {
        return gameOver;
    }

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


}
