package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.stillsobject.Brick;
import uet.oop.bomberman.entities.stillsobject.Portal;
import uet.oop.bomberman.entities.stillsobject.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntity {
    private int timeBeforeExplore = 150;
    private int timeFlame = 15;
    private int timeTransfer = 40;
    private boolean explored;
    private int flameLen = 1;
    private List<Flame> flameList = new ArrayList<>();

    public Bomb(int x, int y, int flameLen) {
        super(x, y, Sprite.bomb.getFxImage());
        this.flameLen = flameLen;
        explored = false;
    }

    @Override
    public void update() {
        animate();
        if (explored == false) {
            if (timeBeforeExplore-- > 0) {
                setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, timeTransfer).getFxImage());
            } else {
                explored = true;
                explosion();
            }
        } else {
            if (timeFlame-- == 0) {
                setImg(null);
            } else {
//                frameRender();
            }
        }
    }

    private void explosion() {//init FlameList
        int x = getXUnit();
        int y = getYUnit();

        flameList.add(new Flame(x, y, 4, false));// add center

        //check left
        int il = 1;
        for (; il <= flameLen; il++) {
            int xLeft = x - il;
            Entity e = BombermanGame.canvas.getEntityInCoodinate(xLeft, y);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < il; i++) {
            if (i == il - 1) {
                flameList.add(new Flame(x - i, y, 2, true));
            } else {
                flameList.add(new Flame(x - i, y, 2, false));
            }
        }

        //check right
        int ir = 1;
        for (; ir <= flameLen; ir++) {
            int xRight = x + ir;
            Entity e = BombermanGame.canvas.getEntityInCoodinate(xRight, y);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < ir; i++) {
            if (i == ir - 1) {
                flameList.add(new Flame(x + i, y, 3, true));
            } else {
                flameList.add(new Flame(x + i, y, 3, false));
            }
        }

        //check up
        int iu = 1;
        for (; iu <= flameLen; iu++) {
            int yUp = y - iu;
            Entity e = BombermanGame.canvas.getEntityInCoodinate(x, yUp);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < iu; i++) {
            if (i == iu - 1) {
                flameList.add(new Flame(x, y - i, 0, true));
            } else {
                flameList.add(new Flame(x, y - i, 0, false));
            }
        }

        //check down
        int id = 1;
        for (; id <= flameLen; id++) {
            int yDown = y + id;
            Entity e = BombermanGame.canvas.getEntityInCoodinate(x, yDown);
            if (!canPassThrough(e)) {
                break;
            }
        }
        for (int i = 1; i < id; i++) {
            if (i == id - 1) {
                flameList.add(new Flame(x, y + i, 1, true));
            } else {
                flameList.add(new Flame(x, y + i, 1, false));
            }
        }
    }

    public void frameRender(GraphicsContext gc) {
        flameList.forEach(g -> g.render(gc));
    }

    public boolean isExplored() {
        return explored;
    }

    public boolean canPassThrough(Entity e) { // return false if ko truyen qua dc e, true if truyen qua dc
        if (e instanceof Brick) {
            ((Brick) e).setDestroyed(true);
            return false;
        }
        if (e instanceof Wall || e instanceof Portal) {
            return false;
        }
        return true;
    }
}