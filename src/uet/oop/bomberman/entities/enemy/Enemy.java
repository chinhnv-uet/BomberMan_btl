package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ai.AI;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.stillsobject.*;
import uet.oop.bomberman.frameGame.Keyboard;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.Item;

import java.util.List;


public abstract class Enemy extends AnimatedEntity {

    protected int velocity = 0;

    protected boolean isAlive = true;
    protected int direction = 0;
    //0 up, 1 down, 2 left, 3 right

    protected AI ai;

    protected Bomber bomber = new Bomber(0, 0, new Keyboard());
    protected int timeDead = 20;
    public static final int[] AddToXToCheckCollision = {2, Sprite.SCALED_SIZE - 2, Sprite.SCALED_SIZE - 2, 2};
    public static final int[] AddToYToCheckCollision = {2, 2, Sprite.SCALED_SIZE - 2, Sprite.SCALED_SIZE - 2};

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }


    public boolean canMove(int x, int y) {
        int xUnit = (int) x / Sprite.SCALED_SIZE;
        int yUnit = (int) y / Sprite.SCALED_SIZE;
        Entity e = BombermanGame.canvas.getEntityInCoodinate(xUnit, yUnit);
        if (this instanceof Kondoria && e instanceof Brick) {
        	return true;
        }
        if (e instanceof Wall || e instanceof Brick || e instanceof Bomb || e instanceof Portal) {
            return false;
        }
        
        return true;
    }

    private int tempX = 0, tempY = 0;

    public boolean isMoving() {
        if (isAlive) {
            if (tempX != this.getXUnit() && tempY != this.getYUnit()) {
                tempX = this.getXUnit();
                tempY = this.getYUnit();
                return true;
            } else return false;
        }
        return false;
    }

    public abstract void deadAnimation();

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }


    public Bomber getBomber() {
        return bomber;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void ifCollideWithItemOrFlame() {
        int x = getXUnit();
        int y = getYUnit();
        //enemy gap bat ky item auto se tang speed
        Entity e = BombermanGame.canvas.getEntityInCoodinate(x, y);
        if (e instanceof Item) {
            if (!(this instanceof Dragon)) {
                setVelocity(velocity + 1);
            }
            e.setImg(null);
        }
        List<Bomb> bombList = bomber.getBombList();
        for (Bomb b : bombList) {
            List<Flame> fl = b.getFlameList();
            for (Flame f : fl) {
                if (f.getXUnit() == x && f.getYUnit() == y) {
                    setAlive(false);
                    break;
                }
            }
        }
    }

    public int getAnimate() {
        return animate;
    }


	public int getVelocity() {
		return velocity;
	}
    
}
