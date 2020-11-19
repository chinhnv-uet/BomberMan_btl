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
    private List<Entity> collidableEntities;
    private List<Grass> grassList;

    private Bomber bomber;

    public void createMapLevel(String path) {
        collidableEntities = new ArrayList<>();
        grassList = new ArrayList<>();
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
                            collidableEntities.add((Entity) object2);
                            break;
                        case 'p':
                            bomber = new Bomber(j, i, new Keyboard());
                            break;
                        case '1':
                            object = new Balloon(j, i);
                            collidableEntities.add((Enemy)object);
                            break;
                        case '2':
                            object = new Oneal(j, i);
                            collidableEntities.add((Enemy)object);
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

//    public int getW() {
//        return w;
//    }
//
//    public void setW(int w) {
//        this.w = w;
//    }
//
//    public int getH() {
//        return h;
//    }
//
//    public void setH(int h) {
//        this.h = h;
//    }

    public List<Entity> getCollidableEntities() {
        return collidableEntities;
    }

    public List<Grass> getGrassList() {
        return grassList;
    }

    public Bomber getBomber() {
        return bomber;
    }

}
