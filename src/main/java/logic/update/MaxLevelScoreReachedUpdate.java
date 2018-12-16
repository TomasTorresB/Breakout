package logic.update;

/**
 *
 * @author Juan-Pablo Silva
 */
public class MaxLevelScoreReachedUpdate implements LevelUpdate {
    @Override
    public void accept(LevelUpdateReceiver levelUpdateReceiver) {
        levelUpdateReceiver.maxLevelScoreUpdate(this);
    }
}
