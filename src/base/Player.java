package base;

import java.util.*;

public class Player implements Comparable<Player>{
	private int id;
	private String name;
	private List<Card> hand;
	private Card secretObjective;
	private Card startingCard;
	private int points;
	private String color;
	private Map map;
	private static int COUNTER=0;

	public Player(String name, String color) {
		this.id = COUNTER;
		COUNTER++;
		this.name = name;
		this.color = color;
		hand = new ArrayList<Card>();
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
		}	
		else {
			throw new Exception();
		}
	}

	/**
	 * set the starting card for the player
	 * 
	 * @param startingCard
	 */
	public void SetStartingCard(Card startingCard) throws Exception {
		if (startingCard.getCardType() == CardType.STARTING) {
			this.startingCard = startingCard;
		}	
		else {
			throw new Exception();
		}
		
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
		return points>=20;
	}
	
	public int compareTo(Player player) {
		return player.getPoints() - this.getPoints();
	}
	
	public boolean equals(Player player) {
		if(player.getPoints() == this.getPoints()) {
			return true;
		}else {
			return false;
		}
	}
	
	public Card selectCard(int index) throws IllegalArgumentException{
		if(index<=0 || index > hand.size()) {
			throw new IllegalArgumentException("Unexpected value: "+index);
		}
		return hand.get(index-1);
	}
	
	public void playCard(int x, int y, Card card) throws Exception{
		this.AddPoints(map.placeCard(x, y, card));
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

	public Card getStartingCard() {
		return startingCard;
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
		sb.append(toString());
		sb.append("\nHand:\n[\n");
		
		for(Card card : hand) {
			sb.append(card);
			sb.append("\n");
		}
		sb.append("]");
		
		return sb.toString();
	}
}