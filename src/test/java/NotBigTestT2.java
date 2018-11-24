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

    @Before
    public void setUp() {
        game = new Game(3);
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
        //assertEquals(0, l.getCurrentPoints());
        assertFalse(l.isPlayableLevel());
        assertFalse(l.hasNextLevel());
        assertEquals(0, l.getNumberOfBricks());
        assertEquals(null, l.getNextLevel());
    }
}
