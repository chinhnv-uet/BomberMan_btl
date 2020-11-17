package uet.oop.bomberman.entities.stillsobject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {

    private boolean isExploded = false;

    private boolean brickHasItem;

    public Brick(int x, int y) {
        super(x, y, Sprite.brick.getFxImage());
    }

    public boolean isBrickHasItem() {
        return brickHasItem;
    }


    public void setBrickHasItem(boolean brickHasItem, Item item) {
        this.brickHasItem = brickHasItem;
        if (isExploded) {
            this.setImg(item.getImg());
        }
    }


    public void update() {
    }


}
