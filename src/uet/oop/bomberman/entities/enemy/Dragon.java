package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.ai.AILevel4;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;


public class Dragon extends Enemy {
    protected int max_Steps = Sprite.SCALED_SIZE / 2; // 2 is velocity
    protected int steps_now = max_Steps;

    public Dragon(int x, int y) {
        super(x, y, Sprite.boss_down1.getFxImage());
        ai = new AILevel4(bomber, this);
        velocity = 2;
        direction = -1;
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

        if (steps_now == max_Steps) {
            setDirection(ai.setDirect());
            steps_now = 0;
        }

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

        this.setX(tempX);
        this.setY(tempY);
        steps_now++;

    }

    @Override
    public void update() {

        if (!isAlive) {
            deadAnimation();
        } else {
            animate();
            move();
            ifCollideWithItemOrFlame();
            updateImg();
            CollideWithBomb();
        }
    }

    private void updateImg() {
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

    public void updateBomberForAI() {
        ((AILevel4) ai).updateBomber(bomber);
    }

    public void CollideWithBomb() {
        List<Bomb> bombs = bomber.getBombList();
        for (Bomb b : bombs) {
            if (b.getXUnit() == this.getXUnit() && b.getYUnit() == this.getYUnit()) {
                setVelocity(0);
                break;
            }
        }
    }
}