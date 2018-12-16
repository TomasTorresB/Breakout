package logic.brick;

import logic.update.BrickDestroyedUpdate;

/**
 *
 * @author Juan-Pablo Silva
 */
public class WoodenBrick extends AbstractBrick {
    public WoodenBrick() {
        super(3, 200);
    }

    @Override
    protected void sendUpdate() {
        notifyObservers(new BrickDestroyedUpdate(this));
    }

    @Override
    public boolean isGlassBrick() {
        return false;
    }

    @Override
    public boolean isWoodenBrick() {
        return true;
    }

    @Override
    public boolean isMetalBrick() {
        return false;
    }
}
