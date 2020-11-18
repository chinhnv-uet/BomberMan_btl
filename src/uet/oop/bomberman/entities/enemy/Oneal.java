package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.ai.AILevel2;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;


public class Oneal extends Enemy {
	
    public Oneal(int x, int y) {
        super(x, y, Sprite.oneal_left1.getFxImage());
        ai = new AILevel2(bomber, this);
        direction = ai.setDirect();
    }

    @Override
    public void deadAnimation() {
        this.setImg(Sprite.oneal_dead.getFxImage());
        //TODO time-sleep 1s


        this.removeFromGame();
    }

    
    public void move() {

        while (isAlive) {
            int tempX = x, tempY = y;
            switch (direction) {
                case 0:
                    tempX = x - 1;
                    break;
                case 1:
                    tempX = x + 1;
                    break;
                case 2:
                    tempY = y - 1;
                    break;
                case 3:
                    tempY = y + 1;
                    break;
            }

            if (canMove(tempX, tempY)) {
                this.setX(tempX);
                this.setY(tempY);
            }
        }

    }
    
    @Override
    public void update() {
    	
    	if (!isAlive) {
    		deadAnimation();
    	} else {
    		animate();
    		move();
    		if (direction == 0)
    			this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, timeTransfer).getFxImage());
    		if (direction == 1)
    			this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, timeTransfer).getFxImage());
    		if (direction == 2)
    			this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_right1, Sprite.oneal_left3, animate, timeTransfer).getFxImage());
    		if (direction == 0)
    			this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_left2, Sprite.oneal_right2, animate, timeTransfer).getFxImage());
    		
    	}
    }

}
