package Base;

import java.util.List;

public class GoldCard extends PointCard{

	private List<Symbol> requirements;
	
	public GoldCard(List<Symbol> center, Symbol[] corners, List<Symbol> requirements){
		super(center,corners);
		this.requirements=requirements;	}
	
	public GoldCard(List<Symbol> center,Symbol cornerTopLeft,Symbol cornerTopRight,
			Symbol cornerBottomLeft,Symbol cornerBottomRight) {
		
		super(center,cornerTopLeft,cornerTopRight,
				cornerBottomLeft,cornerBottomRight);
	}
	
	@Override
	public boolean verifyCondition(Map map) {
		return map.verifyCardCondition(this);
	}
	@Override
	public int getPoints(Map map) {
		return map.getCardConditionPoints(this);
	}

}
