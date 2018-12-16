package logic.update;

/**
 *
 * @author Juan-Pablo Silva
 */
public interface BrickUpdateReceiver {
    void brickDestroyedUpdate(BrickDestroyedUpdate brickDestroyedUpdate);
    void metalBrickDestroyedUpdate(MetalBrickDestroyedUpdate metalBrickDestroyedUpdate);
}
