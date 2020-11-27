package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.ai.AILevel1;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy {
	//Dac diem: giong Balloon, nhung di chuyen nhanh hon 
	public Doll(int x, int y) {
		super(x, y, Sprite.doll_left1.getFxImage());
		ai = new AILevel1(this);
		velocity = 3;
	}

	@Override
	public void deadAnimation() {
		if (timeDead-- > 0) {
            this.setImg(Sprite.movingSprite(Sprite.doll_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, timeTransfer).getFxImage());
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
	                if (!canMove(xx, yy)) {
	                    setDirection(ai.setDirect());
	                    return;
	                }
	            }
	            this.setX(tempX);
	            this.setY(tempY);

	        }

	}
	@Override
	public void update() {
		animate();
        if (!isAlive) {
            deadAnimation();
        } else {
            move();
            ifCollideWithItemOrFlame();
            timeTransfer = 15;
            if (direction == 0)
                this.setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, timeTransfer ).getFxImage());
            else if (direction == 1)
                this.setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, timeTransfer ).getFxImage());
            else if (direction == 2)
                this.setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_right1, Sprite.doll_left3, animate, timeTransfer).getFxImage());
            else if (direction == 3)
                this.setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_left2, Sprite.doll_right2, animate, timeTransfer).getFxImage());

        }

	}

}
