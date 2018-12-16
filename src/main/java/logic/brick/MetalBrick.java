package logic.brick;

import logic.update.MetalBrickDestroyedUpdate;

/**
 *
 * @author Juan-Pablo Silva
 */
public class MetalBrick extends AbstractBrick {
    public MetalBrick() {
        super(10, 0);
    }

    @Override
    protected void sendUpdate() {
        notifyObservers(new MetalBrickDestroyedUpdate());
    }

    @Override
    public boolean isGlassBrick() {
        return false;
    }

    @Override
    public boolean isWoodenBrick() {
        return false;
    }

    @Override
    public boolean isMetalBrick() {
        return true;
    }
}
