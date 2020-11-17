package uet.oop.bomberman.entities.stillsobject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Item extends Entity {
	
	private String id;
	
	public Item(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}


	@Override
	public void update() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

}