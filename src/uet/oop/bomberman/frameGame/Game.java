package uet.oop.bomberman.frameGame;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.stillsobject.*;
import uet.oop.bomberman.entities.character.Bomber;

import java.util.List;

public class Game {
    private List<Grass> grassList;
    private List<Entity> entityList; // list to check collision
    private List<Bomb> bombs;
    private List<Enemy> enemyList;

    public static String[] paths = {"res\\levels\\Level1.txt"};

    public Bomber bomberman;
    private Level level = new Level();
    private int currentLevel = 1;

    public Game() {
    }

    public void createMap() {
        level.createMapLevel(paths[currentLevel - 1]);
        this.setGrassList(level.getGrassList());

        bomberman = level.getBomber();
        entityList = level.getCollidableEntities();
        enemyList = level.getEnemyList();
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
    }

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grassList.forEach(g -> g.render(gc));

        entityList.forEach(e -> {
            e.render(gc);
        });
        enemyList.forEach(e -> e.render(gc));
        bomberman.bombRender(gc);
        bomberman.render(gc);
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
}
