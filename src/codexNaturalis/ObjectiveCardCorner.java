package Base;

import java.util.List;

public class ObjectiveCardCorner extends ObjectiveCard {

	public ObjectiveCardCorner(List<Symbol> center, Symbol[] corners){
		super(center,corners);
	}
	
	public ObjectiveCardCorner(List<Symbol> center,Symbol cornerTopLeft,Symbol cornerTopRight,
			Symbol cornerBottomLeft,Symbol cornerBottomRight) {
		
		super(center,cornerTopLeft,cornerTopRight,
				cornerBottomLeft,cornerBottomRight);
	}
	
	@Override
	public int getPoints(Map map) {
		return map.getCardConditionPoints(this);
	}

}
