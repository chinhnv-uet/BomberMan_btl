package uet.oop.bomberman.ai;



import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.character.Bomber;

import java.awt.*;

@SuppressWarnings("restriction")
public class AILevel2 extends AI {
	private Bomber bomberman;
	private Enemy e;

	public AILevel2(Bomber b, Enemy e) {
		this.bomberman = b;
		this.e = e;
	}
    public boolean recognizeBomberman() {
        //neu tim thay vi tri cua Bomberman trong pham vi 3 don vi thi di chuyen gan toi do

        Rectangle rec = new Rectangle(e.getXUnit() - 3, e.getYUnit() - 3, 6, 6);
        Rectangle postitionOfBomberman = new Rectangle(bomberman.getXUnit(), bomberman.getYUnit(), 1, 1);
        if (rec.contains(postitionOfBomberman)) return true;
        return false;
    }
	@Override
	public int setDirect() {
		if (!recognizeBomberman()) {

            return new AILevel1().setDirect();

        } else {
        	
            if (e.getX() > bomberman.getX()) {
                return 0;
            } else if (e.getX() < bomberman.getX()) {
            	return 1;
            } else if (e.getY() > bomberman.getY()) {
            	return 2;
            } else if (e.getY() < bomberman.getY()) {
            	return 3;
            }

        }
		return 0;
	}

}
