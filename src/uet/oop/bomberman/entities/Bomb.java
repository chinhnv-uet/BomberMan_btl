package uet.oop.bomberman.entities;

import java.util.List;

import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntity {

    private List<Entity> bricks;
    private List<Enemy> enemies;

    public Bomb(int x, int y) {
        super(x, y, Sprite.bomb.getFxImage());
    }

    @Override
    public void update() {
        animate();
    }

//    public void destroyBricksAndEnemies() {
//        for (Entity e : bricks) {
//            if (checkCollision(e, new Bomb(x + 1, y, null)) ||
//                    checkCollision(e, new Bomb(x - 1, y, null)) ||
//                    checkCollision(e, new Bomb(x, y + 1, null)) ||
//                    checkCollision(e, new Bomb(x, y - 1, null))
//            ) {
//
//                e.removeFromGame();
//            }
//        }
//        for (Enemy e : enemies) {
//            if (checkCollision(e, new Bomb(x + 1, y, null)) ||
//                    checkCollision(e, new Bomb(x - 1, y, null)) ||
//                    checkCollision(e, new Bomb(x, y + 1, null)) ||
//                    checkCollision(e, new Bomb(x, y - 1, null))
//            ) {
//            	e.deadAnimation();
//                e.removeFromGame();
//            }
//        }
//
//    }

    public List<Entity> getBricks() {
        return bricks;
    }

    public void setBricks(List<Entity> bricks) {
        this.bricks = bricks;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }


}
