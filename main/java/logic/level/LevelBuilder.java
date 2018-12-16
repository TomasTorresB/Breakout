package logic.level;

import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import java.util.Random;

/**
 *
 * @author Juan-Pablo Silva
 */
public class LevelBuilder {
    private BreakoutLevel level;
    private Random random;

    public static LevelBuilder builder() {
        return new LevelBuilder();
    }

    private LevelBuilder() {
        this.level = new BreakoutLevel();
        random = new Random();
    }

    public LevelBuilder setSeed(int seed) {
        random.setSeed(seed);
        return this;
    }

    public LevelBuilder setName(String name) {
        level.setName(name);
        return this;
    }

    public LevelBuilder setNormalBricks(int numberOfBricks, double probOfGlass) {
        for (int i = 0; i < numberOfBricks; i++) {
            if (random.nextDouble() <= probOfGlass) {
                level.addBrick(new GlassBrick());
            } else {
                level.addBrick(new WoodenBrick());
            }
        }
        return this;
    }

    public LevelBuilder setMetalBricks(int numberOfBricks, double probOfMetal) {
        for (int i = 0; i < numberOfBricks; i++) {
            if (random.nextDouble() <= probOfMetal) {
                level.addBrick(new MetalBrick());
            }
        }
        return this;
    }

    public BreakoutLevel getLevel() {
        return level;
    }

}
