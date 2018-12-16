package logic.level;

import controller.Game;
import logic.brick.Brick;
import logic.update.BrickDestroyedUpdate;
import logic.update.MetalBrickDestroyedUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Juan-Pablo Silva
 */
public class EmptyLevel implements Level {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    @Override
    public List<Brick> getBricks() {
        return new ArrayList<>();
    }

    @Override
    public Level getNextLevel() {
        return this;
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
        level.setNextLevel(this);
        return level;
    }

    @Override
    public void setNextLevel(Level level) {

    }

    @Override
    public void assignGame(Game game) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void brickDestroyedUpdate(BrickDestroyedUpdate brickDestroyedUpdate) {

    }

    @Override
    public void metalBrickDestroyedUpdate(MetalBrickDestroyedUpdate metalBrickDestroyedUpdate) {

    }
}
