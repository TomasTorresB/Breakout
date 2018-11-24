package logic.level;

import logic.brick.Brick;

import java.util.*;

/**
 * Implements the basics of the initial level of the game
 *
 * @author Tomás Torres
 */
public class Level_0 implements Level, Observer {
    private String name;
    private ArrayList<Brick> bricks;
    private Level nextLevel;
    public Level_0() {
        name = "";
        bricks = new ArrayList<>();
        nextLevel = this;
    }

    @Override
    public void addObserverGame(Observer game) {

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    @Override
    public List<Brick> getBricks() {
        return this.bricks;
    }

    @Override
    public Level getNextLevel() {
        return this.nextLevel;
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel() {
        return this.getNextLevel() != null && this.getNextLevel() != this;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        if (this.hasNextLevel()) {
            return this.nextLevel.addPlayingLevel(this.getNextLevel());
        } else {
            this.setNextLevel(level);
            return this;
        }
    }

    @Override
    public void setNextLevel(Level level) {
        this.nextLevel = level;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void addMaxPointsGlass() {
    }

    @Override
    public void addMaxPointsWood() {
    }

    @Override
    public int getCurrentPoints() {
        return  1;
    }
}
