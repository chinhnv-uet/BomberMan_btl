package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.javafx.geom.Rectangle;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

@SuppressWarnings("restriction")
public class Oneal extends Enemy {
	
	public Bomber bomber;
	
	private List<Entity> obstacles = new ArrayList<>();
	
	public Oneal(int x, int y, Image img) {
		super(x, y, img);
	}
	
	@Override
	public void deadCollision() {
		this.setImg(Sprite.oneal_dead.getFxImage());
		//TO-DO time-sleep 1s
		
		
		this.removeFromGame();
	}
	

	
	public boolean recognizeBomberman() {
		//neu tim thay vi tri cua Bomberman trong pham vi 3 don vi thi di chuyen gan toi do
		
		Rectangle rec = new Rectangle(x - 3, y - 3, 6, 6);
		Rectangle postitionOfBomberman = new Rectangle(x, y, 1, 1);
		if (rec.contains(postitionOfBomberman)) return true;
		return false;
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
		int direct = 1;
		// 1 left, 2 right, 3 up, 4 down
		while (true) {
			if (!recognizeBomberman()) {
				
				direct = generator.nextInt(4);
				
			} else {
				
				if (x > bomber.getX()) {
					direct = 1;
				} else if (x < bomber.getX()) {
					direct = 2;
				} else if (y > bomber.getY()) {
					direct = 3;
				} else if (y < bomber.getY()) {
					direct = 4;
				}
				
			}
			
			int tempX = x, tempY = y;
			switch(direct) {
				case 1:
					tempX = x - 1;
					
					this.setImg(Sprite.oneal_left1.getFxImage());
					break;
				case 2: 
					tempX = x + 1;
					
					this.setImg(Sprite.oneal_right1.getFxImage());
					break;
				case 3:
					tempY = y - 1;

					this.setImg(Sprite.oneal_left1.getFxImage());
					this.setImg(Sprite.oneal_right1.getFxImage());
					break;
				case 4: 
					tempY = y + 1;

					this.setImg(Sprite.oneal_left1.getFxImage());
					this.setImg(Sprite.oneal_right1.getFxImage());
					break;
			}
			
			if (canMove(tempX, tempY)) {
				this.setX(tempX);
				this.setY(tempY);
			}
		}
		
	}

	public Bomber getBomber() {
		return bomber;
	}

	public void setBomber(Bomber bomber) {
		this.bomber = bomber;
	}
	
	

}
