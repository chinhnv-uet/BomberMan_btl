package uet.oop.bomberman.entities.enemy;


import uet.oop.bomberman.ai.AILevel1;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {


    public Balloon(int x, int y) {
        super(x, y, Sprite.balloom_left1.getFxImage());
        ai = new AILevel1();
    }

    @Override
    public void deadAnimation() {

        this.setImg(Sprite.balloom_dead.getFxImage());
        
        //day balloon ra khoi map
        this.removeFromGame();
    }

    public void move() {
        if (isAlive) {
//            if (animate%200 == 0) {
//                direction = 1-direction;
//            }
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

            for (int i = 0; i < 4; i++) {
                int xx = tempX + AddToXToCheckCollision[i];
                int yy = tempY + AddToYToCheckCollision[i];
                if (!canMove(xx, yy)) {
                    setDirection(1-direction);
                    return ;
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
    		if (direction == 0)
    			this.setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, timeTransfer).getFxImage());
    		else if (direction == 1)
    			this.setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, timeTransfer).getFxImage());
    		else if (direction == 2)
    			this.setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_right1, Sprite.balloom_left3, animate, timeTransfer).getFxImage());
    		else if (direction == 3)
    			this.setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_left2, Sprite.balloom_right2, animate, timeTransfer).getFxImage());
    		
    	}
    }

}
