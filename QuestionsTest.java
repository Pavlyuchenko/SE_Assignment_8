import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuestionsTest {
    /* Testing Deliverable 4. b */
    /*
     * If we have 4 question categories, then the first categories are on tiles 0,
     * 4, 8...
     * while the question from second category are on tiles 1, 5, 9... and so on
     */

    private Game setupGame() {
        Game game = new Game();
        game.addPlayer("Luuk");
        game.addPlayer("Michal");

        return game;
    }

    @Test
    public void testQuestionCategory() {
        Game game = setupGame();

        game.getCurrentPlayer().updatePosition(1, 12);
        assertEquals("Science Question 50", game.getQuestionManager().askQuestion(game.getCurrentPlayer(), 4));

        game.getCurrentPlayer().updatePosition(2, 12);
        assertEquals("Rock Question 50", game.getQuestionManager().askQuestion(game.getCurrentPlayer(), 4));

        game.getCurrentPlayer().updatePosition(2, 12);
        assertEquals("Science Question 49", game.getQuestionManager().askQuestion(game.getCurrentPlayer(), 4));
    }
}
