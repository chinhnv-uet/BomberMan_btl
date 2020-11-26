package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.frameGame.CanvasGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {

    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    public static int timeLiving = 200; //(seconds) time in a level
    public static Group root;
    private GraphicsContext gc;
    public static CanvasGame canvas;
    private List<Entity> entities = new ArrayList<>();

	public static int scores = 0;
	public static int lives = 3;
    public static Stage window;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
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
        
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                canvas.update();
                canvas.render();
            }
        };
        timer.start();

    }

    
}
