
import java.util.Random;

public class GameRunner {

	private static boolean hasWon;

	public static void main(String[] args) {
		Game aGame = new Game();
		aGame.addPlayer("Chet");
		aGame.addPlayer("Pat");
		aGame.addPlayer("Sue");

		Random rand = new Random();

		do {
			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				hasWon = aGame.answeredWrong();
			} else {
				hasWon = aGame.answeredCorrect();
			}

		} while (!hasWon);
	}
}
