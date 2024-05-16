package base;

public class MapCard {
	private Card card;
	private int zIndex;
	
	public MapCard(Card card, int zIndex) {
		this.card=card;
		this.zIndex=zIndex;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public String toString() {
		return "MapCard{" +
				"card=" + card +
				"index="+ zIndex +
				"}\n";
	}
}
