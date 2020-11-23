package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends Entity {
	
	public Item(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		// TODO Auto-generated constructor stub
	}

	protected String id;
	protected int timeToDisappear = 500;


	@Override
	public void update() {
		timeToDisappear--;
		if (timeToDisappear == 0) {
			this.setImg(null);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		

}