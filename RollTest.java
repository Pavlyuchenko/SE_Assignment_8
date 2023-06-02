import static org.junit.Assert.assertEquals;

import org.junit.*;

/* Testing Deliverable 4. c */
/* 
 * Testing strategy:
 * roll <= 0
 * 0 < roll <= 12
 * roll > 12
 * 
 * inPenaltyBox = true
 * inPenaltyBox = false
 * roll even
 * roll odd
 */
public class RollTest {
    private Game setupGame() {
        Game game = new Game();
        game.addPlayer("Luuk");
        game.addPlayer("Michal");

        return game;
    }

    /* Tests roll <= 0, inPenaltyBox = false */
    @Test
    public void testRollInvalid() {
        Game game = setupGame();

        game.roll(-1);

        assertEquals(0, game.getCurrentPlayer().getPosition());
    }

    /* Tests 0 < roll <= 12, inPenaltyBox = false */
    @Test
    public void testRollNoPenalty() {
        Game game = setupGame();

        game.roll(2);

        assertEquals(2, game.getCurrentPlayer().getPosition());
    }

    /* Tests roll > 12, inPenaltyBox = false */
    @Test
    public void testRollOverflow() {
        Game game = setupGame();

        game.roll(13);

        assertEquals(0, game.getCurrentPlayer().getPosition());
    }

    /* Tests 0 < roll <= 12, inPenaltyBox = true, roll even */
    @Test
    public void testRollPenalty() {
        Game game = setupGame();

        game.getCurrentPlayer().setInPenaltyBox();
        game.roll(2);

        assertEquals(0, game.getCurrentPlayer().getPosition());
    }

    /* Tests 0 < roll <= 12, inPenaltyBox = true, roll odd */
    @Test
    public void testRollPenaltyOdd() {
        Game game = setupGame();

        game.getCurrentPlayer().setInPenaltyBox();
        game.roll(3);

        assertEquals(3, game.getCurrentPlayer().getPosition());
    }
}
