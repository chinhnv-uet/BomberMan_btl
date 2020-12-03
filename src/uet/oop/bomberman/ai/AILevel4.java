package uet.oop.bomberman.ai;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.stillsobject.Brick;
import uet.oop.bomberman.entities.stillsobject.Portal;
import uet.oop.bomberman.entities.stillsobject.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class AILevel4 extends AI {
    private int n = BombermanGame.HEIGHT - 1;
    private int m = BombermanGame.WIDTH;
    private int[][] board = new int[n][m];

    private int[] addToX = {0, 0, -1, 1};
    private int[] addToY = {-1, 1, 0, 0};
    private int count = 0;

    private Bomber bomber;
    private Enemy e;

    public AILevel4(Bomber bomber, Enemy e) {
        this.bomber = bomber;
        this.e = e;
    }

    private void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = -10; //mac dinh la -1
            }
        }
        count = 0;
    }

    //TODO: xet truong hop tim thay trc, ko tim thay sau
    public int setDirect() {
        init();
        findBomber(e.getXUnit(), e.getYUnit()); //ko tim duong den bomber dc
        printBoard();
        return truyVet();
    }

    private int truyVet() {
        int x = e.getXUnit();
        int y = e.getYUnit();
        for (int i = 0; i < 4; i++) {
            int xx = x + addToX[i];
            int yy = y + addToY[i];
            if (xx >= 0 && xx < m && yy >= 0 && yy < n) {
                if (board[y][x] == board[yy][xx] + 1) {
                    if (!tronVenTrong1VienGach(x*32, y*32)) { // chua den chinh giua 2 vien gach
                        if (i < 2) {
                            return 1-i;
                        } else {
                            return 5-i;
                        }
                    } else {
                        return i;
                    }
                }
            }
        }
        return generate.nextInt(4); // neu k tim thay thi random
    }

    private boolean findBomber(int x, int y) { //use backtracking
        board[y][x] = 0; // 0 la da xet qua
        if (x == bomber.getXUnit() && y == bomber.getYUnit()) {
            board[y][x] = ++count;
            return true;
        } else {
            for (int i = 0; i < 4; i++) {
                int xNew = x + addToX[i];
                int yNew = y + addToY[i];

                if (xNew >= 0 && xNew < m && yNew >= 0 && yNew < n) {
                    if (board[yNew][xNew] == -10) {
                        Entity tmp = BombermanGame.canvas.getEntityInCoodinate(xNew, yNew);
                        if (tmp instanceof Wall || tmp instanceof Brick || tmp instanceof Portal) {
                            continue;
                        }
                        if (findBomber(xNew, yNew)) {
                            board[y][x] = ++count;
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

    public void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    public int getXUnit() {
        return (int) Math.floor((e.getX()-3)/ Sprite.SCALED_SIZE);
    }

    public int getYUnit() {
        return (int) Math.floor((e.getY()-3)/ Sprite.SCALED_SIZE);
    }

    public boolean tronVenTrong1VienGach(int xx, int yy) {
        if (e.getX()+1 > xx && e.getY() + 1 > yy) {
            return true;
        }
        return false;
    }
}
