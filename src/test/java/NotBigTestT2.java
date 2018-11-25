import controller.Game;
import facade.HomeworkTwoFacade;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.Level;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NotBigTestT2 {
    private Game game;
    private int seed;
    @Before
    public void setUp() {
        game = new Game(3);
        seed = 0;
    }

    @Test
    public void testGameInitialConditions() {
        assertFalse(game.hasCurrentLevel());
        assertFalse(game.hasNextLevel());
        assertEquals(3,game.getBallsLeft());
        assertEquals(0,game.getCurrentPoints());
        assertFalse(game.isGameOver());
        assertFalse(game.winner());
    }

    @Test
    public void testInitialLevelConditions() {
        Level l = game.getCurrentLevel();
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
    public void testNormalLevel() {
        //9 glass 11 wooden 9 metal
        Level l = game.newLevelWithBricksFull("L1",20,0.5,0.5,seed);
        game.setCurrentLevel(l);
        assertEquals("L1",l.getName());
        assertEquals(29,l.getNumberOfBricks());
        assertEquals(9*50+11*200,l.getPoints());

        ArrayList<Brick> b = (ArrayList<Brick>) l.getBricks();
        for (Brick brick : b) {
            if (brick instanceof MetalBrick) {
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
            } else {
                brick.hit(); //se destruyen los 9 glass }
            }
        }
        assertEquals(11,l.getNumberOfBricks());
        assertEquals(9*50,l.getCurrentPoints());
        assertEquals(12,game.getBallsLeft());
    }

    @Test
    public void testLoseBalls() {
        game.dropBall();
        assertEquals(2,game.getBallsLeft());
        assertFalse(game.isGameOver());
        assertFalse(game.winner());
        game.dropBall();
        game.dropBall();
        assertEquals(0,game.getBallsLeft());
        assertTrue(game.isGameOver());
        assertFalse(game.winner());
        game.dropBall();
        assertEquals(0,game.getBallsLeft());

    }

    @Test
    public void testNormalGame() {
        //9 glass 11 wooden 9 metal
        Level l1 = game.newLevelWithBricksFull("L1",20,0.5,0.5,seed);
        game.setCurrentLevel(l1);
        assertTrue(game.hasCurrentLevel());
        assertEquals(l1,game.getCurrentLevel());
        assertFalse(game.hasNextLevel());
        // 9 glass 11 wooden 3 metal
        Level l2 = game.newLevelWithBricksFull("L2",20,0.5,0.1,seed);
        game.addPlayingLevel(l2);
        assertNotEquals(l2,game.getCurrentLevel());
        game.dropBall();
        assertEquals(2,game.getBallsLeft());
        assertFalse(game.isGameOver());
        assertFalse(game.winner());
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
        assertEquals(9*50+200*11,game.getCurrentPoints());
        assertNotEquals(l1,game.getCurrentLevel());
        assertEquals(l2,game.getCurrentLevel());
        assertFalse(game.isGameOver());
        assertFalse(game.winner());
        ArrayList<Brick> b2 = (ArrayList<Brick>) l2.getBricks();
        //se destruyen todos los brick no metal
        for (Brick brick : b2) {
            if (brick instanceof GlassBrick) {
                brick.hit();
            }
            if (brick instanceof WoodenBrick) {
                brick.hit();
                brick.hit();
                brick.hit();
            }
        }
        assertEquals(18*50+200*22,game.getCurrentPoints());
        assertNotEquals(l2,game.getCurrentLevel());
        assertTrue(game.isGameOver());
        assertTrue(game.winner());
    }
}
