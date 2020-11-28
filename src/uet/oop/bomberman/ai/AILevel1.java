package uet.oop.bomberman.ai;

import uet.oop.bomberman.entities.enemy.Enemy;

public class AILevel1 extends AI {
	private Enemy enemy;
	
	public AILevel1(Enemy e) {
		this.enemy = e;
		enemy.setDirection(2);
	}
	
    @Override
    public int setDirect() {
    	return 5 - enemy.getDirection() ;
    }
    
	public Enemy getEnemy() {
		return enemy;
	}
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
}
