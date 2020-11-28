package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.frameGame.CanvasGame;
import uet.oop.bomberman.frameGame.MenuGame;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

    //frame game
    public final int WIDTH = 31;
    public final int HEIGHT = 14;
    public Group root;
    private GraphicsContext gc;
    public static CanvasGame canvas;
    public MenuGame menuGame;
    public static Stage window;

    //thong so game
    public static int timeLiving = 300;
    public static int scores = 0;
    public static int lives = 3;

    public boolean showMenu = true;
    public boolean mute = false;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        window = stage;

        // Tao Canvas
        canvas = new CanvasGame(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        window.setScene(scene);
        window.setTitle(CanvasGame.TITTLE);
        window.show();

        //init menu game
        menuGame = new MenuGame(canvas.getInput());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (showMenu) {
                    menuGame.showMenuGame(gc);
                    menuGame.update();

                    //handle selections in menu
                    if (menuGame.isQuit()) {
                        window.close();
                    } else if (menuGame.isStartGame()) {
                        mute = menuGame.isMute();
                        menuGame.setStartGame(false);
                        showMenu = false;
                        canvas.setTransferLevel(true);
                    }
                } else {
                    canvas.update();
                    canvas.render();
                    if (canvas.returnMenu()) { //khi win or loose se return menu chinh
                        showMenu = true;
                        canvas.setReturnMenu(false);
                    }
                }
            }
        };
        timer.start();
    }
}