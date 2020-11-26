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
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.items.PlusBombItem;
import uet.oop.bomberman.items.PlusFlameItem;

public class Level {
    private int level, w, h;

    private List<Entity> collidableEntities;
    private List<Grass> grassList;
    private List<Enemy> enemyList;
    private Bomber bomber;

    public void createMapLevel(String path) {
        collidableEntities = new ArrayList<>();
        grassList = new ArrayList<>();
        enemyList = new ArrayList<>();
        try {
            FileReader in = new FileReader(path);
            BufferedReader br = new BufferedReader(in);

            String infoOfMap;
            infoOfMap = br.readLine();
            level = Integer.parseInt(infoOfMap.substring(0, 1));
            h = Integer.parseInt(infoOfMap.substring(2, 4));
            w = Integer.parseInt(infoOfMap.substring(5));

            for (int i = 0; i < h; i++) {
                String temp = br.readLine();
                Entity object;
                for (int j = 0; j < temp.length(); j++) {
                    object = new Grass(j, i);
                    grassList.add((Grass) object);

                    switch (temp.charAt(j)) {
                        case '#':
                            object = new Wall(j, i);
                            collidableEntities.add(object);
                            break;
                        case '*':
                            object = new Brick(j, i);
                            collidableEntities.add(object);
                            break;
                        case 'x':
                            Brick object2 = new Brick(j, i);
                            object2.setBrickHasPortal(true);
                            collidableEntities.add((Brick) object2);
                            break;
                        case 'p':
                            bomber = new Bomber(j, i, new Keyboard());
                            break;
                        case '1':
                            object = new Balloon(j, i);
                            enemyList.add((Balloon) object);
                            break;
                        case '2':
                            object = new Oneal(j, i);
                            enemyList.add((Oneal) object);
                            break;
                        case 'b':
                            object = new Brick(j, i);
                            Item pbi = new PlusBombItem(j, i, Sprite.powerup_bombs.getFxImage());

                            ((Brick) object).setBrickHasItem(true, pbi);
                            collidableEntities.add((Brick) object);

                            pbi.setId("pbi");
                            break;
                        case 'f':
                            object = new Brick(j, i);
                            Item pfi = new PlusFlameItem(j, i, Sprite.powerup_flames.getFxImage());

                            ((Brick) object).setBrickHasItem(true, pfi);
                            collidableEntities.add((Brick) object);

                            pfi.setId("pfi");
                            break;
                        case 's':
                            object = new Brick(j, i);
                            Item psi = new PlusFlameItem(j, i, Sprite.powerup_speed.getFxImage());

                            ((Brick) object).setBrickHasItem(true, psi);
                            collidableEntities.add((Brick) object);

                            psi.setId("psi");
                            break;
                    }
                }
            }
            //updates in Game class
//            for (Entity e : enemyList) {
//                if (e instanceof Enemy) {
//                    ((Enemy) e).setBomber(bomber);
//                    if (e instanceof Oneal) {
//                        ((Oneal) e).updateBomberForAI();
//                    }
//                }
//            }
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


    public List<Entity> getCollidableEntities() {
        return collidableEntities;
    }

    public List<Grass> getGrassList() {
        return grassList;
    }

    public Bomber getBomber() {
        return bomber;
    }


    public List<Enemy> getEnemyList() {
        return enemyList;
    }
	public int getW() {
		return w;
	}


	public int getH() {
		return h;
	}

	
	
}
