package uet.oop.bomberman.ai;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.stillsobject.Brick;
import uet.oop.bomberman.entities.stillsobject.Portal;
import uet.oop.bomberman.entities.stillsobject.Wall;

import java.util.ArrayList;
import java.util.List;

public class AILevel4 extends AI { //TODO: ailevel4 k the an item de tang toc do chay dc
    private final int n = BombermanGame.HEIGHT - 1;
    private final int m = BombermanGame.WIDTH;
    private int[][] board = new int[n][m];

    //to check can reach bomber or not
    private int[][] f = new int[n][m];

    //if cant find bomber
    private int lastestDirection = 0;

    private final int[] addToX = {0, -1, 0, 1};
    private final int[] addToY = {1, 0, -1, 0};
    private int count = 0;

    private boolean reachedTarget = true;
    private point target = new point();
    private List<point> targetList = new ArrayList<>();

    private Bomber bomber;
    private Enemy e;

    public AILevel4(Bomber bomber, Enemy e) {
        this.bomber = bomber;
        this.e = e;
        target.x = e.getXUnit();
        target.y = e.getYUnit();
    }

    private void init(int[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = -1; //mac dinh la -1
            }
        }
        count = 0;
    }

    public int setDirect() {
        if (targetList.size() == 0) {
            init(board);
            findBomber(e.getXUnit(), e.getYUnit());
        }
        init(f);
        if (canReachBomber(e.getXUnit(), e.getYUnit())) {
            return truyVet();
        } else {
            if (e.getAnimate() % 40 == 0) {
                lastestDirection =  generate.nextInt(4);
            }
            return lastestDirection;
        }
    }

    private int truyVet() {
        if (!reachedTarget) {
            return findTarget();
        }
        else {
            if (targetList.size() > 0) {
                target = targetList.get(targetList.size() - 1);
                targetList.remove(targetList.size() - 1);
            } else {
                init(board);
                findBomber(e.getXUnit(), e.getYUnit());
            }
            reachedTarget = false;
        }
        return generate.nextInt(4); // neu k tim thay thi random
    }

    private int findTarget() {
        int x = target.x * 32;
        int y = target.y * 32;
        if (e.getX() > x + 1) {
            return 2;
        } else if (e.getX() < x) {
            return 3;
        } else if (e.getY() < y) {
            return 1;
        } else if (e.getY() > y + 1) {
            return 0;
        } else {
            if (targetList.size() == 0) {
                reachedTarget = true;
                return generate.nextInt(4);
            }
            else {
                target = targetList.get(targetList.size() - 1);
                targetList.remove(targetList.size() - 1);
            }
            return findTarget();
        }
    }

    private boolean findBomber(int x, int y) { //use backtracking
        board[y][x] = 0; // 0 la da xet qua
        if (x == bomber.getXUnit() && y == bomber.getYUnit()) {//tim thay bomber
            board[y][x] = ++count;
            targetList.add(new point(x, y));
            return true;
        } else {
            for (int i = 0; i < 4; i++) {
                int xNew = x + addToX[i];
                int yNew = y + addToY[i];

                if (xNew >= 0 && xNew < m && yNew >= 0 && yNew < n) {
                    if (board[yNew][xNew] == -1) {
                        Entity tmp = BombermanGame.canvas.getEntityInCoodinate(xNew, yNew);
                        if (tmp instanceof Wall || tmp instanceof Brick || tmp instanceof Portal || tmp instanceof Bomb) {
                            continue;
                        }
                        if (findBomber(xNew, yNew)) {
                            board[y][x] = ++count;
                            targetList.add(new point(x, y));
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void updateBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public void ReachedTargetAndChangeDirect() {
        targetList = new ArrayList<>();
        init(board);
        findBomber(e.getXUnit(), e.getYUnit());
        this.reachedTarget = true;
    }

    private boolean canReachBomber(int x, int y) { //use backtracking
        f[y][x] = 0; // 0 la da xet qua
        if (x == bomber.getXUnit() && y == bomber.getYUnit()) {//tim thay bomber
            return true;
        } else {
            for (int i = 0; i < 4; i++) {
                int xNew = x + addToX[i];
                int yNew = y + addToY[i];

                if (xNew >= 0 && xNew < m && yNew >= 0 && yNew < n) {
                    if (f[yNew][xNew] == -1) {
                        Entity tmp = BombermanGame.canvas.getEntityInCoodinate(xNew, yNew);
                        if (tmp instanceof Wall || tmp instanceof Brick || tmp instanceof Portal || tmp instanceof Bomb) {
                            continue;
                        }
                        if (canReachBomber(xNew, yNew)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

class point {
    public int x;
    public int y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public point() {

    }
}