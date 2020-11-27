package uet.oop.bomberman.items;
import uet.oop.bomberman.graphics.Sprite;

public class FlamePassItem extends Item {

	public FlamePassItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.powerup_flamepass.getFxImage());
	}

}
