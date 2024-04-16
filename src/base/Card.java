package base;

public abstract class Card {
	private int id;
	private Symbol cardColor;
	private Side side [];//0:front, 1:back
	private CardType cardType;
	private int visibleSide;
	
	
	public Card(int id, Symbol cardColor, Side front, Side back, CardType cardType) {
		this.id=id;
		this.cardColor = cardColor;
		this.cardType = cardType;
		side = new Side [2];
		side[0]=front;
		side[1]=back;
	}
	
	public MapCard getMapCard(int zIndex) {
		return new MapCard(this,zIndex);
	}

	public Symbol getCardColor() {
		return cardColor;
	}

	public void setCardColor(Symbol cardColor) {
		this.cardColor = cardColor;
	}

	public Side[] getSide() {
		return side;
	}

	public void setSide(Side[] side) {
		this.side = side;
	}

	public int getId() {
		return id;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public Side getVisibleSide() {
		return side[visibleSide];
	}

	public void swapSide() {
		
		visibleSide = (visibleSide+1)%2; 
	}
	
	
}
