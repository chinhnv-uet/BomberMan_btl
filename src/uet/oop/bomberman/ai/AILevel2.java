package uet.oop.bomberman.ai;

public class AILevel2 extends AI {

    @Override
    public int setDirect() {
        return generate.nextInt(4);
    }

    
}
