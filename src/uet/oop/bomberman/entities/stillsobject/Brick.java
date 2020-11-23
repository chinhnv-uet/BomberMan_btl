package uet.oop.bomberman.entities.stillsobject;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.items.PlusBombItem;

public class Brick extends AnimatedEntity {

    private boolean isDestroyed = false;
    private int timeAnimate = 30;//time show animation brick explosion
    private int timeTransfer = 40; // time transfer between img
    
    private boolean brickHasItem = false;
    private Item item;
    
    private boolean brickHasPortal = false;

    public Brick(int x, int y) {
        super(x, y, Sprite.brick.getFxImage());
    }

    public boolean isBrickHasItem() {
        return brickHasItem;
    }

    public void setBrickHasItem(boolean brickHasItem, Item item) {
        this.brickHasItem = brickHasItem;
        this.item = item;
    }

    public boolean isBrickHasPortal() {
        return brickHasPortal;
    }

    public void setBrickHasPortal(boolean brickHasPortal) {
        this.brickHasPortal = brickHasPortal;
    }

    public void update() {
        if (isDestroyed) {
            animate();
            if (timeAnimate-- > 0) {
                setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animate, timeTransfer).getFxImage());
            } else {
                this.setImg(null);
            }
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

	public Item getItem() {
		return item;
	}

}
