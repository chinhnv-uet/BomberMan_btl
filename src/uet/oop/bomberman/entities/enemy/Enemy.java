package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ai.AI;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.stillsobject.*;
import uet.oop.bomberman.frameGame.Keyboard;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends AnimatedEntity {

    protected boolean isAlive = true;
    protected int direction = 0;
    //0 left, 1 right, 2 up, 3 down

    protected AI ai;

    protected final int[] AddToXToCheckCollision = {2, Sprite.SCALED_SIZE-2, Sprite.SCALED_SIZE-2, 2};
    protected final int[] AddToYToCheckCollision = {2, 2, Sprite.SCALED_SIZE-2, Sprite.SCALED_SIZE-2};

    protected Bomber bomber = new Bomber(0, 0, new Keyboard());
    protected final int timeTransfer = 26;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }


    public boolean canMove(int x, int y) {
        int xUnit = (int) x / Sprite.SCALED_SIZE;
        int yUnit = (int) y / Sprite.SCALED_SIZE;
        Entity e = BombermanGame.canvas.getEntityInCoodinate(xUnit, yUnit);
        if (e instanceof Wall || e instanceof Brick || e instanceof Bomb) {
            return false;
        }
        return true;
    }

    public abstract void deadAnimation();

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }


    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }

}
