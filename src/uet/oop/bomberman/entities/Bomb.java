package uet.oop.bomberman.entities;

import java.util.List;

import javafx.scene.image.Image;

public class Bomb extends Entity {

    private List<Entity> bricks;
    private List<Entity> enemies;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public void destroyBricksAndEnemies() {
        for (Entity e : bricks) {
            if (checkCollision(e, new Bomb(x + 1, y, null)) ||
                    checkCollision(e, new Bomb(x - 1, y, null)) ||
                    checkCollision(e, new Bomb(x, y + 1, null)) ||
                    checkCollision(e, new Bomb(x, y - 1, null))
            ) {

                e.removeFromGame();
            }
        }
        for (Entity e : enemies) {
            if (checkCollision(e, new Bomb(x + 1, y, null)) ||
                    checkCollision(e, new Bomb(x - 1, y, null)) ||
                    checkCollision(e, new Bomb(x, y + 1, null)) ||
                    checkCollision(e, new Bomb(x, y - 1, null))
            ) {

                e.removeFromGame();
            }
        }

    }

    public List<Entity> getBricks() {
        return bricks;
    }

    public void setBricks(List<Entity> bricks) {
        this.bricks = bricks;
    }

    public List<Entity> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Entity> enemies) {
        this.enemies = enemies;
    }


}
