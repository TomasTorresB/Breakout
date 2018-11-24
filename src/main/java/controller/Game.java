package controller;

import logic.brick.*;
import logic.level.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva, Tomás Torres
 */
public class Game implements Observer {
    private Level currentLevel;
    private Level inicial = new Level_0();
    private Level finalLevel = new Level_Final();
    private int currentPoints;
    private int balls;
    public Game(int balls) {
        currentLevel = inicial;
        currentPoints = 0;
        this.balls = balls;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            if (arg.equals("GlassBrickDestroyed")) {
                currentPoints += 50;
            }
            if (arg.equals("WoodenBrickDestroyed")) {
                currentPoints += 200;
            }
            if (arg.equals("MetalBrickDestroyed")) {
                this.balls++;
            }
            if (arg.equals("Se destruyeron todos los bloques")) {
                if (!this.winner()) {
                    this.goNextLevel();
                }
                else {
                    this.setCurrentLevel(finalLevel);
                }
            }
        }
    }

    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        Level l = new Level_1(name);
        l.addObserverGame(this);
        ArrayList<Brick> B = (ArrayList<Brick>) l.getBricks(); //eliminar cast
        Random r = new Random(seed);
        for (int i = 0; i < numberOfBricks ; i++  ) {
            if (r.nextDouble() <= probOfGlass) {
                GlassBrick gb = new GlassBrick();
                B.add(i,gb);
                gb.addObserverGameOrLevel(this);
                gb.addObserverGameOrLevel(l);
                l.addMaxPointsGlass();

            }
            else {
                WoodenBrick wb = new WoodenBrick();
                B.add(i,wb);
                wb.addObserverGameOrLevel(this);
                wb.addObserverGameOrLevel(l);
                l.addMaxPointsWood();
            }
        }
        for (int i = 0; i < numberOfBricks ; i++  ) {
            if (r.nextDouble() <= probOfMetal) {
                MetalBrick mb = new MetalBrick();
                B.add(i,mb);
                mb.addObserverGameOrLevel(this);
                mb.addObserverGameOrLevel(l);
            }
        }
        return l;
    }

    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        Level l = new Level_1(name);
        l.addObserverGame(this);
        ArrayList<Brick> B = (ArrayList<Brick>) l.getBricks(); //eliminar cast
        Random r = new Random(seed);
        for (int i = 0; i < numberOfBricks ; i++  ) {
            if (r.nextDouble() <= probOfGlass) {
                GlassBrick gb = new GlassBrick();
                B.add(i,gb);
                gb.addObserverGameOrLevel(this);
                gb.addObserverGameOrLevel(l);
                l.addMaxPointsGlass();

            }
            else {
                WoodenBrick wb = new WoodenBrick();
                B.add(i,wb);
                wb.addObserverGameOrLevel(this);
                wb.addObserverGameOrLevel(l);
                l.addMaxPointsWood();
            }
        }
        return l;
    }

    public String getLevelName() {
        return this.currentLevel.getName();
    }

    public boolean hasCurrentLevel() {
        return this.currentLevel != inicial && this.currentLevel != finalLevel;
    }

    public void addPlayingLevel(Level level) {
        this.currentLevel.addPlayingLevel(level);
    }

    public void setCurrentLevel(Level level) {
        level.addObserverGame(this);
        this.currentLevel = level;
    }

    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    public boolean hasNextLevel() {
        return this.currentLevel.hasNextLevel();
    }

    public void goNextLevel() {
        if (this.hasNextLevel()) {
            this.currentLevel = this.currentLevel.getNextLevel();
            this.currentLevel.addObserverGame(this);
        }
    }

    public int getLevelPoints() {
        return this.currentLevel.getPoints();
    }

    public int getCurrentPoints() {
        return this.currentPoints;
    }

    public int getBallsLeft() {
        return this.balls;
    }

    public int dropBall() {
        if (this.balls > 0) {
            this.balls--;
        }
        return this.getBallsLeft();
    }

    public boolean isGameOver() {
        return this.currentLevel == null || this.balls == 0;
    }

    public int numberOfBricks() {
        return this.currentLevel.getNumberOfBricks();
    }

    public List<Brick> getBricks() {
        return this.currentLevel.getBricks();
    }
    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return this.currentLevel.getCurrentPoints() == this.currentLevel.getPoints() && !this.currentLevel.hasNextLevel() && this.balls > 0;
    }
}
