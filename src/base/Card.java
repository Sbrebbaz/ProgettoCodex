package base;

public class Card {
	private int id;
	private static int COUNTER=0;
	private Symbol cardColor;
	private Side side [];//0:front, 1:back
	private CardType cardType;
	private int visibleSide;
	/**
	 * construction for the Card
	 * @param cardColor indicates the color group of this card
	 * @param front indicates the type of front side
	 * @param back indicates the type of back side
	 * @param cardType indicates the type group of this card
	 */
	
	public Card(Symbol cardColor, Side front, Side back, CardType cardType) {
		this.id=COUNTER;
		COUNTER++;
		this.cardColor = cardColor;
		this.cardType = cardType;
		side = new Side [2];
		side[0]=front;
		side[1]=back;
	}
	/**
	 * create a MapCard with this card and the indicated zIndex
	 * @param zIndex
	 * @return return the MapCard
	 */
	public MapCard getMapCard(int zIndex) {
		return new MapCard(this,zIndex);
	}
	/**
	 * get the cardColor of this card
	 * @return
	 */
	public Symbol getCardColor() {
		return cardColor;
	}
	/**
	 * set the cardColor of this card
	 * @param cardColor
	 */
	public void setCardColor(Symbol cardColor) {
		this.cardColor = cardColor;
	}
	/**
	 * get the side (0 front, 1 back)
	 * @param index
	 * @return return the side, if the index is out of range return null
	 */
	public Side getSide(int index) {
		if(index <2 && index>=0)
			return side[index];
		return null;
	}

	/**
	 * set both side
	 * @param side
	 */
	public void setSide(Side[] side) {
		this.side = side;
	}
	/**
	 * get id of this card
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * get cardType of this card
	 * @return
	 */
	public CardType getCardType() {
		return cardType;
	}
	/**
	 * set cardType of this card
	 * @param cardType
	 */
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	/**
	 * get only visible side of this card
	 * @return
	 */
	public Side getVisibleSide() {
		return side[visibleSide];
	}
	/**
	 * swap visible side with the other
	 */
	public void swapSide() {
		
		if(this.getCardType() != CardType.OBJECTIVE && this.getSide((visibleSide+1)%2) != null) {
			visibleSide = (visibleSide+1)%2; 
		}
	}
	/**
	 * determinate if this card can be place
	 * @return returns true if this card can be placed, else returns false
	 */
	public boolean placeableCard() {
		if(this.getCardType() == CardType.OBJECTIVE) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * @retrun return a String with all informations of this card
	 */
	public String toString() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("\t[ \n\tid: '");
		sb.append(id);

		sb.append("' color: '");
		sb.append(cardColor);

		sb.append("' type: '");
		sb.append(cardType);

		sb.append("' \n\tfront: ");
		sb.append(side[0].toString());

		sb.append(" \n\tback: ");
		sb.append(side[1].toString());
		
		sb.append("\n\t]");
		
		return sb.toString();
	}
	/**
	 * 
	 * @return return a String with the general informations of this card and only the informations of the visible side
	 */
	public String toStringVisibleSide() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("[ id: '");
		sb.append(id);

		sb.append("' color: '");
		sb.append(cardColor);

		sb.append("' type: '");
		sb.append(cardType);
		
		sb.append("' side: '");
		sb.append(side[visibleSide].toString());

		sb.append("']");
		
		return sb.toString();
	}
	
}
