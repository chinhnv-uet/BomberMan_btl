package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }


    public boolean checkCollision(Entity e1, Entity e2) {

        if (e1.getX() == e2.getX() && e1.getY() == e2.getY()) {
            return true;
        }
        return false;

    }

    public void removeFromGame() {
        this.setImg(null);
        this.setX(-1);
        this.setY(-1);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public abstract void update();

    //xUnit, yUnit is coodinate calculated by tiles
    public int getXUnit() {
        return (int) x / Sprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return (int) y / Sprite.SCALED_SIZE;
    }
}
