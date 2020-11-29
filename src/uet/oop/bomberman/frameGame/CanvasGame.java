package uet.oop.bomberman.frameGame;


import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Entity;

public class CanvasGame extends Canvas {
    private Game game = new Game();
    private Keyboard input = new Keyboard();
    public static final String TITTLE = "Bomberman";

    public CanvasGame(int width, int height) {
        super(width, height);

        //key Event
        this.requestFocus();
        this.setFocusTraversable(true);
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                input.updateKeyPressed(keyEvent);
            }
        });
        this.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                input.updateKeyReleased(keyEvent);
            }
        });

        //create map
        game.createMap();
    }


    public void update() {
        if (game.isGameOver()) {
            return;
        }
        game.update();
    }

    public void render() {
        game.render(this);
    }

    public Keyboard getInput() {
        return input;
    }

    public Entity getEntityInCoodinate(int x, int y) {
        return game.getEntityOnCoodinate(x, y);
    }

    public void setTransferLevel(boolean isTransfer) {
        game.setTransferLevel(isTransfer);
    }

    public boolean returnMenu() {
        return game.isReturnMainMenu();
    }

    public void setReturnMenu(boolean returnMenu) {
        game.setReturnMainMenu(returnMenu);
    }
}
