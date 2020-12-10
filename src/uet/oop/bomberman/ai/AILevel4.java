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

public class AILevel4 extends AI {
    private final int n = BombermanGame.HEIGHT - 1;
    private final int m = BombermanGame.WIDTH;
    private int[][] board = new int[n][m];

    private int targetId = -1;

    private point target = new point();
    private List<point> targetList = new ArrayList<>();

    private Bomber bomber;
    private Enemy e;

    public AILevel4(Bomber bomber, Enemy e) {
        this.bomber = bomber;
        this.e = e;
    }

    private void init(int[][] board) { // 0 la ko di dc, 1 la enemy, 2 la bomber, 3 la co the di dc
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Entity tmp = BombermanGame.canvas.getEntityInCoodinate(j, i);
                if (tmp instanceof Wall || tmp instanceof Brick || tmp instanceof Portal || tmp instanceof Bomb) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = 3;
                }
            }
        }
        board[e.getYUnit()][e.getXUnit()] = 1;
        if (board[bomber.getYUnit()][bomber.getXUnit()] == 3) {
            board[bomber.getYUnit()][bomber.getXUnit()] = 2;
        }
        List<Bomb> bombs = bomber.getBombList();
        for (Bomb b : bombs) {
            board[b.getYUnit()][b.getXUnit()] = 0;
        }
    }

    public int setDirect() {
        init(board);
        if (!findBomber()) {
            return randomDirect();
        }
        targetId = targetList.size()-2;
        if (targetId < 0) {
            return randomDirect();
        }
        target = targetList.get(targetId);
        return findTarget();
    }

    private int findTarget() {
        int x = target.x;
        int y = target.y;
        if (e.getXUnit() == x + 1) {
            return 2;
        } else if (e.getXUnit() == x - 1) {
            return 3;
        } else if (e.getYUnit() == y - 1) {
            return 1;
        } else if (e.getYUnit() == y + 1) {
            return 0;
        } else {
            return randomDirect();
        }
    }

    private boolean findBomber() { //use breadth first search (BFS)
        bfsMatrix matrix = new bfsMatrix(m*n+2);
        List<Integer> lst = matrix.findMinimunMove(board);
        if (lst == null) {
            return false;
        }
        for (int i = 0; i < lst.size(); i++) {
            int j = lst.get(i);
            targetList.add(new point((j-1)%m, (j-1)/m));
        }
        return true;
    }

    public void updateBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public void ReachedTargetAndChangeDirect() { //TODO: noise
        targetId = -1;
    }

    public int randomDirect() {
        boolean cantMove = true;
        int t = -1;
        while (cantMove) { // while util random direct can move
            t = generate.nextInt(4);
            int x = e.getXUnit();
            int y = e.getYUnit();
            switch (t) {
                case 0:
                    y -= 1;
                    break;
                case 1:
                    y += 1;
                    break;
                case 2:
                    x -= 1;
                    break;
                case 3:
                    x += 1;
                    break;
            }
            Entity e = BombermanGame.canvas.getEntityInCoodinate(x, y);
            if (e instanceof Wall || e instanceof Brick || e instanceof Portal || e instanceof Bomb) {
                continue;
            } else {
                cantMove = false;
            }
        }
        return t;
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