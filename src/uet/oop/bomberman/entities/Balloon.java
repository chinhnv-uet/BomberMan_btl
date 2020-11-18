package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javafx.scene.image.Image;
import uet.oop.bomberman.ai.AILevel1;
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
        while (isAlive) {
            direction = ai.setDirect();
            int tempX = x, tempY = y;
            switch (direction) {
                case 1:
                    tempX = x - 1;
                    break;
                case 2:
                    tempX = x + 1;
                    break;
                case 3:
                    tempY = y - 1;
                    break;
                case 4:
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
    			this.setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, timeTransfer).getFxImage());
    		if (direction == 1)
    			this.setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, timeTransfer).getFxImage());
    		if (direction == 2)
    			this.setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_right1, Sprite.balloom_left3, animate, timeTransfer).getFxImage());
    		if (direction == 0)
    			this.setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_left2, Sprite.balloom_right2, animate, timeTransfer).getFxImage());
    		
    	}
    }

}
