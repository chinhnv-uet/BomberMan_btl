package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;

public abstract class Character extends AnimatedEntity {
    protected int direction;// 0 Up, 1 Down, 2 Left, 3 Right
    protected boolean alive = true;
    protected boolean moving = false;
    protected int velocity;
    protected final int timeTransfer = 26;

    public Character(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getTimeTransfer() {
        return timeTransfer;
    }

    public abstract void update();

    public abstract void render();

    public abstract void calculateMove();

    public abstract boolean canMove();
}
