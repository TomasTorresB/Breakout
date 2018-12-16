package controller;

import logic.brick.Brick;
import logic.level.EmptyLevel;
import logic.level.Level;
import logic.update.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game extends Observable implements Observer, LevelUpdateReceiver {
    private Level currentLevel;
    private int cumulativeScore;
    private int ballsLeft;
    private boolean winner;

    public Game(int balls) {
        ballsLeft = balls;
        currentLevel = new EmptyLevel();
        cumulativeScore = 0;
        winner = false;
    }

    public List<Brick> getBricks() {
        return currentLevel.getBricks();
    }

    public boolean hasNextLevel() {
        return currentLevel.hasNextLevel();
    }

    public void goNextLevel() {
        setLevel(currentLevel.getNextLevel());
    }

    public boolean hasCurrentLevel() {
        return currentLevel.isPlayableLevel();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setLevel(Level level) {
        currentLevel = level;
        level.assignGame(this);
    }

    public int getPoints() {
        return cumulativeScore;
    }

    public int getBalls() {
        return ballsLeft;
    }

    public void dropBall() {
        if (ballsLeft > 0) {
            ballsLeft--;
        }
    }

    public boolean playing() {
        return ballsLeft > 0;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof LevelUpdate) {
            ((LevelUpdate) arg).accept(this);
        }
    }

    @Override
    public void scoreUpdate(ScoreUpdate scoreUpdate) {
        cumulativeScore += scoreUpdate.getScore();
        Integer xd = new Integer(cumulativeScore);
        setChanged();
        notifyObservers(xd);
    }

    @Override
    public void maxLevelScoreUpdate(MaxLevelScoreReachedUpdate maxLevelScoreReachedUpdate) {
        goNextLevel();
        if (!hasCurrentLevel()) {
            winner = true;
            setChanged();
            notifyObservers("Game ended");
        }
        else {
            setChanged();
            notifyObservers("maxLevelScoreUpdate");
        }
    }

    @Override
    public void metalBrickDestroyedUpdate(MetalBrickDestroyedUpdate metalBrickDestroyedUpdate) {
        ballsLeft++;
        setChanged();
        notifyObservers("MetalBrickDestroyedUpdate");
    }

    public void addPlayingLevel(Level level) {
        currentLevel.addPlayingLevel(level);
    }

    public boolean winner() {
        return winner;
    }
}
