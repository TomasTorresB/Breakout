package logic.update;

import logic.brick.Brick;

/**
 *
 * @author Juan-Pablo Silva
 */
public class BrickDestroyedUpdate implements BrickUpdate {
    private Brick brick;
    public BrickDestroyedUpdate(Brick brick) {
        this.brick = brick;
    }

    public Brick getBrick() {
        return brick;
    }

    @Override
    public void accept(BrickUpdateReceiver brickUpdateReceiver) {
        brickUpdateReceiver.brickDestroyedUpdate(this);
    }
}
