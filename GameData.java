package Yahtzee;
import java.io.*;
import java.util.*;

//reference: https://www.baeldung.com/java-serialization

public class GameData implements Serializable {
	private Vector<Player> players;
	private int currentTurnIdx;
	private int roundCount;
	
	GameData(Vector<Player> players) {
		this.players = players;
		currentTurnIdx = 0;
		roundCount = players.size() * 13;
	}
	
	public void setPlayerList(Vector<Player> players) {
		this.players = players;
		currentTurnIdx = 0;
		roundCount = players.size() * 13;
	}
	
	public Vector<Player> getPlayerList() {
		return players;
	}
	
	public void setCurrentPlayerTurn(int turnNumber) {
		currentTurnIdx = turnNumber;
	}

	public void increaseTurnIdx() {
		if (!(++currentTurnIdx < players.size())) {
			currentTurnIdx = 0;
		}
		--roundCount;
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentTurnIdx);
	}
	
	public void setRoundCount(int roundNumber) {
		roundCount = roundNumber;
	}
	
	public int getRoundCount() {
		return roundCount;
	}

	public void reset() {
		players.clear();
		currentTurnIdx = 0;
		roundCount = 0;
	}
}