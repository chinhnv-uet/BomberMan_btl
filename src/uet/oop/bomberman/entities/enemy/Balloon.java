package uet.oop.bomberman.entities.enemy;


import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ai.AILevel1;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.items.PlusBombItem;
import uet.oop.bomberman.items.PlusFlameItem;
import uet.oop.bomberman.items.PlusSpeedItem;

public class Balloon extends Enemy {

    
    public Balloon(int x, int y) {
        super(x, y, Sprite.balloom_left1.getFxImage());
        ai = new AILevel1();
        setDirection(ai.setDirect());
        velocity = 1;
    }

    @Override
    public void deadAnimation() {
        if (timeDead-- > 0) {
            this.setImg(Sprite.movingSprite(Sprite.balloom_dead, Sprite.mob_dead1, Sprite.mob_dead2, animate, timeTransfer).getFxImage());
        }
        else {
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
                    return ;
                }
            }
            this.setX(tempX);
            this.setY(tempY);
           
            //enemy gap bat ky item auto se tang speed
            Entity e = BombermanGame.canvas.getEntityInCoodinate(tempX/Sprite.SCALED_SIZE, tempY/Sprite.SCALED_SIZE);
            if (e instanceof PlusFlameItem || e instanceof PlusBombItem || e instanceof PlusSpeedItem) {
            	setVelocity(velocity + 1);
            	e.setImg(null);
            }
        }

    }

    @Override
    public void update() {
        animate();
    	if (!isAlive) {
    		deadAnimation();
    	} else {
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
