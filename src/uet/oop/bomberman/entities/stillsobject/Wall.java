package uet.oop.bomberman.entities.stillsobject;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    private Sprite[] spriteList = {Sprite.wall1, Sprite.wall2, Sprite.wall3, Sprite.wall4, Sprite.wall5, Sprite.wall6, Sprite.wall7};

    public Wall(int x, int y, int level) {
        super(x, y, null);
        this.setImg(spriteList[level].getFxImage());
    }

    @Override
    public void update() {

    }
}
