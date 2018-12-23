import controller.Game;
import facade.HomeworkTwoFacade;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.BreakoutLevel;
import logic.level.Level;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


import static org.junit.Assert.*;

public class NotBigTestT2 {
    private HomeworkTwoFacade hw2;
    private Game game;
    private int seed;
    @Before
    public void setUp() {
        hw2 = new HomeworkTwoFacade();
        game = hw2.game;
        seed = 0;
    }

    @Test
    public void testGameInitialConditions() {
        assertFalse(hw2.hasCurrentLevel());
        assertFalse(hw2.hasNextLevel());
        assertEquals(3,hw2.getBallsLeft());
        assertEquals(0,hw2.getLevelPoints());
        assertFalse(hw2.isGameOver());
        assertFalse(hw2.winner());
    }

    @Test
    public void testInitialLevelConditions() {
        Level l = hw2.getCurrentLevel();
        assertEquals(0, l.getPoints());
        assertFalse(l.isPlayableLevel());
        assertFalse(l.hasNextLevel());
        assertEquals(0, l.getNumberOfBricks());
    }

    @Test
    public void testGlassBricks() {
        Brick gb = new GlassBrick();
        assertEquals(1,gb.remainingHits());
        assertFalse(gb.isDestroyed());
        gb.hit();
        assertEquals(0,gb.remainingHits());
        assertTrue(gb.isDestroyed());
        gb.hit();
        assertEquals(0,gb.remainingHits());
        assertTrue(gb.isDestroyed());
    }

    @Test
    public void testWoodBricks() {
        Brick wb = new WoodenBrick();
        assertEquals(3,wb.remainingHits());
        assertFalse(wb.isDestroyed());
        wb.hit();
        assertEquals(2,wb.remainingHits());
        assertFalse(wb.isDestroyed());
        wb.hit();
        wb.hit();
        assertEquals(0,wb.remainingHits());
        assertTrue(wb.isDestroyed());
    }

    @Test
    public void testMetalBricks() {
        Brick mb = new MetalBrick();
        assertEquals(10,mb.remainingHits());
        assertFalse(mb.isDestroyed());
        mb.hit();
        assertEquals(9,mb.remainingHits());
        assertFalse(mb.isDestroyed());
        mb.hit();
        mb.hit();
        mb.hit();
        mb.hit();
        mb.hit();
        mb.hit();
        mb.hit();
        mb.hit();
        mb.hit();
        assertEquals(0,mb.remainingHits());
        assertTrue(mb.isDestroyed());
    }



    @Test
    public void testLevelCreation() {
        int numberOfGlass = 0;
        int numberOfWood = 0;
        int numberOfMetal = 0;

        //20 glass
        Level l = hw2.newLevelWithBricksNoMetal("Only Glass",20,1,seed);
        //20 wood
        Level l2 = hw2.newLevelWithBricksNoMetal("Only Wood",20,0,seed);
        //9 glass 11 wood
        Level l3 = hw2.newLevelWithBricksNoMetal("Both",20,0.5,seed);
        //9 glass 11 wood
        Level l4 = hw2.newLevelWithBricksFull("Both2",20,0.5,0,seed);
        //9 glass 11 wooden 9 metal
        Level l5 = hw2.newLevelWithBricksFull("Everything",20,0.5,0.5,seed);
        hw2.setCurrentLevel(l);
        hw2.addPlayingLevel(l2);
        hw2.addPlayingLevel(l3);
        hw2.addPlayingLevel(l4);
        hw2.addPlayingLevel(l5);

        assertTrue(hw2.hasCurrentLevel());
        assertTrue(hw2.getCurrentLevel().isPlayableLevel());
        assertFalse(hw2.isGameOver());
        assertEquals(20,hw2.getCurrentLevel().getNumberOfBricks());
        assertEquals(1000,hw2.getCurrentLevel().getPoints());
        assertEquals(l,hw2.getCurrentLevel());
        ArrayList<Brick> b = (ArrayList<Brick>) hw2.getBricks();
        for (Brick brick : b) {
            if (brick instanceof GlassBrick) {
                numberOfGlass++;
            }
            if (brick instanceof WoodenBrick) {
                numberOfWood++;
            }
            if (brick instanceof MetalBrick) {
                numberOfMetal++;
            }
        }
        assertEquals(numberOfGlass,20);
        assertEquals(numberOfWood,0);
        assertEquals(numberOfMetal,0);
        numberOfGlass = 0;
        numberOfWood = 0;
        numberOfMetal = 0;

        hw2.goNextLevel();
        assertTrue(hw2.hasCurrentLevel());
        assertTrue(hw2.getCurrentLevel().isPlayableLevel());
        assertFalse(hw2.isGameOver());
        assertEquals(20,hw2.getCurrentLevel().getNumberOfBricks());
        assertEquals(200*20,hw2.getCurrentLevel().getPoints());
        assertEquals(l2,hw2.getCurrentLevel());
        ArrayList<Brick> b2 = (ArrayList<Brick>) hw2.getBricks();
        for (Brick brick : b2) {
            if (brick instanceof GlassBrick) {
                numberOfGlass++;
            }
            if (brick instanceof WoodenBrick) {
                numberOfWood++;
            }
            if (brick instanceof MetalBrick) {
                numberOfMetal++;
            }
        }
        assertEquals(numberOfGlass,0);
        assertEquals(numberOfWood,20);
        assertEquals(numberOfMetal,0);
        numberOfGlass = 0;
        numberOfWood = 0;
        numberOfMetal = 0;

        hw2.goNextLevel();
        assertTrue(hw2.hasCurrentLevel());
        assertTrue(hw2.getCurrentLevel().isPlayableLevel());
        assertFalse(hw2.isGameOver());
        assertEquals(20,hw2.getCurrentLevel().getNumberOfBricks());
        assertEquals(9*50+11*200,hw2.getCurrentLevel().getPoints());
        assertEquals(l3,hw2.getCurrentLevel());
        ArrayList<Brick> b3 = (ArrayList<Brick>) hw2.getBricks();
        for (Brick brick : b3) {
            if (brick instanceof GlassBrick) {
                numberOfGlass++;
            }
            if (brick instanceof WoodenBrick) {
                numberOfWood++;
            }
            if (brick instanceof MetalBrick) {
                numberOfMetal++;
            }
        }
        assertEquals(numberOfGlass,9);
        assertEquals(numberOfWood,11);
        assertEquals(numberOfMetal,0);
        numberOfGlass = 0;
        numberOfWood = 0;
        numberOfMetal = 0;

        hw2.goNextLevel();
        assertTrue(hw2.hasCurrentLevel());
        assertTrue(hw2.getCurrentLevel().isPlayableLevel());
        assertFalse(hw2.isGameOver());
        assertEquals(20,hw2.getCurrentLevel().getNumberOfBricks());
        assertEquals(9*50+11*200,hw2.getCurrentLevel().getPoints());
        assertEquals(l4,hw2.getCurrentLevel());
        ArrayList<Brick> b4 = (ArrayList<Brick>) hw2.getBricks();
        for (Brick brick : b4) {
            if (brick instanceof GlassBrick) {
                numberOfGlass++;
            }
            if (brick instanceof WoodenBrick) {
                numberOfWood++;
            }
            if (brick instanceof MetalBrick) {
                numberOfMetal++;
            }
        }
        assertEquals(numberOfGlass,9);
        assertEquals(numberOfWood,11);
        assertEquals(numberOfMetal,0);
        numberOfGlass = 0;
        numberOfWood = 0;
        numberOfMetal = 0;

        hw2.goNextLevel();
        assertTrue(hw2.hasCurrentLevel());
        assertTrue(hw2.getCurrentLevel().isPlayableLevel());
        assertFalse(hw2.isGameOver());
        assertNotEquals(20,hw2.getCurrentLevel().getNumberOfBricks());
        assertEquals(29,hw2.getCurrentLevel().getNumberOfBricks());
        assertEquals(9*50+11*200,hw2.getCurrentLevel().getPoints());
        assertEquals(l5,hw2.getCurrentLevel());
        ArrayList<Brick> b5 = (ArrayList<Brick>) hw2.getBricks();
        for (Brick brick : b5) {
            if (brick instanceof GlassBrick) {
                numberOfGlass++;
            }
            if (brick instanceof WoodenBrick) {
                numberOfWood++;
            }
            if (brick instanceof MetalBrick) {
                numberOfMetal++;
            }
        }
        assertEquals(numberOfGlass,9);
        assertEquals(numberOfWood,11);
        assertEquals(numberOfMetal,9);

        assertFalse(hw2.hasNextLevel());
        hw2.goNextLevel();
        assertFalse(hw2.winner());
        assertFalse(hw2.isGameOver());
    }

    @Test
    public void testLoseBalls() {
        hw2.dropBall();
        assertEquals(2,hw2.getBallsLeft());
        assertFalse(hw2.isGameOver());
        assertFalse(hw2.winner());
        hw2.dropBall();
        hw2.dropBall();
        assertEquals(0,hw2.getBallsLeft());
        assertTrue(hw2.isGameOver());
        assertFalse(hw2.winner());
        hw2.dropBall();
        assertEquals(0,hw2.getBallsLeft());
    }

    @Test
    public void testNormalGame() {
        //9 glass 11 wooden 9 metal
        Level l1 = hw2.newLevelWithBricksFull("L1",20,0.5,0.5,seed);
        assertFalse(hw2.getCurrentLevel().isPlayableLevel());
        hw2.setCurrentLevel(l1);
        assertTrue(hw2.hasCurrentLevel());
        assertTrue(hw2.getCurrentLevel().isPlayableLevel());
        assertEquals(l1,hw2.getCurrentLevel());
        assertFalse(hw2.hasNextLevel());
        // 9 glass 11 wooden 3 metal
        Level l2 = hw2.newLevelWithBricksFull("L2",20,0.5,0.1,seed);
        game.addPlayingLevel(l2);
        assertNotEquals(l2,hw2.getCurrentLevel());
        game.dropBall();
        assertEquals(2,hw2.getBallsLeft());
        assertFalse(hw2.isGameOver());
        assertFalse(hw2.winner());
        assertEquals(9*50+200*11,hw2.getCurrentLevel().getPoints());
        ArrayList<Brick> b1 = (ArrayList<Brick>) l1.getBricks();
        //se destruyen todos los brick no metal
        for (Brick brick : b1) {
            if (brick instanceof GlassBrick) {
                brick.hit();
            }
            if (brick instanceof WoodenBrick) {
                brick.hit();
                brick.hit();
                brick.hit();
            }
        }
        assertEquals(9*50+200*11,hw2.getCurrentPoints());
        assertNotEquals(l1,hw2.getCurrentLevel());
        assertEquals(l2,hw2.getCurrentLevel());
        assertFalse(hw2.isGameOver());
        assertFalse(hw2.winner());
        ArrayList<Brick> b2 = (ArrayList<Brick>) l2.getBricks();
        for (Brick brick : b2) {
            if (brick instanceof MetalBrick) { //se destruuen los 3 metal
                brick.hit();
                brick.hit();
                brick.hit();
                brick.hit();
                brick.hit();
                brick.hit();
                brick.hit();
                brick.hit();
                brick.hit();
                brick.hit();
            }
        }
        assertEquals(9*50+200*11,hw2.getCurrentPoints());
        assertNotEquals(18*50+200*22,hw2.getCurrentPoints());
        assertEquals(5,hw2.getBallsLeft());
        assertFalse(hw2.isGameOver());
        for (Brick brick : b2) {
            if (brick instanceof WoodenBrick) { //se destruyen los wood
                brick.hit();
                brick.hit();
                brick.hit();
            }
            if (brick instanceof GlassBrick) { //se destruyen los glass
                brick.hit();
            }
        }
        assertEquals(18*50+200*22,hw2.getCurrentPoints());
        assertFalse(hw2.isGameOver());
        assertTrue(hw2.winner());
    }
}
