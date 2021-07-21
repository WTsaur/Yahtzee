package Yahtzee;

import java.util.List;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class Board extends Panel {
    private static ScorecardPanel scorecardPanel;
    private static JPanel dicePanel;
    private JLabel currentPlayerLabel;
    private static JLabel rollTrackerLabel;
    private static JButton rollButton;
    private static List<DiceModel> Dice = new ArrayList<>();
    private static List<Integer> CurrentRoll = new ArrayList<Integer>();
    private final int BoardBorder = 20;
    private static Player CurrentPlayer = new Player("");
    private int BTN_SCALE_VAL = 50;
    private static int rollCount = 0;
    private static boolean rollHasChanged = false;

    public Board(String bgImgPath) {
        super(bgImgPath);
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        currentPlayerLabel = new JLabel("Player Test");
        currentPlayerLabel.setFont(new Font("Sansserif", Font.PLAIN, 24));
        currentPlayerLabel.setForeground(Color.WHITE);

        rollTrackerLabel = new JLabel("Roll 0/3");
        rollTrackerLabel.setFont(new Font("Sansserif", Font.PLAIN, 24));
        rollTrackerLabel.setForeground(Color.WHITE);

        // Create Dice and add to List
        for (int i = 0; i < 5; ++i) {
            DiceModel die = new DiceModel();
            Dice.add(die);
        }

        // create pause button
        URL urlPE = this.getClass().getResource(Constants.PAUSE_IMAGE_PATH);
        Image imgPE = new ImageIcon(urlPE).getImage().getScaledInstance(BTN_SCALE_VAL, BTN_SCALE_VAL, Image.SCALE_DEFAULT);
        ImageIcon peIcon = new ImageIcon(imgPE);
        JButton pauseButton = new JButton(peIcon);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Yahtzee.togglePauseScreen();
                Yahtzee.toggleBoardScreen();
            }
        });

        // create score card button and label
        JLabel scoreLabel = new JLabel("Score Card");
        scoreLabel.setFont(new Font("Sansserif", Font.PLAIN, 18));
        scoreLabel.setForeground(Color.WHITE);

        URL urlSC = this.getClass().getResource(Constants.SCORECARD_IMAGE_PATH);
        Image imgSC = new ImageIcon(urlSC).getImage().getScaledInstance(BTN_SCALE_VAL, BTN_SCALE_VAL, Image.SCALE_DEFAULT);
        ImageIcon scIcon = new ImageIcon(imgSC);
        JButton scoreCardButton = new JButton(scIcon);
        scoreCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 toggleScoreCard();
            }
        });

        // create roll dice button and label
        JLabel rollLabel = new JLabel("Roll Dice");
        rollLabel.setFont(new Font("Sansserif", Font.PLAIN, 18));
        rollLabel.setForeground(Color.WHITE);

        URL urlRB = this.getClass().getResource(Constants.ROLLBTN_IMAGE_PATH);
        Image imgRB = new ImageIcon(urlRB).getImage().getScaledInstance(BTN_SCALE_VAL, BTN_SCALE_VAL, Image.SCALE_DEFAULT);
        ImageIcon dieIcon = new ImageIcon(imgRB);
        rollButton = new JButton(dieIcon);
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();   
            }
        });

        // JPanel for Player name and roll tracker
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
        labelPanel.setOpaque(false);
        labelPanel.add(currentPlayerLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(rollTrackerLabel);

        // JPanel for labelPanel and pause button
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(labelPanel, BorderLayout.LINE_START);
        topPanel.add(pauseButton, BorderLayout.LINE_END);

        // JPanel for Dice
        JPanel diceInnerTopPanel = new JPanel(new FlowLayout());
        diceInnerTopPanel.setOpaque(false);
        diceInnerTopPanel.add(Dice.get(0));
        diceInnerTopPanel.add(Dice.get(1));
        diceInnerTopPanel.add(Dice.get(2));

        JPanel diceInnerBottomPanel = new JPanel(new FlowLayout());
        diceInnerBottomPanel.setOpaque(false);
        diceInnerBottomPanel.add(Dice.get(3));
        diceInnerBottomPanel.add(Dice.get(4));
        
        dicePanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        dicePanel.setOpaque(false);
        dicePanel.setLayout(new GridBagLayout());
        dicePanel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        dicePanel.add(diceInnerTopPanel, gbc);
        gbc.gridy = 1;
        dicePanel.add(diceInnerBottomPanel, gbc);

        // JPanel for dice roll and score card buttons/labels
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(scoreLabel, gbc);
        gbc.gridy++;
        buttonPanel.add(scoreCardButton, gbc);
        gbc.gridy++;
        buttonPanel.add(Box.createRigidArea(new DimensionUIResource(0, 10)), gbc);
        gbc.gridy++;
        buttonPanel.add(rollLabel, gbc);
        gbc.gridy++;
        buttonPanel.add(rollButton, gbc);

        scorecardPanel = new ScorecardPanel();
        scorecardPanel.setVisible(false);

        //add all components to board
        add(topPanel, BorderLayout.PAGE_START);
        add(scorecardPanel, BorderLayout.LINE_START);
        add(dicePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.LINE_END);

        setBorder(BorderFactory.createEmptyBorder(BoardBorder, BoardBorder, BoardBorder, BoardBorder));
    }

    public static void reset() {
        scorecardPanel.setVisible(false);
        dicePanel.setVisible(false);
        resetRollTrackerLabel();
        CurrentRoll.clear();
        for (DiceModel die : Dice) {
            die.reset();
        }
    }

    public static void prepareBoard() {
        toggleScoreCard();
        reset();
    }

    public static void toggleScoreCard() {
        if (scorecardPanel.isVisible()) {
            scorecardPanel.setVisible(false);
            dicePanel.setVisible(true);
            rollButton.setEnabled(true);
        } else {
            if (CurrentRoll.size() > 0) {
                dicePanel.setVisible(false);
                rollButton.setEnabled(false);
                if (rollHasChanged) {
                    scorecardPanel.calculateOptionsForRoll(CurrentRoll);
                    rollHasChanged = false;
                }
                scorecardPanel.setVisible(true);
            } else {
                displayNoRollErrorMessage();
            }
        }
    }

    public static void resetRollTrackerLabel() {
        rollCount = 0;
        rollTrackerLabel.setText("Roll " + rollCount + "/3");
    }

    public void updateRollTrackerLabel() {
        rollTrackerLabel.setText("Roll " + rollCount + "/3");
    }

    public void rollDice() {
        if (++rollCount <= 3) {
            updateRollTrackerLabel();
            if (!dicePanel.isVisible()) {
                dicePanel.setVisible(true);
            }
            for (DiceModel die : Dice) {
                if (!die.isSelected()) {
                    die.roll();
                }
            }
            CurrentRoll.clear();
            for (DiceModel die : Dice) {
                CurrentRoll.add(die.getValue());
            }
            rollHasChanged = true;
        } else {
            displayMaxRollErrorMessage();
        }
    }

    public static void scoreMarkedListener() {
        prepareBoard();
        Yahtzee.NextTurn();
    }

    public static void displayNoRollErrorMessage() {
        JOptionPane.showMessageDialog(null, "Don't forget to roll those dice!", "", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayMaxRollErrorMessage() {
        JOptionPane.showMessageDialog(null, "You have reached your maximum number of re-rolls!", "", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setCurrentPlayer(Player player) {
        currentPlayerLabel.setText(player.getName());
        CurrentPlayer = player;
        scorecardPanel.setScorePanel(CurrentPlayer.getScorecard());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}