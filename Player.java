public class Player {
    private String name;
    private int points;
    private int position;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.position = 0;
        this.inPenaltyBox = false;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean isInPenaltyBox() {
        return this.inPenaltyBox;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementPoints() {
        this.points++;
    }

    public void updatePosition(int position, int boardSize) {
        this.position = this.position + position;
        if (this.position > boardSize - 1)
            this.position = this.position - boardSize;
    }

    public void setInPenaltyBox() {
        this.inPenaltyBox = true;
    }

    public void setOutPenaltyBox() {
        this.inPenaltyBox = false;
    }

    public String toString() {
        return this.getName();
    }
}