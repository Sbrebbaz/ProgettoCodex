package base;

public class Card {
	private int id;
	private static int COUNTER=0;
	private Symbol cardColor;
	private Side side [];//0:front, 1:back
	private CardType cardType;
	private int visibleSide;
	
	
	public Card(Symbol cardColor, Side front, Side back, CardType cardType) {
		this.id=COUNTER;
		COUNTER++;
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

	public Side getSide(int index) {
		return side[index];
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
		
		if(this.getCardType() != CardType.OBJECTIVE && this.getSide((visibleSide+1)%2) != null) {
			visibleSide = (visibleSide+1)%2; 
		}
	}
	
	public boolean placeableCard() {
		if(this.getCardType() == CardType.OBJECTIVE) {
			return false;
		}else {
			return true;
		}
	}
	
	public String toString() {
		return "["+" id:"+id+" colore:"+cardColor+" tipo:"+cardType+" front:"+side[0].toString()+" back:"+side[1].toString()+"]\n";
	}
	
}
