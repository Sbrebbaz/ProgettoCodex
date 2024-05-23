package base;

import java.util.ArrayList;
import java.util.Arrays;

public class Table {
	private Deck resourceDeck;
	private Deck goldDeck;
	private Deck objectiveDeck;
	private Deck startingDeck;
	private ArrayList <Card> tableCards;
	private Card tableObjectiveCards[];
	private final int N_TABLE_CARDS = 4;
	private final int N_OBJECTIVE_TABLE_CARDS = 2;
	/**
	 * construct a table with all deck taken from the Json file and prepare the table cards
	 */
	public Table() {// inizializza i decks dal file, pesca 2 carte gold e resource e li mette in tableCards e pesca 2 carte Objective e li mette in tableObjectiveCards
		ArrayList <Card> cards= new ArrayList<Card>(Arrays.asList(CardUtility.getCards()));
		ArrayList <Card> resource = new ArrayList<Card>();
		ArrayList <Card> gold = new ArrayList<Card>();
		ArrayList <Card> objective = new ArrayList<Card>();
		ArrayList <Card> starting = new ArrayList<Card>();
		for(Card card:cards) {
			switch(card.getCardType()) {
			case RESOURCE:
				resource.add(card);
				break;
			case GOLD:
				gold.add(card);
				break;
			case OBJECTIVE:
				objective.add(card);
				break;
			case STARTING:
				starting.add(card);
			}
		}
		resourceDeck = new Deck(resource);
		goldDeck = new Deck(gold);
		objectiveDeck = new Deck(objective);
		startingDeck = new Deck(starting);
		tableCards = new ArrayList<Card>();
		tableObjectiveCards = new Card[N_OBJECTIVE_TABLE_CARDS];
		try {
			for(int i = 0;i<N_TABLE_CARDS/2;i++) {
				tableCards.add(goldDeck.drawCard());
			}
			for(int i = N_TABLE_CARDS/2;i<N_TABLE_CARDS;i++) {
				tableCards.add(resourceDeck.drawCard());
			}
			
			for(int i = 0; i<N_OBJECTIVE_TABLE_CARDS;i++) {
				tableObjectiveCards[i] = objectiveDeck.drawCard();
			}
		}catch(Exception e) {
			System.out.println("errore nell'inizializzazione del tavolo da gioco:"+e.getMessage());
		}
	}
	/**
	 * find if you can draw from a specific deck
	 * @param deck
	 * @return
	 */
	public boolean canDraw(Deck deck) {
		return !deck.isEmpty();
	}
	/**
	 * draw a card from the type
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public Card drawCard(CardType type) throws Exception{
		switch(type) {
		case RESOURCE:
			if(canDraw(resourceDeck)) {
				return resourceDeck.drawCard();
			}else {
				throw new Exception();
			}
		case GOLD:
			if(canDraw(resourceDeck)) {
				return goldDeck.drawCard();
			}else {
				throw new Exception();
			}
			
		case STARTING:
			if(canDraw(resourceDeck)) {
				return startingDeck.drawCard();
			}else {
				throw new Exception();
			}
			
		case OBJECTIVE:
			if(canDraw(resourceDeck)) {
				return objectiveDeck.drawCard();
			}else {
				throw new Exception();
			}
		default:
			throw new Exception();
		}
	}
	/**
	 * draw selected card from the table
	 * @param index
	 * @return
	 * @throws Exception
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public Card drawTableCard(int index) throws Exception,ArrayIndexOutOfBoundsException{
		if(index<0 || index >= tableCards.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		if(tableCards.get(index) != null) {
			Card tmp = tableCards.get(index);
			tableCards.set(index,this.drawCard(tableCards.get(index).getCardType()));
			return tmp;
		}else {
			throw new Exception();
		}
	}
	/**
	 * get resource deck
	 * @return
	 */
	public Deck getResourceDeck() {
		return resourceDeck;
	}
	/**
	 * set resource deck
	 * @return
	 */
	public void setResourceDeck(Deck resourceDeck) {
		this.resourceDeck = resourceDeck;
	}
	/**
	 * get gold deck
	 * @return
	 */
	public Deck getGoldDeck() {
		return goldDeck;
	}
	/**
	 * set gold deck
	 * @return
	 */
	public void setGoldDeck(Deck goldDeck) {
		this.goldDeck = goldDeck;
	}
	/**
	 * get objective deck
	 * @return
	 */
	public Deck getObjectiveDeck() {
		return objectiveDeck;
	}
	/**
	 * set objective deck
	 * @return
	 */
	public void setObjectiveDeck(Deck objectiveDeck) {
		this.objectiveDeck = objectiveDeck;
	}
	/**
	 * get starting deck
	 * @return
	 */
	public Deck getStartingDeck() {
		return startingDeck;
	}
	/**
	 * set starting deck
	 * @return
	 */
	public void setStartingDeck(Deck startingDeck) {
		this.startingDeck = startingDeck;
	}


	/**
	 * get the selected objective card from the table
	 * @param index
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public Card getTableObjectiveCards(int index) throws ArrayIndexOutOfBoundsException{
		if(index >= 0 && index < N_OBJECTIVE_TABLE_CARDS) {
			return tableObjectiveCards[index];
		}else {
			throw new ArrayIndexOutOfBoundsException();
		}
		
	}
	
	/**
	 * get the max number of table cards
	 * @return
	 */
	public int getN_TABLE_CARDS() {
		return N_TABLE_CARDS;
	}
	/**
	 * get the max number of objective table cards
	 * @return
	 */
	public int getN_OBJECTIVE_TABLE_CARDS() {
		return N_OBJECTIVE_TABLE_CARDS;
	}
	/**
	 * find if the deck is empty
	 * @return
	 */
	public boolean isEmpty() {
		return resourceDeck.isEmpty() && goldDeck.isEmpty() && tableCards.isEmpty();
	}

	/**
	 * returns a String with all information of the Table
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("TABLE:");
		sb.append("\n");
		
		sb.append("resourceDeck : ");
		sb.append(resourceDeck);
		
		sb.append("goldDeck : ");
		sb.append(goldDeck);
		
		sb.append("tableCards : ");
		for(Card c : tableCards) {
			sb.append("\n");
			sb.append(c);			
		}
		
		sb.append("\ntableObjectiveCards : ");
		for(Card c : tableObjectiveCards) {
			sb.append("\n");
			sb.append(c);			
		}
				
		return sb.toString();
	}

	/**
	 * returns a String with all information of the Table related to the draw phase
	 */
	public String toStringDrawTable() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("TABLE:");
		sb.append("\n");
		
		sb.append("resourceDeck : ");
		sb.append(resourceDeck);
		
		sb.append("goldDeck : ");
		sb.append(goldDeck);
		
		sb.append("tableCards : ");
		for(Card c : tableCards) {
			sb.append("\n");
			sb.append(c);			
		}
		
		return sb.toString();
	}
	/**
	 * returns a String with all information of the Table cards
	 */
	public String toStringTableCards() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("TABLE:");
		sb.append("\n");
		
		sb.append("tableCards : ");
		for(Card c : tableCards) {
			sb.append("\n");
			sb.append(c);			
		}
		
		return sb.toString();
	}
}
