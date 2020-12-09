package uet.oop.bomberman.entities.stillsobject;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {

    private Sprite[] spriteList = {Sprite.grass1, Sprite.grass2, Sprite.grass3, Sprite.grass4, Sprite.grass5, Sprite.grass6, Sprite.grass7};

    public Grass(int x, int y, int level) {
        super(x, y, null);
        this.setImg(spriteList[level].getFxImage());
    }

    @Override
    public void update() {

    }
}
