package uet.oop.bomberman.entities;

import java.util.List;

import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntity {
    private int timeBeforeExplore = 200;
    private int timeFrame = 10;
    private int timeTransfer = 40;
    private boolean explored;

    public Bomb(int x, int y) {
        super(x, y, Sprite.bomb.getFxImage());
        animate = 0;
        explored = false;
    }

    @Override
    public void update() {
        animate();
        if (explored == false) {
            if (animate < timeBeforeExplore) {
                setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, timeTransfer).getFxImage());
            } else {
                explored = true;
                animate = 0;
            }
        }
    }

    public boolean isExplored() {
        return explored;
    }
}
