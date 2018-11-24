package logic.brick;

import java.util.Observable;
import java.util.Observer;

/**
 * Implements the behavior of a wooden brick
 *
 * @author Tomás Torres
 */
public class WoodenBrick extends Observable implements Brick {
    private int Health;
    public WoodenBrick(){
        Health = 3;
    }

    @Override
    public void hit() {
        if (!this.isDestroyed()) {
            this.Health -= 1;
            if (this.isDestroyed()) {
                setChanged();
                notifyObservers("WoodenBrickDestroyed");
            }
        }
    }

    @Override
    public boolean isDestroyed() {
        return this.Health == 0;
    }

    @Override
    public int getScore() {
        return 200;
    }

    @Override
    public int remainingHits() {
        if (this.isDestroyed()) {
            return 0;
        }
        else {
            return this.Health;
        }
    }

    @Override
    public void addObserverGameOrLevel(Observer g) {
        this.addObserver(g);
    }

}
