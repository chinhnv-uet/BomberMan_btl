package uet.oop.bomberman.ai;

public abstract class AI {
	public abstract int setDirect();
	public int setDirection2(int n) {
		return 1-n;
	}
}
