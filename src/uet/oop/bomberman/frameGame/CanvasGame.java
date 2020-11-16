package uet.oop.bomberman.frameGame;


import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class CanvasGame extends Canvas {
    private int width;
    private int height;
    private Game game = new Game();
    private Keyboard input = new Keyboard();
    public static final String TITTLE = "Bomberman";

    public CanvasGame(int width, int height) {
        super(width, height);
        this.width = width;
        this.height = height;

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

        game.createMap();
    }

    public void update() {
        game.update();

    }

    public void render() {
        game.render(this);
    }

    public Keyboard getInput() {
        return input;
    }
}
