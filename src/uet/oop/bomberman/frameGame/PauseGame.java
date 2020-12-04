package uet.oop.bomberman.frameGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;

public class PauseGame {
    private Keyboard keyboard;
    private int selecting;

    public int finalSelected;

    public PauseGame(Keyboard kb) {
        keyboard = kb;
        selecting = 0;
        finalSelected = -1;
    }

    public void showPauseGame(GraphicsContext gc) {
        String sound = BombermanGame.mute ? "off" : "on";

        gc.setFill(Color.OLIVE);
        gc.fillRect(0, 0, 992, 448);
        gc.setFont(new Font(60));
        gc.setFill(Color.WHITE);
        switch (selecting) {
            case 0:
                gc.fillText("Sound: " + sound, 350, 200);
                gc.fillText("Main menu", 350, 260);
                gc.setFill(Color.RED);
                gc.fillText("Resume", 350, 140);
                break;
            case 1:
                gc.fillText("Resume", 350, 140);
                gc.fillText("Main menu", 350, 260);
                gc.setFill(Color.RED);
                gc.fillText("Sound: " + sound, 350, 200);
                break;
            case 2:
                gc.fillText("Resume", 350, 140);
                gc.fillText("Sound: " + sound, 350, 200);
                gc.setFill(Color.RED);
                gc.fillText("Main menu", 350, 260);
                break;
        }
    }

    public void update() {
        if (!keyboard.release && keyboard.down && selecting < 2) {
            selecting++;
            keyboard.setRelease(true);
        } else if (!keyboard.release && keyboard.up && selecting > 0) {
            selecting--;
            keyboard.setRelease(true);
        }
        if (keyboard.enter && !keyboard.release) {
            switch (selecting) {
                case 0:
                    finalSelected = 1;
                    break;
                case 1:
                    BombermanGame.mute = !BombermanGame.mute;
//                    BombermanGame.mute = mute;
                    break;
                case 2:
                    finalSelected = 2;
                    break;
            }
            if (selecting == 2) {
                selecting = 0; //reset selecting
            }
            keyboard.setRelease(true);
        }
    }

    public int getFinalSelected() {
        return finalSelected;
    }

    public void setFinalSelected(int finalSelected) {
        this.finalSelected = finalSelected;
    }
}
