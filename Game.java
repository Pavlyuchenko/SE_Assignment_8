import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	private final int QUESTION_PER_CATEGORY = 50;
	private final String QUESTION_CATEGORIES[] = { "Pop", "Science", "Sports", "Rock" };
	private final int BOARD_SIZE = 12;
	private final int POINTS_TO_WIN = 6;

	private QuestionManager questionManager = new QuestionManager();

	private ArrayList<Player> players = new ArrayList<>();

	/*
	 * private ArrayList<String> players = new ArrayList<>();
	 * private ArrayList<Integer> playersPositions = new ArrayList<>();
	 * private ArrayList<Integer> playersPoints = new ArrayList<>();
	 * private ArrayList<Boolean> inPenaltyBox = new ArrayList<>();
	 */

	private Player currentPlayer;
	private boolean isGettingOutOfPenaltyBox;

	public Game() {
		questionManager.createQuestions(QUESTION_CATEGORIES, QUESTION_PER_CATEGORY);
	}

	public boolean isPlayable() {
		return (players.size() >= 2);
	}

	public boolean addPlayer(String playerName) {
		Player newPlayer = new Player(playerName);
		players.add(newPlayer);

		if (players.size() == 1) {
			currentPlayer = newPlayer;
		}

		System.out.println(playerName + " was added");
		System.out.println("There is currently " + players.size() + " of players in the game");

		return true;
	}

	public void roll(int roll) {
		if (roll <= 0 || roll > BOARD_SIZE) {
			System.out.println("Invalid roll");
			return;
		}
		System.out.println(currentPlayer + " is the current player");
		System.out.println("They have rolled a " + roll);

		boolean rolledEven = roll % 2 == 0;

		if (currentPlayer.isInPenaltyBox() && rolledEven) {
			System.out.println(currentPlayer + " is not getting out of the penalty box");
			return;
		}

		if (currentPlayer.isInPenaltyBox() && !rolledEven) {
			currentPlayer.setOutPenaltyBox();
			System.out.println(currentPlayer + " is getting out of the penalty box");
		}

		updatePlayerPosition(roll);
	}

	private void updatePlayerPosition(int roll) {
		currentPlayer.updatePosition(roll, BOARD_SIZE);

		System.out.println(currentPlayer
				+ "'s new location is "
				+ currentPlayer.getPosition());
		System.out.println("The category is " + currentCategory());

		questionManager.askQuestion(currentPlayer, questionManager, QUESTION_CATEGORIES.length);
	}

	private String currentCategory() {
		return QUESTION_CATEGORIES[currentPlayer.getPosition() % QUESTION_CATEGORIES.length];
	}

	public boolean answeredCorrect() {
		if (currentPlayer.isInPenaltyBox()) {
			nextPlayer();
			return false;
		} else {
			System.out.println("Answer was correct!!!!");
			currentPlayer.incrementPoints();
			System.out.println(currentPlayer
					+ " now has "
					+ currentPlayer.getPoints()
					+ " Gold Coins.");
		}
		nextPlayer();

		return didPlayerWin();
	}

	public boolean answeredWrong() {
		System.out.println("Answer was incorrect!!!!");
		System.out.println(currentPlayer + " was sent to the penalty box");

		currentPlayer.setInPenaltyBox();
		nextPlayer();

		return false;
	}

	private void nextPlayer() {
		int currentPlayerIndex = players.indexOf(currentPlayer);
		currentPlayerIndex++;
		if (currentPlayerIndex == players.size())
			currentPlayerIndex = 0;
	}

	private boolean didPlayerWin() {
		return (currentPlayer.getPoints() == POINTS_TO_WIN);
	}
}
