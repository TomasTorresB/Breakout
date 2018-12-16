package logic.update;

/**
 *
 * @author Juan-Pablo Silva
 */
public class MetalBrickDestroyedUpdate implements BrickUpdate, LevelUpdate {

    @Override
    public void accept(BrickUpdateReceiver brickUpdateReceiver) {
        brickUpdateReceiver.metalBrickDestroyedUpdate(this);
    }

    @Override
    public void accept(LevelUpdateReceiver levelUpdateReceiver) {
        levelUpdateReceiver.metalBrickDestroyedUpdate(this);
    }
}
