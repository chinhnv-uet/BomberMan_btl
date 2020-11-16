package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class Enemy extends Entity {

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public abstract void deadAnimation();

}
