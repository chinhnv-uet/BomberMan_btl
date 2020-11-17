package uet.oop.bomberman.frameGame;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.stillsobject.Grass;
import uet.oop.bomberman.entities.stillsobject.Wall;
import uet.oop.bomberman.entities.character.Bomber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Wall> wallList;
    private List<Grass> grassList;

	

	public static String[] paths = {"C:\\Users\\Administrator\\Documents\\GitHub\\BomberMan_btl\\src\\uet\\oop\\bomberman\\frameGame\\Level1.txt",
									"Level2.txt", 
									"Level3.txt"};
    //list enemy
    //list brick
    //list item
    //portal
    Entity bomberman;
    private Level level = new Level();
    private int currentLevel = 1;
    private final int WIDTH = 20;
    private final int HEIGHT = 15;

    public Game() {
        grassList = new ArrayList<>();
        wallList = new ArrayList<>();
    }

    public void createMap() throws IOException { 
    	
        level.createMapLevel("C:\\Users\\Administrator\\Documents\\GitHub\\BomberMan_btl\\src\\uet\\oop\\bomberman\\frameGame\\Level1.txt");
        
        this.setWallList(level.getWallList());
        this.setGrassList(level.getGrassList());
        bomberman = new Bomber(1, 1, new Keyboard());
    }

    public void update() {
        //enemy update
        //brick update
        bomberman.update();
    }

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grassList.forEach(g -> g.render(gc));
        wallList.forEach(g -> g.render(gc));
        bomberman.render(gc);
    }

    public Entity getEntityOnCoodinate(int x, int y) {
        for (Wall e : wallList) {
            if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }
        //TODO: for brick, enemy
        return null;
    }
    public List<Wall> getWallList() {
		return wallList;
	}

	public void setWallList(List<Wall> wallList) {
		this.wallList = wallList;
	}

	public List<Grass> getGrassList() {
		return grassList;
	}

	public void setGrassList(List<Grass> grassList) {
		this.grassList = grassList;
	}
}
