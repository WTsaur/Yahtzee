package Yahtzee;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

public class ScorecardPanel extends JPanel {

    private Set<String> NoBtnSet = new HashSet<String>() {{
        add("Total");
        add("63+ scores a 35 Bonus");
        add("Upper Section Total");
        add("Lower Section Total");
        add("Grand Total");
    }};
    private Scorecard ScoreData = null;
    private List<ScorecardRow> upperScoreRows = new ArrayList<ScorecardRow>();
    private List<ScorecardRow> lowerScoreRows = new ArrayList<ScorecardRow>();
    private ScorecardRow upperTotalRow;
    private ScorecardRow lowerTotalRow;
    private ScorecardRow grandTotalRow;
    private ScorecardRow totalRow;
    private ScorecardRow upperBonusRow;

    private class ScorecardRow {
        
        private JButton selectButton;
        private JLabel textLabel;
        private JLabel scoreLabel;
        private boolean isMarked;

        public ScorecardRow(String text) {
            textLabel = new JLabel(text);
            textLabel.setForeground(Color.BLACK);
            scoreLabel = new JLabel(Constants.SCORE_NULL);
            scoreLabel.setForeground(Color.BLACK);
            scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
            selectButton = new JButton("Select");
            selectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectButton.getText().equals("Select")) {
                        ScoreData.insertScore(getName(), getScore());
                        scoreLabel.setForeground(Color.BLACK);
                    } else {
                        scoreLabel.setText("0");
                        ScoreData.insertScore(getName(), 0);
                    }
                    //Prepare for next player
                    ScoreData = null;
                    Board.scoreMarkedListener();
                }
            });

            if (NoBtnSet.contains(text)) {
                selectButton.setVisible(false);
            }
        }

        private String getName() {
            return textLabel.getText();
        }

        private int getScore() {
            return Integer.parseInt(scoreLabel.getText());
        }

        private boolean IsMarked() {
            return isMarked;
        }

        private void setScoreLabel(int score) {
            isMarked = false;
            if (score == -1) {
                scoreLabel.setText(Constants.SCORE_NULL);
                selectButton.setVisible(true);
            } else {
                selectButton.setVisible(false);
                scoreLabel.setText(Integer.toString(score));
                isMarked = true;
            }
            scoreLabel.setForeground(Color.BLACK);
        }

        private void setScorePreview(int score) {
            if (score == 0) {
                selectButton.setText("Mark as 0");
            } else {
                selectButton.setText("Select");
            }
            selectButton.setVisible(true);
            scoreLabel.setForeground(new ColorUIResource(255, 79, 79));
            scoreLabel.setText(Integer.toString(score));
        }

        private void setButtonVisibility(boolean val) {
            if (!scoreLabel.getText().equals(Constants.SCORE_NULL)) {
                selectButton.setVisible(val);     
            }
        }
    }

    public ScorecardPanel() {
        setBackground(new ColorUIResource(251, 249, 250));
        setBorder(new LineBorder(new ColorUIResource(107, 129, 141), 8, false));
        setLayout(new GridBagLayout());

        Dimension size = new Dimension(880, 270);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setSize(size);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel upperSectLabel = new JLabel("Upper Section");
        upperSectLabel.setFont(new Font("Sansserif", Font.BOLD, 17));

        JLabel lowerSectLabel = new JLabel("Lower Section");
        lowerSectLabel.setFont(new Font("Sansserif", Font.BOLD, 17));

        for (String label : Scorecard.UpperSectionLabels) {
            upperScoreRows.add(new ScorecardRow(label));
        }
        for (String label : Scorecard.LowerSectionLabels) {
            lowerScoreRows.add(new ScorecardRow(label));
        }
        upperTotalRow = new ScorecardRow("Upper Section Total");
        lowerTotalRow = new ScorecardRow("Lower Section Total");
        grandTotalRow = new ScorecardRow("Grand Total");
        totalRow = new ScorecardRow("Total");
        upperBonusRow = new ScorecardRow("63+ scores a 35 Bonus");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(upperSectLabel, gbc);

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new GridLayout(upperScoreRows.size() + 4, 3));
        for (int i = 0; i < upperScoreRows.size(); ++i) {
            ScorecardRow row = upperScoreRows.get(i);
            leftPanel.add(row.textLabel);
            leftPanel.add(row.scoreLabel);
            leftPanel.add(row.selectButton);
        }
        leftPanel.add(totalRow.textLabel);
        leftPanel.add(totalRow.scoreLabel);
        leftPanel.add(totalRow.selectButton);
        leftPanel.add(upperBonusRow.textLabel);
        leftPanel.add(upperBonusRow.scoreLabel);
        leftPanel.add(upperBonusRow.selectButton);
        leftPanel.add(upperTotalRow.textLabel);
        leftPanel.add(upperTotalRow.scoreLabel);
        leftPanel.add(upperTotalRow.selectButton);
        leftPanel.add(grandTotalRow.textLabel);
        leftPanel.add(grandTotalRow.scoreLabel);
        leftPanel.add(grandTotalRow.selectButton);

        gbc.gridy++;
        add(leftPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(Box.createRigidArea(new Dimension(10, 0)), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(lowerSectLabel, gbc);

        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new GridLayout(lowerScoreRows.size() + 1, 3));
        for (int i = 0; i < lowerScoreRows.size(); ++i) {
            ScorecardRow row = lowerScoreRows.get(i);
            rightPanel.add(row.textLabel);
            rightPanel.add(row.scoreLabel);
            rightPanel.add(row.selectButton);
        }
        rightPanel.add(lowerTotalRow.textLabel);
        rightPanel.add(lowerTotalRow.scoreLabel);
        rightPanel.add(lowerTotalRow.selectButton);

        gbc.gridy++;
        add(rightPanel, gbc);
    }

    public void setScorePanel(Scorecard scores) {
        if (ScoreData == null) {
            ScoreData = scores;
            setScoreLabels();
        }
    }

    public void viewRollPossiblities(List<Integer> roll) {
        showOptionsForRoll(roll);
    }

    public void setScoreLabels() {
        String rowName;
        int score;
        for (ScorecardRow row : upperScoreRows) {
            rowName = row.getName();
            score = ScoreData.getScore(rowName);
            row.setScoreLabel(score);
        }
        for (ScorecardRow row : lowerScoreRows) {
            rowName = row.getName();
            score = ScoreData.getScore(rowName);
            row.setScoreLabel(score);
        }
        totalRow.setScoreLabel(ScoreData.getTotal());
        upperBonusRow.setScoreLabel(ScoreData.getUpperBonus());
        upperTotalRow.setScoreLabel(ScoreData.getUpperTotal());
        lowerTotalRow.setScoreLabel(ScoreData.getLowerTotal());
        grandTotalRow.setScoreLabel(ScoreData.getGrandTotal());
    }

    public void showOptionsForRoll(List<Integer> roll) {
        Collections.sort(roll);
        String rollType;
        for (ScorecardRow row : upperScoreRows) {
            if (row.IsMarked()) {
                row.setButtonVisibility(false);
            } else {
                rollType = row.getName();
                int previewScore = calcRoll(rollType, roll);
                row.setScorePreview(previewScore);
            }
        }

        boolean checkBonusYahtzee = false;
        for (ScorecardRow row : lowerScoreRows) {
            if (row.IsMarked()) {
                row.setButtonVisibility(false);
                if (row.getName().equals("Yahtzee")) {
                    checkBonusYahtzee = true;
                }
            } else {
                rollType = row.getName();
                int previewScore = calcRoll(rollType, roll);
                if (checkBonusYahtzee) {
                    row.setScorePreview(previewScore + 50);
                } else {
                    row.setScorePreview(previewScore);
                }
            }
        }
    }

    public int calcRoll(String type, List<Integer> roll)
	{
		int sum = 0;
		switch (type)
		{
		case "Aces":
			for (int i = 0; i < 5; i++)
			{
				if (roll.get(i) == 1)
				{
					sum += 1;
				}
			}
			break;
		case "Twos":
			for (int i = 0; i < 5; i++)
			{
				if (roll.get(i) == 2)
				{
					sum += 2;
				}
			}
			break;
		case "Threes":
			for (int i = 0; i < 5; i++)
			{
				if (roll.get(i) == 3)
				{
					sum += 3;
				}
			}
			break;
		case "Fours":
			for (int i = 0; i < 5; i++)
			{
				if (roll.get(i) == 4)
				{
					sum += 4;
				}
			}
			break;
		case "Fives":
			for (int i = 0; i < 5; i++)
			{
				if (roll.get(i) == 5)
				{
					sum += 5;
				}
			}
			break;
		case "Sixes":
			for (int i = 0; i < 5; i++)
			{
				if (roll.get(i) == 6)
				{
					sum += 6;
				}
			}
			break;
		case "3 of a kind":
			if ((roll.get(0) == roll.get(1) && roll.get(0) == roll.get(2)) ||
                (roll.get(1) == roll.get(2) && roll.get(1) == roll.get(3)) ||
                (roll.get(2) == roll.get(3) && roll.get(2) == roll.get(4))) {
                for (Integer i : roll) {
                    sum += i;
                }
            }
			break;
		case "4 of a kind":
            if ((roll.get(0) == roll.get(1) && roll.get(0) == roll.get(2) && roll.get(0) == roll.get(3)) ||
            (roll.get(1) == roll.get(2) && roll.get(1) == roll.get(3) && roll.get(1) == roll.get(4))) {
                for (Integer i : roll) {
                    sum += i;
                }
            }
			break;
		case "Full House":
			if (((roll.get(0) == roll.get(1) && roll.get(0) == roll.get(2)) && (roll.get(3) == roll.get(4))) ||
				((roll.get(2) == roll.get(3) && roll.get(2) == roll.get(4))  && (roll.get(0) == roll.get(1))))
				{
					sum = 25;
				}
			break;
		case "Small Straight":
                /* TODO: issue is if a roll is 1, 2, 2, 3 ,4  this is a small straight but code won't see
                1, 2, 3, 4 */
			if ((roll.get(0) + 1 == roll.get(1) && roll.get(1) + 1 == roll.get(2) && roll.get(2) + 1 == roll.get(3)) ||
				(roll.get(1) + 1 == roll.get(2) && roll.get(2) + 1 == roll.get(3) && roll.get(3) + 1 == roll.get(4)))
			{
				sum = 30;
			}
			break;
		case "Large Straight":
			if (roll.get(0) + 1 == roll.get(1) && roll.get(1) + 1 == roll.get(2)
                && roll.get(2) + 1 == roll.get(3) && roll.get(3) + 1 == roll.get(4))
			{
				sum = 40;
			}
			break;
		case "Chance":
			for (Integer i : roll) {
                sum += i;
            }
			break;
        case "Bonus Yahtzee":
		case "Yahtzee":
			if (roll.get(0) == roll.get(1) && roll.get(0) == roll.get(2)
                && roll.get(0) == roll.get(3) && roll.get(0) == roll.get(4))
			{
				sum = 50;
			}
            break;
		default:
			break;
		}
		return sum;
	}
}
