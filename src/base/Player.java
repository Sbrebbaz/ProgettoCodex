package base;

import java.util.*;

public class Player implements Comparable<Player> {
	private int id;
	private String name;
	private List<Card> hand;
	private Card secretObjective;
	private int points;
	private String color;
	private Map map;
	private static int COUNTER = 0;

	public Player(String name, String color) {
		this.id = COUNTER;
		COUNTER++;
		this.name = name;
		this.color = color;
		hand = new ArrayList<Card>();
		map = new Map();
	}

	/**
	 * set the secret objective card for the player
	 * 
	 * @param secretObjective
	 * @throws Exception
	 */
	public void SetSecretObjective(Card secretObjective) throws Exception {
		if (secretObjective.getCardType() == CardType.OBJECTIVE) {
			this.secretObjective = secretObjective;
		} else {
			throw new Exception();
		}
	}

	public void placeStartingCard(int index) throws Exception, IllegalArgumentException {
		if (!inRange(index - 1)) {
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
		map.placeStartingCard(hand.remove(index - 1));
	}

	/**
	 * add to the hand of the player one card
	 * 
	 * @param card
	 */
	public void DrawCard(Card card) {
		hand.add(card);
	}

	/**
	 * add the score points to the player
	 * 
	 * @param points
	 */
	public void AddPoints(int points) {
		this.points += points;
	}

	public boolean winningPoints() {
		return points >= 20;
	}

	public int compareTo(Player player) {
		return player.getPoints() - this.getPoints();
	}

	public boolean equals(Player player) {
		if (player.getPoints() == this.getPoints()) {
			return true;
		} else {
			return false;
		}
	}

	public Card selectCard(int index) throws IllegalArgumentException {
		if (!inRange(index - 1)) {
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
		return hand.get(index - 1);
	}

	public boolean inRange(int index) {
		return index >= 0 && index < hand.size();
	}

	public void playCard(int x, int y, int index) throws Exception, IllegalArgumentException {
		if (!inRange(index - 1)) {
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
		this.AddPoints(map.placeCard(x, y, hand.get(index - 1)));
		hand.remove(index - 1);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public Card getSecretObjective() {
		return secretObjective;
	}

	public Map getMap() {
		return map;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Player ");
		sb.append("id ");
		sb.append(id);
		sb.append(" name '");
		sb.append(name);
		sb.append("' ");
		sb.append("points ");
		sb.append(points);
		sb.append("]");
		return sb.toString();
	}

	public String ToStringPlayerHand() {

		StringBuilder sb = new StringBuilder();
		sb.append(color);
		sb.append(toString());
		sb.append(UIUtility.ANSI_RESET);
		sb.append("\nHand:\n[\n");

		for (Card card : hand) {
			sb.append("\t");
			sb.append(card);
			sb.append("\n");
		}
		sb.append("]");

		return sb.toString();
	}
}