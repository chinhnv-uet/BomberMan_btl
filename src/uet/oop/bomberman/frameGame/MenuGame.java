package uet.oop.bomberman.frameGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;

public class MenuGame {
    private boolean startGame = false;
    private boolean mute = false;
    private boolean quit = false;

    private Keyboard keyboard;
    private int Selecting = 0;
    private final int[] CoodinateYOfPointer = {200, 250, 300};

    public MenuGame(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void showMenuGame(GraphicsContext gc) {
        try {
            FileInputStream file = new FileInputStream("src/uet/oop/bomberman/frameGame/bgrGame.png");
            Image bgrImg = new Image(file);

            FileInputStream Pointer = new FileInputStream("src/uet/oop/bomberman/frameGame/bombPointer.png");
            Image pointer = new Image(Pointer);

            gc.setFill(Color.BLACK);
            gc.clearRect(0, 0, 992, 448);
            gc.drawImage(bgrImg, 0, 0);
            gc.drawImage(pointer, 350, CoodinateYOfPointer[Selecting]);

            gc.setFont(new Font("", 40));
            gc.fillText("New game", 400, 250);

            String sound;
            if (mute) {
                sound = "off";
            } else {
                sound = "on";
            }
            gc.fillText("Sound: " + sound, 400, 300);

            gc.fillText("Quit", 400, 350);

            //TODO: co the cai tien cho them high score, cach choi nua
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!keyboard.release && keyboard.down && Selecting < 2) {
            Selecting++;
            keyboard.setRelease(true);
        }
        else if (!keyboard.release && keyboard.up && Selecting > 0) {
            Selecting--;
            keyboard.setRelease(true);
        }
        if (keyboard.space && !keyboard.release) {
            switch (Selecting) {
                case 0:
                    startGame = true;
                    break;
                case 1:
                    mute = !mute;
                    break;
                case 2:
                    quit = true;
                    break;
            }
            keyboard.setRelease(true);
        }
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public boolean isMute() {
        return mute;
    }

    public boolean isQuit() {
        return quit;
    }
}
