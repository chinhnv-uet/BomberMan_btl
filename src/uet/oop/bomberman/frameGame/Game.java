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
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    public static String[] paths = {"res\\levels\\Level1.txt", "res\\levels\\Level2.txt", "res\\levels\\Level3.txt",};
    public int WIDTH, HEIGHT;

    private List<Grass> grassList;
    private List<Entity> entityList; // list to check collision
    private List<Bomb> bombs;
    private List<Enemy> enemyList;

    public Bomber bomberman = new Bomber(1, 1, new Keyboard());
    public Bomber bomberInPreLevel = new Bomber(1, 1, new Keyboard());
    private Bomber originBomber;

    public Level level = new Level();
    private int currentLevel = 1;
    private int timeShowTransferLevel = 100;
    private boolean TransferLevel = false;

    private boolean gameOver = false;

    public Game() {
    }

    public void createMap() {

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
        setTime();
    }

    public void update() {
        if (TransferLevel == false) {
            updateAllEntities();
        }
        if (bomberman.isAlive() == false) {
            BombermanGame.lives -= 1;

            bomberInPreLevel.restoreBomber(originBomber);
            this.createMap();
        }
        if (BombermanGame.lives == 0) gameOver = true;

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
                System.out.println("You win");
                gameOver = true;
                bomberman.setAlive(false);
                return;
            }
            this.createMap();
        }
    }

    // set timer for one life
    static Timer timer;
    static int interval;

    int delay = 1000;
    int period = 1200;

    public void setTime() {
        timer = new Timer();
        interval = BombermanGame.timeLiving;
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (BombermanGame.lives != 0 && bomberman.isAlive()) setInterval();
            }

        }, delay, period);


    }

    public final int setInterval() {
        if (interval <= 1) {
            timer.cancel();
            bomberman.setAlive(false);
        }
        return --interval;
    }


    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (TransferLevel == false) {
            renderInfoOfCurrentLevel(gc);
            grassList.forEach(g -> g.render(gc));

            entityList.forEach(e -> e.render(gc));
            enemyList.forEach(e -> {
                e.render(gc);
                e.setBomber(bomberman);
                if (e instanceof Oneal) ((Oneal) e).updateBomberForAI();
                if (e instanceof Minvo) ((Minvo) e).updateBomberForAI();
                if (e instanceof Kondoria) ((Kondoria) e).updateBomberForAI();
            });
            bomberman.bombRender(gc);
            bomberman.render(gc);
        } else {
            if (timeShowTransferLevel-- > 0) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 992, 448);
                gc.setFill(Color.WHITE);
                gc.setFont(new Font("", 60));
                gc.fillText("Level: " + currentLevel, 400, 200);
            } else {
                TransferLevel = false;
                timeShowTransferLevel = 100;
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

    public void renderInfoOfCurrentLevel(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 416, 992, 448);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 15));
        gc.fillText("Time left: " + interval, 0, 440);
        gc.fillText("Level: " + currentLevel, 200, 440);
        gc.fillText("Lives: " + BombermanGame.lives, 500, 440);
        gc.fillText("Scores: " + BombermanGame.scores, 700, 440);
    }
}
