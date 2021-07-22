package Yahtzee;

public class Player implements Serializable {
    private String name;
    private Scorecard scorecard;

    public Player(String name) {
        setName(name);
        scorecard = new Scorecard();
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
