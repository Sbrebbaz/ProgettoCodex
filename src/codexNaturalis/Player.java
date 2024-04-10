package Base;

import java.util.List;

public class Player {
	private int id;
	private String name;
	private List<Card> hand;
	private ObjectiveCard secretObjective;
	private StartingCard startingCard;
	private int points;
	private Color color;
	private Map map;
	
	public Player(int id,String name,Color color) {
		this.id=id;
		this.name=name;
		this.color=color;
	}
	
	/**
	 * set the secret objective card for the player
	 * @param secretObjective
	 */
	public void SetSecretObjective(ObjectiveCard secretObjective) {
		this.secretObjective=secretObjective;
	}
	
	/**
	 * set the starting card for the player
	 * @param startingCard
	 */
	public void SetStartingCard(StartingCard startingCard) {
		this.startingCard=startingCard;
	}
	
	/** add to the hand of the player one card
	 * 
	 * @param card
	 */
	public void DrawCard(Card card) {
		hand.add(card);
	}

	/**
	 * add the score points to the player
	 * @param points
	 */
	public void AddPoints(int points) {
		this.points+=points;
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

	public ObjectiveCard getSecretObjective() {
		return secretObjective;
	}

	public StartingCard getStartingCard() {
		return startingCard;
	}

	public Map getMap() {
		return map;
	}
	

}
