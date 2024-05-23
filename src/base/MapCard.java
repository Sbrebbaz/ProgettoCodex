package base;

public class MapCard {
	private Card card;
	private int zIndex;
	/**
	 * creates a new map cell
	 * @param card
	 * @param zIndex
	 */
	public MapCard(Card card, int zIndex) {
		this.card=card;
		this.zIndex=zIndex;
	}
	/**
	 * get card placed in the MapCard
	 * @return
	 */
	public Card getCard() {
		return card;
	}
	/**
	 * place Card in the MapCard
	 * @param card
	 */
	public void setCard(Card card) {
		this.card = card;
	}
	/**
	 * get zIndex of the card
	 * @return
	 */
	public int getzIndex() {
		return zIndex;
	}
	/**
	 * set zIndex of the card
	 * @param zIndex
	 */
	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	/**
	 * returns a String with all informations of the MapCard
	 */
	@Override
	public String toString() {
		return card.toStringVisibleSide() + " zIndex="+ zIndex;
	}
}
