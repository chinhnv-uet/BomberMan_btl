package uet.oop.bomberman.ai;

import java.util.Random;

public abstract class AI {
    public Random generate = new Random();

    public abstract int setDirect();

    public boolean wantToChangeDirect = false;

    public void setWantToChangeDirect(boolean wantToChangeDirect) {
        this.wantToChangeDirect = wantToChangeDirect;
    }

}
