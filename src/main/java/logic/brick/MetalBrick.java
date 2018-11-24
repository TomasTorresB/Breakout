package logic.brick;

import java.util.Observable;
import java.util.Observer;

/**
 * Implements the behavior of a metal brick
 *
 * @author Tomás Torres
 */
public class MetalBrick extends Observable implements Brick{
    private int Health;
    public MetalBrick(){
        Health = 10;
    }

    @Override
    public void hit() {
        if (!this.isDestroyed()) {
            this.Health -= 1;
            if (this.isDestroyed()) {
                setChanged();
                notifyObservers("MetalBrickDestroyed");
            }
        }
    }

    @Override
    public boolean isDestroyed() {
        return this.Health == 0;
    }

    @Override
    public int getScore() {
        return 0;
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
