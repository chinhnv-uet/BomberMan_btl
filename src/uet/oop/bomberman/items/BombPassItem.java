package uet.oop.bomberman.items;

import uet.oop.bomberman.graphics.Sprite;

public class BombPassItem extends Item {

	public BombPassItem(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.powerup_bombpass.getFxImage());
	}

}
