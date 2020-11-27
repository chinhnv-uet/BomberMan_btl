package uet.oop.bomberman.items;

import uet.oop.bomberman.graphics.Sprite;

public class BrickPassItem extends Item {

	public BrickPassItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.powerup_wallpass.getFxImage());
	}

}
