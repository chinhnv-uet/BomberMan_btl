package uet.oop.bomberman.frameGame;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.stillsobject.*;
import uet.oop.bomberman.soundAndTimer.Sound;
import uet.oop.bomberman.soundAndTimer.Timers;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.*;
import java.util.List;


public class Game {
    public static String[] paths = {"res\\levels\\Level1.txt"};
    public int WIDTH, HEIGHT;

    private List<Grass> grassList;
    private List<Entity> entityList; // list to check collision
    private List<Bomb> bombs;
    private List<Enemy> enemyList;

    //bomber
    public static Bomber bomberman = new Bomber(1, 1, new Keyboard());
    public Bomber bomberInPreLevel = new Bomber(1, 1, new Keyboard());
    private Bomber originBomber;

    //level
    public Level level = new Level();
    private int currentLevel = 1;
    private int timeShowTransferLevel = 150;
    private boolean TransferLevel = false;

    private boolean gameOver = false;
    private boolean returnMainMenu = false;
    
    private Timers timer = new Timers();
    private Timers timeToStopCanPassBom = new Timers();
    private Timers timeToStopCanPassFlame = new Timers();
    
    public Sound soundGame = new Sound(Sound.soundGame);
    public Sound soundLoseGame = new Sound(Sound.soundLoseGame);
    public Sound soundWinGame = new Sound(Sound.soundWinGame);
    public Sound soundLevel_up = new Sound(Sound.soundTransferLevel);
    public Sound soundDead = new Sound(Sound.soundDead);

    
    
    public Game() {
    }


	public void createMap() {
    	//TODO: map được tạo ra trước, nên là thời gian cứ bị đếm trong khi chưa vào Game
        if (currentLevel > paths.length) return;
        level.createMapLevel(paths[currentLevel - 1]);
        WIDTH = level.getW();
        HEIGHT = level.getH();

        this.setGrassList(level.getGrassList());

        //phuc hoi cac thuoc tinh bomber cua level truoc va set vi tri moi
        originBomber = level.getBomber();
        bomberman.restoreBomber(bomberInPreLevel);
        bomberman.setX(originBomber.getX());
        bomberman.setY(originBomber.getY());
        bomberman.setAlive(true);

        entityList = level.getCollidableEntities();
        enemyList = level.getEnemyList();
        
        timer.setInterval(BombermanGame.timeLiving);
        timer.setTime();
    }
    private boolean isPlayingSoundGame = false;
    

	public void update() {
		
		
		if (!TransferLevel) {
			soundLevel_up.getMediaPlayer().stop();
            Timers.delay += 400;
            
            updateAllEntities();
            
            if  (!isPlayingSoundGame ) {
        		soundGame.setSound();
        		isPlayingSoundGame = true;
            } 
            
            
        } else {
        	
        	 	soundGame.getMediaPlayer().stop();
            	isPlayingSoundGame = false;
            	soundLevel_up.setSound();
            	
        	
        	
        }
        
        if (bomberman.isAlive() == false) {
        	soundGame.getMediaPlayer().stop();
        	soundDead.setSound();
        	
        	Timers.delay += 400;
            BombermanGame.lives -= 1;
            bomberInPreLevel.restoreBomber(originBomber);
            this.createMap();
        } else {
        	soundGame.setSound();
        }
        if (BombermanGame.lives == 0) gameOver = true;
        if (gameOver) {
        	soundGame.getMediaPlayer().stop();
        	soundDead.getMediaPlayer().stop();
        	if (BombermanGame.lives == 0) soundLoseGame.setSound();
        	else if (BombermanGame.lives > 0) soundWinGame.setSound();
        }
    }

    public void updateAllEntities() {
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
        if (enemyList.size() == 0) {
            bomberman.setKillAllEnemies((true));
        }
        if (bomberman.isCollideWithAPortal()) {
            bomberInPreLevel.restoreBomber(bomberman);
            currentLevel++;
            TransferLevel = true;

            if (currentLevel > paths.length) {
                TransferLevel = false;
                gameOver = true;
                bomberman.setAlive(false);
                return;
            }
            this.createMap();
        }
    }
    


    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        
        if (TransferLevel == false ) {
            renderInfoOfCurrentLevel(gc);
            grassList.forEach(g -> g.render(gc));
            entityList.forEach(e -> e.render(gc));

            enemyList.forEach(e -> {
                e.render(gc);
                e.setBomber(bomberman);
                if (e instanceof Oneal) ((Oneal) e).updateBomberForAI();
                if (e instanceof Kondoria) ((Kondoria) e).updateBomberForAI();
            });
            bomberman.bombRender(gc);
            bomberman.render(gc);
        } else if (TransferLevel == true) {
        	
            if (timeShowTransferLevel-- > 0) {
                renderTransferLevelScreen(gc);
            } else {
                TransferLevel = false;
                timeShowTransferLevel = 150;
            }
        }

        if (gameOver) {
            if (timeShowTransferLevel-- > 0) { // show gameover animation
                if (BombermanGame.lives == 0) {
                	renderGameOverScreen(gc);
                }
                else if (BombermanGame.lives > 0) {
                	renderVictoryScreen(gc);
                }
            } else { // return main menu
                TransferLevel = false;
                gameOver = false;
                timeShowTransferLevel = 150;
                returnMainMenu = true;
                

                //reset lives and level
                BombermanGame.lives = 3;
                currentLevel = 1;
                BombermanGame.scores = 0;
            }
        }
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

    public void setGrassList(List<Grass> grassList) {
        this.grassList = grassList;
    }

    public void addEntity(Entity e) {
        entityList.add(e);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setTransferLevel(boolean transferLevel) {
        TransferLevel = transferLevel;
    }

    public boolean isReturnMainMenu() {
        return returnMainMenu;
    }

    public void setReturnMainMenu(boolean returnMainMenu) {
        this.returnMainMenu = returnMainMenu;
    }

    

    public void renderInfoOfCurrentLevel(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 416, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 15));
        gc.fillText("Time left: " + timer.getInterval(), 0, 440);
        gc.fillText("Level: " + currentLevel, 200, 440);
        gc.fillText("Lives: " + BombermanGame.lives, 300, 440);
        gc.fillText("Scores: " + BombermanGame.scores, 400, 440);
        if (Bomber.canPassBom) {
        	timeToStopCanPassBom.setInterval(50);
        	timeToStopCanPassBom.setTime();
        	gc.fillText("Pass Bomb in: " + timeToStopCanPassBom.getInterval(), 500, 440);
        }
        if (Bomber.canPassFlame) {
        	timeToStopCanPassFlame.setInterval(60);
        	timeToStopCanPassFlame.setTime();
        	gc.fillText("Pass Flame in: " + timeToStopCanPassFlame.getInterval(), 700, 440);
        }
    }

    public void renderTransferLevelScreen(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 60));
        gc.fillText("Level: " + currentLevel, 400, 250);
    }

    public void renderGameOverScreen(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 60));
        gc.fillText("You Lose!\nGame Over :(", 350, 200);
        gc.fillText("Your score: " + BombermanGame.scores, 350, 350);
    }

    public void renderVictoryScreen(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 60));
        gc.fillText("You win!\nCongrats!! :)", 350, 200);
        gc.fillText("Your score: " + BombermanGame.scores, 350, 350);
    }
}
