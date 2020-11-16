package uet.oop.bomberman.frameGame;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.stillsobject.Grass;
import uet.oop.bomberman.entities.stillsobject.Wall;
import uet.oop.bomberman.entities.character.Bomber;


import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Wall> wallList;
    private List<Grass> grassList;
    //list enemy
    //list brick
    //list item
    //portal
    Entity bomberman;
    //Level

    private final int WIDTH = 20;
    private final int HEIGHT = 15;

    public Game() {
        grassList = new ArrayList<>();
        wallList = new ArrayList<>();
    }

    public void createMap() { //TODO: chuyen thanh doc file txt
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    wallList.add(new Wall(i, j));
                } else if (j % 2 == 0 && i != 1 && i != WIDTH - 2) {
                    wallList.add(new Wall(i, j));
                } else {
                    grassList.add(new Grass(i, j));
                }
            }
        }

        bomberman = new Bomber(1, 1, new Keyboard());
    }

    public void update() {
        //enemy update
        //brick update
        bomberman.update();
    }

    public void render(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grassList.forEach(g -> g.render(gc));
        wallList.forEach(g -> g.render(gc));
        bomberman.render(gc);
    }

    public Entity getEntityOnCoodinate(int x, int y) {
        for (Wall e : wallList) {
            if (e.getXUnit() == x && e.getYUnit() == y) {
                return e;
            }
        }
        //TODO: for brick, enemy
        return null;
    }
}
