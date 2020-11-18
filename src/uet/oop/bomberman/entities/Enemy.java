package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import uet.oop.bomberman.ai.AI;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.stillsobject.*;
import uet.oop.bomberman.frameGame.Keyboard;

public abstract class Enemy extends AnimatedEntity {

	protected boolean isAlive = true;
	protected int direction = 0;
    //0 left, 1 right, 2 up, 3 down
	
	protected AI ai;
    protected List<Wall> wallList = new ArrayList<>();
    protected List<Brick> brickList = new ArrayList<>();
    protected Bomber bomber = new Bomber(0, 0, new Keyboard());
    protected final int timeTransfer = 26;
    
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    
    public boolean canMove(int x, int y) {
        for (Wall e : wallList) {
            if (e.getX() == x && e.getY() == y) {
                return false;
            }
        }
        for (Brick e : brickList) {
            if (e.getX() == x && e.getY() == y) {
                return false;
            }
        }
        return true;
    }
    
    public abstract void deadAnimation();
    

	public List<Wall> getWallList() {
		return wallList;
	}

	public void setWallList(List<Wall> wallList) {
		this.wallList = wallList;
	}

	public List<Brick> getBrickList() {
		return	brickList;
	}

	public void setBrickList(List<Brick> brickList) {
		this.brickList = brickList;
	}

	public void setBomber(Bomber bomber) {
		this.bomber = bomber;
	}


	
	public int getDirection() {
		return direction;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}
    
    

}
