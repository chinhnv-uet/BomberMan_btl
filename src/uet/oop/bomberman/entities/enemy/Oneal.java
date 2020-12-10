package uet.oop.bomberman.entities.enemy;


import uet.oop.bomberman.ai.AILevel3;
import uet.oop.bomberman.graphics.Sprite;


public class Oneal extends Enemy {
	protected int max_Steps = Sprite.SCALED_SIZE / 2; // 2 is velocity
	protected int steps_now = max_Steps;
	
    public Oneal(int x, int y) {
        super(x, y, Sprite.oneal_left1.getFxImage());
        ai = new AILevel3(bomber, this);
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
            CollideWithBomb();
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
        ((AILevel3) ai).updateBomber(bomber);
    }
    
}
