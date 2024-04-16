package base;

import java.util.List;

public class Player {
	private int id;
	private String name;
	private List<Card> hand;
	private Card secretObjective;
	private Card startingCard;
	private int points;
	private Color color;
	private Map map;

	public Player(int id, String name, Color color) {
		this.id = id;
		this.name = name;
		this.color = color;
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
	
	public void PlayCard(int x, int y, Card card) {
		
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
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

}
