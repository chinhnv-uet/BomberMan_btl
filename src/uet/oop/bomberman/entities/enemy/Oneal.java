package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ai.AILevel2;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.items.PlusBombItem;
import uet.oop.bomberman.items.PlusFlameItem;
import uet.oop.bomberman.items.PlusSpeedItem;


public class Oneal extends Enemy {
	
    public Oneal(int x, int y) {
        super(x, y, Sprite.oneal_left1.getFxImage());
        ai = new AILevel2(this.getBomber(), this);
        velocity = 2;
    }
	@Override
    public void deadAnimation() {
    	if (timeDead-- > 0) {
            this.setImg(Sprite.movingSprite(Sprite.oneal_dead, Sprite.oneal_dead, animate, timeTransfer).getFxImage());
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
                if (!canMove(xx, yy) || ai.wantToChangeDirect) {
                	setDirection(ai.setDirect());
                	ai.setWantToChangeDirect(false);
                    return ;
                } 
            }
            this.setX(tempX);
            this.setY(tempY);
            
          //enemy gap bat ky item auto se tang speed
            Entity e = BombermanGame.canvas.getEntityInCoodinate(this.getXUnit(), this.getYUnit());
            if (e instanceof PlusFlameItem || e instanceof PlusBombItem || e instanceof PlusSpeedItem) {
            	setVelocity(velocity + 1);
            	e.setImg(null);
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
    		
    		if (animate % (3*Sprite.SCALED_SIZE) == 0 && !ai.wantToChangeDirect) ai.setWantToChangeDirect(true);
    		else ai.setWantToChangeDirect(false);
    		
    		if (direction == 0)
    			this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, timeTransfer).getFxImage());
    		if (direction == 1)
    			this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, timeTransfer).getFxImage());
    		if (direction == 2)
    			this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_right1, Sprite.oneal_left3, animate, timeTransfer).getFxImage());
    		if (direction == 3)
    			this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_left2, Sprite.oneal_right2, animate, timeTransfer).getFxImage());
    		
    	}
    }

}
