package logic.level;

import logic.brick.Brick;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the basics of the final level of the game
 *
 * @author Tomás Torres
 */
public class Level_Final implements Level,Observer {
    private String name;
    private Level nextLevel;
    public Level_Final() {
        name = "end";
        nextLevel = null;
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
        return null;
    }

    @Override
    public Level getNextLevel() {
        return null;
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel() {
        return false;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        return null;
    }

    @Override
    public void setNextLevel(Level level) {

    }

    @Override
    public void addObserverGame(Observer game) {

    }

    @Override
    public void addMaxPointsGlass() {

    }

    @Override
    public void addMaxPointsWood() {

    }

    @Override
    public int getCurrentPoints() {
        return 0;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
