package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.ai.AILevel4;
import uet.oop.bomberman.graphics.Sprite;


public class Boss extends Enemy {

    public Boss(int x, int y) {
        super(x, y, Sprite.boss_down1.getFxImage());
        ai = new AILevel4(bomber, this);
        velocity = 2;
    }

    @Override
    public void deadAnimation() {
        if (timeDead-- > 0) {
            this.setImg(Sprite.movingSprite(Sprite.boss_down1, Sprite.mob_dead1, Sprite.mob_dead2, animate, timeTransfer).getFxImage());
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
                this.setImg(Sprite.movingSpriteFourImg(Sprite.boss_up1, Sprite.boss_up2, Sprite.boss_up3, Sprite.boss_up4, animate, timeTransfer).getFxImage());
            } else if (direction == 1) {
                this.setImg(Sprite.movingSpriteFourImg(Sprite.boss_down1, Sprite.boss_down2, Sprite.boss_down3, Sprite.boss_down4, animate, timeTransfer).getFxImage());
            } else if (direction == 2) {
                this.setImg(Sprite.movingSpriteFourImg(Sprite.boss_left1, Sprite.boss_left2, Sprite.boss_left3, Sprite.boss_left4, animate, timeTransfer).getFxImage());
            } else if (direction == 3) {
                this.setImg(Sprite.movingSpriteFourImg(Sprite.boss_right1, Sprite.boss_right2, Sprite.boss_right3, Sprite.boss_right4, animate, timeTransfer).getFxImage());
            }

        }
    }

    public void updateBomberForAI() {
        ((AILevel4) ai).updateBomber(bomber);
    }

    public void CollideWithBomb() {
        ((AILevel4) ai).ReachedTargetAndChangeDirect();
    }
}