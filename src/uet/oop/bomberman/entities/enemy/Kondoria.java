package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.ai.AILevel3;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
	//dac diem: giong Oneal, nhung co the di xuyen Brick
	protected int max_Steps = Sprite.SCALED_SIZE / 2; // 2 is velocity
	protected int steps_now = max_Steps;
	public Kondoria(int x, int y) {
		super(x, y, Sprite.kondoria_left1.getFxImage());
		velocity = 2;
		ai = new AILevel3(bomber,this);
	}

	@Override
    public void deadAnimation() {
        if (timeDead-- > 0) {
            this.setImg(Sprite.movingSprite(Sprite.kondoria_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, timeTransfer).getFxImage());
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
                this.setImg(Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, timeTransfer).getFxImage());
            } else if (direction == 1) {
                this.setImg(Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, timeTransfer).getFxImage());
            } else if (direction == 2) {
                this.setImg(Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_right1, Sprite.kondoria_left3, animate, timeTransfer).getFxImage());
            } else if (direction == 3) {
                this.setImg(Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_left2, Sprite.kondoria_right2, animate, timeTransfer).getFxImage());
            }

        }
    }

    public void updateBomberForAI() {
        ((AILevel3) ai).updateBomber(bomber);
    }

}
