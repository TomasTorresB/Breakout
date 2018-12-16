package logic.level;

import controller.Game;
import logic.brick.Brick;
import logic.update.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Juan-Pablo Silva
 */
public class BreakoutLevel extends Observable implements Level {
    private String name;
    private List<Brick> bricks;
    private int levelPoints;
    private int currentNumberOfBricks;
    private Level nextLevel;
    private int maxLevelScore;

    public BreakoutLevel() {
        this.levelPoints = 0;
        currentNumberOfBricks = 0;
        maxLevelScore = 0;
        bricks = new ArrayList<>();
        nextLevel = new EmptyLevel();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfBricks() {
        return currentNumberOfBricks;
    }

    @Override
    public List<Brick> getBricks() {
        return bricks;
    }

    @Override
    public Level getNextLevel() {
        return nextLevel;
    }

    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    @Override
    public boolean hasNextLevel() {
        return nextLevel.isPlayableLevel();
    }

    @Override
    public int getPoints() {
        return maxLevelScore;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        nextLevel = nextLevel.addPlayingLevel(level);
        return this;
    }

    @Override
    public void setNextLevel(Level level) {
        nextLevel = level;
    }

    @Override
    public void assignGame(Game game) {
        addObserver(game);
    }

    void setName(String name) {
        this.name = name;
    }

    void addBrick(Brick brick) {
        bricks.add(brick);
        brick.assignToLevel(this);
        currentNumberOfBricks++;
        maxLevelScore += brick.getScore();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof BrickUpdate) {
            ((BrickUpdate) arg).accept(this);
        }
    }

    @Override
    public void brickDestroyedUpdate(BrickDestroyedUpdate brickDestroyedUpdate) {
        currentNumberOfBricks--;
        levelPoints += brickDestroyedUpdate.getBrick().getScore();

        setChanged();
        notifyObservers(new ScoreUpdate(brickDestroyedUpdate.getBrick().getScore()));

        if (levelPoints >= maxLevelScore) {
            setChanged();
            notifyObservers(new MaxLevelScoreReachedUpdate());
        }
    }

    @Override
    public void metalBrickDestroyedUpdate(MetalBrickDestroyedUpdate metalBrickDestroyedUpdate) {
        setChanged();
        notifyObservers(new MetalBrickDestroyedUpdate());
    }
}
