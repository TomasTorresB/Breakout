package logic.update;

/**
 *
 * @author Juan-Pablo Silva
 */
public interface LevelUpdateReceiver {
    void scoreUpdate(ScoreUpdate scoreUpdate);
    void maxLevelScoreUpdate(MaxLevelScoreReachedUpdate maxLevelScoreReachedUpdate);
    void metalBrickDestroyedUpdate(MetalBrickDestroyedUpdate metalBrickDestroyedUpdate);
}
