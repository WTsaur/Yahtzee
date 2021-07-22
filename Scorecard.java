package Yahtzee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scorecard implements Serializable {

    public static List<String> UpperSectionLabels = new ArrayList<String>(){{
        add("Aces");
        add("Twos");
        add("Threes");
        add("Fours");
        add("Fives");
        add("Sixes");
    }};

    public static List<String> LowerSectionLabels = new ArrayList<String>() {{
        add("3 of a kind");
        add("4 of a kind");
        add("Full House");
        add("Small Straight");
        add("Large Straight");
        add("Chance");
        add("Yahtzee");
        add("Yahtzee Bonus");
    }};

    private Map<String, Integer> Scores = new HashMap<String, Integer>();
    private int LowerSectionTotal;
    private int UpperSectionTotal;
    private int Total;
    private int UpperBonus;

    public Scorecard() {
        for (String label : UpperSectionLabels) {
            Scores.put(label, -1);
        }
        for (String label : LowerSectionLabels) {
            Scores.put(label, -1);
        }
        LowerSectionTotal = 0;
        UpperSectionTotal = 0;
        Total = 0;
        UpperBonus = 0;
    }

    public int getTotal() {
        return Total;
    }

    public int getUpperBonus() {
        return UpperBonus;
    }

    public int getLowerTotal() {
        return LowerSectionTotal;
    }

    public int getUpperTotal() {
        return UpperSectionTotal;
    }

    public int getGrandTotal() {
        return LowerSectionTotal + UpperSectionTotal;
    }

    public void insertScore(String label, int val) {
        if (UpperSectionLabels.contains(label)) {
            Scores.put(label, val);
            UpperSectionTotal += val;
            Total += val;
            if (Total >= 63) {
                UpperSectionTotal += 35;
                UpperBonus = 35;
            }
        } else {
            if (label.equals("Bonus Yahtzee")) {
                Scores.put(label, val + Scores.get(label));
            } else {
                Scores.put(label, val);
            }
            LowerSectionTotal += val;
        }
    }

    public int getScore(String label) {
        return Scores.get(label);
    }
}
