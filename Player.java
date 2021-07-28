package Yahtzee;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {
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

    @Override
    public int compareTo(Player o) {
        return o.scorecard.getGrandTotal() - this.scorecard.getGrandTotal();
    }
}
