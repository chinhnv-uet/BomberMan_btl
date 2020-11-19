package uet.oop.bomberman.entities.character;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.stillsobject.Brick;
import uet.oop.bomberman.entities.stillsobject.Portal;
import uet.oop.bomberman.entities.stillsobject.Wall;
import uet.oop.bomberman.frameGame.Keyboard;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends uet.oop.bomberman.entities.character.Character {
    private List<Bomb> bombList = new ArrayList<>();
    private Keyboard input;
    private int maxBom = 3;
    private int frameLen = 10;
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
            this.setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, timeTransfer).getFxImage());
        } else {
            if (input.space) {
                if (bombList.size() < maxBom) {
                    Entity e = BombermanGame.canvas.getEntityInCoodinate(getXUnit(), getYUnit());
                    if (e == null) {
                        bombList.add(new Bomb(getXUnit(), getYUnit(), frameLen));
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
        for(Bomb b : bombList) {
            if (b.isExplored()) {
                b.frameRender(gc);
            } else {
                b.render(gc);
            }
        }
    }

    public void bombUpdate() {//TODO: check
        bombList.forEach(b->b.update());
        for (Bomb b : bombList) {
            if (b.getImg() == null) {
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
            if (e instanceof Wall || e instanceof Brick || e instanceof Portal) {
                return false;
            }
            if (e instanceof Enemy) { //TODO: co loi bomber o giua 2 o khi va cham enemy ko chet, co the tack check enemy ra cai khac ko cong x, y nua
                setAlive(false);
                return true;
            }
        }
        return true;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }
}
