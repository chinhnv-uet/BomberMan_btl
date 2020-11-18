package uet.oop.bomberman.frameGame;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.stillsobject.*;
import uet.oop.bomberman.entities.character.Bomber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    //TODO: sau nay toi uu thanh it list hon
    private List<Wall> wallList;
    private List<Grass> grassList;
    private List<Brick> brickList;
    private List<Enemy> enemyList;
    
    public static String[] paths = {"res\\levels\\Level1.txt"};
   
    //list item
    //portal
    public Bomber bomberman;
    private Level level = new Level();
    private int currentLevel = 1;

    public Game() {
    }

    public void createMap() {

        level.createMapLevel(paths[currentLevel - 1]);

        this.setWallList(level.getWallList());
        this.setGrassList(level.getGrassList());
        this.setBrickList(level.getBrickList());
        this.setEnemyList(level.getEnemyList());
        bomberman = level.getBomber();
    }

    public void update() {
      //  enemyList.forEach(e -> e.update());
        brickList.forEach(b -> b.update());
        bomberman.update();
    }

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grassList.forEach(g -> g.render(gc));
        wallList.forEach(g -> g.render(gc));
        brickList.forEach(g -> g.render(gc));
        
        for (Enemy e : enemyList) {
        	e.render(gc);
        	e.setBomber(bomberman);
        	e.setWallList(wallList);
        	e.setBrickList(brickList);
        }
        bomberman.render(gc);
    }

    public Entity getEntityOnCoodinate(int x, int y) {
        for (Wall e : wallList) {
            if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }

        for (Brick b : brickList) {
            if (b.getXUnit() == x && b.getYUnit() == y) {
                return b;
            }
        }
        for (Enemy e : enemyList) {
        	if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }
        return null;
    }

    public void setWallList(List<Wall> wallList) {
        this.wallList = wallList;
    }

    public void setGrassList(List<Grass> grassList) {
        this.grassList = grassList;
    }

    public void setBrickList(List<Brick> brickList) {
        this.brickList = brickList;
    }

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
    
}
