package logic.update;

/**
 *
 * @author Juan-Pablo Silva
 */
public class ScoreUpdate implements LevelUpdate {
    private int score;

    public ScoreUpdate(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void accept(LevelUpdateReceiver levelUpdateReceiver) {
        levelUpdateReceiver.scoreUpdate(this);
    }
}
