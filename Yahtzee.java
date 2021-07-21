package Yahtzee;

import java.util.Vector;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Yahtzee extends JFrame {

    private static Board board;
    private static Menu menu;
    private static RestartScreen rs;
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
	rs = new RestartScreen(Constants.BACKGROUND_IMAGE_PATH);
        board.setVisible(false);
	rs.setVisible(false);

        getContentPane().add(board);
        getContentPane().add(menu);
	getContentPane().add(rs);
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
        if (--RoundCount <= 0) {
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
        //TODO: Display Score rankings and etc.
	board.setVisible(false);
	rs.setVisible(true);
    }
}
