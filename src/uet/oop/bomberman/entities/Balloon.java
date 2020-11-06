package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {
	
	private List<Entity> obstacles = new ArrayList<>();
	
	public Balloon(int x, int y, Image img) {
		super(x, y, img);
	}

	@Override
	public void deadCollision() {
		
		this.setImg(Sprite.balloom_dead.getFxImage());
		//TO-DO: time-sleep 1s --> tao hieu ung bien mat
	
		//day balloon ra khoi map
		this.removeFromGame();
	}
	
	
	public boolean canMove(int x, int y) {
		
		for (Entity e : obstacles) {

			if (e.getX() == x && e.getY() == y) {
				return false;
			}
			
		}
		
		return true;
	}
	
	
	public void move() {
		Random generator = new Random();
		while (true) {
			int direct = generator.nextInt(4);
			// 1 left, 2 right, 3 up, 4 down
			int tempX = x, tempY = y;
			switch(direct) {
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
				
				this.setImg(Sprite.balloom_left1.getFxImage());
				this.setImg(Sprite.balloom_right1.getFxImage());
			}
		}
		
	}

	public List<Entity> getObstacles() {
		return obstacles;
	}

	public void setObstacles(List<Entity> obstacles) {
		this.obstacles = obstacles;
	}

	
	

}
