package Yahtzee;

import java.util.Vector;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Yahtzee extends JFrame {

    private static Board board;
    private static Menu menu;
    private static PauseScreen pauseScreen;
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
	    pauseScreen = new PauseScreen(Constants.BACKGROUND_IMAGE_PATH);
        board.setVisible(false);
	    pauseScreen.setVisible(false);

        getContentPane().add(board);
        getContentPane().add(pauseScreen);
        getContentPane().add(menu);
        pack();

        setTitle(Constants.APP_NAME);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                if (menu.isVisible()) {
                    Yahtzee.QuitGame(false);
                } else {
                    int doSave = JOptionPane.showConfirmDialog(getParent(), "Would you like to save your current game?"); 
                if (doSave == JOptionPane.YES_OPTION) {
                    Yahtzee.QuitGame(true);
                } else if (doSave == JOptionPane.NO_OPTION) {
                    Yahtzee.QuitGame(false);
                }
                }
            }
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
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

    public static void RestartGame() {
        togglePauseScreen();
        Board.reset();
        ScorecardPanel.reset();
        Players.clear();
        CurrentTurn = 0;
        RoundSize = 0;
        RoundCount = 0;
        toggleMenuScreen();
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

    public static void toggleBoardScreen() {
        board.setVisible(!board.isVisible());
    }

    public static void toggleMenuScreen() {
        menu.setVisible(!menu.isVisible());
    }

    public static void togglePauseScreen() {
        pauseScreen.setVisible(!pauseScreen.isVisible());
    }

    public static void displayPlayerTurnMessage(String name) {
        String msg = name + ", it's your turn!";
        JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void QuitGame(boolean doSave) {
        if (doSave) {
			GameData thisGame = new GameData(Players);
			thisGame.setCurrentPlayerTurn(CurrentTurn);
			thisGame.setCurrentRoundCount(RoundCount);
			thisGame.saveCurrentGame(); //saves it to savedGame.txt
        }
        System.exit(0);
    }
	
	public static void LoadGame(boolean doLoad) {
		if(doLoad)
		{
			//assuming this process is close to resetting a game...
			togglePauseScreen();
			Board.reset();
			ScorecardPanel.reset();
			
			GameData oldGame = new GameData;
			oldGame.loadSavedGame(); //loads it from savedGame.txt
			//Players.clear();
			Players = oldGame.getPlayerList();
			//CurrentTurn = 0;
			CurrentTurn = oldGame.getCurrentPlayerTurn();
			//RoundSize = 0;
			RoundSize = Players.size();
			//RoundCount = 0;
			RoundCount = oldGame.getCurrentRoundCount();
			
			toggleMenuScreen();
		}
	}

    public static void EndGame() {
		Map<String, Integer> endGame = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : endGame.entrySet())  //forEach entry in the HashMap...
		{
			endGame.put(Players.getName(), Players.scorecard.getGrandTotal()); //set the player name and grand total in this HashMap
		}
		//TODO: put endGame on a popup window
    }