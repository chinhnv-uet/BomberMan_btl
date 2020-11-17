package uet.oop.bomberman.frameGame;

import java.io.*;
import java.util.List;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.stillsobject.*;

public class Level {
	private int level, w, h;
	private List<Entity> entities;
	private List<Grass> grassList;

	private List<Wall> wallList;

	private List<Brick> brickList;

	public void createMapLevel(String path) throws IOException {
		FileReader in = new FileReader(path);
		BufferedReader br = new BufferedReader(in);

		String infoOfMap = "";
		infoOfMap = br.readLine();
		level = Integer.parseInt(infoOfMap.substring(0, 1));
		h = Integer.parseInt(infoOfMap.substring(2, 4));
		w = Integer.parseInt(infoOfMap.substring(5));

		for (int i = 0; i < h; i++) {
			String temp = br.readLine();
			Entity object;
			for (int j = 0; j < temp.length(); j++) {
				switch (temp.charAt(j)) {
				case '#':

					object = new Wall(j, i);
					entities.add(object);
					wallList.add((Wall) object);
					break;
				case '*':

					object = new Brick(j, i);
					entities.add(object);
					brickList.add((Brick) object);
					break;
				case 'x':
					object = new Portal(j, i);
					entities.add(object);

					// insert a brick above
					Entity object2 = new Brick(j, i);
					entities.add(object2);
					brickList.add((Brick) object);
					break;
				case ' ':
					object = new Grass(j, i);
					entities.add(object);
					grassList.add((Grass) object);
					break;
				default:
					break;
				}
			}
		}
		br.close();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<Grass> getGrassList() {
		return grassList;
	}

	public void setGrassList(List<Grass> grassList) {
		this.grassList = grassList;
	}

	public List<Wall> getWallList() {
		return wallList;
	}

	public void setWallList(List<Wall> wallList) {
		this.wallList = wallList;
	}

	public List<Brick> getBrickList() {
		return brickList;
	}

	public void setBrickList(List<Brick> brickList) {
		this.brickList = brickList;
	}

}
