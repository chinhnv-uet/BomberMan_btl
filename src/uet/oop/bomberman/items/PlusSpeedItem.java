package uet.oop.bomberman.items;

import uet.oop.bomberman.graphics.Sprite;

public class PlusSpeedItem extends Item {

	public PlusSpeedItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.powerup_speed.getFxImage());
	}

}
