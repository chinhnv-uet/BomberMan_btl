package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.ai.AILevel3;
import uet.oop.bomberman.ai.AILevel4;
import uet.oop.bomberman.graphics.Sprite;


public class Oneal extends Enemy {

    public Oneal(int x, int y) {
        super(x, y, Sprite.oneal_left1.getFxImage());
        ai = new AILevel4(bomber, this);
        velocity = 2;
    }

    @Override
    public void deadAnimation() {
        if (timeDead-- > 0) {
            this.setImg(Sprite.movingSprite(Sprite.oneal_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, timeTransfer).getFxImage());
        } else {
            this.removeFromGame();
        }
    }


    public void move() {

        if (isAlive) {
            int tempX = x, tempY = y;
            switch (direction) {
                case 0:
                    tempY = y - velocity;
                    break;
                case 1:
                    tempY = y + velocity;
                    break;
                case 2:
                    tempX = x - velocity;
                    break;
                case 3:
                    tempX = x + velocity;
                    break;
            }

            for (int i = 0; i < 4; i++) {
                int xx = tempX + AddToXToCheckCollision[i];
                int yy = tempY + AddToYToCheckCollision[i];
                if (!canMove(xx, yy) || ai.wantToChangeDirect) {
                    setDirection(ai.setDirect());
                    ai.setWantToChangeDirect(false);
                    return;
                }
            }
            this.setX(tempX);
            this.setY(tempY);

        }

    }

    @Override
    public void update() {

        if (!isAlive) {
            deadAnimation();
        } else {
            animate();
            move();
            ifCollideWithItemOrFlame();

            setDirection(ai.setDirect());

//            if (animate % (3 * Sprite.SCALED_SIZE) == 0 && !ai.wantToChangeDirect) ai.setWantToChangeDirect(true);
//            else ai.setWantToChangeDirect(false);

            if (direction == 0) {
                this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, timeTransfer).getFxImage());
            } else if (direction == 1) {
                this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, timeTransfer).getFxImage());
            } else if (direction == 2) {
                this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_right1, Sprite.oneal_left3, animate, timeTransfer).getFxImage());
            } else if (direction == 3) {
                this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_left2, Sprite.oneal_right2, animate, timeTransfer).getFxImage());
            }

        }
    }

    public void updateBomberForAI() {
        ((AILevel4) ai).updateBomber(bomber);
    }
}
