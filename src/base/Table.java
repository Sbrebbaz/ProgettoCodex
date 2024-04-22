package base;

public class Table {
	private Deck resourceDeck;
	private Deck goldDeck;
	private Deck objectiveDeck;
	private Deck startingDeck;
	private Card tableCards [];
	private Card tableObjectiveCards[];
	private final int N_TABLE_CARDS = 4;
	private final int N_OBJECTIVE_TABLE_CARDS = 2;

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
	
	public Card drawTableCard(int index) throws Exception,ArrayIndexOutOfBoundsException{
		if(index<0 && index >= N_TABLE_CARDS) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		if(tableCards[index] != null) {
			Card tmp = tableCards[index];
			tableCards[index] = this.drawCard(tableCards[index].getCardType());
			return tmp;
		}else {
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

	public Card[] getTableCards() {
		return tableCards;
	}


	public Card[] getTableObjectiveCards() {
		return tableObjectiveCards;
	}
	
	public Card getTableObjectiveCard(int index) throws ArrayIndexOutOfBoundsException{
		if(index >= 0 && index < N_OBJECTIVE_TABLE_CARDS) {
			return tableObjectiveCards[index];
		}
		else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	
	
	
	

}
