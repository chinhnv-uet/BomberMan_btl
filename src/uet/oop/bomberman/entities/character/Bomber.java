package uet.oop.bomberman.entities.character;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.stillsobject.Brick;
import uet.oop.bomberman.entities.stillsobject.Portal;
import uet.oop.bomberman.entities.stillsobject.Wall;
import uet.oop.bomberman.frameGame.Keyboard;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.*;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Character {
    private List<Bomb> bombList = new ArrayList<>();
    private Keyboard input;
    private int maxBom = 1;
    private int frameLen = 1;
    private boolean canPassBom = false;
    private boolean canPassFlame = false;
    private boolean canPassBrick = false;

    private boolean killAllEnemies = false;
    private boolean collideWithAPortal = false;

    private final int[] AddToXToCheckCollision = {0, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE - 10, 0};
    private final int[] AddToYToCheckCollision = {7, 7, Sprite.SCALED_SIZE - 1, Sprite.SCALED_SIZE - 1};

    public Bomber(int x, int y, Keyboard kb) {
        super(x, y, Sprite.player_down.getFxImage()); // luc dau mac dinh la huong xuong
        this.input = kb;
        direction = 1;
        velocity = 2;
        input = kb;
    }

    //copy cac thuoc tinh cua bomber vao 1 bomber moi
    public void restoreBomber(Bomber newBomber) {
        reset();

        this.input = newBomber.input;
        this.velocity = newBomber.velocity;
        this.maxBom = newBomber.maxBom;
        this.frameLen = newBomber.frameLen;
        this.canPassBom = newBomber.canPassBom;
        this.canPassBrick = newBomber.canPassBrick;
        this.canPassFlame = newBomber.canPassFlame;
    }

    public void reset() {
        this.setX(0);
        this.setY(0);
        this.setImg(Sprite.player_down.getFxImage());
        this.direction = 1;
        this.bombList = new ArrayList<>();
        this.killAllEnemies = false;
        this.collideWithAPortal = false;
    }


    public void update() {
        animate();

        bombUpdate();

        ifCollisionWithFlameOrEnemyOrItem();

        input = BombermanGame.canvas.getInput();

        if (isStartDie()) {
            if (timeShowDeath-- > 0) {
                this.setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 30).getFxImage());
            } else {
                setAlive(false);
                setStartDie(false);
                setTimeShowDeath(30);
            }
        } else {

            if (input.space) {
                if (bombList.size() < maxBom) {
                    Entity e = BombermanGame.canvas.getEntityInCoodinate(getXUnit(), getYUnit());
                    if (e == null) {
                        bombList.add(new Bomb(getXUnit(), getYUnit(), frameLen, this));
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

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void setCanPassBom(boolean canPassBom) {
        this.canPassBom = canPassBom;
    }

    public boolean isCanPassBom() {
        return canPassBom;
    }

    public void bombRender(GraphicsContext gc) {
        for (Bomb b : bombList) {
            if (b.isExplored()) {
                b.frameRender(gc);
            } else {
                b.render(gc);
            }
        }
    }

    public void bombUpdate() {
        bombList.forEach(b -> b.update());
        for (Bomb b : bombList) {
            if (b.getImg() == null) {
                bombList.remove(b);
                break;
            } else {
                for (Flame fl : b.getFlameList()) {
                    fl.update();
                }
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
            this.setImg(
                    Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, timeTransfer)
                            .getFxImage());
        } else if (input.down) {
            y += velocity;
            if (!canMove()) {
                y -= velocity;
            }
            setDirection(1);
            this.setImg(Sprite
                    .movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, timeTransfer)
                    .getFxImage());
        } else if (input.left) {
            x -= velocity;
            if (!canMove()) {
                x += velocity;
            }
            setDirection(2);
            this.setImg(Sprite
                    .movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, timeTransfer)
                    .getFxImage());
        } else {
            x += velocity;
            if (!canMove()) {
                x -= velocity;
            }
            setDirection(3);
            this.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate,
                    timeTransfer).getFxImage());
        }
    }

    public boolean canMove() {
        for (int i = 0; i < 4; i++) { //check collision for 4 corners
            int newX = (getX() + AddToXToCheckCollision[i]) / Sprite.SCALED_SIZE;
            int newY = (getY() + AddToYToCheckCollision[i]) / Sprite.SCALED_SIZE;
            Entity e = BombermanGame.canvas.getEntityInCoodinate(newX, newY);

            if (e instanceof Wall || (e instanceof Brick && canPassBrick == false)) {
                return false;
            }
            if (e instanceof Portal) {
                if (killAllEnemies) {
                    collideWithAPortal = true;
                    return true;
                } else
                    return false;
            }
            if (e instanceof Enemy) {
                setAlive(false);
                return true;
            }
            if (e instanceof Bomb) {
                if (canPassBom) {
                    ((Bomb) e).setAllowPass(true); // set nhu nay de khi update tiep tranh bi loi xuyen tuong nhu o duoi
                } else {
                     /*khi check lan luot 4 goc, neu i = 0 la goc trai tren
                    se gap loi di xuyen tuong neu di sang phai vi check goc trai tren neu dang o tren bom nen van di dc
                    ma ben phai lai gap vat can, do vay phai xet du 4 goc neu dang o tren bom tranh loi xuyen tuong*/
                    if (((Bomb) e).isAllowPass()) {
                        continue;
                    } else return false;
                }
            }
        }
        return true;
    }

    public void ifCollisionWithFlameOrEnemyOrItem() {
        int x = getXUnit();
        int y = getYUnit();
        for (Bomb b : bombList) {
            List<Flame> fl = b.getFlameList();
            for (Flame f : fl) {
                if (f.getXUnit() == x && f.getYUnit() == y) {
                    if (!canPassFlame) setStartDie(true);
                    break;
                }
            }
        }
        Entity e = BombermanGame.canvas.getEntityInCoodinate(x, y);
        if (e instanceof Enemy) {
            setStartDie(true);
        }

        if (e instanceof Item) {
            switch (((Item) e).getId()) {
                case "psi":
                    if (velocity < 3) { //velocity max = 3
                        velocity++;
                    }
                    break;
                case "pbi":
                    if (maxBom < 4) { //maxbom highest = 4
                        maxBom++;
                    }
                    break;
                case "pfi":
                    if (frameLen < 3) { //len max = 3
                        frameLen++;
                    }
                    break;
                case "bpi":
                    canPassBom = true;//TODO: trong khoang tg
                    break;
                case "fpi":
                    canPassFlame = true; //TODO: cai them pass trong 1 khoang thoi gian vao
                    break;
                case "wpi":
                    canPassBrick = true; //hiem
                    break;
                case "pli":
                    BombermanGame.lives += 1;
                    break;
            }

            e.setImg(null);

        }
    }

    public void setKillAllEnemies(boolean killAllEnemies) {
        this.killAllEnemies = killAllEnemies;
    }

    public boolean isCollideWithAPortal() {
        return collideWithAPortal;
    }


    public boolean isCanPassFlame() {
        return canPassFlame;
    }

    public void setCanPassFlame(boolean canPassFlame) {
        this.canPassFlame = canPassFlame;
    }
}