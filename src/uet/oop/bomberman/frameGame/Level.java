package uet.oop.bomberman.frameGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.entities.stillsobject.*;

public class Level {
	private int level, w, h;
	private List<Entity> entities;
	private List<Grass> grassList;

	private List<Wall> wallList;

	private List<Brick> brickList;

	private List<Enemy> enemyList;

	private Bomber bomber;

	public void createMapLevel(String path) {
		entities = new ArrayList<>();
		grassList = new ArrayList<>();
		wallList = new ArrayList<>();
		brickList = new ArrayList<>();
		enemyList = new ArrayList<>();
		try {
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
					object = new Grass(j, i);
					entities.add(object);
					grassList.add((Grass) object);

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
						entities.add(object);
						brickList.add((Brick) object2);
						break;
					case 'p':
						bomber = new Bomber(j, i, new Keyboard());
						break;
					case '1':
						object = new Balloon(j, i);
						entities.add(object);
						enemyList.add((Balloon) object);
						break;
					case '2':
						object = new Oneal(j, i);
						entities.add(object);
						enemyList.add((Oneal) object);
						break;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

//    public void setGrassList(List<Grass> grassList) {
//        this.grassList = grassList;
//    }

	public List<Wall> getWallList() {
		return wallList;
	}

//    public void setWallList(List<Wall> wallList) {
//        this.wallList = wallList;
//    }

	public List<Brick> getBrickList() {
		return brickList;
	}

//    public void setBrickList(List<Brick> brickList) {
//        this.brickList = brickList;
//    }

	public Bomber getBomber() {
		return bomber;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

//	public void setEnemyList(List<Enemy> enemyList) {
//		this.enemyList = enemyList;
//	}

}
