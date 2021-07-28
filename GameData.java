package Yahtzee;

import java.io.*;

import java.util.*;

//reference: https://www.baeldung.com/java-serialization

public class GameData implements Serializable {
	private Vector<Player> players;
	private List<Integer> currentRoll;
	private int currentTurnIdx;
	private int roundCount;
	private int rollsMade;
	
	GameData(Vector<Player> players) {
		this.players = players;
		currentTurnIdx = 0;
		roundCount = players.size() * 13;
		currentRoll = new ArrayList<>();
		rollsMade = 0;
	}
	
	public void increaseTurnIdx() {
		if (!(++currentTurnIdx < players.size())) {
			currentTurnIdx = 0;
		}
		--roundCount;
	}

	public void setPlayerList(Vector<Player> players) {
		this.players = players;
		currentTurnIdx = 0;
		roundCount = players.size() * 13;
	}
	
	public void setCurrentPlayerTurn(int turnNumber) {
		currentTurnIdx = turnNumber;
	}

	public void setRoundCount(int roundNumber) {
		roundCount = roundNumber;
	}

	public void setRollData(int rollsMade, List<Integer> currentRoll) {
		this.rollsMade = rollsMade;
		this.currentRoll = currentRoll;
	}
	
	public Vector<Player> getPlayerList() {
		return players;
	}

	public Player getCurrentPlayer() {
		return players.get(currentTurnIdx);
	}
	
	public int getRoundCount() {
		return roundCount;
	}

	public int getRollsMade() {
		return rollsMade;
	}

	public List<Integer> getCurrentRoll() {
		return currentRoll;
	}

	public void reset() {
		players.clear();
		currentTurnIdx = 0;
		roundCount = 0;
	}
}