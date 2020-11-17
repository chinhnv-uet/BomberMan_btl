package uet.oop.bomberman.frameGame;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {
    public boolean up, down, left, right, space;

    public Keyboard() {
        up = false;
        down = false;
        left = false;
        right = false;
        space = false;
    }

    public void updateKeyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.UP) {
            up = true;
        }
        if (e.getCode() == KeyCode.DOWN) {
            down = true;
        }
        if (e.getCode() == KeyCode.LEFT) {
            left = true;
        }
        if (e.getCode() == KeyCode.RIGHT) {
            right = true;
        }
        if (e.getCode() == KeyCode.SPACE) {
            space = true;
        }
    }

    public void updateKeyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.UP) {
            up = false;
        }
        if (e.getCode() == KeyCode.DOWN) {
            down = false;
        }
        if (e.getCode() == KeyCode.LEFT) {
            left = false;
        }
        if (e.getCode() == KeyCode.RIGHT) {
            right = false;
        }
        if (e.getCode() == KeyCode.SPACE) {
            space = false;
        }
    }
}