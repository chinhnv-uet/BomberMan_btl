package uet.oop.bomberman.ai;

import java.util.Random;

public class AILevel1 extends AI {

	@Override
	public int setDirect() {
		return new Random().nextInt(4);
	}
	public int setDirection2(int n) {
		return 3-n;
	}
}
