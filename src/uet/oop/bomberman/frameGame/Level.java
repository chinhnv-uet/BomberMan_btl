package uet.oop.bomberman.frameGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.*;
import uet.oop.bomberman.entities.stillsobject.*;
import uet.oop.bomberman.items.*;
import uet.oop.bomberman.items.Item;

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
                        case '3':
                        	object = new Doll(j, i);
                        	enemyList.add((Doll) object);
                        	break;
                        case '4':
                        	object = new Minvo(j, i);
                        	enemyList.add((Minvo) object);
                        	break;
                        case '5':
                        	object = new Kondoria(j, i);
                        	enemyList.add((Kondoria) object);
                        	break;
                        case 'b':
                            object = new Brick(j, i);
                            Item pbi = new PlusBombItem(j, i);

                            ((Brick) object).setBrickHasItem(true, pbi);
                            collidableEntities.add((Brick) object);

                            pbi.setId("pbi");
                            break;
                        case 'f':
                            object = new Brick(j, i);
                            Item pfi = new PlusFlameItem(j, i);

                            ((Brick) object).setBrickHasItem(true, pfi);
                            collidableEntities.add((Brick) object);

                            pfi.setId("pfi");
                            break;
                        case 's':
                            object = new Brick(j, i);
                            Item psi = new PlusSpeedItem(j, i);

                            ((Brick) object).setBrickHasItem(true, psi);
                            collidableEntities.add((Brick) object);

                            psi.setId("psi");
                            break;
                        case 'B':
                            object = new Brick(j, i);
                            Item bpi = new BombPassItem(j, i);

                            ((Brick) object).setBrickHasItem(true, bpi);
                            collidableEntities.add((Brick) object);

                            bpi.setId("bpi");
                            break;
                        case 'F':
                            object = new Brick(j, i);
                            Item fpi = new FlamePassItem(j, i);

                            ((Brick) object).setBrickHasItem(true, fpi);
                            collidableEntities.add((Brick) object);

                            fpi.setId("fpi");
                            break;
                        case 'W':
                            object = new Brick(j, i);
                            Item wpi = new BrickPassItem(j, i);

                            ((Brick) object).setBrickHasItem(true, wpi);
                            collidableEntities.add((Brick) object);

                            wpi.setId("wpi");
                            break;
                        case 'l':
                        	object = new Brick(j, i);
                            Item pli = new PlusLiveItem(j, i);

                            ((Brick) object).setBrickHasItem(true, pli);
                            collidableEntities.add((Brick) object);

                            pli.setId("pli");
                            break;
                    }
                }
            }
            for (Entity e : enemyList) {
                if (e instanceof Enemy) {
                    ((Enemy) e).setBomber(bomber);
                    if (e instanceof Oneal) {
                        ((Oneal) e).updateBomberForAI();
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
