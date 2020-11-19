package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private int direct; // 0 UP, 1 DOWN, 2 LEFT, 3 RIGHT, 4 CENTER
    private boolean last = false;

    public Flame(int xUnit, int yUnit, int direct, boolean last) {
        super(xUnit, yUnit, null);////////TODO; sua sau
        switch (direct) {
            case 0:
                if (last) {
                    setImg(Sprite.explosion_vertical_top_last1.getFxImage());
                } else {
                    setImg(Sprite.explosion_vertical1.getFxImage());
                }
                break;
            case 1:
                if (last) {
                    setImg(Sprite.explosion_vertical_down_last1.getFxImage());
                } else {
                    setImg(Sprite.explosion_vertical1.getFxImage());
                }
                break;
            case 2:
                if (last) {
                    setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
                } else {
                    setImg(Sprite.explosion_horizontal1.getFxImage());
                }
                break;
            case 3:
                if (last) {
                    setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
                } else {
                    setImg(Sprite.explosion_horizontal1.getFxImage());
                }
                break;
            case 4:
                setImg(Sprite.bomb_exploded1.getFxImage());
                break;
        }
    }

    @Override
    public void update() {

    }
}
