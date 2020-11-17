package uet.oop.bomberman.frameGame;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.stillsobject.Brick;
import uet.oop.bomberman.entities.stillsobject.Grass;
import uet.oop.bomberman.entities.stillsobject.Wall;
import uet.oop.bomberman.entities.character.Bomber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    //TODO: sau nay toi uu thanh it list hon
    private List<Wall> wallList;
    private List<Grass> grassList;
    private List<Brick> brickList;

    public static String[] paths = {"res\\levels\\Level1.txt"};
    //list enemy
    //list item
    //portal
    Entity bomberman;
    private Level level = new Level();
    private int currentLevel = 1;

    public Game() {
    }

    public void createMap() {

        level.createMapLevel(paths[currentLevel - 1]);

        this.setWallList(level.getWallList());
        this.setGrassList(level.getGrassList());
        this.setBrickList(level.getBrickList());
        bomberman = level.getBomber();
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
        brickList.forEach(g -> g.render(gc));
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
        //TODO: for enemy
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
}
