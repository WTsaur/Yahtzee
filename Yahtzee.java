package Yahtzee;

import java.util.Vector;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Yahtzee extends JFrame {

    private static Board board;
    private static Menu menu;
    private static PauseScreen pauseScreen;
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
        board.setVisible(false);
	    pauseScreen.setVisible(false);

        getContentPane().add(board);
        getContentPane().add(pauseScreen);
        getContentPane().add(menu);
        pack();

        try {
            FileInputStream savedGameStream = checkSavedGameExists();
            int doLoad = JOptionPane.showConfirmDialog(getParent(), "Would you like to resume your last saved game?" 
                + "\nThe save file will be deleted if you do not resume."); 
            if (doLoad == JOptionPane.YES_OPTION) {
               try {
                loadSavedGame(savedGameStream);
                StartGame(gameData.getPlayerList());
                } catch (ClassNotFoundException | IOException e1) {
                    displayGameLoadError();
                }
            } else if (doLoad == JOptionPane.NO_OPTION) {
               /* User wishes to not load saved game. 
                  Delete saved game. */
                Path path = FileSystems.getDefault().getPath(Constants.GAME_DATA_FILE);
                try {
                    Files.delete(path);
                } catch (IOException e1) {
                    /* file deletion failed */
                }
            }
        } catch (FileNotFoundException e1) {
            /* No saved data to load */
        }
    }
    
    public static void StartGame(Vector<Player> players) {
        gameData = new GameData(players);
        board.setVisible(true);
        menu.setVisible(false);
        Player p = gameData.getCurrentPlayer();
        board.setCurrentPlayer(p);
        displayPlayerTurnMessage(p.getName());
    }

    public static void RestartGame() {
        togglePauseScreen();
        Board.reset();
        ScorecardPanel.reset();
        gameData.reset();
        toggleMenuScreen();
    }

    public static void NextTurn() {
        if (gameData.getRoundCount() > 0) {
            gameData.increaseTurnIdx();
            Player p = gameData.getCurrentPlayer();
            board.setCurrentPlayer(p);
            displayPlayerTurnMessage(p.getName());
        } else {
            EndGame();
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

    public static void displayGameLoadError() {
        JOptionPane.showMessageDialog(null, "Uh Oh! There was an error loading your game!", "", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void QuitGame(boolean doSave) {
        if (doSave) {
			//saves game data to savedGame.txt
            try {
                saveCurrentGame();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Uh Oh! There was an error saving your game!", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        System.exit(0);
    }

    public static void saveCurrentGame() throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(Constants.GAME_DATA_FILE);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(gameData);
		objectOutputStream.flush();
		objectOutputStream.close();
	}

    public FileInputStream checkSavedGameExists() throws FileNotFoundException {
        return new FileInputStream(Constants.GAME_DATA_FILE);
    }

    public void loadSavedGame(FileInputStream fIS) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(fIS);
		gameData = (GameData) objectInputStream.readObject();
		objectInputStream.close();
	}

    public static void EndGame() {
        //TODO: Display Score rankings and etc.
        // Map<String, Integer> endGame = new HashMap<String, Integer>();
		// for (Map.Entry<String, Integer> entry : endGame.entrySet())  //forEach entry in the HashMap...
		// {
		// 	endGame.put(Players.getName(), Players.scorecard.getGrandTotal()); //set the player name and grand total in this HashMap
		// }

		//TODO: put endGame on a popup window
    }
}
