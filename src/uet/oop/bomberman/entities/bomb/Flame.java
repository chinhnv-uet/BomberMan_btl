package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends AnimatedEntity {
    private int direct; // 0 UP, 1 DOWN, 2 LEFT, 3 RIGHT, 4 CENTER
    private boolean last = false;

    public Flame(int xUnit, int yUnit, int direct, boolean last) {
        super(xUnit, yUnit, null);
        this.direct = direct;
        this.last = last;
    }

    @Override
    public void update() {
        animate();
        switch (direct) {
            case 0:
                if (last) {
                    setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, animate, timeTransfer).getFxImage());
                } else {
                    setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, timeTransfer).getFxImage());
                }
                break;
            case 1:
                if (last) {
                    setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, timeTransfer).getFxImage());
                } else {
                    setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, animate, timeTransfer).getFxImage());
                }
                break;
            case 2:
                if (last) {
                    setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, animate, timeTransfer).getFxImage());
                } else {
                    setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, timeTransfer).getFxImage());
                }
                break;
            case 3:
                if (last) {
                    setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, animate, timeTransfer).getFxImage());
                } else {
                    setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animate, timeTransfer).getFxImage());
                }
                break;
            case 4:
                setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, timeTransfer).getFxImage());
                break;
        }
    }
}
