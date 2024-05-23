package base;

import java.util.*;

public class Deck {
	private List<Card> deck;
	/**
	 * construction of Deck, initialize the deck with the list of Card, if null create a new empty deck
	 * @param deck
	 */
	public Deck(List<Card> deck) {
		if(deck != null) {
			this.deck = deck;
			shuffle();
		}
		else {
			deck = new ArrayList<Card>();
		}
	}

	/**
	 * shuffle the cards in the deck
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}

	/**
	 * true if the deck is empty, false if not
	 *
	 * @return
	 */
	public boolean isEmpty() {
		if (deck.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * draw a card from the deck
	 * @return drawn card
	 * @throws Exception, cast if drawn from an empty deck
	 */
	public Card drawCard() throws Exception {
		if (!this.isEmpty()) {
			return deck.remove(0);
		} else {
			throw new Exception();
		}
	}
	/**
	 * adds a card to the deck
	 * @param card
	 */
	public void addCard(Card card) {
		deck.add(card);
		shuffle();
	}
	/**
	 * return a String with all informations of the Deck
	 */
	@Override
	public String toString() {
		return String.format("Remaining cards %s \n",deck.size());
	}
}
