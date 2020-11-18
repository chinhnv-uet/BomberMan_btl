package uet.oop.bomberman.entities.character;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.stillsobject.Brick;
import uet.oop.bomberman.entities.stillsobject.Wall;
import uet.oop.bomberman.frameGame.Keyboard;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends uet.oop.bomberman.entities.character.Character {
    private List<Bomb> bombList = new ArrayList<>();
    private Keyboard input;
    private int maxBom = 3;
    private int frameLen = 1;
    private final int[] AddToXToCheckCollision = {0, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE - 10, 0};
    private final int[] AddToYToCheckCollision = {7, 7, Sprite.SCALED_SIZE-1, Sprite.SCALED_SIZE-1};

    public Bomber(int x, int y, Keyboard kb) {
        super(x, y, Sprite.player_down.getFxImage()); //luc dau mac dinh la huong xuong
        this.input = kb;
        direction = 1;
        velocity = 2;
        input = kb;
    }

    public void update() {
        animate();
        bombUpdate();
        input = BombermanGame.canvas.getInput();
        if (!isAlive()) {
            //TODO showDeadAnimation
        } else {
            if (input.space) {
                if (bombList.size() < maxBom) {
                    Entity e = BombermanGame.canvas.getEntityInCoodinate(getXUnit(), getYUnit());
                    if (e == null) {
                        bombList.add(new Bomb(getXUnit(), getYUnit()));
                    }
                }
            }
            if (input.up || input.right || input.left || input.down) {
                setMoving(true);
            } else {
                setMoving(false);
                switch (direction) {
                    case 0:
                        this.setImg(Sprite.player_up.getFxImage());
                        break;
                    case 1:
                        this.setImg(Sprite.player_down.getFxImage());
                        break;
                    case 2:
                        this.setImg(Sprite.player_left.getFxImage());
                        break;
                    case 3:
                        this.setImg(Sprite.player_right.getFxImage());
                        break;
                }
            }
            if (isMoving()) {
                calculateMove();
            }
        }
    }

    public void render() {

    }

    public void bombRender(GraphicsContext gc) {
        bombList.forEach(b->b.render(gc));
    }

    public void bombUpdate() {
        bombList.forEach(b->b.update());
        for (Bomb b : bombList) {
            if (b.isExplored()) {
                bombList.remove(b);
                break;
            }
        }
    }

    public void calculateMove() {
        if (input.up) {
            y -= velocity;
            if (!canMove()) {
                y += velocity;
            }
            setDirection(0);
            this.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, timeTransfer).getFxImage());
        } else if (input.down) {
            y += velocity;
            if (!canMove()) {
                y -= velocity;
            }
            setDirection(1);
            this.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, timeTransfer).getFxImage());
        } else if (input.left) {
            x -= velocity;
            if (!canMove()) {
                x += velocity;
            }
            setDirection(2);
            this.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, timeTransfer).getFxImage());
        } else {
            x += velocity;
            if (!canMove()) {
                x -= velocity;
            }
            setDirection(3);
            this.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, timeTransfer).getFxImage());
        }
    }

    public boolean canMove() {
        for (int i = 0; i < 4; i++) { //check collision for 4 corners
            int newX = (getX() + AddToXToCheckCollision[i])/Sprite.SCALED_SIZE;
            int newY = (getY() + AddToYToCheckCollision[i])/Sprite.SCALED_SIZE;
            Entity e = BombermanGame.canvas.getEntityInCoodinate(newX, newY);
            if (e instanceof Wall) {
                return false;
            }
            if (e instanceof Brick) {
                return false;
            }
            //TODO: check for other entities
        }
        return true;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }
}
