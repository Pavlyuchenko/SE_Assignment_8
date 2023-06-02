
/**
 * Group 29
 * Michal Pavlíček, Luuk Dobbelaar
 * i6306065, i6331748
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

public class GameTest {
    /* Testing Deliverable 4. a */
    /**
     * Testing strategy:
     * Answer is correct + player in Penalty box
     * Answer is correct + player not in Penalty box
     * Answer is wrong
     */

    private Game setupGame() {
        Game game = new Game();
        game.addPlayer("Luuk");
        game.addPlayer("Michal");

        return game;
    }

    @Test
    public void testCorrectAnswerInPenaltyBox() {
        Game game = setupGame();

        Player Luuk = game.getCurrentPlayer();
        int oldPosition = Luuk.getPosition();
        Luuk.setInPenaltyBox();

        assertEquals(false, game.answeredCorrect());
        assertEquals(Luuk.getPosition(), oldPosition);
        assertFalse(game.getCurrentPlayer() == Luuk);
    }

    @Test
    public void testCorrectAnswerNotPenaltyBox() {
        Game game = setupGame();

        Player Luuk = game.getCurrentPlayer();
        int oldPoints = Luuk.getPoints();
        Luuk.setOutPenaltyBox();

        assertEquals(false, game.answeredCorrect());
        assertEquals(Luuk.getPoints(), oldPoints + 1);

        assertFalse(game.getCurrentPlayer() == Luuk);

        int currentPoints = Luuk.getPoints();
        for (int i = currentPoints; i < game.getPointsToWin(); i++) {
            Luuk.incrementPoints();
        }
        assertEquals(true, game.answeredCorrect());
    }

    @Test
    public void testWrongAnswer() {
        Game game = setupGame();
        Player Luuk = game.getCurrentPlayer();
        Luuk.setOutPenaltyBox();

        assertEquals(false, game.answeredWrong());
        assertEquals(true, Luuk.isInPenaltyBox());
        assertFalse(game.getCurrentPlayer() == Luuk);
    }
}
