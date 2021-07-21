package Yahtzee;

import java.util.Vector;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Yahtzee extends JFrame {

    private static Board board;
    private static Menu menu;
    private static int RoundCount;
    private static int RoundSize;
    private static int CurrentTurn;
    private static Vector<Player> Players;

    public Yahtzee() {
        InitUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Yahtzee ex = new Yahtzee();
            ex.setVisible(true);
        });
    }

    private void InitUI() {
        menu = new Menu(Constants.BACKGROUND_IMAGE_PATH);
        board = new Board(Constants.BACKGROUND_IMAGE_PATH);
        board.setVisible(false);

        getContentPane().add(board);
        getContentPane().add(menu);
        pack();
        setTitle(Constants.APP_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public static void StartGame(Vector<Player> players) {
        Players = players;
        board.setVisible(true);
        menu.setVisible(false);
        CurrentTurn = 0;
        RoundSize = players.size();
        RoundCount = RoundSize * 13;
        Player p = Players.get(CurrentTurn);
        board.setCurrentPlayer(p);
        displayPlayerTurnMessage(p.getName());
    }

    public static void NextTurn() {
        if (--RoundCount < 0) {
            EndGame();
        } else {
            if (++CurrentTurn >= RoundSize) {
                CurrentTurn = 0;
            }
            Player p = Players.get(CurrentTurn);
            board.setCurrentPlayer(p);
            displayPlayerTurnMessage(p.getName());
        }
    }

    public static void displayPlayerTurnMessage(String name) {
        String msg = name + ", it's your turn!";
        JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void EndGame() {
        // Display Score rankings and etc.
    }
    
    import javax.swing.JPanel;

    import java.util.Arrays;
    import java.utils.Array;

    public class Scorecard extends JPanel {

        /**
         * Create the panel.
         */
        public Scorecard(String Player) {
            String playerName = Player;
            final String[] typeLabels = 	{"none", 	"Aces", 	"Twos", 	"Threes", 	"Fours", 	"Fives", 	"Sixes", 	"3 of a Kind", 	"4 of a Kind", 	"Full House", 	"Small Straight", 	"Large Straight", 	"Chance", 	"Yahtzee", 	"Bonus Yahtzees!"};
            String[] scores = 				{"0",		"-",		"-",		"-",		"-",		"-",		"-",		"-",			"-",			"-",			"-",				"-",				"-",		"-",		"0"};
        }

        public int calcEntry(int type, int rolls[])
        {
            int runningScore = 0;

            switch(type)
            {
            case 1:
                for(int i = 0; i<5; i++)
                {
                    if(rolls[i] == 1)
                    {
                        runningScore++;
                    }
                }
                break;
            case 2:
                for(int i = 0; i<5; i++)
                {
                    if(rolls[i] == 2)
                    {
                        runningScore += 2;
                    }
                }
                break;
            case 3:
                for(int i = 0; i<5; i++)
                {
                    if(rolls[i] == 3)
                    {
                        runningScore += 3;
                    }
                }
                break;
            case 4:
                for(int i = 0; i<5; i++)
                {
                    if(rolls[i] == 4)
                    {
                        runningScore += 4;
                    }
                }
                break;
            case 5:
                for(int i = 0; i<5; i++)
                {
                    if(rolls[i] == 5)
                    {
                        runningScore += 5;
                    }
                }
                break;
            case 6:
                for(int i = 0; i<5; i++)
                {
                    if(rolls[i] == 6)
                    {
                        runningScore += 6;
                    }
                }
                break;
            case 7: // 3 of a Kind
                for(int i = 0; i<5; i++)
                {
                    runningScore += rolls[i];
                }
                break;
            case 8: // 4 of a Kind
                for(int i = 0; i<5; i++)
                {
                    runningScore += rolls[i];
                }
                break;
            case 9: // Full House
                runningScore = 25;
            case 10: // Small Straight
                runningScore = 30;
            case 11: // Large Straight
                runningScore = 40;
            case 12: // Chance
                for(int i = 0; i<5; i++)
                {
                    runningScore += rolls[i];
                }
                break;
            case 13: // Yahtzee
                runningScore = 50;
            default:
                break;
            }
            return runningScore;
        }

        public boolean validChoice(int type, int rolls[])
        {
            boolean isValid = false;
            switch (type)
            {
            case 1:
                for (int i = 0; i < 5; i++)
                {
                    if (rolls[i] == 1)
                    {
                        isValid = true;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 5; i++)
                {
                    if (rolls[i] == 2)
                    {
                        isValid = true;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 5; i++)
                {
                    if (rolls[i] == 3)
                    {
                        isValid = true;
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 5; i++)
                {
                    if (rolls[i] == 4)
                    {
                        isValid = true;
                    }
                }
                break;
            case 5:
                for (int i = 0; i < 5; i++)
                {
                    if (rolls[i] == 5)
                    {
                        isValid = true;
                    }
                }
                break;
            case 6:
                for (int i = 0; i < 5; i++)
                {
                    if (rolls[i] == 6)
                    {
                        isValid = true;
                    }
                }
                break;
            case 7: // 3 of a Kind
                Arrays.sort(rolls);
                if ((rolls[0] == rolls[1] && rolls[0] == rolls[2]) ||
                    (rolls[1] == rolls[2] && rolls[1] == rolls[3]) ||
                    (rolls[2] == rolls[3] && rolls[2] == rolls[4]))
                {
                    isValid = true;
                }
                break;
            case 8: // 4 of a Kind
                Arrays.sort(rolls);
                if ((rolls[0] == rolls[1] && rolls[0] == rolls[2] && rolls[0] == rolls[3]) ||
                    (rolls[1] == rolls[2] && rolls[1] == rolls[3] && rolls[1] == rolls[4]))
                {
                    isValid = true;
                }
                break;
            case 9: // Full House
                Arrays.sort(rolls);
                if (((rolls[0] == rolls[1] && rolls[0] == rolls[2]) && (rolls[3] == rolls[4])) ||
                    ((rolls[2] == rolls[3] && rolls[2] == rolls[4])  && (rolls[0] == rolls[1])))
                    {
                        isValid = true;
                    }
                break;
            case 10: // Small Straight
                Arrays.sort(rolls);
                if ((++rolls[0] == rolls[1] && ++rolls[1] == rolls[2] && ++rolls[2] == rolls[3]) || /* all but 5th */
                    (++rolls[1] == rolls[2] && ++rolls[2] == rolls[3] && ++rolls[3] == rolls[4]) || /* all but 1st */
					(++rolls[0] == rolls[2] && ++rolls[2] == rolls[3] && ++rolls[3] == rolls[4]) || /* all but 2nd */
					(++rolls[0] == rolls[1] && ++rolls[1] == rolls[2] && ++rolls[2] == rolls[4]))   /* all but 4th */
                {
                    isValid = true;
                }
                break;
            case 11: // Large Straight
                Arrays.sort(rolls);
                if (++rolls[0] == rolls[1] && ++rolls[1] == rolls[2] && ++rolls[2] == rolls[3] && ++rolls[3] == rolls[4])
                {
                    isValid = true;
                }
                break;
            case 12: // Chance
                isValid = true;
                break;
            case 13: // Yahtzee
                if (rolls[0] == rolls[1] && rolls[0] == rolls[2] && rolls[0] == rolls[3] && rolls[0] == rolls[4])
                {
                    isValid = true;
                }
                break;
            default:
                break;
            }
            return isValid;
        }

    }
}
