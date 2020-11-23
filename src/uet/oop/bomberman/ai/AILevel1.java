package uet.oop.bomberman.ai;


public class AILevel1 extends AI {

	@Override
	public int setDirect() {
		return generate.nextInt(4);
	}
	public int setDirection2(int n) {
		return 3-n;
	}
}
