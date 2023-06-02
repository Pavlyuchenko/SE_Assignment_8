import static org.junit.Assert.assertEquals;

import org.junit.*;

/* 
 * Testing strategy:
 * roll <= 0
 * 0 < roll <= 12
 * roll > 12
 */
public class RollTest {
    /* Tests roll <= 0 */
    @Test
    public void testRoll() {
        Game game = new Game();

        assertEquals(0, game.roll(0));
    }
}
