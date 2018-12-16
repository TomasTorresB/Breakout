package logic.brick;

import logic.level.Level;

import java.util.Observable;

/**
 *
 * @author Juan-Pablo Silva
 */
public abstract class AbstractBrick extends Observable implements Brick {
    private int remainingHits;
    private boolean destroyed;
    private int score;

    AbstractBrick(int remainingHits, int score) {
        this.remainingHits = remainingHits;
        this.destroyed = false;
        this.score = score;
    }

    @Override
    public void hit() {
        if (!destroyed) {
            remainingHits--;
            if (remainingHits <= 0) {
                destroyed = true;
                setChanged();
                sendUpdate();
            }
        }
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int remainingHits() {
        return remainingHits;
    }

    @Override
    public void assignToLevel(Level level) {
        addObserver(level);
    }

    protected abstract void sendUpdate();
}
