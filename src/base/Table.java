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

	public Card getTableCards(int index) throws ArrayIndexOutOfBoundsException {
		if(index >= 0 && index< tableCards.size()) {
			return tableCards.get(index);
		}else {
			throw new ArrayIndexOutOfBoundsException();
		}
		
	}


	public Card getTableObjectiveCards(int index) throws ArrayIndexOutOfBoundsException{
		if(index >= 0 && index < N_OBJECTIVE_TABLE_CARDS) {
			return tableObjectiveCards[index];
		}else {
			throw new ArrayIndexOutOfBoundsException();
		}
		
	}
	

	public int getN_TABLE_CARDS() {
		return N_TABLE_CARDS;
	}

	public int getN_OBJECTIVE_TABLE_CARDS() {
		return N_OBJECTIVE_TABLE_CARDS;
	}

	public boolean isEmpty() {
		return resourceDeck.isEmpty() && goldDeck.isEmpty() && tableCards.isEmpty();
	}


	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("TABLE:");
		sb.append("\n");
		
		sb.append("resourceDeck : ");
		sb.append(resourceDeck);
		
		sb.append("goldDeck : ");
		sb.append(goldDeck);
		
		sb.append("objectiveDeck : ");
		sb.append(objectiveDeck);
		
		sb.append("startingDeck : ");
		sb.append(startingDeck);
		
		sb.append("tableCards : ");
		for(Card c : tableCards) {
			sb.append("\n\t");
			sb.append(c);			
		}
		
		sb.append("tableObjectiveCards : ");
		for(Card c : tableObjectiveCards) {
			sb.append("\n\t");
			sb.append(c);			
		}
				
		return sb.toString();
	}
}
