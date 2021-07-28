package Yahtzee;

import java.util.Collections;
import java.util.Vector;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Yahtzee extends JFrame {

    private static Board board;
    private static Menu menu;
    private static PauseScreen pauseScreen;
    private static EndgameScreen endgameScreen;
    private static GameData gameData;

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
        setTitle(Constants.APP_NAME);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            /* in case user does not quit through the pause menu while playing a game */
            @Override
            public void windowClosing(WindowEvent e) {
                if (menu.isVisible()) {
                    Yahtzee.QuitGame(false);
                } else {
                    int doSave = JOptionPane.showConfirmDialog(getParent(), "Would you like to save your current game?"); 
                if (doSave == JOptionPane.YES_OPTION) {
                    QuitGame(true);
                } else if (doSave == JOptionPane.NO_OPTION) {
                    QuitGame(false);
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
        
        menu = new Menu(Constants.BACKGROUND_IMAGE_PATH);
        board = new Board(Constants.BACKGROUND_IMAGE_PATH);
	    pauseScreen = new PauseScreen(Constants.BACKGROUND_IMAGE_PATH);
        endgameScreen = new EndgameScreen(Constants.BACKGROUND_IMAGE_PATH);
        board.setVisible(false);
	    pauseScreen.setVisible(false);
        endgameScreen.setVisible(false);

        getContentPane().add(board);
        getContentPane().add(pauseScreen);
        getContentPane().add(endgameScreen);
        getContentPane().add(menu);
        pack();

        try {
            FileInputStream savedGameStream = checkSavedGameExists();
            int doLoad = JOptionPane.showConfirmDialog(getParent(), "Would you like to resume your last saved game?" 
                + "\nThe save file will be deleted if you do not resume."); 
            if (doLoad == JOptionPane.YES_OPTION) {
               try {
                loadSavedGame(savedGameStream);
                StartGame();
                } catch (ClassNotFoundException | IOException e1) {
                    displayGameLoadError();
                }
            } else if (doLoad == JOptionPane.NO_OPTION) {
               /* User wishes to not load saved game. 
                  Delete saved game. */
                deleteSaveFile();
            }
        } catch (FileNotFoundException e1) {
            /* No saved data to load */
        }
    }

    /* make appropriate panels visible and setup board using game data */
    public static void StartGame() {
        board.setVisible(true);
        menu.setVisible(false);
        Player p = gameData.getCurrentPlayer();
        board.setCurrentPlayer(p);
        board.setRollData(gameData.getRollsMade(), gameData.getCurrentRoll());
        displayPlayerTurnMessage(p.getName());
    }
    
    /* create new game data with new players and start game */
    public static void StartGame(Vector<Player> players) {
        gameData = new GameData(players);
        StartGame();
    }

    public static void RestartGame() {
        endgameScreen.setVisible(false);
        pauseScreen.setVisible(false);
        Board.reset();
        ScorecardPanel.reset();
        gameData.reset();
        menu.setVisible(true);
        deleteSaveFile();
    }

    public static void NextTurn() {
        if (gameData.getRoundCount() >= 0) {
            gameData.increaseTurnIdx();
            Player p = gameData.getCurrentPlayer();
            board.setCurrentPlayer(p);
            displayPlayerTurnMessage(p.getName());
        } else {
            EndGame();
        }
    }

    private static void deleteSaveFile() {
        Path path = FileSystems.getDefault().getPath(Constants.GAME_DATA_FILE);
        try {
            Files.delete(path);
        } catch (IOException e1) {
            /* file deletion failed or file does not exist */
        }
    }

    private static void displayPlayerTurnMessage(String name) {
        String msg = name + ", it's your turn!";
        JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void displayGameLoadError() {
        JOptionPane.showMessageDialog(null, "Uh Oh! There was an error loading your game!", "", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void QuitGame(boolean doSave) {
        if (doSave) {

            /* save current roll data to GameData object */
            gameData.setRollData(board.getRollsMade(), board.getRoll());
			/* saves game data to savedGame.txt */
            try {
                saveCurrentGame();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Uh Oh! There was an error saving your game!", "", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            /* User does not wish to save current game. Delete save file. */
            deleteSaveFile();

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


    private static void saveCurrentGame() throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(Constants.GAME_DATA_FILE);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(gameData);
		objectOutputStream.flush();
		objectOutputStream.close();
	}

    private static FileInputStream checkSavedGameExists() throws FileNotFoundException {
        return new FileInputStream(Constants.GAME_DATA_FILE);
    }

    private void loadSavedGame(FileInputStream fIS) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(fIS);
		gameData = (GameData) objectInputStream.readObject();
		objectInputStream.close();
	}

    private static void EndGame() {
        /* Sort Players (players implements comparable so that players are sorted by scorecard grand totals) */
        Collections.sort(gameData.getPlayerList());
        endgameScreen.setRankings(gameData.getPlayerList());
        board.setVisible(false);
	    endgameScreen.setVisible(true);

        /* If game was saved, resumed at a later time and then completed, then delete save file. */
        Path path = FileSystems.getDefault().getPath(Constants.GAME_DATA_FILE);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            /* Game was not previously saved. No need to delete. */
        }
    }

    public static void toggleBoardScreen() {
        board.setVisible(!board.isVisible());
    }

    public static void togglePauseScreen() {
        pauseScreen.setVisible(!pauseScreen.isVisible());
    }
}

