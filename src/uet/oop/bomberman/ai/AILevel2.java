package uet.oop.bomberman.ai;



import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.character.Bomber;

public class AILevel2 extends AI {
	private Bomber bomberman;
	private Enemy e;
	
	public AILevel2(Bomber b, Enemy e) {
		this.bomberman = b;
		this.e = e;
	}
    public boolean recognizeBomberman() {
        //tra ve true, neu bomber va enemy cach nhau 3 don vi
    	//TODO thuat toan ko hieu qua 
        double distance = Math.pow(e.getX() - bomberman.getX(), 2) +
        					Math.pow(e.getY() - bomberman.getY(), 2);

        if (Math.sqrt(distance) < Sprite.SCALED_SIZE * 3) {

        	setWantToChangeDirect(true);
        	return true;
        }
        return false;
    }
	@Override
	public int setDirect() {
		if (!recognizeBomberman()) {

            return generate.nextInt(4);

        } else {
        	//TODO, sua lai thuat toan...du da phat hien ra Bomber, nhung quai vat khong duoi theo?
        	//System.out.println("yes");
        	
        	if (e.isMoving()) {
        		
        		int randomCheckDir = generate.nextInt(2); //check Row or Col first?
        		if (randomCheckDir == 0) {		//checkRow first
        			int horizon = checkRow();
	                if (horizon == -1) return checkCol(); 
	                else return horizon;
        		} else {
        			int vertical = checkCol();
        			if (vertical == -1) return checkRow();
        			else return vertical;
        		}
	            	
        	} else {
        		return generate.nextInt(4);
        	}
        	
        }
	}
	
	private int checkRow() {
		if (e.getXUnit() > bomberman.getXUnit()) return 2;
		else if (e.getXUnit() < bomberman.getXUnit()) return 3;
		return -1; //equal
	}
	private int checkCol() {
		if (e.getYUnit() > bomberman.getYUnit()) return 0;
		else if (e.getYUnit() > bomberman.getYUnit()) return 1;
		return -1; //equal
	}

}
