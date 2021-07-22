package Yahtzee;
import java.io.*;
import java.util.*;

//reference: https://www.baeldung.com/java-serialization

public class GameData implements Serializable {
	Vector<Player> players;
	int currentPlayerTurn;
	int currentRoundCount;
	
	GameData(Vector<Player> players) {
		this.players = players;
		currentPlayerTurn = 0;
		currentRoundCount = 0;
	}
	
	public void setPlayerList(Vector<Player> players) {
		this.players = players;
	}
	
	public Vector<Player> getPlayerList () {
		return players;
	}
	
	public void setCurrentPlayerTurn(int turnNumber) {
		currentPlayerTurn = turnNumber;
	}
	
	public int getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}
	
	public void setCurrentRoundCount(int roundNumber) {
		currentRoundCount = roundNumber;
	}
	
	public int getCurrentRoundCount() {
		return currentRoundCount;
	}
	
	public void saveCurrentGame() {
		FileOutputStream fileOutputStream = new FileOutputStream("savedGame.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(this);
		objectOutputStream.flush();
		objectOutputStream.close();
	}
	
	public GameData loadSavedGame() {
		FileInputStream fileInputStream = new FileInputStream("savedGame.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		GameData game = (GameData) objectInputStream.readObject();
		objectInputStream.close();
		return game;
	}
}