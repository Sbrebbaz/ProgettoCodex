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
	/**
	 * create a new player whit their name and color
	 * @param name
	 * @param color
	 */
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
	/**
	 * 
	 * place the starting card in the center of the map
	 * @param index
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
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
	 * add the scored points to the player
	 * 
	 * @param points
	 */
	public void AddPoints(int points) {
		this.points += points;
	}
	/**
	 *find if the player have the points required for finish the game
	 * @return
	 */
	public boolean winningPoints() {
		return points >= 20;
	}
	/**
	 * compare a player whit another in descending order
	 */
	public int compareTo(Player player) {
		return player.getPoints() - this.getPoints();
	}
	/**
	 * find if two players are equals
	 * @param player
	 * @return
	 */
	public boolean equals(Player player) {
		if (player.getPoints() == this.getPoints()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * select a card from the hand
	 * @param index
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Card selectCard(int index) throws IllegalArgumentException {
		if (!inRange(index - 1)) {
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
		return hand.get(index - 1);
	}
	/**
	 * find if the index for selecting the card is in rage
	 * @param index
	 * @return
	 */
	public boolean inRange(int index) {
		return index >= 0 && index < hand.size();
	}
	/**
	 * place the selected card in the map
	 * @param x
	 * @param y
	 * @param index
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public void playCard(int x, int y, int index) throws Exception, IllegalArgumentException {
		if (!inRange(index - 1)) {
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
		this.AddPoints(map.placeCard(x, y, hand.get(index - 1)));
		hand.remove(index - 1);
	}
	/**
	 * get the name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * set the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get the hand
	 * @return
	 */
	public List<Card> getHand() {
		return hand;
	}
	/**
	 * set the hand
	 * @param hand
	 */
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	/**
	 * get the point
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * add point to the player
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * get the color
	 * @return
	 */
	public String getColor() {
		return color;
	}
	/**
	 * set the color
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * get id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * get the secret objective card 
	 * @return
	 */
	public Card getSecretObjective() {
		return secretObjective;
	}
	/**
	 * get the map linked to the player
	 * @return
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * returns a String whit the generic informations of the Player
	 */
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
	/**
	 * returns a String whit the hand of the player
	 * @return
	 */
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