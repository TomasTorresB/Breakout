package logic.level;
import logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the basics of a normal level of the game
 *
 * @author Tomás Torres
 */
public class Level_1 extends Observable implements Level,Observer {
    private String name;
    private Level nextLevel;
    private ArrayList<Brick> bricks;
    private int maxPoints = 0;
    private int bricksDestroyed;
    private int currentPoints;
    public Level_1(String name) {
        this.name = name;
        bricks = new ArrayList<>();
        maxPoints = 0;
        nextLevel = null;
        bricksDestroyed = 0;
        currentPoints = 0;
    }

    @Override
    public void addObserverGame(Observer g) {
        this.addObserver(g);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNumberOfBricks() {
        return this.bricks.size()-this.bricksDestroyed;
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
        return true;
    }

    @Override
    public boolean hasNextLevel() {
        return this.nextLevel != null;
    }

    @Override
    public int getPoints() {
        return this.maxPoints;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        if (this.hasNextLevel()) {
             return this.nextLevel.addPlayingLevel(level);
        }
        else {
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
        if (arg instanceof String) {
            this.bricksDestroyed++;
            if (arg.equals("GlassBrickDestroyed")) {
                currentPoints += 50;
            }
            if (arg.equals("WoodenBrickDestroyed")) {
                currentPoints += 200;
            }
            if (this.currentPoints == this.maxPoints) {
                setChanged();
                notifyObservers("Se destruyeron todos los bloques");
            }
        }
    }

    @Override
    public void addMaxPointsGlass() {
        this.maxPoints += 50;
    }

    @Override
    public void addMaxPointsWood() {
        this.maxPoints += 200;
    }

    @Override
    public int getCurrentPoints() {
        return this.currentPoints;
    }
}
