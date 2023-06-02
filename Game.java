import java.util.ArrayList;

public class Game {
	private final int QUESTION_PER_CATEGORY = 50;
	private final String QUESTION_CATEGORIES[] = { "Pop", "Science", "Sports", "Rock" };
	private final int BOARD_SIZE = 12;
	private final int POINTS_TO_WIN = 6;

	private QuestionManager questionManager = new QuestionManager();
	private ArrayList<Player> players = new ArrayList<>();

	private Player currentPlayer;

	public Game() {
		questionManager.createQuestions(QUESTION_CATEGORIES, QUESTION_PER_CATEGORY);
	}

	public boolean addPlayer(String playerName) {
		Player newPlayer = new Player(playerName);
		players.add(newPlayer);

		if (players.size() == 1) {
			currentPlayer = newPlayer;
		}

		System.out.println(playerName + " was added");
		System.out.println("There are currently " + players.size() + " players in the game");

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

		currentPlayer.updatePosition(roll, BOARD_SIZE);
		System.out.println(currentPlayer
				+ "'s new location is "
				+ currentPlayer.getPosition());
		System.out.println("The category is " + currentCategory());

		System.out.println(questionManager.askQuestion(currentPlayer, QUESTION_CATEGORIES.length));

	}

	private String currentCategory() {
		return QUESTION_CATEGORIES[currentPlayer.getPosition() % QUESTION_CATEGORIES.length];
	}

	public boolean answeredCorrect() {
		if (currentPlayer.isInPenaltyBox()) {
			nextPlayer();
			return false;
		}

		currentPlayer.incrementPoints();

		System.out.println("Answer was correct!!!!");
		System.out.println(currentPlayer
				+ " now has "
				+ currentPlayer.getPoints()
				+ " Gold Coins.");
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

		currentPlayer = players.get(currentPlayerIndex);
	}

	private boolean didPlayerWin() {
		return (currentPlayer.getPoints() == POINTS_TO_WIN);
	}

	// used for testing only
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public QuestionManager getQuestionManager() {
		return questionManager;
	}

	public int getPointsToWin() {
		return POINTS_TO_WIN;
	}
}
