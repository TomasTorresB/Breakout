package logic.brick;

import logic.level.Level;

public class GoldenBrick implements Brick {

    @Override
    public void hit() {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public int remainingHits() {
        return 0;
    }

    @Override
    public void assignToLevel(Level level) {

    }

    public boolean isGlassBrick() {
        return false;
    }
    public boolean isWoodenBrick() {
        return false;
    }
    public boolean isMetalBrick() {
        return false;
    }
}
