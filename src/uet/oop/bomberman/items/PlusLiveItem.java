package uet.oop.bomberman.items;

import uet.oop.bomberman.graphics.Sprite;

public class PlusLiveItem extends Item {

	public PlusLiveItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.powerup_detonator.getFxImage());
	}

}
