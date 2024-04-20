package base;

public class Table {
	private Deck resourceDeck;
	private Deck goldDeck;
	private Deck objectiveDeck;
	private Deck startingDeck;
	private Deck tableCards [];
	private final int nTableCard = 6;

	public Table() {

	}

	public boolean canDraw(Deck deck) {
		return deck.isEmpty();
	}

	public Card drawCard(CardType type) throws Exception{
		switch(type) {
		case RESOURCE:
			return resourceDeck.drawCard();
		case GOLD:
			return goldDeck.drawCard();
		case STARTING:
			return startingDeck.drawCard();
		case OBJECTIVE:
			return objectiveDeck.drawCard();
		default:
			throw new Exception();
		}
	}

	public Deck getResourceDeck() {
		return resourceDeck;
	}

	public void setResourceDeck(Deck resourceDeck) {
		this.resourceDeck = resourceDeck;
	}

	public Deck getGoldDeck() {
		return goldDeck;
	}

	public void setGoldDeck(Deck goldDeck) {
		this.goldDeck = goldDeck;
	}

	public Deck getObjectiveDeck() {
		return objectiveDeck;
	}

	public void setObjectiveDeck(Deck objectiveDeck) {
		this.objectiveDeck = objectiveDeck;
	}

	public Deck getStartingDeck() {
		return startingDeck;
	}

	public void setStartingDeck(Deck startingDeck) {
		this.startingDeck = startingDeck;
	}

	public Deck[] getTableCards() {
		return tableCards;
	}

	public void setTableCards(Deck[] tableCards) {
		this.tableCards = tableCards;
	}
	
	

}
