package logic.brick;

import java.util.Observable;
import java.util.Observer;

/**
 * Implements the behavior of a glass brick
 *
 * @author Tomás Torres
 */
public class GlassBrick extends Observable implements Brick {
    private int Health;
    public GlassBrick(){
        Health = 1;
    }

    @Override
    public void hit() {
        if (!this.isDestroyed()) {
            this.Health = 0;
            setChanged();
            notifyObservers("GlassBrickDestroyed");
        }
    }

    @Override
    public boolean isDestroyed() {

        return this.Health <= 0;
    }

    @Override
    public int getScore() {
        return 50;
    }

    @Override
    public int remainingHits() {
        if (this.isDestroyed()) {
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public void addObserverGameOrLevel(Observer o) {
        this.addObserver(o);
    }
}
