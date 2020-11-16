package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.frameGame.Keyboard;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;

public class Bomber extends uet.oop.bomberman.entities.character.Character {
    //listbom
    protected Keyboard input;
    //protect int maxBom

    public Bomber(int x, int y, Keyboard kb) {
        super(x, y, Sprite.player_down.getFxImage()); //luc dau mac dinh la huong xuong
        this.input = kb;
        direction = 1;
        velocity = 2;
        input = kb;
    }

    public void update() {
        animate();
        input = BombermanGame.canvas.getInput();
        if (alive == false) {
            //TODO showDeadAnimation
        } else {
            if (input.up || input.right || input.left || input.down) {
                setMoving(true);
            } else {
                setMoving(false);
                switch (direction) {
                    case 0:
                        this.setImg(Sprite.player_up.getFxImage());
                        break;
                    case 1:
                        this.setImg(Sprite.player_down.getFxImage());
                        break;
                    case 2:
                        this.setImg(Sprite.player_left.getFxImage());
                        break;
                    case 3:
                        this.setImg(Sprite.player_right.getFxImage());
                        break;
                }
            }
            if (isMoving()) {
                calculateMove();
            }
        }
    }

    public void render() {

    }

    public void calculateMove() {
        if (input.up) {
            y -= velocity;
            //TODO: add canMove method.
            setDirection(0);
            this.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, timeTransfer).getFxImage());
        } else if (input.down) {
            y += velocity;
            setDirection(1);
            this.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, timeTransfer).getFxImage());
        } else if (input.left) {
            x -= velocity;
            setDirection(2);
            this.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, timeTransfer).getFxImage());
        } else {
            x += velocity;
            setDirection(3);
            this.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, timeTransfer).getFxImage());
        }
    }
}
